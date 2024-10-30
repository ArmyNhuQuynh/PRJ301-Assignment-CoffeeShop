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
    <title>View Order - Coffee Shop</title>
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

        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        .btn-coffee {
            background-color: #d48a9a; /* Button color */
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            text-align: center;
            display: block;
            margin: 20px auto;
            transition: background-color 0.3s ease;
        }

        .btn-coffee:hover {
            background-color: #c17584; /* Darker pink on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Order Status</h1>
        <form action="CheckStatusServlet" method="post" class="text-center mb-4">
            <input type="text" name="txtOrderId" placeholder="Enter your Order ID" value="${not empty txtOrderId ? txtOrderId : ''}" required class="form-control w-50 d-inline" />
            <input type="submit" value="Check Status" class="btn-coffee" />
        </form>

        <c:if test="${not empty errorMessage}">
            <h2 class="error-message">${errorMessage}</h2>
        </c:if>

        <c:if test="${not empty ORDER}">
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Status</th>
                        <th>Total</th>
                        <th>Phone Number</th>
                        <th>Table ID</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${ORDER.orderId}</td>
                        <td>${ORDER.orderDate}</td>
                        <td>${ORDER.status}</td>
                        <td>${ORDER.total} vnd</td>
                        <td>${ORDER.phonenumber}</td>
                        <td>${ORDER.tableId}</td>
                    </tr>
                </tbody>
            </table>
        </c:if>

        <c:url var="url" value="DispatchServlet">
            <c:param name="btAction" value="Search"/>
            <c:param name="txtSearchValue" value=""/>
        </c:url>
        <a href="${url}" class="btn-coffee">Return to Shopping</a>
    </div>
</body>
</html>