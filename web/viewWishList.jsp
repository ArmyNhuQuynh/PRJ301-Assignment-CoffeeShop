<%-- 
    Document   : viewWishList
    Created on : Oct 18, 2024, 1:05:48 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>WISH LIST</title>
</head>
<body>
    <h1>Your wish list</h1>
    <c:set var="cart" value="${requestScope.listItemLike}"/>
    <c:if test="${not empty cart}">
        <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Image</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="sum" value="0" scope="page"/>
                <c:forEach items="${cart}" var="item"> <!-- Loop through cart directly -->
                    <tr>
                        <td>${item.itemName}</td> <!-- Use the correct property name -->
                        <td><img src="${item.image}" alt="${item.itemName}" width="100" height="100"/></td> <!-- Use the correct property name -->
                        <td>${item.price} VND</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <button type="button" onclick="window.location.href = 'home.jsp'">Add more items to wish list</button>
    </c:if>
    <c:if test="${empty cart}">
        <c:url var="url" value="DispatchServlet">
            <c:param name="btAction" value="Search"/>
            <c:param name="txtSearchValue" value=""/>
        </c:url>
        <h2>No items in your cart!</h2>
        <a href="${url}">Click here to return to the shopping page</a>
    </c:if>
</body>
</html>

