var deletedProductIds = [];

var updatedQuantitiesMap = {};

var updatedQuantities
$(document).ready(function () {
    var userId = sessionStorage.getItem("userId");

    $.ajax({
        type: "GET",
        url: "/shoppingcartmvcapplication/cart/products",
        contentType: "application/json",
        headers: {
            "userId": userId
        },
        success: function (products) {
            sessionStorage.setItem('products', JSON.stringify(products));
            updateUIWithOriginalProducts(products);
            console.log(products);
            var tableBody = $("#products");
            tableBody.empty();
            products.forEach(function (product) {
                var row = "<tr>" +
                    "<td>" + product.name + "</td>" +
                    "<td>" + product.price + "</td>" +
                    "<td class='quantity' id='quantity-" + product.id + "'>" + product.quantity + "</td>" +
                    "<td>" + product.category + "</td>" +
                    "<td class='action-buttons'>" +
                    "<button class='action-button increase' data-product-id='" + product.id + "'><i class='fas fa-plus'></i></button>" +
                    "<button class='action-button decrease' data-product-id='" + product.id + "'><i class='fas fa-minus'></i></button>" +
                    "<button class='action-button delete' data-product-id='" + product.id + "'><i class='fas fa-trash-alt'></i></button>" +
                    "</td>" +
                    "</tr>";
                tableBody.append(row);
            });
        },
        error: function (error) {
            console.error("Error fetching products in cart:", error);
            alert("Error fetching products in cart");
        }
    });

    $(document).on('click', '.action-button.increase', function () {
        var productId = $(this).data('product-id');
        changeQuantity('increase', productId);
    });

    $(document).on('click', '.action-button.decrease', function () {
        var productId = $(this).data('product-id');
        changeQuantity('decrease', productId);
    });

    $(document).on('click', '.action-button.delete', function () {
        var productId = $(this).data('product-id');
        deleteProduct(productId);
    });

    function changeQuantity(action, productId) {
        var quantityElement = $("#quantity-" + productId);
        var currentQuantity = parseInt(quantityElement.text());

        if (action === "increase") {
            currentQuantity++;
        } else if (action === "decrease" && currentQuantity > 0) {
            currentQuantity--;
        }
        updatedQuantitiesMap[productId] = currentQuantity;
        quantityElement.text(currentQuantity);
        sessionStorage.setItem("updatedQuantities", JSON.stringify(updatedQuantitiesMap));
    }

    function deleteProduct(productId) {
        $("#quantity-" + productId).closest('tr').remove();
        deletedProductIds.push(productId);
    }
});

function submitCart() {
    var userId = sessionStorage.getItem("userId");
    var mapData = {
        "updatedData": updatedQuantitiesMap,
        "deletedData": deletedProductIds
    };

    $.ajax({
        type: "POST",
        url: "/shoppingcartmvcapplication/updateordelete",
        contentType: "application/json",
        headers: {
            "userId": userId
        },
        data: JSON.stringify(mapData),
        success: function (response) {
            swal.fire({
                title: "Quantity updated successfully !! ",
                icon: 'info',
                showConfirmButton: false,
                timer: 1500
            });
        },
        error: function (error) {
            alert("Error updating quantity");
        }
    });
}

function discardCart() {
    showConfirmationPopup("Are you sure you want to discard changes?")
        .then((result) => {
            if (result.isConfirmed) {
                sessionStorage.removeItem('updatedQuantities');

                var originalProducts = JSON.parse(sessionStorage.getItem('products'));
                updateUIWithOriginalProducts(originalProducts);

                showSuccessPopup("Changes discarded successfully");
            }
        });
}

function showConfirmationPopup(message) {
    return Swal.fire({
        title: 'Confirmation',
        text: message,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, discard changes!'
    });
}

function showSuccessPopup(message) {
    Swal.fire({
        title: 'Success',
        text: message,
        icon: 'success'
    });
}

function showErrorPopup(message) {
    Swal.fire({
        title: 'Error',
        text: message,
        icon: 'error'
    });
}

function updateUIWithOriginalProducts(products ) {
    var tableBody = $("#products");
    tableBody.empty();

    products.forEach(function (product) {
        sessionStorage.getItem('updatedQuantities');
        var row = "<tr>" +
            "<td>" + product.name + "</td>" +
            "<td>" + product.price + "</td>" +
            "<td class='quantity' id='quantity-" + product.id + "'>" + product.quantity + "</td>" +
            "<td>" + product.category + "</td>" +
            "<td class='action-buttons'>" +
            "<button class='action-button increase' data-product-id='" + product.id + "'><i class='fas fa-plus'></i></button>" +
            "<button class='action-button decrease' data-product-id='" + product.id + "'><i class='fas fa-minus'></i></button>" +
            "<button class='action-button delete' data-product-id='" + product.id + "'><i class='fas fa-trash-alt'></i></button>" +
            "</td>" +
            "</tr>";
        tableBody.append(row);
    });
}

