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
        <!--<link href="<c:url value="../resources/main.css" />" rel="stylesheet">-->
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
            
            .sender4{
                float:right;
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
            .text{
                position: absolute;
                top: 150px;
                left: 800px;
                width: 200px;
                height: 100px;
            }
            .messs{
                position: relative;
                width: 400px;
                height: 200px;

            }

        </style>
    </head>
    <body>
         <ul>
           <li><a href="<c:url value="/parent/diary"/>">Diary</a></li>
           <li><a href="<c:url value="/parent/messages"/>">Messages</a></li>
           <li><a href="<c:url value="/parent/opendoors"/>">Open doors</a></li>
            <li class="log">
                <form action="/e-register/signout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="Logout"/> 
                </form>
                
            </li>
        </ul>
        
<%--        <div class="messs">
            <c:forEach items="${allMessagesBetweenParentAndTeacher}" var="message">
                <ul style="list-style-type:square;">
                <dl class="sender${message.getSenderStatus()}">

                    <dt><kbd>${message.getFormatedDate()}</kbd></dt>
                    <dd>${message.getMessage()}</dd>

                </dl>
                </ul>
            </c:forEach>
        </div>--%>
        
        <div class="text">
            <form:form action="/e-register/parent/opendoorrequest" method="post" modelAttribute="openDoorRequestDto">
                <form:hidden path="requestId" />
                <form:hidden path="openDoorId" />
                <form:hidden path="parentId" />
                
                <form:label path="chosenWeek"> This teacher holds open door on ${dayOfTheWeek}, please choose a week for your request: </form:label><br>
                <form:input path="chosenWeek" name="chosenWeek" type="week" min="${year}-W${openDoorRequestDto.startWeek}" max="${year}" required="true"/>
                
                <form:label path="chosenTime">Choose a time for open door between ${openDoorRequestDto.openDoor.startTime} and ${openDoorRequestDto.openDoor.endTime} :</form:label><br>
                <form:input path="chosenTime" type="time" name="chosenTime" min="${openDoorRequestDto.openDoor.startTime}" max="${openDoorRequestDto.openDoor.endTime}" required="true" /><br>
                
                <%-- min="${openDoorRequestDto.openDoor.startTime}" max="${openDoorRequestDto.openDoor.endTime}" required --%>
                
                
                    <input type="submit" value="Send"/> 
            </form:form>
        </div>
    </body>
</html>