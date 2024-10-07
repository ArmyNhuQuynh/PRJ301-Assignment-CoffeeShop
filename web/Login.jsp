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
        <title>Login</title>
    </head>
    <body>
        <form action="DispatchServlet" method="Get">
            Email <input type="text" name="txtEmail" value="" />
            Password <input type="password" name="txtPassword" value="" />
            <input type="submit" value="Login" name="btAction" />
        </form>
    </body>
</html>
