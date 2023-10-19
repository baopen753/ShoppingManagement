<%-- 
    Document   : shopping
    Created on : Oct 14, 2023, 9:56:55 PM
    Author     : PC
--%>

<%@page import="sample.shopping.Cart"%>
<%@page import="java.util.Map"%>
<%@page import="sample.shopping.Product"%>
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
            HashMap<Integer, Product> map = (HashMap<Integer, Product>) session.getAttribute("AVAILABLE_PRODUCT");

            if (map != null) {
        %>




        <%
            for (Map.Entry<Integer, Product> product : map.entrySet()) {

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
