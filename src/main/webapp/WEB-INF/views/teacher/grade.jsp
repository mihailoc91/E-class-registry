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
        <title>Grades Page</title>
         <link href="<c:url value="../resources/main.css" />" rel="stylesheet">
    </head>
    <body>
        <%@include file="navbar_teacher.jsp" %>
        <h1 id="provera">Grades Page:</h1>
        <div class="form">
            
            <table id="table">
                <tr>
                    <th></th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Subject</th>
                    <th>Date</th>
                    <th>Grade</th>
                </tr>
                <c:forEach items="${allGrades}" var="grade">
                    <tr>
                        <td><b>Grade</b></td>
                        <td>${grade.getStudent().getFirstName()}</td>    
                        <td>${grade.getStudent().getLastName()}</td>  
                        <td>${grade.getSubject().getSubjectName()}</td> 
                        <td>${grade.getFormatedDate()}</td>
                        <td>${grade.getGrade()}</td>
                        <td><a href="<c:url value="/teacher/allgrades/delete?id=${grade.getId()}"/>" style="text-decoration: none">Delete</a></td>
                    </tr>
                </c:forEach>
      
            <form:form method="POST" action="/e-register/teacher/allgrades" modelAttribute="gradeAdd" >
                <form:hidden path="id" />
                <form:hidden path="finalGrade" />
                <form:hidden path="teacher.id"/>
                <form:hidden path="subject.subjectId"/>
                <form:hidden path="student.studentId"/>
                
                <tr>
                    <td><b>Add new Grade</b></td>
                    <td>${finalGrade.getStudent().getFirstName()}</td>    
                    <td>${finalGrade.getStudent().getLastName()}</td>  
                    <td>${finalGrade.getSubject().getSubjectName()}</td> 
                    <td></td>
                    <td><form:input path="grade" type="text" name="grade"/></td>
                    <td><input type="submit" value="Add" style="height: 50%; width: 50%;" /></td>
                </tr> 
                
            </form:form>
        
            <form:form method="POST" action="/e-register/teacher/allgrades" modelAttribute="finalGrade" >
                <form:hidden path="id" />
                <form:hidden path="finalGrade" />
                <form:hidden path="teacher.id"/>
                <form:hidden path="subject.subjectId"/>
                <form:hidden path="student.studentId"/>
         
                <tr>
                    <td><b>Final Grade</b></td>
                    <td>${finalGrade.getStudent().getFirstName()}</td>    
                    <td>${finalGrade.getStudent().getLastName()}</td>  
                    <td>${finalGrade.getSubject().getSubjectName()}</td> 
                    <td>${finalGrade.getFormatedDate()}</td>
                    <td><form:input path="grade" type="text" name="grade"/></td>
                    <td><input type="submit" value="Save" style="height: 50%; width: 50%;" /></td>
                </tr>   
            </form:form>
            </table>
        </div>
    </body>
</html>
