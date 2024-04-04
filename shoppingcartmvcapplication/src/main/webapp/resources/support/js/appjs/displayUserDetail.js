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
