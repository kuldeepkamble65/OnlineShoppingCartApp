function loginUser() {
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    var userData = {
        "email": email,
        "password": password
    };

    $.ajax({
        type: "POST",
        url: "/shoppingcartmvcapplication/login",
        contentType: "application/json",
        data: JSON.stringify(userData),
        success: function (userDto) {
            if (userDto && userDto.userId) {
                sessionStorage.setItem("userId", userDto.userId);
                showSuccessPopup("Login successfully!")
                    .then(function () {
                        window.location.href = "/shoppingcartmvcapplication/buyproduct";
                    });
            } else {
                alert("User not exist!");
            }

        },
        error: function (error) {
            showErrorPopup("Login failed. Please check your username and password.");
        }
    });
}
function showSuccessPopup(message) {
   return  Swal.fire({
        title: 'Success',
        text: message,
        icon: 'success',
        timer: 4000
    });
}

function showErrorPopup(message) {
    Swal.fire({
        title: 'Error',
        text: message,
        icon: 'error'
    });
}

$(document).ready(function () {
    $("#togglePassword").click(function () {
        var passwordInput = $("#password");

        if (passwordInput.attr("type") === "password") {
            passwordInput.attr("type", "text");
        } else {
            passwordInput.attr("type", "password");
        }
    });
});

