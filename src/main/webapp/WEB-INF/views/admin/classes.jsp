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
        <h1 id="provera">Welcome to classes page</h1>
        <div>
            <table id="table" data-pages="${allClasses.get(0).getNumberOfPages()}" >
                <tr>
                    <th>Class name</th>
                    <th>Teacher</th>
                    
                    <!--<th>Edit</th>
                    <th>Delete</th>-->
                </tr>
                <c:forEach items="${allClasses}" var="classDto">
                    <tr>
                        <td>${classDto.getClassName()}</td> 
                        <td>${classDto.getTeacherFirstName()} ${classDto.getTeacherLastName()}</td> 
                        
                       
                        
                       <td><a href="<c:url value="/admin/class?id=${classDto.getClassId()}"/>">Edit</a></td>
                        <td><a href="<c:url value="/admin/class/remove?id=${classDto.getClassId()}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="table_navigation"></div>
       
        <script src="<c:url value="resources/table_paging.js"/>" ></script>  
    </body>
</html>
