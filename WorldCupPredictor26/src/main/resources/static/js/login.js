function showRegister() {
    $("#loginSection").hide();
    $("#registerSection").show();
    $("#message").text("");
}

function showLogin() {
    $("#registerSection").hide();
    $("#loginSection").show();
    $("#message").text("");
}

function login() {
    $.ajax({
        url: "/api/login",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            email: $("#loginEmail").val(),
            password: $("#loginPassword").val()
        }),
        success: function(data) {
            if (!data) {
                $("#message").removeClass("text-success").addClass("text-danger");
                $("#message").text("Login failed. Please check your email and password.");
            } else {
                localStorage.setItem("userId", data.id);
                localStorage.setItem("userName", data.name);
                window.location.href = "/welcome";
            }
        }
    });
}

function register() {
    $.ajax({
        url: "/api/register",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            name: $("#regName").val(),
            email: $("#regEmail").val(),
            password: $("#regPassword").val()
        }),
        success: function(data) {
            if (!data) {
                $("#message").removeClass("text-success").addClass("text-danger");
                $("#message").text("Registration failed. Complete all fields or use a different email.");
            } else {
                $("#message").removeClass("text-danger").addClass("text-success");
                $("#message").text("Account created. Please login.");
                showLogin();
            }
        }
    });
}