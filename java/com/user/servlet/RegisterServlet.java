package com.user.servlet;

import java.io.IOException;
import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("fname");
            String email = req.getParameter("email");
            String phno = req.getParameter("phno");
            String password = req.getParameter("password");
            String check = req.getParameter("check");
            
            System.out.println("Received form data:");
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Phone No: " + phno);
            System.out.println("Password: " + password);
            System.out.println("Check: " + check);

            
            User us = new User();
            us.setName(name);
            us.setEmail(email);
            us.setPhno(phno);
            us.setPassword(password);

            HttpSession session = req.getSession();

            if (check != null) {
                UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
                
                boolean f2 = dao.checkUser(email);
                if(f2) {
                	
                	
                	 boolean f = dao.userRegister(us);

                     if (f) {
                         session.setAttribute("succMsg", "Register Success..");
                         resp.sendRedirect("register.jsp");
                     } else {
                         session.setAttribute("FailedMsg", "Something wrong on server..");
                         resp.sendRedirect("register.jsp");
                     }
                	
                }else {
                	
                	session.setAttribute("FailedMsg", "User Already Exist Try Another Email id");
                    resp.sendRedirect("register.jsp");
                }
                
                
                
                
            } else {
                session.setAttribute("FailedMsg", "Please Check Agree Terms & Condition..");
                resp.sendRedirect("register.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
