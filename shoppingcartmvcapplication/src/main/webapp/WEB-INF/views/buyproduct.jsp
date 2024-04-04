<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online Shopping Cart</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            text-align: center;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        h1 {
            color: #333;
        }

        a {
            color: white;
            text-decoration: none;
            transition: color 0.3s;
        }

        a:hover {
            color: #2ecc71;
        }
        #a5{
            margin-right: 75%;
        }

        label {
            color: white;
        }

        #myCartSection {
            display: flex;
            justify-content: flex-start;
            align-items: center;
            margin: 10px 0;
            padding-left: 10px;
            background-color: darkslateblue;
        }

        #myDiv {
            margin-left: 20px;
            margin-right: 20px;
        }

        #productCategory {
            margin-left: 10px;
            background-color: antiquewhite;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        #categoryHeading {
            margin-top: 20px;
            color: #333;
        }
        /*#logout{*/
        /*    margin-right: 10px;*/
        /*}*/
        #logout-btn {
            background-color: orange;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .slider {
            overflow: hidden;
            width: 100vw;
            height: 50vh;
            position: relative;
            margin-bottom: 20px;
            border-radius: 8px;
        }

        .slider .slide {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-size: cover;
            background-position: center;
            animation: slider 12.5s infinite;
        }

        .slider .slide:nth-child(1) {
            background-image: url('https://e1.pxfuel.com/desktop-wallpaper/685/690/desktop-wallpaper-stationery-stationary-thumbnail.jpg');
            animation-delay: 0s;
        }
        .slider .slide:nth-child(2) {
            background-image: url('https://miro.medium.com/v2/resize:fit:900/1*7zDC_-f2ayrdgfRZTeuGwg.jpeg');
            animation-delay: -2.5s;
        }

        .slider .slide:nth-child(3) {
            background-image: url('https://e0.pxfuel.com/wallpapers/204/284/desktop-wallpaper-electronics-shopping-store-buy-home-kitchen-appliances-online-james-co.jpg');
            animation-delay: -5s;
        }

        .slider .slide:nth-child(4) {
            background-image: url('https://cdn.pixabay.com/photo/2016/11/22/19/08/hangers-1850082_960_720.jpg');
            animation-delay: -7.5s;
        }

        .slider .slide:nth-child(5) {
            background-image: url('https://assets-global.website-files.com/617b224ba2374548fcc039ba/617b224ba237453ce1c0409b_hpfulq-1234-1024x512.jpg');
            animation-delay: -10s;
        }

        @keyframes slider {
            0%, 16%, 100% {
                transform: translateX(0);
                animation-timing-function: ease;
            }
            20% {
                transform: translateX(-100%);
                animation-timing-function: step-end;
            }
            96% {
                transform: translateX(100%);
                animation-timing-function: ease;
            }
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        #closePopup {
            padding: 8px;
            background-color: orange;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .popup {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: white;
            padding: 20px;
            border: 1px solid #888888;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            z-index: 1;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: orchid;
            color: white;
        }
    </style>
</head>
<body>

<h1>Dashboard</h1>


<div id="myPopup" class="popup">
    <table id="userTable">
        <thead>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
            <th>Mobile No</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
    <br>
    <button id="closePopup">Close</button>
</div>


<h2 id="categoryHeading">Buy Product</h2>

<div id="myCartSection">
    <a id="a4" href="showCart" onclick="displayCart()">My Cart</a>
    <div id="myDiv">
        <label for="productCategory">Category:</label>
        <select id="productCategory" name="productCategory" onchange="onCategoryChange()">
            <option value="" disabled selected>Search product</option>
            <option value="STATIONARY">Stationary</option>
            <option value="COSMATIC">Cosmetic</option>
            <option value="ELECTRONICS">Electronics</option>
            <option value="CLOTHING">Clothing</option>
            <option value="SPORTS">Sports</option>
        </select>
    </div>
    <a id="a5" onclick="displayUserDteail()">Profile</a>
    <div class="logout">
        <button type="button" id="logout-btn">Logout</button>
    </div>
</div>

<div class="slider">
    <div class="slide"></div>
    <div class="slide"></div>
    <div class="slide"></div>
    <div class="slide"></div>
    <div class="slide"></div>
</div>
<div>
    <div class="image1Div">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaJONpQjFNriWqRepC9IAtMSQmc9tyF8DRNQ&usqp=CAU">
    </div>
</div>


<script src="<c:url value="/resources/support/js/appjs/dashboard.js"/>"></script>
</body>
</html>
