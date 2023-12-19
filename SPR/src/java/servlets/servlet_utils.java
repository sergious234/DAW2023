/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servlets;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import models.Usuario;

/**
 *
 * @author sergio
 */
public interface servlet_utils {

    @Deprecated
    public static final String USER_SESSION_ATTR = "user";

    public static final String USER_OBJ_ATTR = "user_obj";

    abstract EntityManager getEntityManager();

    default Optional<String> get_req_attr(HttpServletRequest request, String attr) {
        return Optional.ofNullable(request.getAttribute(attr))
            .map(e -> (String) e);
    }

    default Optional<String> get_session_attr(HttpServletRequest request, String attr) {
        return Optional.ofNullable(request.getSession().getAttribute(attr))
            .map(e -> (String) e);
    }

    default Usuario findByUserName(String user_name) {
        String jpql = "SELECT u FROM Usuario u WHERE u.userName = :userName";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("userName", user_name);
        List<Usuario> usuarios = query.getResultList();
        if (!usuarios.isEmpty()) {
            return usuarios.get(0);
        } else {
            return null;
        }
    }

    default boolean current_user_is_admin(HttpServletRequest request) {
        var user = this.get_session_attr(request, USER_SESSION_ATTR);

        return user.map(e -> findByUserName(e).is_admin())
            .orElse(false);
    }
}
