<%-- 
    Document   : verify
    Created on : Sep 29, 2020, 12:11:44 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Email Page</title>
    </head>
    <body>

        <h1>Verify Your Email</h1>
        <h2>Check your email and then enter code below</h2>
        <form action="MainController" method="POST">
            Code <input type="text" name="txtCode"/>
            <input type="submit" name="btnAction" value="Enter"/>         
        </form>      
        <h4>${requestScope.errorCode}</h4>
    </body>
</html>
