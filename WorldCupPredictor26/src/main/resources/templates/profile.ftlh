<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/styles.css">
</head>

<body class="predict-page">

<div class="top-header">
    <div class="header-left">
        <span class="header-logo">👤</span>
        <span>USER PROFILE</span>
    </div>

    <div class="header-right">
        <button class="btn btn-light" onclick="window.location.href='/predict'">Back to Predictions</button>
        <button class="btn btn-danger" onclick="logout()">Logout</button>
    </div>
</div>

<div class="container mt-4">

    <div class="card p-4 mb-4">
        <h2>👤 My Profile</h2>
        <p><strong>Name:</strong> <span id="profileName">Loading...</span></p>
        <p><strong>Email:</strong> <span id="profileEmail">Loading...</span></p>
    </div>

    <div class="card p-4 mb-4">
        <h3>🏆 My Current Prediction</h3>

        <p class="mt-3">
            <strong>Country:</strong>
            <span id="profilePrediction">None</span>
        </p>

        <p>
            <strong>Last Updated:</strong>
            <span id="profileUpdated">N/A</span>
        </p>
    </div>

</div>

<footer class="footer">
    © 2026 Rocky. All rights reserved.
</footer>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script>
let userId = localStorage.getItem("userId");
let userName = localStorage.getItem("userName");
let userEmail = localStorage.getItem("userEmail");

if (!userId) {
    window.location.href = "/";
}

$("#profileName").text(userName || "N/A");
$("#profileEmail").text(userEmail || "N/A");

function flagImg(countryCode) {
    if (!countryCode) {
        return "";
    }

    return "<img class='flag' src='https://flagcdn.com/w40/" + countryCode + ".png'>";
}

function loadProfilePrediction() {
    $.get("/api/prediction?userId=" + userId, function(data) {
        if (data) {
            $("#profilePrediction").html(flagImg(data.countryCode) + " " + data.teamName);
            $("#profileUpdated").text(data.updatedAt);
        }
    });
}

function logout() {
    localStorage.clear();
    window.location.href = "/";
}

loadProfilePrediction();
</script>

</body>
</html>