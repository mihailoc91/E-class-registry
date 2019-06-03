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
        <h1>Create new student:</h1>
        <div class="form">
            <form:form method="POST" action="/e-register/student" modelAttribute="studentDto">
                <form:hidden path="studentId" />
               
                <fieldset>
                    <form:label path="firstName">First name</form:label>
                    <form:input path="firstName" type="text" name="firstName"/>
                </fieldset>
                <fieldset>
                    <form:label path="lastName">Last name</form:label>
                    <form:input path="lastName" type="text" name="lastName"/>
                </fieldset>
                
                <fieldset>
                <form:select path="classId" name="classId">
                    <form:option value="0" label="--Please select one of the available classes--"/>
                    <c:forEach items="${allClasses}" var="classDto">
                        <form:option value="${classDto.getClassId()}" label="${classDto.getClassName()}"/>
                    </c:forEach>
                </form:select>
                </fieldset>
                 <fieldset>
                       <form:select path="parents" name="parents"  items="${allParents}" multiple="true" itemLabel="firstName" itemValue="id" />
                         <%--<form:options items="${allParents}" itemLabel="firstName" itemValue="id"/>--%>
                         
                     <%--</form:select>--%>
             
                </fieldset>
                <input type="submit" value="Save"/>
            </form:form>
        </div>
    </body>
</html>
