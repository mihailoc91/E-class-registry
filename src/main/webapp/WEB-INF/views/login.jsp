<%-- 
    Document   : login
    Created on : Jan 14, 2019, 7:22:04 PM
    Author     : Mihailo
--%>
<%--<c:url value="/login" var="loginUrl"/>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
        <link href="<c:url value="resources/login.css" />" rel="stylesheet">
    </head>
    <body class="bod">
        <div>
            <h1 id="provera">Welcome to Login page</h1>

            <form class="nuser" name='f' action="${loginUrl}" method="POST">
                <c:if test="${param.error == null}">        
<!--                    <p>
                        ${param.error==null }
                        			Invalid username and password.
                    </p>-->
                </c:if>
                <c:if test="${param.logout != null}">       
                    <p>
                        <!--			You have been logged out.--> ${param.logout}
                    </p>
                </c:if>
                    
                    
                        Username:
                        <input type="text" name="username" value=""/>
                        <br>
                    
                        Password:
                        <input type="password" name="password"/> 
                        <br>
                        
                        
                        Remember me:
                        <input type="checkbox" name="remember-me"/> 
                        
                        

                         <input type="submit" value="Login"/> 
                    
                    
                    
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                
            </form>
        </div>
    </body>
</html>
