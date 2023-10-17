

<%@page import="sample.shopping.Product"%>
<%@page import="sample.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checking View</title>
    </head>
    <body>
        <%
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null && cart.size() > 0) {
        %>    
        <h1>View Cart</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID Product</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Edit</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>

                <%
                    double total = 0;
                    int index = 1;
                    for (Product product : cart.getCart().values()) {
                        total += product.getPrice() * product.getQuanity();
                %>

            <form action="MainController" method="Get">   <%-- editting directly from the form --%>

                <tr>
                    <td><%= index++%></td>

                    <td>
                        <input type="text" name="productID" value="<%= product.getProductID()%>" readonly="" />
                    </td>

                    <td><%= product.getProductName()%> </td>
                    <td><%= product.getPrice()%> </td>

                    <td>
                        <input type="text" name="quantity" value="<%= product.getQuanity()%>" required="" />
                    </td>

                    <td><%= product.getPrice() * product.getQuanity()%> </td>

                    <td>
                        <input type="submit" name="action" value="Edit" />
                    </td>
                    <td>
                        <input type="submit" name="action" value="Remove" />
                    </td>
                </tr>
            </form> 
            <%
                }
            %>
        </tbody>
    </table>
    <h1>
        Total: <%= total%><br>
    </h1>

    <form action="MainController" method="POST">
        <input type="submit"  name="action" value="Check out" />
    </form>



    <%            }

    %>
    <a href="shopping.jsp">Add more</a>


</body>
</html>
