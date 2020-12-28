<%-- 
    Document   : register
    Created on : Sep 25, 2020, 11:42:42 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
    <body>

        <a href="MainController">Login</a><br><br>
        
        <form action="MainController" method="POST">
            <c:set var="error" value="${requestScope.ERROR_USER}"/>
            Mail <input type="email" name="txtMail" s/></br>
            <c:if test="${not empty error.errorMail}">
                <font color="red">${error.errorMail}</font></br>
            </c:if>
            Name <input type="text" name="txtName"/></br>
            <c:if test="${not empty error.errorName}">
                <font color="red">${error.errorName}</font></br>
            </c:if>
            Password <input type="password" name="txtPassword"/></br>
            <c:if test="${not empty error.errorPassword}">
                <font color="red">${error.errorPassword}</font></br>
            </c:if>
            RePassword <input type="password" name="txtRePassword"/></br>
            <c:if test="${not empty error.errorRePassword}">
                <font color="red">${error.errorRePassword}</font></br>
            </c:if>
            <input type="submit" name="btnAction" value="SignUp"/>
            <input type="reset" value="Reset"/>          
        </form>

    </body>
</html>
