<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>EBook: Register</title>
    <%@ include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
    <%@ include file="all_component/navbar.jsp"%>
    <div class="container p-2">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <div class="card">
                    <div class="card-body">
                        <h4 class="text-center">Registration Page</h4>
                        
                        <!-- Display Success or Error Messages -->
                        <c:if test="${not empty sessionScope.succMsg}">
                            <div class="alert alert-success">
                                <c:out value="${sessionScope.succMsg}"/>
                            </div>
                            <%-- Clear the success message after displaying --%>
                            <c:remove var="succMsg"/>
                        </c:if>
                        
                        <c:if test="${not empty sessionScope.FailedMsg}">
                            <div class="alert alert-danger">
                                <c:out value="${sessionScope.FailedMsg}"/>
                            </div>
                            <%-- Clear the error message after displaying --%>
                            <c:remove var="FailedMsg"/>
                        </c:if>


                        <!-- Registration Form -->
                        
                        <form action="register" method="post">
                            <div class="form-group">
                                <label for="name">Enter Full Name</label>
                                <input type="text" class="form-control" id="name" required="required" name="fname">
                            </div>
                            <div class="form-group">
                                <label for="email">Email address</label>
                                <input type="email" class="form-control" id="email" required="required" name="email">
                            </div>
                            <div class="form-group">
                                <label for="phone">Phone No</label>
                                <input type="tel" class="form-control" id="phone" required="required" name="phno">
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" required="required" name="password">
                            </div>
                            <div class="form-group form-check">
                                <input type="checkbox" class="form-check-input" name="check" id="termsCheck">
                                <label class="form-check-label" for="termsCheck">I agree to the terms and conditions</label>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="all_component/footer.jsp"%>
</body>
</html>
