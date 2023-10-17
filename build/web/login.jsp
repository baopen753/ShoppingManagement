
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page JSP</title>
    </head>
    <body>
        <h1>Login for Shopping</h1>
        <form action="MainController" method="POST">
            Username<input type="text" name="userName" />
            <br>Password <input type="password" name="passWord" />
            <br><input type="submit" name="action" value="Login" />
            <input type="reset"  value="Reset" />
        </form>
    </body> 
</html>
