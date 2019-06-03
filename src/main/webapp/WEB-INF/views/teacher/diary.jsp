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
        <%@include file="navbar_teacher.jsp" %>
        <h1 id="provera">Welcome to diary page</h1>
       <div>
           
            <form:form method="POST" action="/e-register/diary" modelAttribute="gradesDtoUspeh">
               
            <table id="table"  >
               
                <tr>
                    <th>Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <c:forEach items="${allSubjects}" var="subject">
                        <th>${subject.getSubjectName()}</th>
                        <th>Final</th> 
                    </c:forEach> 
                          
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${allStudents}" var="student" varStatus="count">
                    <tr>
                        <td>${count.index+1}</td>
                        <td>${student.getFirstName()}</td> 
                        <td>${student.getLastName()}</td>
            
                    <c:forEach items="${allSubjects}" var="subject">
                    <td><a href="<c:url value="/teacher/allgrades?idP=${student.getStudentId()}&subject=${subject.getSubjectId()}&teacher=${teacher.getId()}"/>" style="text-decoration: none">
                            <fieldset>
                                <c:forEach items="${gradesDtoUspeh.grades}" var="ocena" varStatus="tagStatus" >
                                    <c:if test = "${student.getStudentId() == ocena.student.studentId}">
                                             <c:if test = "${subject.getSubjectId() == ocena.subject.subjectId}">
                                                ${ocena.grade}
                                            </c:if>
                                     </c:if>  
                                </c:forEach>
                            </fieldset>
                         </a> 
                    </td>
                    
                        <c:forEach items="${allFinalGrades}" var="finalGrades">
                             <c:if test = "${student.getStudentId() == finalGrades.student.studentId}">
                                    <c:if test = "${subject.getSubjectId() == finalGrades.subject.subjectId}">
                                     <td>   
                                        <a href="<c:url value="/teacher/allgrades?idP=${student.getStudentId()}&subject=${subject.getSubjectId()}&teacher=${teacher.getId()}"/>" style="text-decoration: none">
                                            <fieldset>
                                            ${finalGrades.getGrade()}
                                            </fieldset>
                                        </a>
                                    </td>
                                    </c:if>
                             </c:if>
                        </c:forEach>
                    </c:forEach>  
                      </tr>
                </c:forEach>
            </table>
            </form:form>
        </div>
    </body>
</html>
