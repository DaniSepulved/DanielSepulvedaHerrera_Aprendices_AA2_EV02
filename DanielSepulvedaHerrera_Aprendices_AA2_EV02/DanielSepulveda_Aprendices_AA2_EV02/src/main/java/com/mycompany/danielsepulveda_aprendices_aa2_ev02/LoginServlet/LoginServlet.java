package com.mycompany.danielsepulveda_aprendices_aa2_ev02.LoginServlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String DUsuario = "root";
        String DPassword = "4334";
        String url = "jdbc:mysql://localhost:3306/Prueba";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        try (Connection conexion = DriverManager.getConnection(url, DUsuario, DPassword);
             Statement st = conexion.createStatement()) {

            ResultSet rs = st.executeQuery(
                "SELECT * FROM Usuarios WHERE nombre='" + usuario + "' AND password='" + password + "'");

            if (rs.next()) {
                request.getSession().setAttribute("usuario", usuario);
                response.sendRedirect("panel.jsp");
            } else {
                response.sendRedirect("index.html");
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}