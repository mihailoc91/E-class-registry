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
        <title> contact page </title>
        <link href="<c:url value="resources/main.css" />" rel="stylesheet">
    </head>
    <body class="bod">
        <ul>
            <li><a href="<c:url value="/index"/>">Home</a></li>
            <li><a href="<c:url value="/contact"/>">Contact</a></li>
            <li><a href="<c:url value="/about"/>">About</a></li>

        </ul>
        <h2>Welcome to contact page!</h2>
        <div class="kontakt">
            <pre>
	Address: Mite Ruzica 14
	Phone: 011/756233254
	E-mail: ourschool.gmail.com
            </pre>
            <pre>
	Director Milica Mirkovic 
	m.milicamirkovic@gmail.com 
	063/74787788	
            </pre>

            <pre>
	Secretary Milos Minic
	mminic@gmail.com
	064/45215444
            </pre>
        </div>
    </body>
</html>
