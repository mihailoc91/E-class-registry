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
        <title>Announcements</title>
        <link href="<c:url value="resources/moj.css" />" rel="stylesheet">
    </head>
    <body>
        <h1 id="provera">Welcome to announcements page</h1>
        
        <div>
            <table id="table">
                <tr>
                    <th>Announcement Title</th>
                    <th>Creation date</th>
                    <th>Announcement</th>
                    <th>Class</th>
                </tr>
                <c:forEach items="${listOfAnnouncement}" var="announcement">
                    <tr>
                        <td>${announcement.getTitle()}</td>
                        <td>${announcement.getFormatedDate()}</td>
                        <td><textarea readonly>${announcement.getAnnouncement()}</textarea></td>
                        
                        <td><c:forEach items="${announcement.classesName}" var="name">
                                    <label>${name} </label>
                            </c:forEach>
                        </td>
                    </tr>
               </c:forEach>
            </table>
        </div> 
    </body>
</html>
