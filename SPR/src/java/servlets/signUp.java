/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "signUp", urlPatterns = {"/signUp"})
public class signUp extends HttpServlet {

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
        String nickname = request.getParameter("name");
        String email = request.getParameter("correo");
        String pass = request.getParameter("clave");

        try (PrintWriter out = response.getWriter()) {
            //Class.forName("com.mysql.jdbc.Driver");
            String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
            //String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
            String DB_URL = "jdbc:derby://localhost:1527/users";
            String USER = "root";
            String PASS = "95AS3E";
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                ps = conn.prepareStatement("INSERT INTO Usuarios (NOMBRE, CORREO, PASSWORD) VALUES (?, ?, ?)");
                ps.setString(1, nickname);
                ps.setString(2, email);
                ps.setString(3, pass);
                int count = ps.executeUpdate();

            } catch (SQLException sql_ex) {
                out.println("<h1> Error en el registro de usuario </h1>");
                switch (sql_ex.getErrorCode()) {
                    case 30000: {
                        out.println("Un usuario con el mismo nombre ya existe");
                        break;
                    }
                    default:
                        out.println(sql_ex.toString() + "\n" + sql_ex.getErrorCode());
                }
            } finally {
                try {
                    conn.close();
                    ps.close();
                } catch (Exception e) {
                    System.out.println("Viva el error management");
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(signUp.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
