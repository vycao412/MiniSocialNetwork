<%-- 
    Document   : createPost.jsp
    Created on : Sep 26, 2020, 3:41:48 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Post Page</title>
    </head>
    <body>

    <c:if test="${empty sessionScope.USER}">
        <c:redirect url="login.html"/>
    </c:if>

    <a href="MainController?btnAction=Logout">Logout</a></br>

    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <script type="text/javascript" src="js/additional-methods.js"></script>
    <script type="text/javascript">
        $(function () {

            // validate contact form on keyup and submit
            // 1. sua ten form
            $("#createPost").validate({
                // set the rules for the field title
                rules: {// 2. add rule
                    txtTitle: {
                        required: true
                    },
                    txtDes: {
                        required: true
                    },
                    fileImage: {
                        accept: "doc|bmp|png|jpg"
                    }
                }   // end rules
            })  // end validate
        }); // end function
    </script>

    <h1>CREATE POST</h1>
    <form name="createPost" action="PostController" id="createPost"
          enctype="multipart/form-data" method="POST">
        Title <input type="text" name="txtTitle" value="" required/></br>
        Description <textarea name="txtDes" value="" required></textarea></br>
        Image <input type="file" name="fileImage" value=""/></br>
        <input type="submit" value="Post" name="btnAction" accept="doc|bmp|png|jpg"/>
    </form>
</body>
</html>
