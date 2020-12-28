<%-- 
    Document   : loginByEmail
    Created on : Dec 7, 2020, 8:53:43 AM
    Author     : VYCAO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login By Email Page</title>
    </head>
    <body>

        <form action="MainController" method="POST">
            <c:set var="error" value="${requestScope.ERROR_EMAIL}"/>
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
            <input type="submit" name="btnAction" value="LoginByEmail"/>
            <input type="reset" value="Reset"/>        
        </form>

    </body>
</html>
