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
        <title>View Order</title>
    </head>
 <body>
        <form action="CheckStatusServlet" method="post">
            <input type="text" name="txtOrderId" value="${not empty txtOrderId ? txtOrderId : ''}" />
            <input type="submit" value="Check Status" />
        </form>
        
        <c:if test="${not empty errorMessage}">
            <h2 style="color: red;">${errorMessage}</h2>
        </c:if>

        <c:if test="${not empty ORDER}">
            <table border="1">
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
        <a href="${url}">Click here to return to the shopping page</a>
    </body>
</html>
