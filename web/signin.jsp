<%@ include file="jspf/header.jspf" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>index</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" />
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>
    <link href="css/signin/swapcss.css" rel="stylesheet">
    <link rel="stylesheet" href="css/signin/materialdesignicons.min.css">
    <link rel="stylesheet" href="css/signin/login.css">

</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Beauty Salon</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <c:if test="${user != null}">
                    <li class="nav-item active login-info">
                        <div class="nav-link">Logged in as ${user.name} (${user.role.value})
                            <span class="sr-only">(current)</span>
                        </div>
                    </li>
                </c:if>
                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Services</a>
                </li>
                <c:choose>
                    <c:when test="${user == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="signup.jsp">Sing up</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="signin.jsp">Sing in</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="<ct:profile role='${user.role}'/>">Profile</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Sing out</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item">
                    <a class="nav-link" href="#">Russian</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<main>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6 login-section-wrapper">
                <div class="brand-wrapper">
                    <img src="img/signin/logo.jpg" >
                </div>
                <div class="login-wrapper my-auto ">
                    <h1 class="login-title text-center">Log in</h1>
                    <form action="signin" method="post">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" name="email" id="email" class="form-control" placeholder="email@example.com" required autofocus>
                        </div>
                        <div class="form-group mb-4">
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password" class="form-control" placeholder="enter your passsword" required autofocus>
                        </div>
                        <input name="login" id="login" class="btn btn-block login-btn" type="submit" value="Login">
                    </form>
                    <c:if test="${error != null}">
                        <div class="alert alert-danger error">
                                ${error}
                        </div>
                    </c:if>
                    <p class="login-wrapper-footer-text">Don't have an account? <a href="signup.jsp" class="text-reset">Register here</a></p>
                </div>
            </div>
            <div class="col-sm-6 px-0 d-none d-sm-block">
                <img src="img/signin/photologin.jpg" alt="login image" class="login-img">
            </div>
        </div>
    </div>
</main>
<script src="js/signin/popper.min.js"></script>
</body>
</html>
