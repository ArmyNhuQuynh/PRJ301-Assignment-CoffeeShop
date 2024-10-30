<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Report</title>
    <style>
         button {
                padding: 5px 10px;
                cursor: pointer;
            }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        /* Styles for the summary section */
        .summary {
            margin-top: 20px;
            padding: 15px;
            border: 1px solid #333;
            background-color: #f9f9f9;
            border-radius: 5px;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
        }
        .summary h3 {
            margin: 0;
        }
        .summary p {
            margin: 5px 0;
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
    <h1>Order Report</h1>
    
    <form method="POST" action="DispatchServlet">
        <label for="periodType">Select Period:</label>
        <select id="periodType" name="periodType">
            <option value="day">Day</option>
            <option value="month">Month</option>
        </select>
        
        <br><br>
        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" required>
        
        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" required>
        
        <br><br>
        <input type="submit" value="Generate Report" name="btAction" />
    </form>

    <c:if test="${not empty reportList}">
        <h2>Report Results</h2>
        <table>
            <thead>
                <tr>
                    <th>Period</th>
                    <th>Order Count</th>
                    <th>Total Revenue</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="report" items="${reportList}">
                    <tr>
                        <td>${report.period}</td>
                        <td>${report.orderCount}</td>
                        <td>${fn:escapeXml(report.totalRevenue)}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <c:set var="totalOrderCount" value="0" />
        <c:set var="totalRevenue" value="0" />
        
        <c:forEach var="report" items="${reportList}">
            <c:set var="totalOrderCount" value="${totalOrderCount + report.orderCount}" />
            <c:set var="totalRevenue" value="${totalRevenue + report.totalRevenue}" />
        </c:forEach>

        <!-- Summary Section -->
        <div class="summary">
            <h3>Summary</h3>
            <p>From ${fn:escapeXml(param.startDate)} to ${fn:escapeXml(param.endDate)}:</p>
            <p>Total Order Count: ${totalOrderCount}</p> <!-- Hiển thị tổng số đơn hàng -->
            <p>Total Revenue: <fmt:formatNumber value="${totalRevenue}" type="currency" /></p>
        </div>
    </c:if>
</body>
</html>