
$(document).ready(function(){
    alert("Welcome!");
});

function addProduct() {
    var productName = document.getElementById('productName').value;
    var productPrice = document.getElementById('productPrice').value;
    var productQuantity = document.getElementById('productQuantity').value;
    var productCategory = document.getElementById('productCategory').value;
    var expiryDate = document.getElementById('expiryDate').value;

    var productData = {
        "name": productName,
        "price": productPrice,
        "quantity": productQuantity,
        "category": productCategory,
        "expiryDate": expiryDate
    };

    // Use AJAX to send data to the server
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/shoppingcartmvcapplication/addproduct",
        contentType: "application/json",
        data: JSON.stringify(productData),
        success: function (response) {
            alert(response);
            //window.location.href = "/loginUser/addTask";

        },
        error: function (error) {
            alert("Error adding product: " + error);
        }
    });
}