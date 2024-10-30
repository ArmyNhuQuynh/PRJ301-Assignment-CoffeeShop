<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Manager Product</title>
    <style>
         button {
                padding: 5px 10px;
                cursor: pointer;
            }
        /* Styles for the create and update popups */
        #createPopup, #updatePopup {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
            z-index: 1000;
        }
        #overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.7);
            z-index: 999;
        }
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
    margin: 5px; /* Thêm margin để tạo khoảng cách giữa các nút */
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
    <script>
        // JavaScript to show/hide popups
        function openCreatePopup() {
            document.getElementById("overlay").style.display = "block";
            document.getElementById("createPopup").style.display = "block";
        }

        function closeCreatePopup() {
            document.getElementById("overlay").style.display = "none";
            document.getElementById("createPopup").style.display = "none";
        }

        function openUpdatePopup(itemId, itemName, price, status, image) {
            document.getElementById("overlay").style.display = "block";
            document.getElementById("updatePopup").style.display = "block";

            // Fill the fields with the current item data
            document.getElementById("updateItemId").value = itemId;
            document.getElementById("updateItemName").value = itemName;
            document.getElementById("updatePrice").value = price;
            document.getElementById("updateStatus").value = status;
            document.getElementById("updateImage").value = image;
        }

        function closeUpdatePopup() {
            document.getElementById("overlay").style.display = "none";
            document.getElementById("updatePopup").style.display = "none";
        }
    </script>
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
    <h1>Manager Product</h1>
    
    <!-- Nút Create -->
    <button onclick="openCreatePopup()">Create New Product</button>
    
    <!-- Bảng hiển thị sản phẩm -->
    <table border="1">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Image</th>
                <th>Price</th>
                <th>Status</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="o" items="${products}">
                <tr>
                    <td>${o.itemId}</td>
                    <td>${o.itemName}</td>
                    <td>${o.image}</td>
                    <td>${o.price}</td>
                    <td>${o.status}</td>
                    <td>
                        <button onclick="openUpdatePopup(${o.itemId}, '${o.itemName}', ${o.price}, '${o.status}', '${o.image}')">Update</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Popup tạo sản phẩm -->
    <div id="overlay" onclick="closeCreatePopup()"></div>
    <div id="createPopup">
        <h2>Create New Product</h2>
        <form action="DispatchServlet" method="POST">
            <label for="itemName">Name:</label><br>
            <input type="text" id="itemName" name="itemName" required><br><br>

            <label for="price">Price:</label><br>
            <input type="number" id="price" name="price" required><br><br>

            <label for="status">Status:</label><br>
            <select id="status" name="status">
                <option value="true">Active</option>
                <option value="false">Inactive</option>
            </select><br><br>

            <label for="image">Image URL:</label><br>
            <input type="text" id="image" name="image"><br><br>
            <input type="submit" value="Create Product" name="btAction" />
            <button type="button" onclick="closeCreatePopup()">Cancel</button>
        </form>
    </div>

    <!-- Popup cập nhật sản phẩm -->
    <div id="updatePopup">
        <h2>Update Product</h2>
        <form action="DispatchServlet" method="POST">
            <input type="hidden" id="updateItemId" name="itemId">
            <label for="updateItemName">Name:</label><br>
            <input type="text" id="updateItemName" name="itemName" required><br><br>

            <label for="updatePrice">Price:</label><br>
            <input type="number" id="updatePrice" name="price" required><br><br>

            <label for="updateStatus">Status:</label><br>
            <select id="updateStatus" name="status">
                <option value="true">Active</option>
                <option value="false">Inactive</option>
            </select><br><br>

            <label for="updateImage">Image URL:</label><br>
            <input type="text" id="updateImage" name="image"><br><br>

           <input type="submit" value="Update Product" name="btAction" />
            <button type="button" onclick="closeUpdatePopup()">Cancel</button>
        </form>
    </div>
</body>
</html>
