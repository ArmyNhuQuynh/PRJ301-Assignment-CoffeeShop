<%-- 
    Document   : error
    Created on : Sep 28, 2024, 3:56:04 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8d7da;
                color: #721c24;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                height: 100vh;
                margin: 0;
                text-align: center;
            }
            h1 {
                font-size: 3em;
                margin: 0;
            }
            p {
                font-size: 1.2em;
                margin: 10px 0;
            }
            .error-code {
                font-size: 6em;
                font-weight: bold;
                margin: 20px 0;
            }
            .btn {
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                text-decoration: none;
                font-size: 1em;
                margin-top: 20px;
            }
            .btn:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="error-code">404</div>
        <h1>Oops! Page Not Found</h1>
        <p>Sorry, the page you are looking for does not exist or has been moved.</p>
        <form action="DispatchServlet" method="POST" style="display: inline;">
            <input type="hidden" name="btAction" value="Search">
            <input type="hidden" name="txtSearchValue" value="">
            <button type="submit" class="btn">Return to Home</button>
        </form>
    </body>
</html>
