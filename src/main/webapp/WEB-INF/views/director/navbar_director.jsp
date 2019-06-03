
        <ul>
             <li><a href="<c:url value="/director/chart"/>">Charts for Classes</a></li>
            <li><a href="<c:url value="/director/chartSchool"/>">Chart for Subjects</a></li>
            
            <li class="log">
                <form action="/e-register/signout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="Logout"/> 
                </form>
                
            </li>
        </ul>
        