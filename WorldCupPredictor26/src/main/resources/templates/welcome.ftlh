<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Loading...</title>

    <link rel="stylesheet" href="/css/styles.css">
</head>

<body class="loading-page">

    <div class="loading-container">

        <video id="loadingVideo"
               class="loading-video"
               autoplay
               muted
               loop
               playsinline>

            <source src="/videos/loading-worldcup.mp4"
                    type="video/mp4">

        </video>

        <h1 id="welcomeName">Welcome</h1>

        <p class="loading-message">
    ⚽ Loading Prediction Board...
</p>

<p class="loading-subtext">
    Please wait while we prepare the FIFA World Cup 2026 prediction experience.
</p>

        <div class="loader"></div>

        <div class="loading-footer">
            © 2026 Rocky
        </div>

    </div>

    <script>

        let userName = localStorage.getItem("userName");

        document.getElementById("welcomeName").innerHTML =
            "Welcome, " + userName;

        let video = document.getElementById("loadingVideo");

        video.playbackRate = 1.70;

        setTimeout(function() {
            window.location.href = "/predict";
        }, 7000);

    </script>

</body>
</html>