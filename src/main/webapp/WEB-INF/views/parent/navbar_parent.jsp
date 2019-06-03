
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
        