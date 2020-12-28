<%-- 
    Document   : search
    Created on : Sep 25, 2020, 5:11:00 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>

    <body>
        <c:if test="${empty sessionScope.USER}">
            <c:redirect url="login.html"/>
        </c:if>


        <h1>Welcome: ${sessionScope.USER.name}</h1></br>
        <a href="MainController?btnAction=Logout">Logout</a></br>
        <a href="MainController?btnAction=HomePageLink">Home Page</a><br>
        <a href="MainController?btnAction=Create">Create Post</a>


        <c:if test="${requestScope.LIST_ARTICLE != null}">
            <c:if test="${not empty requestScope.LIST_ARTICLE}">
                <c:forEach var="dto" items="${requestScope.LIST_ARTICLE}">
                    <h2>${dto.title}</h2>
                    <h4>${dto.date}</h4>
                    <p>${dto.description}</p>
                    <c:if test="${not empty dto.image}">
                        <img src="image/${dto.image}">
                    </c:if>
                    <c:url var="detail" value="MainController">
                        <c:param name="btnAction" value="Detail"></c:param>
                        <c:param name="txtPostId" value="${dto.postID}"></c:param>
                    </c:url>
                    <a href="${detail}">Details</a>
                </c:forEach>
            </c:if>
            <c:if test="${empty requestScope.LIST_ARTICLE}">
                <h2>There is no post!</h2>
            </c:if>
        </c:if>

        </br></br>
        Page <c:forEach begin="1" end="${sessionScope.endPage1}" var="i">
            <a href="SearchController?index1=${i}&txtSearch=${save}">${i}</a>
        </c:forEach>

    </body>
</html>
