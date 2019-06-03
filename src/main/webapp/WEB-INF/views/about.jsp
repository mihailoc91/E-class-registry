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
        <meta charset="utf-8">
        <title> about page </title>
        <link href="<c:url value="resources/main.css" />" rel="stylesheet">
    </head>
    <body class="bod">
        <ul>
            <li><a href="<c:url value="/index"/>">Home</a></li>
            <li><a href="<c:url value="/contact"/>">Contact</a></li>
            <li><a href="<c:url value="/about"/>">About</a></li>
        </ul>
        <h3>About us!</h3>
        <p class="nn"> 
            The International School of Belgrade is accredited by the Council of International Schools (CIS), New England Association of Schools and Colleges (NEASC), and authorized by the International Baccalaureate Organization (IBO), and the Serbian Ministry of Education, Science and Technological Development.</br>

            What is accreditation?</br>

            Accreditation is a process through which a school is regularly measured against external standards of quality determined by the accrediting agencies. “The accreditation process has long been recognized in the United States and in international school circles as a highly effective means of initiating and maintaining school improvement and demonstrating adherence to a set of publicly stated standards.” [The Guide to School Evaluation and Accreditation, Eighth Edition (Version 8.2), CIS]. The CIS and NEASC methods of accreditation work in tandem to focus on a school’s mission and vision as the impetus for continued school improvement. </p>



    </body>
</html>