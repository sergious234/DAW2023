<%@page import = "models.Usuario" %>
<%@page language="java" import="java.util.List,java.lang.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table-fixed text-white">
    <thead>
        <tr>
            <th class="border w-52">NOMBRE</th>
        </tr>
    </thead>
    <tbody class="text-white" id="table_body"> 
        <%
            List<Usuario> users = (List<Usuario>) request.getAttribute("users_data");
            if (users == null) {
                return;
            }
            for (Usuario u : users) {
                out.println("<tr>");
                out.println("<td class=\"border\" >"+ u.getUserName() + "</td>");
                out.println("</tr>");
            }
        %>
    </tbody>
</table>
