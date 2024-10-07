<%-- 
    Document   : checkOut
    Created on : Sep 29, 2024, 12:30:15 AM
    Author     : DELL
--%>

<%@page import="java.util.Map"%>
<%@page import="Cart.ItemsCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
    </head>
    <body>
       <h1>Your Cart</h1>
    <c:set var="cart" value="${sessionScope.CART}"/>
    <c:if test="${not empty cart}">
        <c:set var="items" value="${cart.items}"/>
        <c:if test="${not empty items}">
            <form action="cartDispatch">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Item Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Sum</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${CART.items}" var="item" varStatus="counter">
    <tr>
        <td>${item.itemName}</td> 
        <td>${item.price}</td> 
        <td>${item.value}</td> 
        <td>${item.value}</td> 
         <td><input type="checkbox" name="chkItem" value="${item.key}" /></td>
    </tr>
</c:forEach>

                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3">
                    <a href="home.jsp">Add more items to your cart</a>
                    </td>
                    <td>
                        <input type="submit" value="Remove Selected Items" name="btAction" />
                    </td>
                    </tr>
                    </tfoot>
                </table>
            <label for="txtCheckOutName">Name*:</label>
    <input type="text" id="txtCheckOutName" name="txtCheckOutName" required /><br>

    <label for="txtPhoneNumber">Phone Number:</label>
    <input type="tel" id="txtPhoneNumber" name="txtPhoneNumber" /><br>

    <label for="txtTableNumber">Table Number:</label>
    <input type="tel" id="txtLandlineNumber" name="txtLandlineNumber" /><br>

    <input type="submit" value="Check out" name="btAction" />
            </form>
        </c:if>
    </c:if>
    <c:if test="${empty cart}">
        <c:url var="url" value="home.jsp">
            <c:param name="btAction" value="Search"/>
        </c:url>
        <h2>
            No cart is existed!!!
        </h2>
        <a href="${url}">Click here to return to shopping page</a>
    </c:if>
    </body>
</html>
