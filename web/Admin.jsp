<%-- 
    Document   : Admin
    Created on : Sep 28, 2024, 2:03:57 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Page</title>
    <style>
        /* Nền và font */
        body {
            background-color: #f3f0eb;
            font-family: Arial, sans-serif;
        }
        
        /* Tiêu đề */
        h1 {
            color: #4f9fd9;
        }

        /* Container chào mừng */
        .welcome-container {
            display: flex;
            align-items: center;
            font-size: 24px;
            margin-bottom: 20px;
            color: #5d4037; /* Màu nâu nhạt */
        }
        
        /* Tên người dùng */
        .user-name {
            color: #d32f2f; /* Màu đỏ đậm */
            text-decoration: underline;
            margin-left: 10px;
        }
        
        /* Nút đăng xuất */
        .logout-btn {
            margin-left: auto;
            padding: 8px 15px;
            cursor: pointer;
            font-size: 16px;
            border: none;
            color: white;
            background-color: #e57373; /* Màu đỏ nhạt */
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .logout-btn:hover {
            background-color: #d32f2f; /* Màu đỏ đậm khi hover */
        }

        /* Nhóm nút */
        .button-group form {
            display: inline-block;
            margin-right: 10px;
        }
        .button-group input[type="submit"] {
            padding: 10px 20px;
            cursor: pointer;
            font-size: 16px;
            border: none;
            color: white;
            background-color: #4CAF50; /* Màu xanh lá */
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .button-group input[type="submit"]:hover {
            background-color: #388E3C; /* Màu xanh lá đậm khi hover */
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <span>Welcome,</span>
        <span class="user-name">${sessionScope.USER.fullName}</span>
        <form action="DispatchServlet" method="POST" style="margin-left: 10px;">
            <input type="submit" value="Log out" name="btAction" class="logout-btn" />  
        </form>
    </div>
    <div class="button-group">
        <form action="DispatchServlet" method="POST">
            <input type="submit" value="Manage Product" name="btAction" />    
        </form>
        <form action="DispatchServlet" method="GET">
            <input type="submit" value="Manage Order" name="btAction" /> 
        </form>
        <form action="DispatchServlet" method="POST">
            <input type="submit" value="Report" name="btAction" />  
        </form>
    </div>
</body>
</html>
