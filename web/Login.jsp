<%-- 
    Document   : Login
    Created on : Sep 27, 2024, 10:21:54 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Coffee Shop - Admin Login</title>
        <style>
            /* Background and font styling */
            body {
                font-family: 'Arial', sans-serif;
                background-image: url('https://your-image-url.com/coffee-background.jpg');
                background-size: cover;
                background-position: center;
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100vh;
                margin: 0;
                color: #3e2723;
            }
            /* Overlay to enhance readability */
            .overlay {
                background: rgba(255, 255, 255, 0.9);
                padding: 20px;
                width: 100%;
                max-width: 400px;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            /* Header and logo */
            .overlay h2 {
                font-size: 26px;
                margin-bottom: 15px;
                color: #5d4037;
            }
            .logo {
                max-width: 150px;
                margin-bottom: 15px;
                border-radius: 75px
            }
            /* Input fields */
            .overlay input[type="text"],
            .overlay input[type="password"] {
                width: 90%;
                padding: 12px;
                margin: 8px 0;
                border: 1px solid #d7ccc8;
                border-radius: 5px;
                font-size: 16px;
            }
            /* Submit button */
            .overlay input[type="submit"] {
                width: 100%;
                padding: 12px;
                background-color: #795548;
                border: none;
                color: white;
                font-size: 18px;
                font-weight: bold;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .overlay input[type="submit"]:hover {
                background-color: #5d4037;
            }
            /* Footer branding */
            .login-footer {
                margin-top: 20px;
                font-size: 14px;
                color: #6d4c41;
            }
        </style>
    </head>
    <body>
        <div class="overlay">
            <img src="https://tse1.mm.bing.net/th?id=OIG4.De6QqnLGK5cJefsbSBkh&pid=ImgGn"  class="logo">
            <h2> Coffee Shop Admin Login</h2>
            <form action="DispatchServlet" method="POST">
                <label for="txtEmail">Email</label>
                <input type="text" name="txtEmail" id="txtEmail" required />
                
                <label for="txtPassword">Password</label>
                <input type="password" name="txtPassword" id="txtPassword" required />
                
                <input type="submit" value="LOGIN" name="btAction" />
            </form>
            <div class="login-footer">
                <p>The Coffee Shop</p>
            </div>
        </div>
    </body>
</html>
