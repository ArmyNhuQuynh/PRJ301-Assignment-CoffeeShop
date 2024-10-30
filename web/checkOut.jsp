<%-- 
    Document   : checkOut
    Created on : Sep 29, 2024, 12:30:15 AM
    Author     : DELL
--%>

<%@page import="java.util.Map"%>
<%@page import="Cart.ItemsCart"%>
<%@page import="Items.ItemsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Coffee Shop - Your Cart</title>
          <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        body {
            background-size: cover;
            background-position: center;
            color: #3e2723;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.9);
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

        .coffee-card {
            padding: 15px;
            text-align: center;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            background-color: white;
            margin-bottom: 20px;
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

        /* Input styles */
        input[type="tel"], input[type="number"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border-radius: 5px;
            border: 1px solid #d7ccc8;
        }

        /* Total price style */
        .total-price {
            font-weight: bold;
            font-size: 1.2em;
        }
    </style>
    </head>
    <body>
    <div class="container">
        <h1>Coffee Shop - Your Cart</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="sum" value="0" scope="page"/>
                        <c:forEach items="${cart.items}" var="entry">
                        <form action="DispatchServlet" method="POST">
                            <c:set var="item" value="${entry.key}"/> 
                            <c:set var="quantity" value="${entry.value}"/> 

                            <c:set var="totalPrice" value="${item.price * quantity}"/> 
                            <c:set var="sum" value="${sum + totalPrice}" scope="page"/> 

                            <tr>
                                <td>${item.itemName}</td> 
                                <td><img src="${item.image}" alt="${item.itemName}" width="100" height="100"/></td> 
                                <td>
                                    <input type="number" name="itemvalue" value="${quantity}" min="1" />
                                    <input type="hidden" name="itemid" value="${item.itemId}" /> 
                                </td>
                                <td>${item.price} VND</td>
                                <td>
                                    <input type="submit" value="Update quantity" name="btAction" class="btn-coffee" />
                                    <input type="submit" value="Remove item" name="btAction" class="btn-coffee" />
                                </td>
                            </tr>
                        </form>
                        </c:forEach>
                        <tr>
                            <td colspan="3" class="total-price">Total:</td>
                            <td colspan="2" class="total-price">${sum} VND</td>
                        </tr>
                    </tbody>
                </table>

                <h3>Customer Details</h3>
                <form action="DispatchServlet" onsubmit="return validateForm()">
                    <input type="hidden" name="txtTotal" value="${sum}" />

                    <label for="txtPhoneNumber">Phone Number:</label>
                    <input type="tel" id="txtPhoneNumber" name="txtPhoneNumber" required/><br>

                    <label for="txtTableNumber">Table Number:</label>
                    <input type="number" id="txtLandlineNumber" name="txtTableId" min="0" max="10"/><br>

                    <input type="submit" value="Check out" name="btAction" class="btn-coffee"/>
                </form>

                <script>
                    function validateForm() {
                        var phoneNumber = document.getElementById("txtPhoneNumber").value;
                        var tableId = document.getElementById("txtLandlineNumber").value; 
                       
                        var phoneRegex = /^0\d{9}$/;                   
                        var tableIdNum = parseInt(tableId, 10); 
                        var errorMessage = "";
                        
                        if (phoneNumber === "") {
                            phoneNumber = "0"; 
                        }
                        if (tableId === "") {
                            tableIdNum = 0;
                        }
                        if (phoneNumber === "0" && tableId === "") {
                            errorMessage = "Bạn phải nhập ít nhất một số điện thoại hoặc ID bàn.";
                        } else if (phoneNumber !== "0" && !phoneRegex.test(phoneNumber)) {
                            errorMessage = "Số điện thoại phải có 10 chữ số và bắt đầu bằng 0.";
                        } else if (tableId !== "" && (isNaN(tableIdNum) || tableIdNum < 0 || tableIdNum > 10)) {
                            errorMessage = "ID bàn phải là một số từ 0 đến 10.";
                        }
                        if (errorMessage) {
                            alert(errorMessage);
                            return false; 
                        }
                        document.getElementById("txtPhoneNumber").value = phoneNumber;
                        document.getElementById("txtLandlineNumber").value = tableIdNum;

                        return true;  
                    }
                </script>

                <c:url var="urla" value="DispatchServlet">
                    <c:param name="btAction" value="Search"/>
                    <c:param name="txtSearchValue" value=""/>
                </c:url>
                <a href="${urla}" class="btn-coffee">Add more items to Cart</a>

            </c:if>
            <c:if test="${empty items}">
                <c:url var="url" value="DispatchServlet">
                    <c:param name="btAction" value="Search"/>
                    <c:param name="txtSearchValue" value=""/>
                </c:url>
                <h2>No items in your cart!</h2>
                <a href="${url}" class="btn-coffee">Click here to return to the shopping page</a>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <c:url var="url" value="DispatchServlet">
                <c:param name="btAction" value="Search"/>
                <c:param name="txtSearchValue" value=""/>
            </c:url>
            <h2 style="text-align: center">No items in your cart!!!</h2>
            <a href="${url}" class="btn-coffee" style="margin-left: 360px">Click here to return to the shopping page</a>
        </c:if>
    </div>
</body>
</html>