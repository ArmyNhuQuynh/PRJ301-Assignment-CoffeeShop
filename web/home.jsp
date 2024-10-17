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
        <title>COFFEE_SHOP</title>
        <link rel="stylesheet" type="text/css" href="css/home.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body>
        <a href="Login.jsp">Login</a><br/>
        <form action="DispatchServlet" method="POST">
            <input type="submit" value="View your cart" name="btAction" />    
        </form>
        <form action="DispatchServlet" method="POST">
            <input type="submit" value="View your wish list" name="btAction" />    
        </form>
        <form action="DispatchServlet" method="POST">
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" /><br/>
            <div class="container">
                <div class="row">
                    <c:forEach var="dto" items="${product}" >
                        <div class="col-4">
                            <div class="coffee">
                                <a class="img">
                                    <img src="${dto.image}" alt="Company Logo" class="logo" />

                                </a>
                                <div class="name_price">
                                    <div class="name">
                                        <h4>
                                            ${dto.itemName}
                                        </h4>
                                    </div>
                                    <div class="price_quantity">
                                        <p>${dto.price} vnd</p>
                                    </div>
                                    <form action="DispatchServlet" method="post">
                                        <input type="hidden" name="txtItemId" value="${dto.itemId}" />
                                        <input type="hidden" name="txtlastSearchValue" value="${param.txtSearchValue}" />
                                        <input type="submit" value="Add to cart" name="btAction" />
                                        <input type="submit" value="Add to wish list" name="btAction" />
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach> 
                </div>
            </div>

        </form>
    </body>
</html>
