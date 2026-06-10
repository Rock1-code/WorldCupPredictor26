<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>World Cup Predictor 2026</title>

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
            <button class="btn btn-danger" onclick="logout()">Logout</button>
        </div>
    </div>

    <div class="hero-video-banner">
        <video id="wcVideo" autoplay muted loop playsinline poster="/images/worldcup-hero.jpg">
            <source src="/videos/worldcup-hero.mp4" type="video/mp4">
        </video>

        <div class="video-overlay">
           
            <p class="ivy-message">
                🌎 Make your prediction before kickoff and see how others votes.
            </p>

            <button id="soundButton"
                    class="btn btn-warning btn-sm"
                    onclick="toggleSound()">
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

                    <button class="btn btn-success" onclick="savePrediction()">Save Prediction</button>

                    <p class="mt-4">
                        <strong>Your Current Prediction:</strong>
                        <span id="myPrediction">None</span>
                    </p>
                </div>
            </div>

            <div class="col-md-6">
                <div class="hero-card">
                    <img src="/images/worldcup-hero.jpg"
                         alt="FIFA World Cup 2026"
                         class="hero-image">

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
                            data-bs-target="#leaderboardCollapse"
                            aria-expanded="false">
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

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <script src="/js/predict.js"></script>
    
<footer class="footer">
    © 2026 Rocky. All rights reserved.
</footer>
</body>
</html>