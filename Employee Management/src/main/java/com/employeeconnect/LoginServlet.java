package com.employeeconnect;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (name, password, email, country) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, country);
            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                out.println("<h3 style='color:green;'>Login successful!</h3>");
            } else {
                out.println("<h3 style='color:red;'>Login failed. Try again.</h3>");
            }


            /*if (rows > 0) {
                response.sendRedirect("success.jsp");
            } else {
                out.println("<h3>Login failed. Try again.</h3>");
            }*/
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
