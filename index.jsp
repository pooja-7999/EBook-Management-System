<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<title>Ebook: Index</title>
<%@ include file="all_component/allCss.jsp"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<style type="text/css">
.back-img {
	background: url("img/b.jpg");
	height: 50vh;
	width: 100%;
	background-size: cover;
	background-repeat: no-repeat;
}

.crd-ho:hover {
	background: #f2f7f7;
}
</style>
</head>

<%
Connection con = null;
try {
	con = DBConnect.getConn();
	// Use the connection
	//out.println("Database Connection Tested");
} catch (Exception e) {
	e.printStackTrace();
	//out.println("Error: Unable to connect to the database.");
} finally {
	// Optional: close the connection if not needed anymore
	try {
		if (con != null && !con.isClosed()) {
	con.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
%>

<body style="background-color: #f7f7f7;">

	<%
	User u = (User) session.getAttribute("userobj");
	%>

	<%@ include file="all_component/navbar.jsp"%>
	<div class="container-fluid back-img">
		<h2 class="text-center text-danger">EBook Management System</h2>
	</div>


	<!-- ----------------------Start Recent Book-------------------- -->
	<div class="container">
		<h3 class="text-center">Recent Book</h3>
		<div class="row">
			<%
			BookDAOImpl dao2 = new BookDAOImpl(DBConnect.getConn());
			List<BookDtls> list1 = dao2.getRecentBooks();
			int cnt = 0;
			for (BookDtls b : list1) {
				if (cnt >= 4) {
					break; // Stop displaying after 4 books
				}
			%>

			<!-- Repeat for each book item -->
			<div class="col-md-3">
				<div class="card crd-ho">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhotoName()%>"
							style="width: 150px; height: 200px" class="img-thumbnail">
						<p><%=b.getBookName()%></p>
						<p><%=b.getAuthor()%></p>
						<p>
							Categories:
							<%=b.getBookCategory()%></p>
						<div class="row">
							<%
							if (b.getBookCategory().equals("New")) {
							%>
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
							
							
							<%
							}
							%>
							<a href="view_book.jsp?bid=<%=b.getBookId()%>"
								class="btn btn-success btn-sm ml-1">View Details</a> <a href="#"
								class="btn btn-danger btn-sm ml-1 price"><i
								class="fa-solid fa-indian-rupee-sign"></i> <%=b.getPrice()%></a>
						</div>
					</div>
				</div>
			</div>
			<%
			cnt++;
			}
			%>
		</div>
		<div class="text-center mt-1">
			<a href="all_recent_book.jsp"
				class="btn btn-danger btn-sm text-white">View All</a>
		</div>
	</div>


	<!-- ----------------------End Recent Book-------------------- -->

	<hr>

	<!-- ----------------------Start New Book-------------------- -->
	<div class="container">
		<h3 class="text-center">New Book</h3>
		<div class="row">
			<!-- Repeat for each book item -->
			<%
			BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
			List<BookDtls> list = dao.getNewBook();
			int count = 0;
			for (BookDtls b : list) {
				if (count >= 4) {
					break; // Stop displaying after 4 books
				}
			%>
			<div class="col-md-3">
				<div class="card crd-ho">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhotoName()%>"
							style="width: 150px; height: 200px" class="img-thumbnail">
						<p><%=b.getBookName()%></p>
						<p><%=b.getAuthor()%></p>
						<p>
							Categories:
							<%=b.getBookCategory()%></p>
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
							<a href="view_book.jsp?bid=<%=b.getBookId()%>"
								class="btn btn-success btn-sm ml-1">View Details</a> <a href="#"
								class="btn btn-danger btn-sm ml-1 price"><i
								class="fa-solid fa-indian-rupee-sign"></i> <%=b.getPrice()%></a>
						</div>
					</div>
				</div>
			</div>
			<%
			count++;
			}
			%>
		</div>
		<div class="text-center mt-1">
			<a href="all_new_book.jsp" class="btn btn-danger btn-sm text-white">View
				All</a>
		</div>
	</div>

	<!-- ----------------------End New Book-------------------- -->

	<hr>

	<!-- ----------------------Start Old Book-------------------- -->
	<div class="container">
		<h3 class="text-center">Old Book</h3>
		<div class="row">
			<%
			BookDAOImpl dao3 = new BookDAOImpl(DBConnect.getConn());
			List<BookDtls> list2 = dao3.getOldBooks();
			int cnt1 = 0;
			for (BookDtls b : list2) {
				if (cnt1 >= 4) {
					break; // Stop displaying after 4 books
				}
				cnt1++;
			%>

			<!-- Repeat for each book item -->
			<div class="col-md-3">
				<div class="card crd-ho">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhotoName()%>"
							style="width: 150px; height: 200px" class="img-thumbnail">
						<p><%=b.getBookName()%></p>
						<p><%=b.getAuthor()%></p>
						<p>
							Categories:
							<%=b.getBookCategory()%></p>
						<div class="row">
							<a href="view_book.jsp?bid=<%=b.getBookId()%>"
								class="btn btn-success btn-sm ml-5">View Details</a> <a href="#"
								class="btn btn-danger btn-sm ml-1 price"><i
								class="fa-solid fa-indian-rupee-sign"></i> <%=b.getPrice()%></a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
		<div class="text-center mt-1">
			<a href="all_old_book.jsp" class="btn btn-danger btn-sm text-white">View
				All</a>
		</div>
	</div>
	<!-- ----------------------End Old Book-------------------- -->

	<%@ include file="all_component/footer.jsp"%>

	<script>
function formatPrice(price) {
    return Math.floor(parseFloat(price));
}

document.addEventListener('DOMContentLoaded', function() {
    const priceElements = document.querySelectorAll('.price');
    priceElements.forEach(el => {
        el.textContent = formatPrice(el.textContent);
    });
});
</script>
</body>
</html>
