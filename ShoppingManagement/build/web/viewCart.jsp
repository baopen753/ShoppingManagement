<%@page import="sample.products.ProductDTO"%>
<%@page import="sample.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>  
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .gradient-custom {
                /* fallback for old browsers */
                background: #6a11cb;

                /* Chrome 10-25, Safari 5.1-6 */
                background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));

                /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
                background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
            }
        </style>
    </head>
    <body>
        <%
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null && cart.size() > 0) {
        %>
        <section class="h-100 gradient-custom">
            <div class="container py-5">
                <div class="row d-flex justify-content-center my-4">
                    <div class="col-md-8">
                        <div class="card mb-4">
                            <div class="card-header py-3">
                                <h5 class="mb-0">Cart - <%= cart.size()%> items</h5>
                            </div>
                            <div class="card-body">
                                <!-- Iterate through the cart items -->
                                <%
                                    double total = 0;
                                    for (ProductDTO product : cart.getCart().values()) {
                                        total += product.getPrice() * product.getQuanity();
                                %>
                                <!-- Single item -->
                                <div class="row">
                                    <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">
                                        <!-- Data -->
                                        <form action="MainController" method="post">
                                            <input type="hidden" name="productID" value="<%= product.getProductID()%>" />
                                            <input type="hidden" name="price" value="<%= product.getPrice()%>" />
                                            <p><strong><%= product.getProductName()%></strong></p>
                                            <p>Product ID: <%= product.getProductID()%></p>
                                            <p>Price: $<%= product.getPrice()%></p>
                                            <input type="hidden" name="action" value="Remove" />
                                            <button type="submit" class="btn btn-primary btn-sm me-1 mb-2" data-mdb-toggle="tooltip" title="Remove item">
                                                <i class="fa fa-trash"></i>
                                            </button>
                                        </form>
                                        <!-- Data -->
                                    </div>
                                    <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
                                        <!-- Quantity -->
                                        <div class="d-flex mb-4" style="max-width: 300px">
                                            <form action="MainController" method="post">
                                                <input type="hidden" name="productID" value="<%= product.getProductID()%>" />
                                                <input type="number" name="quantity" value="<%= product.getQuanity()%>" min="0" />
                                                <button type="submit" class="btn btn-primary px-3 ms-2">
                                                    <i class="fa fa-check"></i>
                                                </button>
                                                <input type="hidden" name="action" value="Edit" />
                                            </form>
                                        </div>

                                        <!-- Quantity -->

                                        <!-- Price -->
                                        <p class="text-start text-md-center">
                                            <strong>$<%= product.getQuanity() * product.getPrice()%></strong>
                                        </p>
                                        <!-- Price -->
                                    </div>
                                </div>
                                <!-- Single item -->
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-header py-3">
                                <h5 class="mb-0">Summary</h5>
                            </div>
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <%
                                        double vatRate = 0.10; // Tỷ lệ VAT 10%
                                        double subtotal = total; // Tổng số tiền ban đầu
                                        double vat = subtotal * vatRate; // Giá trị VAT

                                        double totalPrice = subtotal + vat; // Tổng giá trị cuối cùng
%>
                                    <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                                        Products
                                        <span>$<%= subtotal%></span>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                                        <div>
                                            <strong>VAT (10%)</strong>
                                        </div>
                                        <span>$<%= vat%></span>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                                        <div>
                                            <strong>Total amount (including VAT)</strong>
                                        </div>
                                        <span><strong>$<%= totalPrice%></strong></span>
                                    </li>

                                </ul>
                                <form action="MainController" method="POST">
                                    <button type="submit" class="btn btn-primary btn-lg btn-block" name="action" value="Check out">
                                        Check out
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%
            }
        %>
        <a href="shopping.jsp">Add more</a><br>
        <a href="index.html">Home</a>
    </body>
</html>
