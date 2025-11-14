package com.employeeconnect;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class ContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO contact_messages (name, email, message) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, message);
            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                out.println("<h3 style='color:green;'> Thanks for connectig us!</h3>");
            } else {
                out.println("<h3 style='color:red;'>Login failed. Try again.</h3>");
            }


            /*if (rows > 0) {
                response.sendRedirect("success.jsp");
            } else {
                out.println("<h3>Message failed. Try again.</h3>");
            }*/
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
