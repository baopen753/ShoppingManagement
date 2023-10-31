<%@page import="sample.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager JSP</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </head>
    <body>
        <%
// xac thuc phan quyen : chi user login voi role 'QL' moi vao duoc trang nay
            UserDTO userLogin = (UserDTO) session.getAttribute("LOGIN_USER");
            if (userLogin == null || !userLogin.getRoleID().equals("QL")) {
                response.sendRedirect("login.html");
            }

        %>
        <section style="background-color: #eee;">
            <div class="container py-5">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="card mb-4">
                            <div class="card-body text-center">
                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                                     class="rounded-circle img-fluid" style="width: 150px;">
                                <h5 class="my-3"><%= userLogin.getUserID()%></h5>
                                <div class="d-flex justify-content-center mb-2">
                                    <div>
                                        <button type="button" class="btn btn-primary" onclick="location.href = 'MainController?action=Logout'">Logout</button>
                                        <button type="button" class="btn btn-secondary ms-1" onclick="location.href = 'updateManager.jsp'">Edit</button>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center mb-2">
                                    <div>
                                        <button type="button" class="btn btn-outline-primary" onclick="location.href = 'managerCustomer.jsp'">Manage Customers</button>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center mb-2">
                                    <div>
                                        <button type="button" class="btn btn-outline-primary" onclick="location.href = 'managerProduct.jsp'">Manage Products</button>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center mb-2">
                                    <div>
                                        <button type="button" class="btn btn-outline-primary" onclick="location.href = 'managerOrderDetail.jsp'">Manage Invoices</button>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="card mb-4 mb-lg-0">
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Full Name</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0"><%= userLogin.getName()%></p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Role</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0"><%= userLogin.getRoleID()%></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>

