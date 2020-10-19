<%@ include file="jspf/header.jspf" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>index</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" />

    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>


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
                    <a class="nav-link" href="service">Services</a>
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
                            <a class="nav-link" href="signout">Sing out</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item">
                    <a class="nav-link" href="review">Reviews</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Russian</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

</body>
</html>