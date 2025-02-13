<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
	<%@ include file="all_component/navbar.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
					
					
					<h4 class="text-center text-primary">Edit Profile</h4>
					
					<c:if test="${not empty failedMsg }">
                            <h5 class="text-center text-danger">${failedMsg}</h5>
                            <c:remove var="failedMsg" scope="session"/>
                        </c:if>
                        
                          <c:if test="${not empty succMsg }">
                            <h5 class="text-center text-success">${succMsg}</h5>
                            <c:remove var="succMsg" scope="session"/>
                        </c:if>
					
					<form action="update_profile" method="post"> 
					<input type="hidden" value="${userobj.id }" name="id">
                            <div class="form-group">
                                <label for="name">Enter Full Name</label>
                                <input type="text" class="form-control" id="name" required="required" name="fname" value="${userobj.name }">
                            </div>
                            <div class="form-group">
                                <label for="email">Email address</label>
                                <input type="email" class="form-control" id="email" required="required" name="email" value="${userobj.email }">
                            </div>
                            <div class="form-group">
                                <label for="phone">Phone No</label>
                                <input type="tel" class="form-control" id="phone" required="required" name="phno" value="${userobj.phno }">
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" required="required" name="password">
                            </div>
                           <div class="text-center p-2">
                            <button type="submit" class="btn btn-primary btn-block btn-sm">Update</button>
                        </div>
                        </form>
					
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>