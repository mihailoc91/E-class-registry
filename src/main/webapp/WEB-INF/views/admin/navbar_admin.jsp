
        <ul>
            <li><a href="<c:url value="/admin/class"/>">Class</a></li>
            <li><a href="<c:url value="/admin/class_schedule"/>">Class schedule</a></li>
            <li><a href="<c:url value="/admin/classes"/>">Classes</a></li>
            <li><a href="<c:url value="/admin/student"/>">Student</a></li>
            <li><a href="<c:url value="/admin/students"/>">Students</a></li>
            <li><a href="<c:url value="/admin/subject"/>">Subject</a></li>
            <li><a href="<c:url value="/admin/subjects"/>">Subjects</a></li>
            <li><a href="<c:url value="/admin/user"/>">User</a></li>
            <li><a href="<c:url value="/admin/users"/>">Users</a></li>
            <li><a href="<c:url value="/admin/opendoor"/>">Open door</a></li>
            <li><a href="<c:url value="/admin/announcement"/>">Announcement</a></li>
            <li><a href="<c:url value="/admin/announcements"/>">Announcements</a></li>
            <li class="log">
                <form action="/e-register/signout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="Logout"/> 
                </form>
                
            </li>
        </ul>
        