<%-- 
    Document   : errorDelete
    Created on : Sep 30, 2020, 3:58:16 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Delete Page</title>
    </head>
    <body>
    <c:if test="${empty sessionScope.USER}">
        <c:redirect url="login.html"/>
    </c:if>

    <h1>You can not delete this post because it is not your own post!</h1>
    <a href="HomePageController">Back to Home Page</a>
</body>
</html>
