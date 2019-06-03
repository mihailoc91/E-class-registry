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
        <!--<link href="<c:url value="resources/main.css" />" rel="stylesheet">-->

        <style type="text/css">
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
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
            .mes ul{
                text-align: center;
            }
            .mes li{
                text-align: center;
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
                text-align: center;
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: beige;
            }
            .kiki{
                text-align: center;
            }
            .b{
                text-align: center;
            }
            .aa{
                float: left;
            }

        </style>

    </head>
    <body class='kiki'>
        <ul>

            <li class="aa"><a href="<c:url value="/teacher/class_schedule"/>">Class schedule</a></li>
            <li class="aa"><a href="<c:url value="/teacher/grade"/>">Grade</a></li>
            <li class="aa"><a href="<c:url value="/teacher/diary"/>">Diary</a></li>
            <li class="aa"><a href="<c:url value="/teacher/messages"/>">Messages</a></li>
            <li class="aa"><a href="<c:url value="/teacher/opendoors"/>">Open door requests</a></li>

            <li class="log">
                <form action="/e-register/signout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="Logout"/> 
                </form>

            </li>
        </ul>
        <h1>Chats:</h1>

        <div class="b">
            <ul class="mes">
                <c:forEach items="${allChats}" var="chat">

                    <a href="<c:url value="/teacher/message?parent=${chat.parent.id}" />"> 
                        <li>
                            <p>To: ${chat.parent.firstName} ${chat.parent.lastName}</p>
                            <p><kbd>${chat.getMessage().toString()}</kbd></p>
                        </li>
                    </a>

                </c:forEach>

            </ul>
        </div>


        <div class="dropdown">
            <button onclick="myFunction()" class="dropbtn">New chat</button>
            <div id="myDropdown" class="dropdown-content">


                <c:forEach items="${allParentsForMessages}" var="parent">

                    <a href="<c:url value="/teacher/message?parent=${parent.id}" />"> ${parent.firstName} ${parent.lastName} </a>

                </c:forEach>

            </div>
        </div>

        <script>
            /* When the user clicks on the button, 
             toggle between hiding and showing the dropdown content */
            function myFunction() {
                document.getElementById("myDropdown").classList.toggle("show");
            }

// Close the dropdown menu if the user clicks outside of it
            window.onclick = function (event) {
                if (!event.target.matches('.dropbtn')) {
                    var dropdowns = document.getElementsByClassName("dropdown-content");
                    var i;
                    for (i = 0; i < dropdowns.length; i++) {
                        var openDropdown = dropdowns[i];
                        if (openDropdown.classList.contains('show')) {
                            openDropdown.classList.remove('show');
                        }
                    }
                }
            }

        </script>

    </body>
</html>