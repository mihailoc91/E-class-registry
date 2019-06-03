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
        <h1 id="provera">Welcome to users page</h1>
        <label>Choose users:
            <select class="selectUsers" name="selectUsers" onchange="selectedUsers(this.value)">
                <option value="">Choose users...</option>
                <option value="1">Administrators</option>
                <option value="2">Directors</option>
                <option value="3">Teachers</option>
                <option value="4">Parents</option>
            </select>
        </label>
        <div>
            <table id="table" data-pages="${allUsersDto.get(0).getNumberOfPages()}" data-status="${allUsersDto.get(0).getStatus()}">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>JMBG</th>
                    <th>E-mail</th>
                    <th>Status</th>
                    <%--  <c:if test="${allUsersDto.get(0)!=null}&&${allUsersDto.get(0).getStatus()==3}">
                        <th>Subjects</th> 
</c:if> --%>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:forEach items="${allUsersDto}" var="user">
                    <tr>
                        <td>${user.getFirstName()}</td> 
                        <td>${user.getLastName()}</td> 
                        <td>${user.getJmbg()}</td> 
                        <td>${user.getEmail()}</td> 
                        <td>${user.getStatusName()}</td>
                        <%--<c:if test="${user != null && user.getStatus()=3}">--%>
                        <%--    <td><a href="<c:url value="/teacher?id=${user.getId()}"/>">Edit subjects</a></td> --%>
                        <%--</c:if>--%>
                        <td><a href="<c:url value="/user?id=${user.getId()}&status=${user.getStatus()}"/>">Edit</a></td>
                        <td><a href="<c:url value="/user/remove?id=${user.getJmbg()}&status=${user.getStatus()}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="table_navigation"></div>
        <%// takes value of chosen user and opens a page which shows that users %>
        <script>
            function selectedUsers(val){
                alert(val);
                window.location.replace('users?status='+val);
            }
        </script>  
        <script src="<c:url value="resources/moja_skripta.js"/>" ></script>  
    </body>
</html>
