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
        <title>Open door requests</title>
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
            /* Dropdown Button */
            .dropbtn {

                background-color: #3498DB;
                width: 100%;
                color: white;
                padding: 8px 12px;
                margin: 8px 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            /* Dropdown button on hover & focus */
            .dropbtn:hover, .dropbtn:focus {
                background-color: #2980B9;
            }

            /* The container <div> - needed to position the dropdown content */
            .dropdown {

                position: relative;
                display: inline-block;
            }

            /* Dropdown Content (Hidden by Default) */
            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            /* Links inside the dropdown */
            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            /* Change color of dropdown links on hover */
            .dropdown-content a:hover {background-color: #ddd}

            /* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
            .show {display:block;}


            .mes{

                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: beige;
            }
            .kiki{
                text-align: center;
            }

        </style>

    </head>
    <body class='kiki'>
        <ul>

            <li><a href="<c:url value="/teacher/class_schedule"/>">Class schedule</a></li>
            <li><a href="<c:url value="/teacher/grade"/>">Grade</a></li>
            <li><a href="<c:url value="/teacher/diary"/>">Diary</a></li>
            <li><a href="<c:url value="/teacher/messages"/>">Messages</a></li>
            <li><a href="<c:url value="/teacher/opendoors"/>">Open door requests</a></li>

            <li class="log">
                <form action="/e-register/signout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="Logout"/> 
                </form>

            </li>
        </ul>


        <h1>Requests:</h1>

        <div>
            <c:if test="${!listOfRespondedRequests.isEmpty()}"> 
                <h3>Scheduled visits:</h3>
                <table>
                    <tr>
                        <th>Parent</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Response</th>
                    </tr>
                    <c:forEach items="${listOfRespondedRequests}" var="respondedDto">
                        <tr>
                            <td>${respondedDto.firstNameAndLastName}</td>
                            <td>${respondedDto.formatedDate}</td>
                            <td>${respondedDto.formatedTime}</td>
                            <td>${respondedDto.statusOfRequest}</td>
                        </tr>
                    </c:forEach>
                </table>   
            </c:if>
        </div> 

        <div>
            <c:if test="${!listOfActiveRequests.isEmpty()}"> 
                <h3>Pending requests:</h3>
                <table>
                    <tr>
                        <th>Parent</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Response</th>
                    </tr>
                    <c:forEach items="${listOfActiveRequests}" var="activeDto">
                        <tr>
                            <td>${activeDto.firstNameAndLastName}</td>
                            <td>${activeDto.formatedDate}</td>
                            <td>${activeDto.formatedTime}</td>
                            <td><select class="selectUsers" name="selectUsers" onchange="selectedUsers(this.value,${activeDto.requestId})">
                                    <option value="">Respond to request...</option>
                                    <option value="1">Accept</option>
                                    <option value="0">Reject</option>
                                </select></td>
                        </tr>
                    </c:forEach>
                </table>   
            </c:if>
        </div> 

        <script>
            function selectedUsers(val, id) {

                window.location.replace('http://localhost:8080/e-register/teacher/opendoorrequest?ans=' + val + '&id=' + id);
            }
        </script> 



    </body>
</html>