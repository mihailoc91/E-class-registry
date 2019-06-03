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
        <title>user Page</title>
        <link href="<c:url value="../resources/main.css" />" rel="stylesheet">
    </head>
    <body>
         <%@include file="navbar_admin.jsp" %>
        <h1>Create new user:</h1>
        <div class="nuser">
            <form:form method="POST" action="/e-register/admin/user" modelAttribute="userDto">
                <form:hidden path="id" />
                <form:hidden path="subjects" />
                <form:hidden path="kids" />
                <br>
                <form:label path="jmbg">JMBG</form:label><br>
                <form:input path="jmbg" type="number" name="jmbg"/>
                <br>

                <form:label path="email">Email</form:label><br>
                <form:input path="email" type="text" name="email"/>
                <br>

                <form:label path="password">Password</form:label><br>
                <form:input path="password" type="password" name="password"/>
                <br>

                <form:label path="status">Status</form:label><br>
                <form:select path="status" type="number" name="status"><br>
                    <form:option value="1">Administrator</form:option>
                    <form:option value="2">Director</form:option>
                    <form:option value="3">Teacher</form:option>
                    <form:option value="4">Parent</form:option>
                </form:select>

                <br>
                <form:label path="firstName">First name</form:label><br>
                <form:input path="firstName" type="text" name="firstName"/>

                <br>
                <form:label path="lastName">Last name</form:label><br>
                <form:input path="lastName" type="text" name="lastName"/>
                <br>
                <input type="submit" value="Create"/>
            </form:form>
        </div>
    </body>
</html>
