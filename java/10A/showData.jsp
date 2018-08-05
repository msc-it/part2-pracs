<jsp:useBean id="user" class="com.practicals.UserData" scope="session"/>
<jsp:setProperty name="user" property="*"/>
<HTML>
<BODY>
    <h2>You entered</h2>
    <strong>Name:</strong> <%= user.getUsername() %><BR> 
    <strong>Email:</strong> <%= user.getEmail() %><BR> 
    <strong>Age:</strong> <%= user.getAge() %><BR> 
</BODY>
</HTML>
    