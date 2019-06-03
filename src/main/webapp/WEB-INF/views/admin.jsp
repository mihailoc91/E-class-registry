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
        <title>Index</title>
        <link href="<c:url value="resources/moj.css" />" rel="stylesheet">
    </head>
    <body>
        <h1 id="provera">Welcome to admin page</h1>
        <div>
            <p><a href="<c:url value="/users"/>">Users</a></p>
            <p><a href="<c:url value="/subjects"/>">Subjects</a></p>
            <p><a href="<c:url value="/users"/>">Classes</a></p>
            <p><a href="<c:url value="/class_schedule"/>">Class schedule</a></p>
            <p><a href="<c:url value="/users"/>">Announcements</a></p>
        </div>
    </body>
</html>
