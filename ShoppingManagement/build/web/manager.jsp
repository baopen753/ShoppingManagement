
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager JSP</title>
    </head>
    <body>
        <h1>Welcome Manager to Store</h1>

        <div>
            <a href="managerCustomer.jsp" >Manager Customers</a>
        </div>
        <div>
            <a href="managerProduct.jsp" >Manager Products</a>
        </div>
        <div>
            <a href="managerOrderDetail.jsp" >Manager Invoices  </a>
        </div>
        <div>
            <a href="updateManager.jsp">Update</a>
        </div>
        <div>
            <form action="MainController" method="POST">
                <input type="submit" name="action" value="Logout"  />
            </form>
        </div>

    </body>
</html>
