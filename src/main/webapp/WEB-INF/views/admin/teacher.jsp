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
        <title>Teacher</title>
         <link href="<c:url value="../resources/main.css" />" rel="stylesheet">
    </head>
    <body>
        <h1>Teachers ${userDto.getFirstName()} ${userDto.getLastName()} subjects: </h1>
        <div class="form">
            <form:form method="POST" action="/e-register/admin/teacher" modelAttribute="userDto">
                <form:hidden path="id" />
                <form:hidden path="jmbg" />
                <form:hidden path="email" />
                <form:hidden path="password" />
                <form:hidden path="status" />
                <form:hidden path="firstName" />
                <form:hidden path="lastName" /><br>
                <c:forEach items="${allSubjects}" var="subjectDto"><br>
                    <form:checkbox path="subjects" value="${subjectDto.getSubjectId()}"/> ${subjectDto.getSubjectName()}<br>
                </c:forEach>
                    <br>
                <input type="submit" value="Create"/>
            </form:form>
        </div>
    </body>
</html>
