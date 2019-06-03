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
        <title>Grades</title>
        <link href="<c:url value="../resources/main.css" />" rel="stylesheet">
    </head>
    <body>
         <%@include file="navbar_parent.jsp" %>
        <h1 id="provera">Welcome to diary page</h1>
       <div>
               
            <table id="table"  >
               
                <tr>
                    <th>Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <c:forEach items="${allSubjects}" var="subject">
                        <th>${subject.getSubjectName()}</th>
                        <th>Final</th> 
                    </c:forEach> 
                </tr>
                <c:forEach items="${listOfStudents}" var="student" varStatus="count">
                    <tr>
                        <td>${count.index+1}</td>
                        <td>${student.getFirstName()}</td> 
                        <td>${student.getLastName()}</td>
            
                    <c:forEach items="${allSubjects}" var="subject">
                        <td>
                            
                                <c:forEach items="${allGrades}" var="ocena">
                                    <c:if test = "${student.getStudentId() == ocena.student.studentId}">
                                             <c:if test = "${subject.getSubjectId() == ocena.subject.subjectId}">
                                            
                                                    ${ocena.grade}
                                            </c:if>
                                     </c:if>  
                                </c:forEach>
                           
                         
                        </td>
                        <td> 
                            
                                 <c:forEach items="${allFinalGrades}" var="finalGrades">
                                     <c:if test = "${student.getStudentId() == finalGrades.student.studentId}">
                                         <c:if test = "${subject.getSubjectId() == finalGrades.subject.subjectId}">
                                              ${finalGrades.getGrade()}
                                        </c:if>
                                    </c:if>
                                 </c:forEach>
                            
                        </td>    
                    </c:forEach>  
                      </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
