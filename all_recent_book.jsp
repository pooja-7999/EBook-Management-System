<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.DAO.BookDAOImpl"%>
<%@ page import="com.entity.BookDtls"%>
<%@ page import="java.util.List"%>

<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="com.DB.DBConnect"%>
<%@ page import="com.entity.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Recent Book</title>
<%@ include file="all_component/allCss.jsp"%>
<style type="text/css">
.crd-ho:hover {
	background: #fcf7f7;
}
</style>
</head>
<body>

<%
	User u = (User) session.getAttribute("userobj");
	%>

	<%@ include file="all_component/navbar.jsp"%>
	<div class="container=fluid">
		<div class="row p-3">

			<%
			BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
			List<BookDtls> list = dao.getAllRecentBooks();

			for (BookDtls b : list) {
			%>

			<!-- Repeat for each book item -->
			<div class="col-md-3">
				<div class="card crd-ho mt-2">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhotoName()%>"
							style="width: 100px; height: 150px" class="img-thumblin">
						<p><%=b.getBookName()%></p>
						<p><%=b.getAuthor()%></p>
						<p>
							<%
							if (b.getBookCategory().equals("Old")) {
							%>
							Categories:<%=b.getBookCategory()%></p>
						<div class="row">
							<a href="view_book.jsp?bid=<%=b.getBookId()%>"
								class="btn btn-success btn-sm ml-5">View Details</a> <a href="#"
								class="btn btn-danger btn-sm ml-1 price"><i
								class="fa-solid fa-indian-rupee-sign"></i> <%=b.getPrice()%></a>
						</div>
						<%
						} else {
						%>
						Categories:<%=b.getBookCategory()%></p>
						<div class="row">
							<%
							if (u == null) {
							%>
							<a href="login.jsp" class="btn btn-danger btn-sm ml-2"><i
								class="fa-solid fa-cart-plus"></i> Add Cart</a>
							<%
							} else {
							%>
							<a href="cart?bid=<%=b.getBookId()%>&uid=<%=u.getId()%>"
								class="btn btn-danger btn-sm ml-2"><i
								class="fa-solid fa-cart-plus"></i> Add Cart</a>
							<%
							}
							%>
								
								 <a
								href="view_book.jsp?bid=<%=b.getBookId()%>"
								class="btn btn-success btn-sm ml-1">View Details</a> <a href="#"
								class="btn btn-danger btn-sm ml-1 price"><i
								class="fa-solid fa-indian-rupee-sign"></i> <%=b.getPrice()%></a>
						</div>

						<%
						}
						%>

					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>

</body>
</html>