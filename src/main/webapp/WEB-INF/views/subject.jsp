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
        <title>Subjects</title>
        <link href="<c:url value="resources/moj.css" />" rel="stylesheet">
    </head>
    <body>
        <h1 id="provera">Welcome to subjects page</h1>
        <div>
            <form:form action="/e-register/subject" method="POST" modelAttribute="subjectDto">
                 <form:hidden path="subjectId" />
                 <form:hidden path="teachers" />
                <fieldset>
                    <form:label path="subjectName">Subject name</form:label>
                    <form:input path="subjectName" type="text" name="subjectName"/>
                </fieldset>
                <input type="Submit" value="Submit"/>
            </form:form>
        </div>
    </body>
</html>
