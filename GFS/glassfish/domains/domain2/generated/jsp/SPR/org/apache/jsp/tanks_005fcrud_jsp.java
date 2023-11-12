package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import models.Tanque;
import java.util.List;
import java.lang.*;

public final class tanks_005fcrud_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write(" \n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>CRUD</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <!--<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">-->\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"/SPR/css/main_menu.css\"/>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body class=\"bg-zinc-950\">\n");
      out.write("       \t<div class=\"flex flex-col\">\n");
      out.write("            <div class=\"flex flex-row gap-x-32\">\n");
      out.write("                <nav class=\"main_menu basis-15 flex-initial space-y-4 pl-3 min-h-screen w-12\">\n");
      out.write("                    <div class=\"mt-3 mr-2\">\n");
      out.write("                        <a class=\"block space-x-3\" href=\"https://github.com/sergious234\">\n");
      out.write("                            <img class=\"no-invert rounded-full\" src=\"/SPR/menu_icons/Sergious.jpg\" alt=\"alt\"/>\n");
      out.write("                            <span class=\"w-full justify-center text-center\">\n");
      out.write("                                Sergious\n");
      out.write("                            </span>\n");
      out.write("                        </a>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"pt-20\">\n");
      out.write("                        <a class=\"inline flex space-x-3\" href=\"ranking.html\">\n");
      out.write("                            <img src=\"/SPR/menu_icons/ranking.svg\" alt=\"alt\"/>\n");
      out.write("                            <span class=\"inline\">Ranking</span>\n");
      out.write("                        </a>\n");
      out.write("                    </div>\n");
      out.write("                    <div>\n");
      out.write("                        <a class=\"inline flex space-x-3\" href=\"jugadores.html\">\n");
      out.write("                            <img src=\"/SPR/menu_icons/player.svg\" alt=\"alt\"/>\n");
      out.write("                            <span>Jugadores</span>\n");
      out.write("                        </a>\n");
      out.write("                    </div>\n");
      out.write("                    <div>\n");
      out.write("                        <a class=\"inline flex space-x-3\" href=\"tanques.html\">\n");
      out.write("                            <img src=\"/SPR/menu_icons/tank.svg\" alt=\"alt\"/>\n");
      out.write("                            <span>Tanques</span>\n");
      out.write("                        </a>\n");
      out.write("                    </div>\n");
      out.write("                    <div>\n");
      out.write("                        <a class=\"inline flex space-x-3\" href=\"clanes.html\">\n");
      out.write("                            <img src=\"/SPR/menu_icons/clan.svg\" alt=\"alt\"/>\n");
      out.write("                            <span>Clanes</span>\n");
      out.write("                        </a>\n");
      out.write("                    </div>\n");
      out.write("                    <div>\n");
      out.write("                        <a class=\"inline flex space-x-3\" href=\"clanes.html\">\n");
      out.write("                            <img src=\"/SPR/menu_icons/formulario.svg\" alt=\"alt\"/>\n");
      out.write("                            <span>Formulario</span>\n");
      out.write("                        </a>\n");
      out.write("                    </div>\n");
      out.write("                </nav>\n");
      out.write("                <!--\n");
      out.write("                    En caso de que se empiece a ver\n");
      out.write("                    chungo cambiar el div por este otro:\n");
      out.write("                    <div class=\"grid grid-cols-9\">\n");
      out.write("                -->\n");
      out.write("                <!-- <div class=\"flex flex-rows justify-between\"> -->\n");
      out.write("                    <div class=\"mt-10 text-start col-span-2\">\n");
      out.write("                        <p class=\"text-4xl text-red-50\">Nuevo tanque</p>\n");
      out.write("                        <div class=\"pt-20\"></div>\n");
      out.write("                        <div>\n");
      out.write("                            <form id=\"add_form\" class=\"grid grid-cols-6 text-red-50\">\n");
      out.write("                                <div class=\"col-span-2 grid grid-rows-4 gap-y-5 max-w-min justify-items-start\">\n");
      out.write("                                    <label>Nombre </label>\n");
      out.write("                                    <label>Daño </label>\n");
      out.write("                                    <label>HP </label>\n");
      out.write("                                    <label>  </label>\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("                                <div class=\"grid col-span-4 grid-rows-4 gap-y-5 justify-items-start\">\n");
      out.write("                                    <input required id=\"tank_name\" class=\"text-black pl-2\" type=\"text\" placeholder=\"Nombre\"/>\n");
      out.write("                                    <input required id=\"tank_dmg\" class=\"text-black pl-2\" type=\"text\" placeholder=\"Daño\"/>\n");
      out.write("                                    <input required  id=\"tank_hp\" class=\"text-black pl-2\" type=\"text\" placeholder=\"HP\"/>\n");
      out.write("                                    <!-- <label>Aceptar </label> -->\n");
      out.write("                                    <input id=\"add_submit\" class=\"justify-self-center border rounded-full px-4 text-lime-400\" type=\"submit\" value=\"Insertar\"/>\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                            </form>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"flex col-span-4 justify-center text-start\">\n");
      out.write("                        <section class=\"mt-10 text-red-50 text-center\">\n");
      out.write("                            <p class=\"max-h-fit text-4xl text-red-50\">Listado de tanques</p>\n");
      out.write("                            <div class=\"pt-20\"></div>\n");
      out.write("                            <table class=\"table-fixed\">\n");
      out.write("                                <thead>\n");
      out.write("                                    <tr>\n");
      out.write("                                        <th class=\"min-w-[1.5rem]\">    </th>\n");
      out.write("                                        <th class=\"min-w-[2.0rem]\">    </th>\n");
      out.write("                                        <th class=\"border w-14\">ID</th>\n");
      out.write("                                        <th class=\"border w-52\">NOMBRE</th>\n");
      out.write("                                        <th class=\"border w-24\">DAÑO</th>\n");
      out.write("                                        <th class=\"border w-24\">VIDA</th>\n");
      out.write("                                    </tr>\n");
      out.write("                                </thead>\n");
      out.write("                                <tbody> \n");
      out.write("                                    ");

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
                                            out.println("<td " + id + "class=\"cursor-pointer border-spacing-x-2 font-black text-red-500\">  X  </td>");
                                            out.println("<td> </td>");
                                            out.println("<td " + id2 + "class=\"border\">"+ t.getId() +"</td>");
                                            out.println("<td class=\"border\" >"+ t.getNombre() + "</td>");
                                            out.println("<td class=\"border\" >"+ t.getDmg() +"</td>");
                                            out.println("<td class=\"border\">"+ t.getHp() +"</td>");
                                            out.println("</tr>");
                                        }
                                    
      out.write("\n");
      out.write("                                </tbody>\n");
      out.write("                            </table>\n");
      out.write("                        </section>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"pl-24 col-span-2 mt-10 text-center\">\n");
      out.write("                        <p class=\"text-4xl text-red-50\">Modificar Tanque</p>\n");
      out.write("                        <div class=\"pt-20\"></div>\n");
      out.write("                        <form id=\"update_form\" class=\"grid grid-cols-6 text-red-50\">\n");
      out.write("                            <div class=\"col-span-2 grid grid-rows-5 gap-y-5 max-w-min justify-items-start\">\n");
      out.write("                                <label>ID</label>\n");
      out.write("                                <label>Nombre</label>\n");
      out.write("                                <label>Daño</label>\n");
      out.write("                                <label>HP</label>\n");
      out.write("                                <label>  </label>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"grid col-span-4 grid-rows-5 gap-y-5 justify-items-start\">\n");
      out.write("                                <input required id=\"update_tank_id\" class=\"text-black min-w-full pl-2\" pattern=\"[0-9]+\" type=\"text\" placeholder=\"ID\">\n");
      out.write("                                <input required id=\"new_tank_name\" class=\"text-black min-w-full pl-2\"  type=\"text\" placeholder=\"Nombre\">\n");
      out.write("                                <input required id=\"new_tank_dmg\" class=\"text-black min-w-full pl-2\" type=\"text\" placeholder=\"Daño\">\n");
      out.write("                                <input required id=\"new_tank_hp\" class=\"text-black min-w-full pl-2\" type=\"text\" placeholder=\"HP\">\n");
      out.write("                                <input id=\"update_submit\" class=\"justify-self-center border rounded-full px-4 text-amber-400\" type=\"submit\" value=\"Update\"/>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            <!-- </div> -->\n");
      out.write("            <footer class=\"pie_menu z-1 static inset-x-0 bottom-0 h-20 items-center grid \">\n");
      out.write("                <section class=\"text-center\">\n");
      out.write("                    Página creada por Sergious234 (saurio).<br>\n");
      out.write("                    No copyright (considera regalarme un poco de oro).\n");
      out.write("                </section>\n");
      out.write("            </footer>\n");
      out.write("        </div>\n");
      out.write("        <script src=\"https://cdn.tailwindcss.com\"></script>\n");
      out.write("        <script src=\"/SPR/js/tanks_crud.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
