
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer JSP</title>
    </head>
    <body>
        <h1>Welcome Customer to Store</h1>

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
