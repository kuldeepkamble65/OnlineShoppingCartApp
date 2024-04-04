<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Dashboard</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

    <style>
        body {
            font-family: Arial;
            margin: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .buttons-container {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
            color: #007bff;
        }

        table {
            width: 70%;
            margin-left: 15%;
            margin-right: 15%;
            border-collapse: collapse;
        }

        table,
        th,
        td {
            border: 1px solid black;
            padding: 8px 12px;
            text-align: left;
        }

        th {
            background-color: deepskyblue;
        }

        .search-container {
            display: flex;
            justify-content: flex-end;
            align-items: center;
            margin-bottom: 5px;
            width: 70%;
        }

        .search-bar {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: 60%;
        }

        .search-buttons button {
            background-color: orangered;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-left: 5px;
            width: 30%;
        }
    </style>
</head>
<body>
<h1>Product Available Under Shop</h1>

<%--<div class="buttons-container">--%>
<%--    <button id="btnStationary" data-category="STATIONARY" onclick="fetchProductsByCategory('STATIONARY')">Stationary</button>--%>
<%--    <button id="btnCosmetic" data-category="COSMATIC" onclick="fetchProductsByCategory('COSMATIC')">Cosmetic</button>--%>
<%--    <button id="btnElectronic" data-category="ELECTRONICS" onclick="fetchProductsByCategory('ELECTRONICS')">Electronic</button>--%>
<%--    <button id="btnClothing" data-category="CLOTHING" onclick="fetchProductsByCategory('CLOTHING')">Clothing</button>--%>
<%--&lt;%&ndash;    <button id="btnSports" data-category="SPORTS" onclick="fetchProductsByCategory('SPORTS')">Sports</button>&ndash;%&gt;--%>
<%--    <button id="btnProfile" type="button" onclick="displayUserDteail()">Profile</button>--%>
<%--</div>--%>

<div class="search-container">
    <input type="text" placeholder="Search...." class="search-bar" id="searchInput">
    <button type="button" id="searchProduct" class="search-buttons">
        <img src="https://w7.pngwing.com/pngs/582/430/png-transparent-search-magnifier-find-zoom-glass-seo-optimization-instagram-icon.png" alt="Search Icon" style="width: 20px; height: 20px;">
    </button>
</div>

<table>
    <thead id="a1">
    <tr>
        <th>Sr. No.</th>
        <th>Product Name</th>
        <th>Unit Price</th>
        <th>Unit Available</th>
        <th>Expiry Date</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody id="product">

    </tbody>

</table>
<script src="<c:url value="/resources/support/js/appjs/dashboard.js"/>"></script>
</body>
</html>
