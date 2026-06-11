console.log("NEW predict.js loaded version 5");
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
    if (!countryCode) {
        return "";
    }

    return "<img class='flag' src='https://flagcdn.com/w40/" + countryCode + ".png'>";
}

function loadTeams() {
    $.get("/api/teams", function(data) {

        let options = "";

        data.forEach(function(team) {
            options +=
                "<option value='" + team.id + "'>" +
                    team.teamName +
                "</option>";
        });

        $("#teamSelect").html(options);

        loadCountries(data);
        loadLeaderboard(data);
        loadMyPrediction();
    });
}

function loadLeaderboard(data) {

    let html = "";

    data.sort(function(a, b) {
        return b.votes - a.votes;
    });

    let rank = 1;

    data.forEach(function(team) {

        let medal = rank;

        if (rank === 1) {
            medal = "🥇";
        } else if (rank === 2) {
            medal = "🥈";
        } else if (rank === 3) {
            medal = "🥉";
        }

        html +=
            "<tr>" +
                "<td>" + medal + "</td>" +
                "<td>" + flagImg(team.countryCode) + " " + team.teamName + "</td>" +
                "<td>" + team.votes + "</td>" +
            "</tr>";

        rank++;
    });

    $("#leaderboardBody").html(html);
}

function loadPredictions() {

    $.get("/api/predictions", function(data) {

        let html = "";

        data.forEach(function(p) {
            html +=
                "<tr>" +
                    "<td>" + p.userName + "</td>" +
                    "<td>" + flagImg(p.countryCode) + " " + p.teamName + "</td>" +
                    "<td>" + p.updatedAt + "</td>" +
                "</tr>";
        });

        $("#predictionsBody").html(html);
    });
}

function loadMyPrediction() {

    $.get("/api/prediction?userId=" + userId, function(data) {

        if (data) {
            $("#myPrediction").html(
                flagImg(data.countryCode) + " " + data.teamName
            );

            $("#teamSelect").val(data.teamId);
        } else {
            $("#myPrediction").text("None");
        }
    });
}

function loadCountries(data) {

    let html = "";

    data.forEach(function(team) {
        html +=
            "<div class='country-item'>" +
                flagImg(team.countryCode) +
                "<span>" + team.teamName + "</span>" +
            "</div>";
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
                $("#myPrediction").html(
                    flagImg(data.countryCode) + " " + data.teamName
                );
            } else {
                $("#myPrediction").text(selectedTeamName);
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

    if (!video || !button) {
        return;
    }

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