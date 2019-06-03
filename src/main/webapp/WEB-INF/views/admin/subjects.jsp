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
        <link href="<c:url value="../resources/main.css" />" rel="stylesheet">
    </head>
    <body>
         <%@include file="navbar_admin.jsp" %>
        <h1 id="provera">Welcome to subjects page</h1>
        
        <div>
            <table id="table">
                <tr>
                    <th>Subject</th>
                    <!--<th>Edit</th>
                    <th>Delete</th>-->
                </tr>
                <c:forEach items="${allSubjectDto}" var="subject">
                    <tr>
                        <td>${subject.getSubjectName()}</td>
                        <td><a href="<c:url value="/admin/subject?id=${subject.getSubjectId()}"/>">Edit</a></td>
                        <td><a href="<c:url value="/admin/subject/remove?id=${subject.getSubjectId()}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div> 
    </body>
</html>
