<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>World Cup Predictor 2026</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/styles.css">
</head>

<body class="predict-page">

<div class="top-header">
    <div class="header-left">
        <span class="header-logo">🏆</span>
        <span>FIFA WORLD CUP 2026 PREDICTOR</span>
    </div>

    <div class="header-right">
        <span id="welcomeText"></span>
        <button class="btn btn-light" onclick="window.location.href='/profile'">Profile</button>
        <button class="btn btn-danger" onclick="logout()">Logout</button>
    </div>
</div>

<div class="hero-video-banner">
    <video id="wcVideo" autoplay muted loop playsinline poster="/images/worldcup-hero.jpg">
        <source src="/videos/worldcup-hero.mp4" type="video/mp4">
    </video>

    <div class="video-overlay">
        <p class="ivy-message">
            🌎 Make your prediction before kickoff and see how others vote.
        </p>

        <button id="soundButton" class="btn btn-warning btn-sm" onclick="toggleSound()">
            🔊 Sound On
        </button>
    </div>
</div>

<div class="container mt-4">

    <div class="row mb-4">
        <div class="col-md-6">
            <div class="card p-4 h-100">
                <h3>🏆 Who do you think will win the FIFA World Cup 2026?</h3>

                <select id="teamSelect" class="form-select mt-3 mb-3"></select>

                <button id="saveBtn" class="btn btn-success" onclick="savePrediction()">
                    Save Prediction
                </button>

                <p class="mt-4">
                    <strong>Your Current Prediction:</strong>
                    <span id="myPrediction">None</span>
                </p>
            </div>
        </div>

        <div class="col-md-6">
            <div class="hero-card">
                <img src="/images/worldcup-hero.jpg" alt="FIFA World Cup 2026" class="hero-image">

                <div class="hero-overlay">
                    <h2>FIFA WORLD CUP 2026</h2>
                    <p>United States • Canada • Mexico</p>
                </div>
            </div>
        </div>
    </div>

    <div class="row mb-4">

        <div class="col-md-6">
            <div class="card p-4 h-100">
                <h4>🏆 Top Predictions</h4>
                <p class="text-muted">See which countries are leading the prediction pool.</p>

                <button class="btn btn-primary w-100 mb-3"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#leaderboardCollapse">
                    📊 View Prediction Leaderboard
                </button>

                <div class="collapse" id="leaderboardCollapse">
                    <div class="leaderboard-scroll">
                        <table class="table table-striped table-hover mt-3">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Country</th>
                                <th>Total Votes</th>
                            </tr>
                            </thead>
                            <tbody id="leaderboardBody"></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card p-4 h-100">
                <h4>🕒 Recent Activity</h4>
                <p class="text-muted">Latest predictions submitted by the team.</p>

                <table class="table table-striped table-hover mt-3">
                    <thead>
                    <tr>
                        <th>User</th>
                        <th>Country</th>
                        <th>Last Updated</th>
                    </tr>
                    </thead>
                    <tbody id="predictionsBody"></tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="card p-4 mb-4">
        <h4>🌎 All Qualified Nations / Popular Picks</h4>
        <div id="allCountries" class="country-grid"></div>
    </div>

</div>

<footer class="footer">
    © 2026 Rocky. All rights reserved.
</footer>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
let userId = localStorage.getItem("userId");
let userName = localStorage.getItem("userName");

if (!userId) {
    window.location.href = "/";
}

$("#welcomeText").text("Welcome, " + userName);

function logout() {
    localStorage.clear();
    window.location.href = "/";
}

function flagImg(countryCode) {
    if (!countryCode) return "";
    return "<img class='flag' src='https://flagcdn.com/w40/" + countryCode + ".png'>";
}

function loadTeams() {
    $.get("/api/teams", function(data) {
        let options = "";

        data.forEach(function(team) {
            options += "<option value='" + team.id + "'>" + team.teamName + "</option>";
        });

        $("#teamSelect").html(options);
        loadLeaderboard(data);
        loadCountries(data);
        loadMyPrediction();
    });
}

function loadLeaderboard(data) {
    data.sort(function(a, b) {
        return b.votes - a.votes;
    });

    let html = "";
    let rank = 1;

    data.forEach(function(team) {
        let medal = rank;

        if (rank === 1) medal = "🥇";
        else if (rank === 2) medal = "🥈";
        else if (rank === 3) medal = "🥉";

        html += "<tr>"
            + "<td>" + medal + "</td>"
            + "<td>" + flagImg(team.countryCode) + " " + team.teamName + "</td>"
            + "<td>" + team.votes + "</td>"
            + "</tr>";

        rank++;
    });

    $("#leaderboardBody").html(html);
}

function loadPredictions() {
    $.get("/api/predictions", function(data) {
        let html = "";

        data.forEach(function(p) {
            html += "<tr>"
                + "<td>" + p.userName + "</td>"
                + "<td>" + flagImg(p.countryCode) + " " + p.teamName + "</td>"
                + "<td>" + p.updatedAt + "</td>"
                + "</tr>";
        });

        $("#predictionsBody").html(html);
    });
}

function loadMyPrediction() {
    $.get("/api/prediction?userId=" + userId, function(data) {
        if (data) {
            $("#myPrediction").html(flagImg(data.countryCode) + " " + data.teamName);
            $("#teamSelect").val(data.teamId);
        } else {
            $("#myPrediction").text("None");
        }
    });
}

function loadCountries(data) {
    let html = "";

    data.forEach(function(team) {
        html += "<div class='country-item'>"
            + flagImg(team.countryCode)
            + "<span>" + team.teamName + "</span>"
            + "</div>";
    });

    $("#allCountries").html(html);
}

function savePrediction() {
    let selectedTeamId = parseInt($("#teamSelect").val());
    let selectedTeamName = $("#teamSelect option:selected").text();

    if (!selectedTeamId) {
        alert("Please select a country first.");
        return;
    }

    $("#saveBtn").prop("disabled", true).text("Saving...");
    $("#myPrediction").text(selectedTeamName);

    $.ajax({
        url: "/api/prediction",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            userId: parseInt(userId),
            teamId: selectedTeamId
        }),
        success: function(data) {
            alert("Prediction saved!");

            if (data) {
                $("#myPrediction").html(flagImg(data.countryCode) + " " + data.teamName);
            }

            loadTeams();
            loadPredictions();

            $("#saveBtn").prop("disabled", false).text("Save Prediction");
        },
        error: function(xhr) {
            console.log(xhr.responseText);
            alert("Prediction could not be saved. Please try again.");
            $("#saveBtn").prop("disabled", false).text("Save Prediction");
        }
    });
}

function toggleSound() {
    let video = document.getElementById("wcVideo");
    let button = document.getElementById("soundButton");

    if (video.muted) {
        video.muted = false;
        button.innerHTML = "🔇 Sound Off";
    } else {
        video.muted = true;
        button.innerHTML = "🔊 Sound On";
    }
}
loadTeams();
loadPredictions();
</script>

</body>
</html>