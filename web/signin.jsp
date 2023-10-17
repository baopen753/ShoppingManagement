<%@page import="sample.users.UserError"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Sign In JSP</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Create a new account</h1>


        <%
            UserError userError = (UserError) request.getAttribute("ERROR");
        %>


        <form action="MainController" method="POST">
            <label>Username</label>
            <input type="text" name="userName" required=""  /> <%= userError.getUsernameError()%> <br>
            <label>Full Name</label>
            <input type="text" name="name" required="" /> <%= userError.getNameError()%><br>  
            <label>Password</label>
            <input type="password" name="passWord" required=""  /><%= userError.getPasswordError()%><br>  
            <label>Re-password</label>
            <input type="password" name="confirm"  required=""  /><%= userError.getConfirmError()%>      <br>            
            <input type="submit" name="action" value="Signin"  />
            <input type="reset" value="Reset"/>
        </form>

        <br> <%= userError.getError()%>
    </body>
</html>
