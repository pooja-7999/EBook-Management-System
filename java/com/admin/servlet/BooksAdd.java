package com.admin.servlet;

import java.io.File;
import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/add_books")
@MultipartConfig
public class BooksAdd extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String bookName = req.getParameter("bname");
			String author = req.getParameter("author");
			String price = req.getParameter("price");
			String categories = req.getParameter("categories");
			String status = req.getParameter("status");
			Part part = req.getPart("bimg");
			String fileName = part.getSubmittedFileName();

			BookDtls b = new BookDtls(bookName, author, price, categories, status, fileName, "admin");
			// System.out.println(b);

			BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());

			boolean f = dao.addBooks(b);

//			
			HttpSession session = req.getSession();

			if (f) {
				
				
				String path = getServletContext().getRealPath("") + "book";
				 File fileUploadDir = new File(path);
				 if (!fileUploadDir.exists()) {
				     fileUploadDir.mkdirs(); // Create the directory if it does not exist
				 }
				 String filePath = path + File.separator + fileName;
				 part.write(filePath); // Save the file to the path
				 

				session.setAttribute("succMsg", "Book AddSuccessfully");
				resp.sendRedirect("admin/add_books.jsp");

			} else {
				session.setAttribute("FailedMsg", "Failed");
				resp.sendRedirect("admin/add_books.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
