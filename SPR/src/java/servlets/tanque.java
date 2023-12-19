/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Tanque;
import models.Usuario;
import org.json.JSONObject;
import warg_api.Tankopedia;

/**
 *
 * @author sergio
 */
@WebServlet(name = "tanque", urlPatterns = { "/tanque/*" })
public class tanque extends HttpServlet implements servlet_utils {

    private static final Logger LOGGER = Logger.getLogger(tanque.class.getName());

    @PersistenceContext(unitName = "ServletJPAPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private Optional<String> get_parameter(HttpServletRequest request, String parameter) {
        var param = request.getParameter(parameter);
        if (param != null) {
            return Optional.of(param);
        } else {
            return Optional.empty();
        }
    }

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

    private boolean add_tank(HttpServletRequest request) {
        var name_param = this.get_parameter(request, "tank_name");
        var dmg_param = this.get_parameter(request, "tank_dmg");
        var hp_param = this.get_parameter(request, "tank_hp");

        if (name_param.isEmpty() || dmg_param.isEmpty() || hp_param.isEmpty()) {
            LOGGER.severe("[ERROR] " + "Faltan campos por rellenar");
            LOGGER.info(name_param.toString());
            LOGGER.info(dmg_param.toString());
            LOGGER.info(hp_param.toString());
            return false;
        }

        var name = name_param.get();

        int dmg;

        try {
            dmg = Integer.parseInt(dmg_param.get().trim());
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "[ERROR] No se pudo obtener el dmg: {0}", e.toString());
            LOGGER.log(Level.INFO, "[INFO] dmg_paran: {0}", dmg_param.get());
            return false;
        }

        int hp;
        try {
            hp = Integer.parseInt(hp_param.get().trim());
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "[ERROR] {0}", e.toString());
            return false;
        }

        if (hp < 1 || dmg < 1) {
            return false;
        }

        Tanque t = new Tanque();
        t.setNombre(name);
        t.setDmg(dmg);
        t.setHp(hp);

        return this.persist(t);
    }

    private boolean delete_tank(Optional<String> id) {

        if (id.isEmpty()) {
            return false;
        }

        long tank_id;

        try {
            tank_id = Long.parseLong(id.get());
        } catch (NumberFormatException _e) {
            return false;
        }

        var res = find_by_pk(Tanque.class, tank_id);

        if (res.isEmpty()) {
            return false;
        }

        Tanque tanque = res.get();
        // em.getTransaction().commit();

        return this.remove(tanque);
    }

    private boolean update_tank(HttpServletRequest request) {
        var name_param = this.get_parameter(request, "tank_name");

        var dmg_param = this.get_parameter(request, "tank_dmg").map(e -> {
            try {
                var x = Integer.parseInt(e.trim());
                if (x > 0) {
                    return x;
                } else {
                    return null;
                }
            } catch (NumberFormatException err) {
                LOGGER.log(Level.SEVERE, "[ERROR] No se pudo obtener el dmg: {0}", err.toString());
                LOGGER.log(Level.INFO, "[INFO] dmg_paran: {0}", e);
                return null;
            }
        });

        var hp_param = this.get_parameter(request, "tank_hp").map(v -> {
            try {
                Integer hp = Integer.valueOf(v.trim());
                if (hp < 0) {
                    return null;
                } else {
                    return hp;
                }
            } catch (NumberFormatException e) {
                LOGGER.log(Level.SEVERE, "[ERROR] {0}", e.toString());
                return null;
            }
        });

        var id_param = this.get_parameter(request, "tank_id").map(v -> {
            try {
                return Long.parseLong(v.trim());
            } catch (NumberFormatException _e) {
                return null;
            }
        });

        if (id_param.isEmpty()
                || name_param.isEmpty() || dmg_param.isEmpty() || hp_param.isEmpty()) {
            LOGGER.severe("[ERROR] " + "Faltan campos por rellenar");
            LOGGER.info(name_param.toString());
            LOGGER.info(dmg_param.toString());
            LOGGER.info(hp_param.toString());
            return false;
        }

        var t = this.find_by_pk(Tanque.class, id_param.get());

        if (t.isEmpty()) {
            return false;
        }

        t.ifPresent(e -> {
            e.setNombre(name_param.get());
            e.setHp(hp_param.get());
            e.setDmg(dmg_param.get());
        });

        this.update(t.get());
        System.out.println(
                "Good update");

        return true;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getPathInfo();
        String vista = "";

        switch (action) {
            case "/add_defaults" -> {
                if (!current_user_is_admin(request)) {
                    response.sendRedirect("/SPR/home");
                    return;
                }
                LOGGER.log(Level.INFO, "Adding default tanks by WRG");
                Tankopedia tp = new Tankopedia();
                List<Tanque> tanques = tp.get_tanks();
                for (var t : tanques) {
                    this.persist(t);
                }
                request.setAttribute("tanques_data", tanques.stream().limit(10).toList());
                vista = "/WEB-INF/jsps/tanks/tanks_crud.jsp";
            }

            case "/update" -> {
                this.update_tank(request);
                vista = "/WEB-INF/jsps/tanks/tanks_crud.jsp";
            }

            case "/delete" -> {
                String tank_id = request.getParameter("tank_id");
                delete_tank(Optional.ofNullable(tank_id));
                vista = "/WEB-INF/jsps/tanks/tanks_crud.jsp";
            }

            case "/add" -> {
                System.out.println("Add request");
                add_tank(request);
                vista = "/WEB-INF/jsps/tanks/tanks_crud.jsp";
            }

            case "/get" -> {
                System.out.println("Get request");
                var tanque = get_tank(request);
                if (tanque.isEmpty()) {
                    return;
                }
                response.setHeader("tank_id", tanque.get().getId().toString());
                response.setHeader("tank_name", tanque.get().getNombre());
                response.setIntHeader("tank_hp", tanque.get().getHp());
                response.setIntHeader("tank_dmg", tanque.get().getDmg());
                vista = "/WEB-INF/jsps/tanks/tank_table.jsp";
            }

            case "/table" -> {
                String str_body = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                JSONObject body = new JSONObject(str_body);
                int page = body.getInt("page");

                var q = em.createQuery("SELECT t FROM Tanque t", Tanque.class);
                q.setFirstResult(10 * page);
                q.setMaxResults(10);
                List<Tanque> tanques = (List<Tanque>) q.getResultList();
                request.setAttribute("tanques_data", tanques);
                request.setAttribute("page", page);
                vista = "/WEB-INF/jsps/tanks/tank_table.jsp";

            }

            case "/crud" -> {
                if (!current_user_is_admin(request)) {
                    vista = "/WEB-INF/jsps/tanks/tanks_view.jsp";
                } else {
                    vista = "/WEB-INF/jsps/tanks/tanks_crud.jsp";
                }

                var q = em.createQuery("SELECT t FROM Tanque t", Tanque.class);
                Integer start = (0);
                System.out.println(start);
                q.setFirstResult(start);
                q.setMaxResults(10);
                List<Tanque> tanques = (List<Tanque>) q.getResultList();
                request.setAttribute("tanques_data", tanques);

                // this.tabla_tanques(writter);
            }

            default ->
                vista = "/WEB-INF/jsps/tanks/tanks_crud.jsp";
        }

        var rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
    }

    /**
     *
     * @param <T>
     * @param entityClass Tipo a devolver
     * @param pk          Clave primaria de la busqueda
     * @return En caso de que exista Optional con entidad, en caso contrario
     *         Optinal Empty
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

    private boolean update(Object object) {
        try {
            utx.begin();
            em.merge(object);
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
