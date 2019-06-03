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
         <%@include file="navbar_admin.jsp" %>
        <h1 id="provera">Welcome to admin page</h1>
        <img src="../resources/admin.png" alt="admin" width="200px" height="300px">
    </body>
</html>
