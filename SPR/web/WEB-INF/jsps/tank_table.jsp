<%-- 
    Document   : tank_table
    Created on : Nov 13, 2023, 6:46:20 PM
    Author     : sergio
--%>

<%@page import = "models.Tanque" %>
<%@page language="java" import="java.util.List,java.lang.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<Tanque> tanques = (List<Tanque>) request.getAttribute("tanques_data");
    if (tanques == null) {
        return;
    }
    int contador = 0;
    for (Tanque t : tanques) {
        String id = "id=\"tank_rmv_" + contador + "\" ";
        String id2 = "id=\"tank_id_" + contador + "\" ";
        contador++;
        out.println("<tr>");
        out.println("<td " + id + "class=\"cursor-pointer border-spacing-x-2 font-black text-red-500\">  x  </td>");
        out.println("<td> </td>");
        out.println("<td " + id2 + "class=\"border\">"+ t.getId() +"</td>");
        out.println("<td class=\"border\" >"+ t.getNombre() + "</td>");
        out.println("<td class=\"border\" >"+ t.getDmg() +"</td>");
        out.println("<td class=\"border\">"+ t.getHp() +"</td>");
        out.println("</tr>");
        }
%>
 