<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <!-- Include jQuery for AJAX -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin-top: 50px;
            color: #333;
        }

        form {
            max-width: 400px;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        button{
            display: block;
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
        }
        select#productCategory {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
            font-family: Arial, sans-serif;
            font-size: 14px;
            color: #555;
        }
        input[type="date"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
            font-family: Arial, sans-serif;
            font-size: 14px;
            color: #555;
        }


    </style>
</head>
<body>

<h2>Add Product</h2>

<form action="/addproduct" method="post" id="addProductForm">
    <label for="productCategory">Category:</label>
    <select id="productCategory" name="productCategory">
        <option value="" disabled selected>Select Category</option>
        <option value="STATIONARY">Stationary</option>
        <option value="COSMATIC">Cosmatic</option>
        <option value="ELECTRONICS">Electronincs</option>
        <option value="CLOTHING">Clothing</option>
        <option value="SPORTS">Sports</option>
    </select>
    <label for="productName">Product Name:</label>
    <input type="text" id="productName" name="productName" required>

    <label for="productPrice">Price:</label>
    <input type="number" id="productPrice" name="productPrice"  required>

    <label for="productQuantity">Quantity:</label>
    <input type="number" id="productQuantity" name="productQuantity"  required>
    <label for="expiryDate">Expiry Date:</label>
    <input type="date" id="expiryDate" name="expiryDate" required>

    <button type="button" onclick="addProduct()">Add Product</button>
</form>

<script src="<c:url value="/resources/support/js/appjs/addproduct.js"/>"></script>

</body>
</html>
