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
    <title>Coffee Shop - Wish List</title>
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

        img {
            border-radius: 5px; /* Round the corners of images */
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
        <img src="https://tse1.mm.bing.net/th?id=OIG4.De6QqnLGK5cJefsbSBkh&pid=ImgGn" alt="Coffee Shop Logo" class="img-fluid mx-auto d-block" style="max-width: 200px; margin-bottom: 20px;">
        <h1>Your Wish List</h1>
        <p class="text-center">At Coffee Shop, we believe in the perfect cup of coffee. Here’s a list of your favorite picks, just waiting to be enjoyed!</p>
        
        <c:set var="cart" value="${requestScope.listItemLike}"/>
        <c:if test="${not empty cart}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Image</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cart}" var="item">
                        <tr>
                            <td>${item.itemName}</td>
                            <td><img src="${item.image}" alt="${item.itemName}" width="100" height="100"/></td>
                            <td>${item.price} VND</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:url var="url" value="DispatchServlet">
                <c:param name="btAction" value="Search"/>
                <c:param name="txtSearchValue" value=""/>
            </c:url>
            <div class="text-center">
                <a href="${url}" class="btn-coffee">Add more items to wish list</a>
            </div>
        </c:if>
        <c:if test="${empty cart}">
            <c:url var="url" value="DispatchServlet">
                <c:param name="btAction" value="Search"/>
                <c:param name="txtSearchValue" value=""/>
            </c:url>
            <h2 class="text-center">No items in your wish list!</h2>
            <div class="text-center">
                <a href="${url}" class="btn-coffee">Click here to return to the shopping page</a>
            </div>
        </c:if>
    </div>
</body>
</html>