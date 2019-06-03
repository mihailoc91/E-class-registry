<%-- 
    Document   : newjsp
    Created on : Apr 7, 2019, 2:43:23 PM
    Author     : Mihailo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin</title>
        <link href="<c:url value="../resources/main.css" />" rel="stylesheet">
    </head>
    <body>
       <%@include file="navbar_parent.jsp" %>
        <h1 id="provera">Welcome to parent page</h1>
        ${loggedInUser.getId()}
        <p><a href="<c:url value="/parent/message"/>">Message</a></p>
        <img src="../resources/parent.png" alt="parent" width="200px" height="300px">
    </body>
</html>
