package com.user.servlet;

import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DAO.CartDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;
import com.entity.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int bid = Integer.parseInt(req.getParameter("bid"));
            int uid = Integer.parseInt(req.getParameter("uid"));

            BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
            BookDtls b = dao.getBookById(bid);
            
            if (b == null) {
                req.getSession().setAttribute("addcart", "Book not found.");
                resp.sendRedirect("all_new_book.jsp");
                return;
            }

            Cart c = new Cart();
            c.setBid(bid);
            c.setUserId(uid);
            c.setBookName(b.getBookName());
            c.setAuthor(b.getAuthor());
            c.setPrice(Double.parseDouble(b.getPrice()));
            c.setTotalPrice(Double.parseDouble(b.getPrice()));

            CartDAOImpl dao2 = new CartDAOImpl(DBConnect.getConn());
            boolean f = dao2.addCart(c);

            HttpSession session = req.getSession();

            if (f) {
            	 req.getSession().setAttribute("addcart", "Book added to cart successfully.");
               
            } else {
            	 req.getSession().setAttribute("addcart", "Failed to add book to cart.");
            }
                
            
            resp.sendRedirect("all_new_book.jsp");
    
           
    } catch (NumberFormatException e) {
        e.printStackTrace();
        req.getSession().setAttribute("addcart", "Invalid number format.");
        resp.sendRedirect("all_new_book.jsp");
    } catch (Exception e) {
        e.printStackTrace();
        req.getSession().setAttribute("addcart", "Server error. Please try again later.");
        resp.sendRedirect("all_new_book.jsp");
    }
        }
    }

