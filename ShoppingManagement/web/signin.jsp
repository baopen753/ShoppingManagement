<%@page import="sample.users.UserError"%>
<!DOCTYPE html>

<html>
<head>
    <title>Sign In JSP</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #2c3338;
            color: #606468;
        }

        .login {
            border: 3px solid #f1f1f1;
            width: 100%;
            max-width: 400px;
            padding: 20px;
            text-align: center;
            background-color: #363b41;
        }

        .loginForm {
            overflow: hidden;
        }

        .loginForm label {
            text-align: left;
            display: block;
            color: #eee;
            margin-top: 10px;
        }

        .loginForm input[type="text"],
        .loginForm input[type="password"] {
            width: 100%;
            padding: 16px;
            margin: 8px 0;
            display: block;
            border: none;
            border-radius: 0.25rem;
            background-color: #3b4148;
            color: #eee;
            font-size: 16px;
            line-height: 1.5;
            outline: none;
        }

        .loginForm input[type="text"]::placeholder,
        .loginForm input[type="password"]::placeholder {
            color: #ccc;
        }

        .textInput {
            text-align: left;
            margin-bottom: 10px;
            padding-left: 20px;
            padding-right: 20px;
        }

        .submit-reset {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }

        .submit-reset input[type="submit"],
        .submit-reset input[type="reset"] {
            flex: 1;
            padding: 14px 20px;
            margin: 8px 4px;
            border: none;
            border-radius: 0.25rem;
            cursor: pointer;
            text-transform: uppercase;
        }

        .submit-reset input[type="submit"] {
            background-color: #ea4c88;
            color: #eee;
            font-weight: 700;
        }

        .submit-reset input[type="reset"] {
            background-color: #d44179;
            color: #eee;
        }

        .error-message {
            color: #ff0000;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="login">
        <h1>Create a new account</h1>

        <%
            UserError userError = (UserError) request.getAttribute("ERROR");
        %>

        <form class="loginForm" action="MainController" method="POST">
            <label>Username</label>
            <input type="text" name="userName" required=""  />
            <span class="error-message"><%= userError.getUsernameError() %></span>
            <br>
            <label>Full Name</label>
            <input type="text" name="name" required="" />
            <span class="error-message"><%= userError.getNameError() %></span>
            <br>
            <label>Password</label>
            <input type="password" name="passWord" required=""  />
            <span class="error-message"><%= userError.getPasswordError() %></span>
            <br>
            <label>Re-password</label>
            <input type="password" name="confirm" required=""  />
            <span class="error-message"><%= userError.getConfirmError() %></span>
            <br>
            <div class="submit-reset">
                <input type="submit" name="action" value="Signin"  />
                <input type="reset" value="Reset"/>
            </div>
        </form>

        <br>
        <span class="error-message"><%= userError.getError() %></span>
    </div>
</body>
</html>

