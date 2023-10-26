
<%@page import="sample.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer JSP</title>
    </head>
    <body>
        <%

            UserDTO userLogin = (UserDTO) session.getAttribute("LOGIN_USER");

        %>
        <h1>Welcome Customer to Store</h1>
        <p>Hello <%= userLogin.getName() %>  </p>
        <div>
            <a href="MainController?action=Shopping_Page" >Shopping</a>
        </div>

        <div>
            <a href="updateUser.jsp">Update</a>
        </div>
        <div>
            <form action="MainController" method="POST">
                <input type="submit" name="action" value="Logout"  />
            </form>
        </div>
    </body>
</html>
