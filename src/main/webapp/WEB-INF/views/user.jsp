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
        <title>JSP Page</title>
         <link href="<c:url value="resources/moj.css" />" rel="stylesheet">
    </head>
    <body>
        <h1>Create new user:</h1>
        <div class="form">
            <form:form method="POST" action="/e-register/user" modelAttribute="userDto">
                <form:hidden path="id" />
                <form:hidden path="subjects" />
                <form:hidden path="kids" />
                <fieldset>
                    <form:label path="jmbg">JMBG</form:label>
                    <form:input path="jmbg" type="number" name="jmbg"/>
                </fieldset>
                <fieldset>
                    <form:label path="email">Email</form:label>
                    <form:input path="email" type="text" name="email"/>
                </fieldset>
                <fieldset>
                    <form:label path="password">Password</form:label>
                    <form:input path="password" type="password" name="password"/>
                </fieldset>
                <fieldset>
                    <form:label path="status">Status</form:label>
                    <form:input path="status" type="number" name="status"/>
                </fieldset>
                <fieldset>
                    <form:label path="firstName">First name</form:label>
                    <form:input path="firstName" type="text" name="firstName"/>
                </fieldset>
                <fieldset>
                    <form:label path="lastName">Last name</form:label>
                    <form:input path="lastName" type="text" name="lastName"/>
                </fieldset>
                <input type="submit" value="Create"/>
            </form:form>
        </div>
    </body>
</html>
