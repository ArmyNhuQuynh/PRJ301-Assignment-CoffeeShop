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
        <title>Your Cart</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">

                <table border="1">
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
                        <c:forEach items="${cart.items}" var="entry"> <!-- Assume entry is a Map entry -->
                        <form action="DispatchServlet" method="POST">
                            <c:set var="item" value="${entry.key}"/> <!-- Key is the ItemsDTO object -->
                            <c:set var="quantity" value="${entry.value}"/> <!-- Value is the quantity -->

                            <c:set var="totalPrice" value="${item.price * quantity}"/> <!-- Calculate total price for current item -->
                            <c:set var="sum" value="${sum + totalPrice}" scope="page"/> <!-- Update sum -->

                            <tr>
                                <td>${item.itemName}</td> <!-- Use the correct property name -->
                                <td><img src="${item.image}" alt="${item.itemName}" width="100" height="100"/></td> <!-- Use the correct property name -->
                                <td>
                                    <input type="number" name="itemvalue" value="${quantity}" />
                                    <input type="hidden" name="itemid" value="${item.itemId}" /> <!-- Assuming itemId is the correct property name -->
                                </td>
                                <td>${item.price} vnd</td>
                                <td>
                                    <input type="submit" value="Update quantity" name="btAction" />
                                    <input type="submit" value="Remove item" name="btAction" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    <tr>
                        <td colspan="3"><strong>Total:</strong></td>
                        <td colspan="2"><strong>${sum} vnd</strong></td>
                    </tr>
                </tbody>
            </table>
            <h3>Customer Details</h3>
            <form action="DispatchServlet" onsubmit="return validateForm()">
                <input type="hidden" name="txtTotal" value="${sum}" />

                <label for="txtPhoneNumber">Phone Number:</label>
                <input type="tel" id="txtPhoneNumber" name="txtPhoneNumber" /><br>

                <label for="txtTableNumber">Table Number:</label>
                <input type="tel" id="txtLandlineNumber" name="txtTableId" /><br>

                <input type="submit" value="Check out" name="btAction" />
            </form>

            <script>
                function validateForm() {
                    var phoneNumber = document.getElementById("txtPhoneNumber").value;
                    var tableId = document.getElementById("txtLandlineNumber").value; // Thay đổi tên biến cho phù hợp

                    // Kiểm tra tính hợp lệ của số điện thoại
                    var phoneRegex = /^0\d{9}$/; // Đảm bảo số điện thoại bắt đầu bằng 0 và có 10 chữ số

                    // Kiểm tra tính hợp lệ của ID bàn
                    var tableIdNum = parseInt(tableId, 10); // Chuyển đổi thành số nguyên để so sánh
                    var errorMessage = "";

                    // Gán giá trị mặc định cho phoneNumber và tableIdNum nếu không nhập
                    if (phoneNumber === "") {
                        phoneNumber = "0"; // Gán giá trị mặc định là 0
                    }
                    if (tableId === "") {
                        tableIdNum = 0; // Gán giá trị mặc định là 0
                    }

                    if (phoneNumber === "0" && tableId === "") {
                        errorMessage = "Bạn phải nhập ít nhất một số điện thoại hoặc ID bàn.";
                    } else if (phoneNumber !== "0" && !phoneRegex.test(phoneNumber)) {
                        errorMessage = "Số điện thoại phải có 10 chữ số và bắt đầu bằng 0.";
                    } else if (tableId !== "" && (isNaN(tableIdNum) || tableIdNum < 0 || tableIdNum > 10)) {
                        errorMessage = "ID bàn phải là một số từ 0 đến 10.";
                    }

                    if (errorMessage) {
                        alert(errorMessage); // Hiển thị thông báo lỗi
                        return false; // Ngăn không cho gửi biểu mẫu
                    }

                    // Cập nhật lại giá trị trong trường hợp không có giá trị nhập
                    document.getElementById("txtPhoneNumber").value = phoneNumber;
                    document.getElementById("txtLandlineNumber").value = tableIdNum;

                    return true;  // Cho phép form submit nếu có ít nhất một giá trị
                }
            </script>

        <c:url var="urla" value="DispatchServlet">
            <c:param name="btAction" value="Search"/>
            <c:param name="txtSearchValue" value=""/>
        </c:url>
        <a href="${urla}">Add more item to Cart</a>
        </c:if>
        <c:if test="${empty items}">
            <c:url var="url" value="DispatchServlet">
                <c:param name="btAction" value="Search"/>
                <c:param name="txtSearchValue" value=""/>
            </c:url>
            <h2>No items in your cart!</h2>
            <a href="${url}">Click here to return to the shopping page</a>
        </c:if>
    </c:if>
    <c:if test="${empty cart}">
        <c:url var="url" value="DispatchServlet">
            <c:param name="btAction" value="Search"/>
            <c:param name="txtSearchValue" value=""/>
        </c:url>
        <h2>No cart exists!</h2>
        <a href="${url}">Click here to return to the shopping page</a>
    </c:if>
</body>
</html>