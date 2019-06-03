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
        <title>students</title>
        <link href="<c:url value="../resources/main.css" />" rel="stylesheet">
    </head>
    <body>
         <%@include file="navbar_admin.jsp" %>
        <h1 id="provera">Welcome to students page</h1>
       <div>
            <table id="table"  data-pages="${allStudentsDto.get(0).getNumberOfPages()}" >
               
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Class</th>
                    <th>Parent</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:forEach items="${allStudentsDto}" var="student">
                    <tr>
                        <td>${student.getFirstName()}</td> 
                        <td>${student.getLastName()}</td> 
                        <td>${student.getClassEntity().getClassName()}</td> 
                        <td>${student.getParentsDto().get(0).getFirstName()} ${student.getParentsDto().get(0).getLastName()}</td> 
                        <td><a href="<c:url value="/admin/student?id=${student.getStudentId()}"/>">Edit</a></td>
                        <td><a href="<c:url value="/admin/student/remove?id=${student.getStudentId()}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="table_navigation"></div>  
       
    </body>
</html>
