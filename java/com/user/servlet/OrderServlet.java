package com.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.DAO.BookOrderImpl;
import com.DAO.CartDAOImpl;
import com.DB.DBConnect;
import com.entity.Book_Order;
import com.entity.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
        	HttpSession session  = req.getSession();
            int id = Integer.parseInt(req.getParameter("id"));

            String name = req.getParameter("username");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            String landmark = req.getParameter("landmark");
            String city = req.getParameter("city");
            String state = req.getParameter("state");
            String pincode = req.getParameter("pincode");
            String paymentType = req.getParameter("payment");

            String fullAdd = address + "," + landmark + "," + city + "," + state + "," + pincode;

            CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());
            
            List<Cart> blist = dao.getBookByUser(id);
            
            if(blist.isEmpty()) {
            	
            	session.setAttribute("FailedMsg", "Add Item");
            	resp.sendRedirect("checkout.jsp");
            	
            }else {
            	
            	BookOrderImpl dao2 = new BookOrderImpl(DBConnect.getConn());

                Book_Order o = null;
                ArrayList<Book_Order> orderList = new ArrayList<Book_Order>();
                
                Random r = new Random();
                
                for (Cart c : blist) {
                    o = new Book_Order();                
                    o.setOrderId("Book-ORD-00" + r.nextInt());
                    o.setUserName(name);
                    o.setEmail(email);
                    o.setPhone(phone);
                    o.setFulladd(fullAdd);
                    o.setBookName(c.getBookName());
                    o.setAuthor(c.getAuthor());
                    o.setPrice(c.getPrice() + "");
                    o.setPaymentType(paymentType);
                    orderList.add(o);
                }

                if ("noselect".equals(paymentType)) {
                	session.setAttribute("FailedMsg", "Please Choose Payment method");
                    resp.sendRedirect("checkout.jsp");
                } else {
                    boolean f = dao2.saveOrder(orderList);
                    if (f) {
                    	 resp.sendRedirect("order_success.jsp");
                       
                    } else {
                    	session.setAttribute("FailedMsg", "Your Order Failed");
                        resp.sendRedirect("checkout.jsp");
                    }
                }

            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
