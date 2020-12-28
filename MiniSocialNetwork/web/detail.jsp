<%-- 
    Document   : detail
    Created on : Sep 29, 2020, 3:25:53 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER}">
            <c:redirect url="login.html"/>
        </c:if>

        <h1>Welcome: ${sessionScope.USER.name}</h1><br>
        <a href="MainController?btnAction=Logout">Logout</a><br>
        <c:if test="${sessionScope.USER.role eq 1}">
            <a href="MainController?btnAction=Create">Create Post</a><br>
        </c:if>
        <a href="MainController?btnAction=ShowNotify&userID=${sessionScope.USER.userID}">Notification List</a>

        <c:set var="dto" value="${requestScope.ARTICLE}"/>
        <h2>Title: ${dto.title}</h2>
        <h4>User: ${requestScope.USER_POST}</h4>
        <h4>Date: ${dto.date}</h4>
        <p>${dto.description}</p>
        <c:if test="${not empty dto.image}">                      
            <img src="image/${dto.image}"/>
        </c:if>
        <p>Like: ${dto.numLike}</p>
        <p>Dislike: ${dto.numDislike}</p>

        <c:if test="${ (sessionScope.USER.userID eq dto.userID) or (sessionScope.USER.role eq 2)}">
            <c:url var="delete" value="MainController">
                <c:param name="btnAction" value="Delete"></c:param>
                <c:param name="txtPostID" value="${dto.postID}"></c:param>
                <c:param name="txtUserIDPost" value="${dto.userID}"></c:param>
                <c:param name="txtUserID" value="${sessionScope.USER.userID}"></c:param>
            </c:url>
            <a href="${delete}" onclick="return confirm('Are you sure you want to delete this article?')">Delete Post</a>
        </c:if>
       
        <br><br>
        <c:set var="id" value="${dto.postID}"/>

        <a href="MainController?btnAction=Emotion&txtPostId=${id}&userID=${sessionScope.USER.userID}&emo=Like">
            <button type="button">Like</button><br>
        </a><br>

        <a href="MainController?btnAction=Emotion&txtPostId=${id}&userID=${sessionScope.USER.userID}&emo=Dislike">
            <button type="button">Dislike</button><br>
        </a><br>

        <form action="MainController" method="POST">
            <input type="hidden" name="txtPostId" value="${id}"/>
            <input type="hidden" name="userID" value="${sessionScope.USER.userID}"/>
            <textarea name="txtComment" value="" required></textarea>
            <input type="submit" name="btnAction" value="Comment"/>
        </form>

        <c:if test="${requestScope.LIST_COMMENT != null}">
            <c:if test="${not empty requestScope.LIST_COMMENT}">
                <c:forEach var="comment" items="${requestScope.LIST_COMMENT}">
                    <c:set var="userID" value="${comment.userID}"/>
                    <h4>User: ${requestScope.LIST_USER_COMMENT[userID]}</h4>
                    <p>${comment.content}</p>
                    <a href="MainController?btnAction=DeleteComment&commentID=${comment.id}&userID=${sessionScope.USER.userID}&userComment=${comment.userID}&txtPostId=${id}" 
                       onclick="return confirm('Are you sure you want to delete this comment?')">Delete</a>
                </c:forEach>
            </c:if>
        </c:if>

    </body>
</html>
