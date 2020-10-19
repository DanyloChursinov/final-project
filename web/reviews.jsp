<%@ include file="jspf/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Service</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" />
    <link href="css/main-style.css" rel="stylesheet" type="text/css">
    <link href="css/reviewform.css" rel="stylesheet" type="text/css">
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>
    <script src="js/bootstrap/bootstrap.bundle.min.js"></script>

</head>
<body>
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
<div class="container contact-form">
    <form method="post" action="add-review">
        <h3>Please leave a review</h3>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <input type="text" name="txtName" class="form-control" placeholder="Your Name *" value="" required autofocus />
                </div>
                <div class="form-group">
                    <p class="font-italic">
                        Select evaluation
                    </p>
                    <div class="dropdown">
                        <select class="custom-select" id="inputGroupSelect02" name="evaluation">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5" selected>5</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <p class="font-italic">
                        Select master name
                    </p>
                    <div class="dropdown">
                        <select class="custom-select" id="inputGroupSelect01" name="masters">
                            <c:forEach var="masters" items="${masters}">
                                <option value="${masters}">${masters}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group text-center">
                    <input type="submit" name="btnSubmit" class="btnContact" value="Send Message" />
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <textarea name="txtMsg" class="form-control" placeholder="Your Message *" style="width: 100%; height: 150px;" required autofocus></textarea>
                </div>
                <c:if test="${error != null}">
                    <div class="alert alert-danger error">
                            ${error}
                    </div>
                </c:if>
            </div>
        </div>
    </form>

</div>
<div class="container" id="wrap">
<div class="list-group" id="list-group">
    <c:forEach var="reviews" items="${reviews}">
        <div class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex justify-content-around">
            <h5 class="mb-1 font-italic">User name: ${reviews.user}</h5>
            <h5 class="mb-1 font-italic">Master name: ${reviews.master}</h5>
            </div>
            <div class="d-flex justify-content-center">
                <h6 class="mb-1 font-italic">Evaluation: ${reviews.evaluation}</h6>
            </div>
            <div class="mx-auto" style="width: 600px;">
            <p class="font-italic">
                 Review: ${reviews.message}
            </p>
            </div>
        </div>
    </c:forEach>
</div>
</div>
</body>
</html>
