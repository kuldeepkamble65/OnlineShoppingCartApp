// function selectCategory(data){
//         let category = $(data).attr('data-category');
//         sessionStorage.category = category;
//         fetchProductsByCategory(sessionStorage.category)
//     }
// function fetchProducts() {
//     $.ajax({
//         type: "GET",
//         url: "/shoppingcartmvcapplication/dashboard",
//         headers: {'category':'SPORTS'},
//         dataType: 'json',
//         success: function (product) {
//             $.each(product, function(index, product) {
//                 var row = "<tr>" +
//                     "<td>" + (index + 1) + "</td>" +
//                     "<td>" + product.name + "</td>" +
//                     "<td>" + product.price + "</td>" +
//                     "<td>" + product.quantity + "</td>" +
//                     "<td>" + product.expiryDate + "</td>" +
//                     "<td><button onclick='addToCart(" + product.id + ")'>add to cart</button></td>" +
//                     "</tr>";
//                 $("#product").append(row);
//             });
//         },
//         error: function () {
//             alert("Error fetching products");
//         }
//     });
// }
// $(document).ready(function (){
//     var userId= sessionStorage.getItem('userId');
//     if(userId) {
//         addproduct(userId);
//     }
// })


function onCategoryChange() {
    var category = $("#productCategory").val();
    sessionStorage.setItem("category", category);
    if (category) {
        fetchProductsByCategory(category);
    }
}
function fetchProductsByCategory(category) {
   // var storedCategory = sessionStorage.getItem("category");
   // category = storedCategory || category;

    let header = {
        category: category,
    };
    $.ajax({
        type: "GET",
        url: "/shoppingcartmvcapplication/dashboard/data",
        dataType: 'json',
        headers: header,
        success: function (products) {
            sessionStorage.setItem("products", JSON.stringify(products));
            displayProducts(products);

            window.location.href = "/shoppingcartmvcapplication/dashboard";
        },
        error: function () {
            alert("Error fetching products");
        }
    });
}

function displayStoredProducts() {
    var storedProducts = sessionStorage.getItem("products");

    if (storedProducts) {
        var products = JSON.parse(storedProducts);
        displayProducts(products);
    }
}

function displayProducts(products) {
    $("#product").empty();  // Clear existing rows
    $.each(products, function (index, product) {
        var row = "<tr>" +
            "<td>" + (index + 1) + "</td>" +
            "<td>" + product.name + "</td>" +
            "<td>" + product.price + "</td>" +
            "<td>" + product.quantity + "</td>" +
            "<td>" + product.expiryDate + "</td>" +
          //  "<td><button onclick='removeproduct(\"" + product.name + "\")'>remove product</button></td>" +
            "<td><button onclick='addproduct(\"" + product.id + "\")'>Add To cart</button></td>" +
            "</tr>";
            "</tr>";
        $("#product").append(row);
    });
}
$(document).ready(function () {
    displayStoredProducts();
});



// function removeproduct(name) {
//     var confirmation = confirm("Are you sure you want to remove the product?");
//
//     if (confirmation) {
//         $.ajax({
//             type: "GET",
//             url: "/shoppingcartmvcapplication/removeproduct/" + name,
//             success: function (response) {
//                 alert("Product removed successfully!");
//             },
//             error: function () {
//                 alert("Error removing product");
//             }
//         });
//     }
// }


function addproduct(productId) {
    var quantity = prompt("Enter quantity for the product:", "1");
    var userId = sessionStorage.getItem("userId");
    if (quantity != null) {
        var productData = {
            userId:userId,
            productId: productId,
            quantity: quantity
        };

        $.ajax({
            type: "POST",
            url: "/shoppingcartmvcapplication/addtocart",
            contentType: "application/json",
            data: JSON.stringify(productData),
            headers: {
                "userId": userId,
                "productId":productId,
                "quantity":quantity
            },
            success: function (response) {
                Swal.fire({
                    icon: 'success',
                    title: 'Product added to cart successfully!',
                });
            },
            error: function (error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Error adding product to cart',
                });
            }
        });
    }
}
function userDetails(){
    window.location.href ="/shoppingcartmvcapplication/displayUserDteail";
}
function displayUserDteail() {
    var userId = sessionStorage.getItem('userId');
    if (userId != null) {
        let header = {
            userId: userId,
        };

        $.ajax({
            type: "GET",
            url: "/shoppingcartmvcapplication/displayUserDteail",
            contentType: "application/json",
            headers: header,
            success: function (response) {
                showUserDetails(response);
                $("#myPopup").show();
            },
            error: function (error) {
                alert("Error fetching data");
            }
        });
    } else {
        alert("User Id is null");
    }
    $("#closePopup").click(function () {
        $("#myPopup").hide();
    });
}


function showUserDetails(userData) {
    var tableBody = $("#userTable tbody");
    tableBody.empty();

    var row = $("<tr>");
    row.append($("<td>").text(userData.firstname));
    row.append($("<td>").text(userData.lastname));
    row.append($("<td>").text(userData.email));
    row.append($("<td>").text(userData.mobileNo));

    tableBody.append(row);
}


function displayCart() {
    window.location.href ="/shoppingcartmvcapplication/showCart";
}

$("#searchProduct").click(function (){
    var category = sessionStorage.getItem('category');
    var searchInput = $("#searchInput").val();
    searchProduct(category, searchInput);
})

 function searchProduct(category, productName) {
    if(productName === ''){
        Swal.fire({
            title: "please enter input...!",
            icon: 'info',
            showConfirmButton: true,
            timer: 4000
        });
        return;
    }
    let header ={
        category: category,
        productName:productName
     }
     $.ajax({
         type: "GET",
         url: "/shoppingcartmvcapplication/searchProduct",
         headers:header,
         success: function (product) {
             displayProducts(product);
         },
         error: function (error){
             Swal.fire({
                 title: "This product is not found...!",
                 icon: 'info',
                 showConfirmButton: true,
                 timer: 4000
             });
         }
     });
 }

$("#logout-btn").click(function(){
   sessionStorage.removeItem("userId");
    window.location.href= "/shoppingcartmvcapplication/login"
});


