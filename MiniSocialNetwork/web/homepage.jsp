<%-- 
    Document   : homepage
    Created on : Sep 29, 2020, 1:17:18 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>

    <body>

        <h1>Welcome: ${sessionScope.USER.name}</h1><br>
        <a href="MainController?btnAction=Logout">Logout</a><br>
        <c:if test="${sessionScope.USER.role eq 1}">
            <a href="MainController?btnAction=Create">Create Post</a><br>
        </c:if>
        <a href="MainController?btnAction=ShowNotify&userID=${sessionScope.USER.userID}">Notification List</a>


        <form action="MainController">
            Search <input type="text" value="${param.txtSearch}" name="txtSearch"/>
            <input type="hidden" value="1" name="index1"/>
            <input type="submit" value="Search" name="btnAction"/>
        </form>


        <c:if test="${sessionScope.LIST_ARTICLES != null}">
            <c:if test="${not empty sessionScope.LIST_ARTICLES}">
                <c:forEach var="dto" items="${sessionScope.LIST_ARTICLES}">
                    <h2>${dto.title}</h2>
                    <h4>${dto.date}</h4>
                    <p>${dto.description}</p>
                    <c:if test="${not empty dto.image}">
                        <c:url value="image/${dto.image}" var="img"></c:url>
                        <img src="${img}"/>
                    </c:if>
                    <c:url var="detail" value="MainController">
                        <c:param name="btnAction" value="Detail"></c:param>
                        <c:param name="txtPostId" value="${dto.postID}"></c:param>
                    </c:url>
                    <a href="${detail}">Details</a>
                </c:forEach>
            </c:if>
            <c:if test="${empty sessionScope.LIST_ARTICLES}">
                <h2>There is no post!</h2>
            </c:if>
        </c:if>

        </br></br>
        Page
        <c:forEach begin="1" end="${sessionScope.endPage}" var="i">
            <a href="HomePageController?index=${i}">${i}</a>
        </c:forEach>

    </body>
</html>
