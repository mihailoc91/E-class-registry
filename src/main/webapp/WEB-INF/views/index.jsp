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
        <meta charset="utf-8">
        <title> welcome page </title>
        <link href="<c:url value="resources/main.css" />" rel="stylesheet">
    </head>
    <body class="bod">
        <ul>
            <li><a href="<c:url value="/"/>">Home</a></li>
            <li><a href="<c:url value="/contact"/>">Contact</a></li>
            <li><a href="<c:url value="/about"/>">About</a></li>
            <li class="log"><a href="<c:url value="/login"/>">Login</a></li>
        </ul>
        <div class="naslov"><h3>Welcome to our school!</h3></div>
        ${loggedInUser.getId()}
    </body>
</html>
