package org.apache.jsp.WEB_002dINF.jsps;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import models.Tanque;
import java.util.List;
import java.lang.*;

public final class tank_005ftable_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
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
      out.write(" \n");
      out.write("\n");
      out.write("\n");

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

      out.write('\n');
      out.write(' ');
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
