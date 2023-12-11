<%@page import = "models.Tanque" %>
<%@page language="java" import="java.util.List,java.lang.*" %> 
<table class="table-fixed">
    <thead>
        <tr>
            <th class="min-w-[1.5rem]">    </th>
            <th class="min-w-[2.0rem]">    </th>
            <th class="border w-14">ID</th>
            <th class="border w-52">NOMBRE</th>
            <th class="border w-24">DAÑO</th>
            <th class="border w-24">VIDA</th>
        </tr>
    </thead>
    <tbody id="table_body"> 
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
    </tbody>
</table>


