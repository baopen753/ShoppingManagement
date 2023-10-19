

<%@page import="sample.users.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update User Page</title>
    </head>
    <body>

        <h1>Updating User Information</h1>

        <% UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError();
            }
        %>

        <form action="MainController" method="POST">
            <label>Full Name</label>
            <input type="text" name="name" /> <%= userError.getNameError()%>  <br> 
            <label>Password</label>
            <input type="password" name="passWord" required="" />  <%= userError.getPasswordError()%>  <br>
            <label>Confirm password</label>
            <input type="password" name="confirm" required=""  />  <%= userError.getConfirmError()%>  <br>
            <input type="submit" name="action" value="Update"  /> 
        </form>

        <%= userError.getError()%>
    </body>
</html>
