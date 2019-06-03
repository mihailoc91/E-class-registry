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
        <link href="<c:url value="../resources/moj.css" />" rel="stylesheet">
    </head>
    <body class="lll">
        <%@include file="navbar_admin.jsp" %>
        <h1 id="provera">Welcome to announcements page</h1>
        
        <div class="form">
            <table id="table">
                <tr>
                    <th>Announcement</th>
                    <th>Creation date</th>
                    <!--<th>Edit</th>
                    <th>Delete</th>-->
                </tr>
                <c:forEach items="${allAnnouncementsDto}" var="announcement">
                    <tr>
                        <td>${announcement.getTitle()}</td>
                        <td>${announcement.getFormatedDate()}</td>
                        <td><a href="<c:url value="/admin/announcement?id=${announcement.getAnnouncementId()}"/>">Edit</a></td>
                        <td><a href="<c:url value="/admin/announcement/remove?id=${announcement.getAnnouncementId()}"/>">Delete</a></td>
                    </tr>
                    
                </c:forEach>
                    
            </table>
                    <br/><br/><br/>
                    
                        <a href="<c:url value="/admin/announcement"/>"><input type="button" value="Create new announcement"/></a>
                        
                   
                    
        </div> 
    </body>
</html>
