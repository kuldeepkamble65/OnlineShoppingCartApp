
    function addUser() {
    var firstname = document.getElementById('firstname').value;
    var lastname = document.getElementById('lastname').value;
    var email = document.getElementById('email').value;
    var mobileNo = document.getElementById('mobile').value;
    var password = document.getElementById('password').value;

        if (!validateName(firstname, "First Name")) {
            return false;
        }

        if (!validateName(lastname, "Last Name")) {
            return false;
        }

        if (!validateMobile(mobileNo)) {
            return false;
        }

        if (!validatePassword(password)) {
            return false;
        }

    var userdata = {
    "firstname": firstname,
    "lastname": lastname,
    "email"   : email,
    "mobileNo": mobileNo,
    "password": password
};

    // Use AJAX to send data to the server
    $.ajax({
    type: "POST",
    url: "/shoppingcartmvcapplication/adduser",
    contentType: "application/json",
    data: JSON.stringify(userdata),
    success: function () {
        Swal.fire({
            title: 'User added Successfully !!',
            icon: 'info',
            showConfirmButton: false,
            timer: 1500
        }).then(function () {
            window.location.replace("/shoppingcartmvcapplication/login");
        });
    },
        error: function () {
            alert("Error adding User ");
        }
    });
    }
    // Function to validate name (only letters)
    function validateName(name, fieldName) {
        var nameRegex = /^[a-zA-Z]+$/;
        if (!name.match(nameRegex)) {
            alert("Please enter only letters for " + fieldName);
            return false;
        }
        return true;
    }

    // Function to validate mobile no
    function validateMobile(mobile) {
        var mobileRegex = /^[0-9]{10}$/;
        if (!mobile.match(mobileRegex)) {
            alert("Please enter a valid 10-digit mobile number.");
            return false;
        }
        return true;
    }

    // Function to validate password
    function validatePassword(password) {
        var passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]+$/;
        if (!password.match(passwordRegex)) {
            alert('Password must contain at least one letter, one number, and one special character.');
            return false;
        }
        return true;
    }
