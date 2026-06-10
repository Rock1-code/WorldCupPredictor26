<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>World Cup Predictor 2026</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<body class="login-page-new">

<div class="login-shell">

    <div class="hero-image-section">

        <img src="/images/login-hero.jpg"
             alt="FIFA World Cup 2026"
             class="login-hero-image">

        <div class="hero-text">
            <h1>FIFA WORLD CUP</h1>
            <h2>2026 Prediction</h2>

            <p class="host-text">
                UNITED STATES • CANADA • MEXICO
            </p>


            <p class="work-text">
                🏢 Register or login using your email address to submit your FIFA World Cup 2026 prediction.
            </p>

            <p class="thanks-text">
                Thank you for being part of our team!
            </p>
        </div>

    </div>

    <div class="form-box">

        <div id="loginSection" class="form-column">

            <h3>Login</h3>

            <input class="form-control mb-3"
       id="loginEmail"
       type="email"
       placeholder="Email Address">

            <input class="form-control mb-3"
                   id="loginPassword"
                   type="password"
                   placeholder="Password">

            <button class="btn btn-success w-100 mb-2"
                    onclick="login()">
                Login
            </button>

            <button class="btn btn-link w-100"
                    onclick="showRegister()">
                Create New Account
            </button>

        </div>

        <div id="registerSection"
             class="form-column"
             style="display:none;">

            <h3>Create Account</h3>

            <input class="form-control mb-3"
                   id="regName"
                   type="text"
                   placeholder="Full Name">

            <input class="form-control mb-3"
       id="regEmail"
       type="email"
       placeholder="Email Address">

            <input class="form-control mb-3"
                   id="regPassword"
                   type="password"
                   placeholder="Password">

            <button class="btn btn-success w-100 mb-2"
                    onclick="register()">
                Register
            </button>

            <button class="btn btn-link w-100"
                    onclick="showLogin()">
                Already have an account? Login
            </button>

        </div>

        <p id="message" class="text-center mt-3"></p>

    </div>

    <footer class="footer">
        © 2026 Rocky. All rights reserved.
    </footer>

</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="/js/login.js"></script>

</body>
</html>