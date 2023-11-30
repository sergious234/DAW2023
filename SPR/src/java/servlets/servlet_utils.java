/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servlets;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import models.Usuario;

/**
 *
 * @author sergio
 */
public interface servlet_utils {

    public static final String USER_SESSION_ATTR = "user";

    default Optional<String> get_session_attr(HttpServletRequest request, String attr) {
        return Optional.ofNullable(request.getSession().getAttribute(attr))
            .map(e -> (String) e);
    }

    public abstract Usuario findByUserName(String user_name);

    default boolean current_user_is_admin(HttpServletRequest request) {
        var user = this.get_session_attr(request, USER_SESSION_ATTR);
        if (user.isEmpty()) {
            return false;
        } else if (this.findByUserName(user.get()).get_perm_lvl().is_admin()) {
            return true;
        } else {
            return false;
        }
    }
}
