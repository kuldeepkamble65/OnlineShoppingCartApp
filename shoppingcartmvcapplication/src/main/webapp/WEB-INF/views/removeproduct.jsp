<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Cart</title>

  <style>
    body{
      font-family: Arial;
      margin: 20px;
    }
    h2{
      text-align: center;
      color: #333333;
    }
    table{
      width:70%;
      margin-left:15%;
      margin-right:15%;
      border-collapse: collapse;
    }
    table, th, td {
      border: 1px solid black;
      padding: 8px 12px;
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
    }
  </style>
</head>
<body>

<h2>Your Cart</h2>

<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>User ID</th>
    <th>Product ID</th>
    <th>Quantity</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <tr th:each="cartItem : ${cartItems}">
    <td th:text="${cartItem.id}"></td>
    <td th:text="${cartItem.user.id}"></td>
    <td th:text="${cartItem.product.id}"></td>
    <td th:text="${cartItem.quantity}"></td>
    <td>
      <a th:href="@{/userCartMapping/remove/{cartId}(cartId=${cartItem.id})}">Remove</a>
    </td>
  </tr>
  </tbody>
</table>

</body>
</html>
