/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.Tanque;
import models.Usuario;

/**
 *
 * @author usuario
 */
@WebServlet(name = "users", urlPatterns = {"/users/*"})
public class users extends HttpServlet {

     private static final Logger LOGGER = Logger.getLogger(tanque.class.getName());

    @PersistenceContext(unitName = "ServletJPAPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    
    /*
    private Optional<Tanque> get_tank(HttpServletRequest request) {
        var id_param = this.get_parameter(request, "tank_id");

        if (id_param.isEmpty()) {
            return Optional.empty();
        }

        // Java da asco
        long id;
        try {
            id = Long.parseLong(id_param.get());
        } catch (NumberFormatException e) {
            return Optional.empty();
        }

        var tanque = find_by_pk(Tanque.class, id);
        return tanque;

    }
    */
    
    private boolean add_user(HttpServletRequest request){
         var user_name_param = this.get_parameter(request, "user_name");
         var email_param = this.get_parameter(request, "email");
         var password_param = this.get_parameter(request, "password");

        if (user_name_param.isEmpty() || email_param.isEmpty() || password_param.isEmpty()) {
            LOGGER.log(Level.SEVERE, "[ERROR] Faltan campos");
            return false;
        }
        
        Pattern email_patt = Pattern.compile("^(.+)@(.+)$");
        if (!email_patt.matcher(email_param.get()).matches()) {
            LOGGER.log(Level.SEVERE, "[ERROR] {0}", "Correo no valido");
            return false;
        }
        
        /*
        Pattern pass_patt = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=])[A-Za-z\\\\d@#$%^&+=]{10,}$");
        if (!pass_patt.matcher(password_param.get()).matches()) {
            LOGGER.log(Level.SEVERE, "[ERROR] {0}", "Contraseña no valida");
            return false;
        }
        */
        
        Usuario u = new Usuario();
        u.user_name = user_name_param.get();
        u.email = email_param.get();
        u.set_pass(password_param.get());
        
        return this.persist(u);       
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getPathInfo();
        String vista = "";
        System.out.println(action);
        switch (action) {
            case "/add" -> {
                System.out.println("Add request");
                boolean success = add_user(request);
                System.out.println(success);
                vista = "/WEB-INF/jsps/formulario.jsp";
            }
            case "/formulario" -> {
                vista = "/WEB-INF/jsps/formulario.jsp";
            }
            default -> {
                vista = "/WEB-INF/jsps/formulario.jsp";
            }
        }
        var rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
    }
    
     private Optional<String> get_parameter(HttpServletRequest request, String parameter) {
        var param = request.getParameter(parameter);
        if (param != null) {
            return Optional.of(param);
        } else {
            return Optional.empty();
        }
    }
     
      /**
     *
     * @param <T>
     * @param entityClass Tipo a devolver
     * @param pk Clave primaria de la busqueda
     * @return En caso de que exista Optional con entidad, en caso contrario
     * Optinal Empty
     */
    private <T extends Object> Optional<T> find_by_pk(Class<T> entityClass, Object pk) {
        try {
            return Optional.of(em.find(entityClass, pk));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private boolean remove(Object obj) {
        try {
            utx.begin();
            Object object = em.merge(obj);
            em.remove(object);
            utx.commit();
            System.out.println("Transaccion realizada con exito");
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "[Error]: {0}", e.toString());
            return false;
        }
    }

    private boolean persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
            System.out.println("Transaccion realizada con exito");
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "[Error]: {0}", e.toString());
            return false;
        }
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
}
