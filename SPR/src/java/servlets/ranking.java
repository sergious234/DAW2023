/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

/**
 *
 * @author sergio
 */
@WebServlet(name = "ranking", urlPatterns = {"/ranking/*"})
public class ranking extends HttpServlet implements servlet_utils {

    private static final Logger LOGGER = Logger.getLogger(replays.class.getName());

    @PersistenceContext(unitName = "ServletJPAPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private List<Usuario> getUser(HttpServletRequest request) throws IOException {
        //String str_body = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        //JSONObject body = new JSONObject(str_body);
        //int page = body.getInt("page");

        var q = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        q.setFirstResult(10 * 0);
        q.setMaxResults(10);
        List<Usuario> users = (List<Usuario>) q.getResultList();
        users.get(0).getReps().size();
        return users;
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
        String vista = "";

        switch (action) {
            case "/list" -> {

                vista = "/WEB-INF/jsps/ranking/ranking.jsp";
                var users = this.getUser(request);

                // Esto solo sirve para recargar las replays
                // de cada usuario.
                for (Usuario user : users) {
                    LOGGER.log(
                        Level.INFO,
                        "User={0}, nr={1}",
                        new Object[]{user.userName, user.getReps().size()}
                    );
                }

                request.setAttribute("usuarios_data", users);
            }
            default -> {
                LOGGER.log(Level.INFO, "Default ranking route");
                vista = "/WEB-INF/jsps/ranking/ranking.jsp";
            }
        }
        var rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
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

}
