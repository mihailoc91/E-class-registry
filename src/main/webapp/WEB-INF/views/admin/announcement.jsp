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
        <link href="<c:url value="../resources/moj.css" />" rel="stylesheet">
    </head>
    <body>
        <%@include file="navbar_admin.jsp" %>
        <h1 id="provera">Create new announcement</h1>
        <div class="form">
            <form:form action="/e-register/admin/announcement" method="POST" modelAttribute="announcementDto">
                <form:hidden path="announcementId" />
                
                    <form:label path="title">Title</form:label><br>
                    <form:input path="title" type="text" name="title"/><br>
                
                    <form:label path="announcement">Announcement</form:label><br>
                    <form:textarea path="announcement" type="text" name="announcement"/><br>
                
                    <form:checkboxes items="${allClasses}" path="classes" itemLabel="className" itemValue="classId" /> <br>

               
                
                    <input type="Submit" value="Save"/>
                <br/><br/><br/><br/><br/>
                    
                        <a href="<c:url value="/admin/announcements"/>"><input type="button" value="Active announcements"/></a>
           
            </form:form>
        </div>
    </body>
</html>
