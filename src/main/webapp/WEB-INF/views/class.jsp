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
        <h1>Create new class:</h1>
        <div class="form">
            <form:form method="POST" action="/e-register/class" modelAttribute="classDto">
                <form:hidden path="classId" name="classId"/>
                <form:select path="teacherId" name="teacherId">
                    <form:option value="0" label="--Please select one of the available teachers--"/>
                    <c:forEach items="${classDto.getListOfAvailableTeachers()}" var="teacher">
                        <form:option value="${teacher.getId()}" label="${teacher.getFirstName()} ${teacher.getLastName()}"/>
                    </c:forEach>
                </form:select>
                <fieldset>
                    <form:label path="className">Class name</form:label>
                    <form:input path="className" type="text" name="className"/>
                </fieldset>
                <input type="submit" value="Create"/>
            </form:form>
        </div>
    </body>
</html>
