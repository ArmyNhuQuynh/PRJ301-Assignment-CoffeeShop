<%-- 
    Document   : home
    Created on : Sep 27, 2024, 12:03:43 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Coffee Shop</title>
        <link rel="stylesheet" type="text/css" href="css/home.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body>
        <form action="DispatchServlet" method="GET" class="mr-2">
            <input type="submit" value="Login" name="btAction" class="login-button">
        </form>
        <div class="container">
            <div class="text-center mb-4">
                <h1>Welcome to Coffee Shop</h1>
                <p>Explore our coffee collection!</p>
                <img src="https://tse1.mm.bing.net/th?id=OIG4.De6QqnLGK5cJefsbSBkh&pid=ImgGn" alt="Coffee Shop Logo" class="logo">
            </div>

            <div class="d-flex justify-content-center mb-4">

                <form action="DispatchServlet" method="POST" class="mr-2">
                    <input type="submit" value="View your cart" name="btAction" class="btn-coffee">
                </form>
                <form action="DispatchServlet" method="POST" class="mr-2">
                    <input type="submit" value="View your wish list" name="btAction" class="btn-coffee">
                </form>
                <button type="button" class="btn-coffee" onclick="window.location.href = 'orderStatus.jsp'">View your Order</button>
            </div>

            <form action="DispatchServlet" method="POST" class="mb-4 text-center">
                <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" class="form-control w-50 d-inline-block" placeholder="Search for coffee...">
                <input type="submit" value="Search" name="btAction" class="btn-coffee">
            </form>

             <div class="row">
                <c:forEach var="dto" items="${product}">
                    <div class="col-md-4 col-sm-6">
                        <div class="coffee-card">
                            <img src="${dto.image}" alt="${dto.itemName}" class="img-fluid logo mb-2">
                            <h4>${dto.itemName}</h4>
                            <div class="price-quantity">
                                <p>${dto.price} VND</p>
                            </div>
                            <form action="DispatchServlet" method="post" class="d-inline">
                                <input type="hidden" name="txtItemId" value="${dto.itemId}">
                                <input type="hidden" name="txtlastSearchValue" value="${param.txtSearchValue}">
                                <input type="submit" value="Add to cart" name="btAction" class="btn-coffee">
                                <input type="submit" value="Add to wish list" name="btAction" class="btn-coffee">
                            </form>
                        </div>
                    </div>
                </c:forEach> 
            </div>
        </div>
    </body>
    <footer class="text-center mt-5 mb-4">
            <p>Thank you for visiting our Coffee Shop! We hope you enjoy our collection and find your favorite coffee.</p>
        </footer>
</html>
