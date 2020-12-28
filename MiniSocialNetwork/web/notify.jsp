<%-- 
    Document   : notify
    Created on : Dec 9, 2020, 12:33:45 PM
    Author     : VYCAO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notify List Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER}">
            <c:redirect url="login.html"/>
        </c:if>

        <h1>Welcome: ${sessionScope.USER.name}</h1></br>
        <a href="MainController?btnAction=Logout">Logout</a></br>
        <a href="MainController?btnAction=HomePageLink">Home Page</a>

        
        <c:if test="${not empty requestScope.LIST_NOTIFY}">
            <table border="1">
                <thead>
                    <tr>
                        <th>New Notification</th>
                        <th>Old Notification</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="notify" items="${requestScope.LIST_NOTIFY}">
                        <tr>

                            <td>
                                <c:if test="${notify.status eq 1}">
                                    ${requestScope.CMT_NAME[notify.userID]} has ${notify.type} on the post 
                                    <a href="MainController?btnAction=ShowCorrectNotify&notifyID=${notify.id}&txtPostId=${notify.postID}">${requestScope.POST[notify.postID]}</a>
                                </c:if>
                            </td>


                            <td>
                                <c:if test="${notify.status eq 5}">
                                    ${requestScope.CMT_NAME[notify.userID]} has ${notify.type} on your post ${requestScope.POST[notify.postID]}

                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>

            </table>
        </c:if>
        
        <c:if test="${ empty requestScope.LIST_NOTIFY }">
            <h4>You do not have any notification.</h4>
        </c:if>




    </body>
</html>
