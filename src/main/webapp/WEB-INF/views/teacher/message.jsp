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
        <link href="<c:url value="../resources/ccc.css" />" rel="stylesheet">
        <style type="text/css">
             ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
            }

            li {
                float: left;
            }

            li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

            li a:hover {
                background-color: #111;
            }


            li {
                display: inline;
            }
            .log{
                float: right;
            }
            .log form input[type=submit]{
                width: 90%;
                background-color: silver;
                color: white;
                padding: 10px 10px;
                margin: 5px 5px;
                border: none;
                border-radius: 1px;
                cursor: pointer;


            } 
            

            .text form input[type=sybmit]{
                width: 90%;
                background-color: #ff0000;
                color: white;
                padding: 10px 10px;
                margin: 5px 5px;
                border: none;
                border-radius: 1px;
                cursor: pointer;
                text-align: center;
            }
            form textarea{
                text-align: center;
                float: top;
                box-sizing: border-box;
                border: 2px solid #ccc;
            }
            .messs{
                background-color: white;
            }
            .ss sender3{
                display:inline-block;
                width:20%;
                float: left;
                text-align: left;
            }
            .ss sender4{
                overflow: hidden;
                margin: 20px 26px;
                text-align: right;
                float: right;
                
            }
            dl{
                list-style-position: outside;
                margin: 3px;
            }

        </style>
    </head>
    <body>
      <%@include file="navbar_teacher.jsp" %>
        
        
        <div class="messs">
            <c:forEach items="${allMessagesBetweenParentAndTeacher}" var="message">
                <!--<ul style="list-style-type:square;">-->
                <dl class="sender${message.getSenderStatus()}">

                    <dt class="ss"><kbd>${message.getFormatedDate()}</kbd></dt>
                    <dd class="ss">${message.getMessage()}</dd>

                </dl>
                <!--</ul>-->
            </c:forEach>
        </div>
        <div class="text">
            <form:form action="/e-register/teacher/sendmessage" method="post" modelAttribute="messageDto">
                <form:hidden path="communicationId" />
                <form:hidden path="parentId" />
                <form:hidden path="teacherId" />
                <form:hidden path="senderStatus"/>
                <form:textarea path="message" rows="20" cols="100"></form:textarea>
                    <input type="submit" value="Send"/> 
            </form:form>
        </div>
    </body>
</html>