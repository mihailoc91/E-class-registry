<ul>
           
            <li><a href="<c:url value="/teacher/class_schedule"/>">Class schedule</a></li>
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
