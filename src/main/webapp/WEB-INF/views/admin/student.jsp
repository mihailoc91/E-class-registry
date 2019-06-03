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
        <title>Student Page</title>
         <link href="<c:url value="../resources/main.css" />" rel="stylesheet">
    </head>
    <body>
         <%@include file="navbar_admin.jsp" %>
        <h1>Create new student:</h1>
        <div class="form">
            <form:form method="POST" action="/e-register/admin/student" modelAttribute="studentDto">
                <form:hidden path="studentId" />
                <form:label path="firstName">First name</form:label><br>
                    <form:input path="firstName" type="text" name="firstName"/>
                    <br>              
                    <form:label path="lastName">Last name</form:label><br>
                    <form:input path="lastName" type="text" name="lastName"/><br>
               
                <form:select path="classId" name="classId"><br>
                    <form:option value="0" label="--Please select one of the available classes--"/><br>
                    <c:forEach items="${allClasses}" var="classDto"><br>
                        <form:option value="${classDto.getClassId()}" label="${classDto.getClassName()}"/>
                    </c:forEach><br>
                </form:select>
                    <br>
                       <form:select path="parents" name="parents"  items="${allParents}" multiple="true" itemLabel="firstName" itemValue="id" />
                         <%--<form:options items="${allParents}" itemLabel="firstName" itemValue="id"/>--%>
                         
                     <%--</form:select>--%>
             <br>
              
                <input type="submit" value="Save"/>
            </form:form>
        </div>
    </body>
</html>
