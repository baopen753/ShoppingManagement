
<%@page import="sample.users.UserDTO"%>
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>        
        <style>
            .card {
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
                max-width: 300px;
                margin: auto;
                text-align: center;
                font-family: arial;
            }

            .price {
                color: grey;
                font-size: 22px;
            }

            .card button {
                border: none;
                outline: 0;
                padding: 12px;
                color: white;
                background-color: #333; /* Màu tối hơn cho nút */
                text-align: center;
                cursor: pointer;
                width: 100%;
                font-size: 18px;
            }

            .card button:hover {
                opacity: 0.7;
            }

            /* Màu tối hơn cho nút Buy Now */
            .card button[value="Buy Now"] {
                background-color: #555;
            }
        </style>
    </head>
    <body>
        <%
            UserDTO userLogin = (UserDTO) session.getAttribute("LOGIN_USER");
        %>
        <%
            Cart cart = (Cart) session.getAttribute("CART");
        %>



        <nav class="navbar navbar-expand-md bg-dark navbar-light">
            <div class="container-fluid">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <p class="btn btn-dark">
                            Hello <%= (userLogin != null) ? userLogin.getName()
                                    : "<a href='login.html' class='text-white'>Guest</a>"%>
                        </p>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-dark" 
                           href="javascript:checkCart();">
                            Your Cart
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <%
            HashMap<Integer, ProductDTO> map = (HashMap<Integer, ProductDTO>) session.getAttribute("AVAILABLE_PRODUCT");
            if (map != null) {
        %>
        <div class="container">
            <div class="row">
                <%
                    for (Map.Entry<Integer, ProductDTO> product : map.entrySet()) {
                %>
                <div class="col-md-4">
                    <div class="card mb-4"> <!-- Thêm lề dưới mỗi thẻ card -->
                        <form action="MainController">
                            <p><%= product.getValue().getProductName()%></p>
                            <p class="price">Price: <%= product.getValue().getPrice()%></p>
                            <input type="submit" name="action" value="Add to Cart" class="btn btn-sm btn-primary" />
                            <input type="hidden" name="productID" value="<%= product.getValue().getProductID()%>" />
                            <input type="submit" name="action" value="BuyNow" class="btn btn-sm btn-dark" />
                        </form>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <%
            }
        %>
        <%
            String addMessages = (String) request.getAttribute("ADD_SUCCESS");
            if (addMessages == null) {
                addMessages = "";
            }
        %>
        <%= addMessages%>
        <%
            String checkingRunoutOfProduct = (String) request.getAttribute("OUT_OF_PRODUCT");
            if (checkingRunoutOfProduct == null) {
                checkingRunoutOfProduct = "";
            }
        %>
        <%= checkingRunoutOfProduct%>
        <script>
            function checkCart() {
            <% if (cart != null) { %>
                location.href = 'viewCart.jsp';
            <% } else { %>
                alertEmptyCart();
            <% }%>
            }

            function alertEmptyCart() {
                alert('Your cart is empty. Please add products to your cart.');
            }
        </script>
    </body>
</html>