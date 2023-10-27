

<%@page import="java.util.List"%>
<%@page import="sample.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager - Customer</title>
    </head>
    <body>
        <h1>Customer Management</h1>

        <%
// xac thuc phan quyen : chi user login voi role 'QL' moi vao duoc trang nay
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("QL")) {
                response.sendRedirect("login.html");
            }

            String searchPattern = request.getParameter("searchPattern");
            if (searchPattern == null) {
                searchPattern = "";
            }

        %>

        <%            String listEmpty = (String) request.getAttribute("LIST_SEARCH_EMPTY");
            if (listEmpty == null) {
                listEmpty = "";
            }
        %>

        <%= listEmpty%>

        <form action="MainController" method="post">
            Search <input type="text" name="searchPattern" value="<%= searchPattern%>"  />
            <input type="submit" name="action" value="SearchCustomer" />
        </form>


        <%
            List<UserDTO> listCustomer = (List<UserDTO>) request.getAttribute("LIST_CUSTOMER");
            if (listCustomer != null) {
                if (listCustomer.size() > 0) {
        %>


        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Customer ID</th>
                    <th>Username</th>
                    <th>Name</th>                  
                    <th>Update Customer</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>

                <%  int count = 1;
                    for (UserDTO customer : listCustomer) {

                %>


            <form action="MainController" method="POST">   <%-- editting directly from the form --%>
                <tr>
                    <td>
                        <%= count++%>
                    </td>
                    <td>
                        <%= customer.getUserID()%>
                    </td>
                    <td>
                        <%= customer.getUserName()%>
                    </td>
                    <td>
                        <input type="text" name="name" value="<%= customer.getName()%>" >
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update Customer"  />
                        <input type="hidden" name="customerID" value="<%= customer.getUserID()%>" />
                        <input type="hidden" name="searchPattern" value="<%= searchPattern%>"  />
                        <input type="hidden" name="userName" value="<%= customer.getUserName()%>"  />
                    </td>

                    <td>
                        <a href="MainController?action=DeleteCustomerFromManager&customerID=<%= customer.getUserID()%>&search=<%= searchPattern%>">Delete</a>
                    </td>



                </tr>
            </form> 



            <%                }


            %>
            <%                    String updateStatus = (String) session.getAttribute("UPDATE_SUCCESS");
                if (updateStatus == null) {
                    updateStatus = "";
                }
            %>

            <%= updateStatus%>

        </tbody>
    </table>

    <%
            }

        }
    %>
</html>
