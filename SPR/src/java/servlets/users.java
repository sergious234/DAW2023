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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import models.Usuario;

/**
 *
 * @author usuario
 */
@WebServlet(name = "users", urlPatterns = {"/users/*"})
public class users extends HttpServlet implements servlet_utils {

    private static final Logger LOGGER = Logger.getLogger(users.class.getName());
    private static final String USER_SESSION_ATTR = "user";

    @PersistenceContext(unitName = "ServletJPAPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private boolean add_user(HttpServletRequest request) {
        var user_name_param = this.get_parameter(request, "signup_username");
        var email_param = this.get_parameter(request, "signup_email");
        var password_param = this.get_parameter(request, "signup_password");

        if (user_name_param.isEmpty() || email_param.isEmpty() || password_param.isEmpty()) {
            LOGGER.log(Level.SEVERE, "[ERROR] Faltan campos");
            return false;
        }

        Pattern email_patt = Pattern.compile("^(.+)@(.+)$");
        if (!email_patt.matcher(email_param.get()).matches()) {
            LOGGER.log(Level.SEVERE, "[ERROR] {0}", ("Correo no valido: " + email_param.get()));
            return false;
        }

        Usuario u = new Usuario();
        u.userName = user_name_param.get();
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
        String vista;
        switch (action) {
            case "/add" -> {
                if (this.add_user(request)) {
                    LOGGER.log(Level.INFO, "Usuario creado correctamente");
                } else {
                    LOGGER.log(Level.SEVERE, "Fallo al crear el usuario");
                }
                vista = "/WEB-INF/jsps/formulario.jsp";
            }

            case "/login" -> {
                if (this.get_session_attr(request, USER_SESSION_ATTR).isPresent()) {
                    response.sendRedirect("/SPR/home");
                    return;
                }

                if (this.log_in(request)) {
                    LOGGER.log(Level.INFO, "[INFO] {0}", ("Succesfull login"));
                    request.getSession().setAttribute(USER_SESSION_ATTR, request.getParameter("username"));

                    var username = this.get_parameter(request, "username");
                    if (username.isPresent()) {
                        var user = this.findByUserName(username.get().trim());
                        System.out.println(user == null);
                        if (user != null) {
                            request.getSession().setAttribute(USER_OBJ_ATTR, user);
                        }
                    }
                } else {
                    LOGGER.log(Level.INFO, "[INFO] {0}", ("Error login"));
                    response.sendError(303);
                    return;
                }
                vista = "/WEB-INF/jsps/formulario.jsp";
            }
            case "/close_session" -> {
                request.getSession().invalidate();
                request.getAttributeNames()
                    .asIterator()
                    .forEachRemaining(e -> request.removeAttribute(e));
                LOGGER.log(Level.INFO, "[INFO] Closing session");
                vista = "/WEB-INF/jsps/home/home.jsp";
            }

            case "/config" -> {
                var user_attr = this.get_session_attr(request, USER_SESSION_ATTR);
                if (user_attr.isPresent()) {
                    var user_obj = this.findByUserName(user_attr.get());
                    request.setAttribute("user_obj", user_obj);
                    vista = "/WEB-INF/jsps/user_conf/user_conf.jsp";
                } else {
                    LOGGER.log(Level.INFO, "[INFO] User not identified, redirecting to home");
                    response.sendRedirect("/SPR/home");
                    return;
                }
            }

            case "/formulario" -> {
                vista = "/WEB-INF/jsps/formulario.jsp";
            }

            case "/list" -> {
                var q = em.createQuery("SELECT t FROM Usuario t", Usuario.class);
                Integer start = (0);
                System.out.println(start);
                q.setFirstResult(start);
                q.setMaxResults(10);
                List<Usuario> tanques = (List<Usuario>) q.getResultList();
                request.setAttribute("users_data", tanques);

                vista = "/WEB-INF/jsps/users/users_list.jsp";
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

    private boolean log_in(HttpServletRequest request) {
        var username = request.getParameter("username");
        var password = request.getParameter("password");

        if (username == null || password == null) {
            LOGGER.log(Level.SEVERE, "[ERROR] {0}", "Faltan campos");
            return false;
        }

        Usuario u = findByUserName(username);

        // No existe el usuario
        if (u == null) {
            return false;
        }

        System.out.println(u.get_perm_lvl());
        return u.getPassword().equals(Usuario.encode_pass(password));
    }

    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }
}
