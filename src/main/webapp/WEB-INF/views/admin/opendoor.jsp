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
        <title>Open door</title>
        <link href="<c:url value="../resources/main.css" />" rel="stylesheet">
    </head>
    <body>
         <%@include file="navbar_admin.jsp" %>
        <h1>Open door: </h1>
        <div class="nuser">
            
            
<!--            skriven openDoorId
                teacherId bira
                day bira
                startTime bira
                endTime bira
-->         
            <form:form method="POST" action="/e-register/admin/opendoor" modelAttribute="openDoorDto">
             <form:hidden path="openDoorId" />
             
              <form:select class="selectUsers" path="teacherId" name="teacherId" onchange="selectedUser(this.value)">
                  <form:option value="0" label="--Please select one of the available teachers--"/><br><br>
                    <c:forEach items="${allClassesAndTeachers}" var="classes"><br>
                        <form:option value="${classes.getTeacherId()}" label="Class: ${classes.getClassName()} Teacher: ${classes.getTeacherFirstName()} ${classes.getTeacherLastName()}"/><br>
                    </c:forEach><br>
                </form:select>
               <br>
            
             <form:label path="dayOfTheWeek">Day of the week</form:label><br>
                <form:select path="dayOfTheWeek" type="number" name="dayOfTheWeek">
                    <form:option value="1">Monday</form:option>
                    <form:option value="2">Tuesday</form:option>
                    <form:option value="3">Wednesday</form:option>
                    <form:option value="4">Thursday</form:option>
                    <form:option value="5">Friday</form:option>
                </form:select><br>
                    
              <label>Choose a time for open door:</label><br>
              <form:label path="startTime" name="beginTime">From:</form:label>
              <form:input path="startTime" type="time" name="startTime" /><br>
                <form:label path="endTime" name="endTime">Until:</form:label>
                <form:input path="endTime" type="time" name="endTime" /> <br>
                
                <input type="submit" value="Submit" onclick="saved()"/>
                <p id="message"></p>
            </form:form>
            <%--<form:form method="POST" action="/e-register/user" modelAttribute="userDto">
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
                    <option value="1">Administrator</option>
                    <option value="2">Director</option>
                    <option value="3">Teacher</option>
                    <option value="4">Parent</option>
                </form:select>

                <br>
                <form:label path="firstName">First name</form:label><br>
                <form:input path="firstName" type="text" name="firstName"/>

                <br>
                <form:label path="lastName">Last name</form:label><br>
                <form:input path="lastName" type="text" name="lastName"/>
                <br>
                <input type="submit" value="Create"/>
            </form:form>--%>
        </div>
        
        <%// takes value of chosen user and opens a page which shows that users %>
        <script>
            function selectedUser(val) {

                window.location.replace('opendoor?id=' + val);
            }
            
            function saved() {

                document.getElementById('message').innerHTML = "Data was sucessfully saved!";
            }
        </script>  
    </body>
</html>
