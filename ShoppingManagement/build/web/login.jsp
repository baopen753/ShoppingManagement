
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page JSP</title>
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
                /* Thuộc tính max-width giúp hạn chế chiều rộng tối đa */
                padding: 20px;
                text-align: center;
                background-color: #363b41;
            }

            .loginForm {
                overflow: hidden;
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

            #createBtn {
                text-align: center;
                margin-top: 20px;
            }

            #createBtn input[type="submit"] {
                width: 100%;
                padding: 14px 20px;
                margin: 8px 4px;
                border: none;
                border-radius: 0.25rem;
                cursor: pointer;
                background-color: #606468;
                color: #eee;
                font-weight: 700;
            }
        </style>
    </head>
    <body>
        <div class="login">
            <form class="loginForm" action="MainController" method="POST">
                <input type="text" name="userName" placeholder="Username" required="">
                <input type="password" name="passWord" placeholder="Password" required="">
                <div class="submit-reset">
                    <input type="submit" name="action" value="Login" />
                    <input type="reset" value="Reset" />
                </div>
            </form>
            <form id="createBtn" action="signin.html">
                <input type="submit" name="action" value="Sign in" class="btn btn-dark" />
            </form>
        </div>
    </body> 
</html>
