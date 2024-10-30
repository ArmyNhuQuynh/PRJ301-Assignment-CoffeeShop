<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <style>
        
            table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        /* Định dạng chung cho tất cả các nút */
       .btn {
    padding: 5px 10px;
    cursor: pointer;
    font-size: 14px;
    border: none;
    color: white;
    background-color: #4CAF50;
    border-radius: 4px;
}
        .btn:hover {
            background-color: #45a049;
        }
        .btn-logout {
            background-color: #f44336;
        }
        .btn-logout:hover {
            background-color: #d73727;
        }
    </style>
    </head>
    <body>
        <form action="DispatchServlet" method="POST">
        <input type="submit" value="Manage Product" name="btAction" class="btn" />    
    </form>
    <form action="DispatchServlet" method="GET">
        <input type="submit" value="Manage Order" name="btAction" class="btn" /> 
    </form>
    <form action="DispatchServlet" method="POST">
        <input type="submit" value="Report" name="btAction" class="btn" />  
    </form>
    <form action="DispatchServlet" method="POST">
        <input type="submit" value="Log out" name="btAction" class="btn btn-logout" />  
    </form>
        <title>Manager Order</title>
        <h1>Manager Order</h1>

        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Total</th>
                    <th>Phone Number</th>
                    <th>Table ID</th>
                    <th>Status</th>
                    <th>Update Status</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}"> 
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.total}</td>
                    <td>${order.phonenumber}</td>
                    <td>${order.tableId}</td>
                    <td>${order.status}</td>

                    <td>
                        <form action="DispatchServlet" method="get">
                            <input type="hidden" name="orderId" value="${order.orderId}"/>
                            <select name="newStatus">
                                <option value="Pending" <c:if test="${order.status == 'Pending'}">selected</c:if>>Pending</option>
                                <option value="Confirm" <c:if test="${order.status == 'Confirm'}">selected</c:if>>Confirm</option>
                                <option value="Shipping" <c:if test="${order.status == 'Shipping'}">selected</c:if>>Shipping</option>
                                <option value="Complete" <c:if test="${order.status == 'Complete'}">selected</c:if>>Complete</option>
                            </select>
                            <input type="submit" value="Update Status" name="btAction" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
