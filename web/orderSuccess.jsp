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
        <title>Check Out</title>
    </head>
    <body>
        <h1>Order success</h1>
        <c:set var="orderId" value="${requestScope.orderId}"/>
        <div>
            Your order id is ${orderId}. You can use to view status of order.
        </div>
        
        <a href="orderStatus.jsp">Click here to return view your Status Order</a><br>
        <c:url var="url" value="DispatchServlet">
            <c:param name="btAction" value="Search"/>
            <c:param name="txtSearchValue" value=""/>
        </c:url>
        <a href="${url}">Click here to return to the shopping page</a>

    </body>
</html>
