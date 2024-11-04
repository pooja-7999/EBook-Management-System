<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.DAO.BookDAOImpl"%>
<%@ page import="com.entity.BookDtls"%>
<%@ page import="com.entity.User"%>
<%@ page import="java.util.List"%>

<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="com.DB.DBConnect"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User: Old Book</title>
<%@ include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f7f7f7;">
	<%@ include file="all_component/navbar.jsp"%>

	<c:if test="${not empty succMsg}">
		<div class="alert alert-success text-center">${success }</div>
		<c:remove var="succMsg" scope="session" />
	</c:if>



	<div class="container p-5">
		<table class="table table-striped">
			<thead class="bg-primary text-white">
				<tr>
					<th scope="col">Book Name</th>
					<th scope="col">Author</th>
					<th scope="col">Price</th>
					<th scope="col">Category</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				User u = (User) session.getAttribute("userobj");
				String email = u.getEmail();

				BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
				List<BookDtls> list = dao.getBookByOld(email, "old");

				for (BookDtls book : list) {
				%>
				<tr>
					<td><%=book.getBookName()%></td>
					<td><%=book.getAuthor()%></td>
					<td><%=book.getPrice()%></td>
					<td><%=book.getBookCategory()%></td>
					<td><a href="delete_old_book?em=<%=email%>&&id=<%=book.getBookId() %>"
						class="btn btn-sm btn-danger">Delete</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>
