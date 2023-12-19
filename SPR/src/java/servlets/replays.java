/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import errors.ReplayError;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;
import jakarta.validation.ConstraintViolationException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Replay;
import models.Usuario;

/**
 *
 * @author sergio
 */
@WebServlet(name = "replays", urlPatterns = {"/replays/*"})
@MultipartConfig
public class replays extends HttpServlet implements servlet_utils {

    private static final Logger LOGGER = Logger.getLogger(replays.class.getName());

    private final String BASE_REPLAY_PATH = "";//getServletContext().getRealPath("/WEB-INF/replays");

    @PersistenceContext(unitName = "ServletJPAPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private Optional<ReplayError> addReplay(HttpServletRequest req) throws IOException, ServletException {
        var f = req.getAttributeNames();
        while (f.hasMoreElements()) {
            var name = f.nextElement();
        }

        Replay replay = new Replay();

        var x = req.getPart("fileData");
        if (x == null) {
            LOGGER.log(Level.SEVERE, "No se recibio ningun archivo");
            return Optional.of(ReplayError.DataNotFound);
        }

        var fileName = x.getSubmittedFileName();

        // Sanear un poquillo el nombre
        if (fileName.contains("\\") || fileName.contains("/")) {
            return Optional.of(ReplayError.SecurityError);
        }

        if (x.getSize() > 4194304) {
            return Optional.of(ReplayError.Other);
        }

        var data = x.getInputStream().readAllBytes();
        var filePath = Path.of(getServletContext().getRealPath("/WEB-INF/replays"), fileName);

        replay.setFilePath(filePath.toString());
        replay.setUser(
            ((Usuario) req.getSession().getAttribute(USER_OBJ_ATTR))
        );

        System.out.println(replay);

        if (this.persist(replay)) {

            // Crear el archivo
            try {
                if (!filePath.toFile().createNewFile()) {
                    LOGGER.log(Level.SEVERE, "No se pudo crear el archivo porque ya existe: {0}", filePath);
                    return Optional.of(ReplayError.FileAlreadyExists);
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "No se pudo crear el archivo: {0}", filePath);
                LOGGER.log(Level.SEVERE, "Se produjo el siguiente error: {0}", e.toString());
                return Optional.of(ReplayError.IOException);
            }

            // Escribir en el archivo
            try (FileOutputStream fos = new FileOutputStream(filePath.toString())) {

                fos.write(data);
                LOGGER.log(Level.INFO, "Replay guardada con exito! {0}", filePath);
                LOGGER.log(Level.INFO, "Escritos {0} bytes", data.length);

                return Optional.empty();

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "No se pudo escribir la replay: {0}", filePath);
                LOGGER.log(Level.SEVERE, "Se produjo el siguiente error: {0}", e.toString());
                return Optional.of(ReplayError.IOException);
            }

        } else {
            return Optional.of(ReplayError.Other);
        }

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getPathInfo();
        System.out.println(action);
        String vista = "/WEB-INF/jsps/home/home.jsp";

        switch (action) {
            case "/add" -> {
                var res = this.addReplay(request);
                if (res.isEmpty()) {
                    response.setStatus(200);
                } else {
                    var rc = res.get().getResponseCode();
                    LOGGER.log(Level.INFO, "Error while uploading the replay error code: {0}", rc);
                    response.sendError(rc);
                }
            }
            default -> {
            }
        }
        response.sendRedirect("/SPR/home");
        return;
        //var rd = request.getRequestDispatcher(vista);
        //rd.forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }

    private boolean persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
            System.out.println("Transaccion realizada con exito");
            return true;
        } catch (ConstraintViolationException e) {
            LOGGER.log(Level.SEVERE, "[CVE Error]: {0}", e.toString());
            LOGGER.log(Level.SEVERE, "[CVE Error]: {0}", e.getConstraintViolations());
            return false;
        } catch (SystemException e) {
            LOGGER.log(Level.SEVERE, "[System Error]: {0}", e.toString());
            LOGGER.log(Level.SEVERE, "[System Error]: {0}", e.getStackTrace());
            return false;
        } catch (EntityExistsException e) {
            LOGGER.log(Level.SEVERE, "[Entity Error]: {0}", e.toString());
            LOGGER.log(Level.SEVERE, "[Entity Error]: {0}", e.getStackTrace());
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "[Gen Error]: {0}", e.toString());
            LOGGER.log(Level.SEVERE, "[Gen Error]: {0}", e.getClass());
            return false;
        }
    }

}
