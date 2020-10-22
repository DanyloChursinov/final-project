<%@ include file="jspf/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${sessionScope.currentLocale != null}">
    <fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
</c:if>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title><fmt:message key="navbar.home"/></title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
    <link href="css/main-styles.css" rel="stylesheet" type="text/css">
    <link href="css/carusel.css" rel="stylesheet" type="text/css">
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>
    <script src="js/bootstrap/bootstrap.min.js"></script>

</head>
<body>
<%@ include file="jspf/navbar.jspf" %>
<section id="reviwes">
    <div class="container">
        <c:forEach var="endWork" items="${endWork}">
        <h2 class="text-center"><fmt:message key="home.open"/> ${startWork} - ${endWork}</h2>
        </c:forEach>
        <h4 class="text-center my-5 pb-5"><fmt:message key="home.clientsays"/></h4>
        <div id="carouselReviews" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="row">

                        <div class="offset-md-1 col-md-5 col-sm-5 text-center">
                            <div class="review">
                                <fmt:message key="home.services"/> <a class="navbar-brand" href="service"><fmt:message key="home.link"/></a>
                                <div class="review-author">
                                    <fmt:message key="home.list"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:forEach var="reviews" items="${reviews}">
                    <div class="carousel-item">
                        <div class="row">
                            <div class="offset-sm-2 col-sm-3 col-md-2"><div class="review-author">
                                <fmt:message key="home.clientname"/> ${reviews.user}
                            </div></div>
                            <div class="offset-md-1 col-md-5 col-sm-5">
                                <div class="review">
                                        ${reviews.message}
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <a class="carousel-control-prev" href="#carouselReviews" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselReviews" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
</section>

<br>
<footer class="py-5 bg-dark footer fixed-bottom">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Beauty Salon 2020</p>
    </div>
</footer>

</body>
</html>