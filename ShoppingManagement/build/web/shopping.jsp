
<%@page import="sample.shopping.Cart"%>
<%@page import="java.util.Map"%>
<%@page import="sample.products.ProductDTO"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping</title>
    </head>
    <body>
        <h1>Let's go shopping !</h1>

        <%
            HashMap<Integer, ProductDTO> map = (HashMap<Integer, ProductDTO>) session.getAttribute("AVAILABLE_PRODUCT");

            if (map != null) {
        %>




        <%
            for (Map.Entry<Integer, ProductDTO> product : map.entrySet()) {

        %>

        <form action="MainController">

            <div>
                <div> 
                    <p><%= product.getValue().getProductName()%></p>
                    <p>Price: <%= product.getValue().getPrice()%></p>    
                </div>
                <div> 
                    <input type="submit" name="action" value="Add to Cart"  />
                    <input type="hidden" name="productID" value="<%= product.getValue().getProductID()%>"  />                 


                    <input type="submit" name="action" value="BuyNow" />
                </div><br>

            </div>
        </form>





        <%                }
        %> 




        <%
            String addMessages = (String) request.getAttribute("ADD_SUCCESS");
            if (addMessages == null) {
                addMessages = "";
            }

        %>

        <%= addMessages%>

        <%
            }

            String checkingRunoutOfProduct = (String) request.getAttribute("OUT_OF_PRODUCT");
            if (checkingRunoutOfProduct == null) {
                checkingRunoutOfProduct = "";

            }

        %>

        <%            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
        %>


        <a href="viewCart.jsp">View Cart</a>


        <%
            }

        %>

        <%= checkingRunoutOfProduct%>





    </body>
</html>
