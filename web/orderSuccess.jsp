<%-- 
    Document   : orderSuccess
    Created on : Oct 23, 2024, 12:31:10 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order Success - Coffee Shop</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        body {
            background-color: #f7f7f7; /* Light background color */
            color: #3e2723; /* Dark text color */
        }

        .container {
            background-color: #ffffff; /* White background for the content */
            border-radius: 10px;
            padding: 30px;
            margin-top: 30px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #795548;
        }

        .thank-you {
            font-size: 1.2em;
            margin-bottom: 20px;
            text-align: center;
        }

        .btn-coffee {
            background-color: #d48a9a; /* Button color */
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-coffee:hover {
            background-color: #c17584; /* Darker pink on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Order Success!</h1>
        <div class="thank-you">
            Thank you for choosing Coffee Shop! Your order is being processed, and we can't wait for you to enjoy our delicious coffee.
        </div>
        <c:set var="orderId" value="${requestScope.orderId}"/>
        <div class="text-center">
            <p>Your order ID is <strong>${orderId}</strong>. Use this to view the status of your order.</p>
            <a href="orderStatus.jsp" class="btn-coffee">View Your Order Status</a><br><br>
            <c:url var="url" value="DispatchServlet">
                <c:param name="btAction" value="Search"/>
                <c:param name="txtSearchValue" value=""/>
            </c:url>
            <a href="${url}" class="btn-coffee">Return to Shopping</a>
        </div>
    </div>
</body>
</html>