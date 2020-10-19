<%@ include file="jspf/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Service</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" />
    <link href="css/main-style.css" rel="stylesheet" type="text/css">
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>

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


<div class="container" id="wrap">
<form class="form-search" action="search-products">
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <label class="input-group-text" for="inputGroupSelect01">SortBy</label>
        </div>
        <select class="custom-select" id="inputGroupSelect02" name="sortBy">
            <option value="sortByMastersA-Z" selected>SortByMasters A-Z</option>
            <option value="sortByMastersZ-A">SortByMasters Z-A</option>
            <option value="sortByRaitingDescending">SortByRaiting Descending</option>
            <option value="sortByRaitingAscending">SortByRaiting Ascending</option>
        </select>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <label class="input-group-text" for="inputGroupSelect01">SearchBy</label>
        </div>
        <select class="custom-select" id="inputGroupSelect01" name="searchBy">
            <option value="all" selected>all</option>
            <option value="masters">masters</option>
            <option value="services">services</option>
        </select>
    </div>
    <div class="text-center">
    <input type="text" class="form-control search-margin" placeholder="Search..."
           name="searchText">
        <input type="submit" class="btn btn-outline-dark" value="Serch and (or) Sort"/>
    </div>
</form>
<c:if test="${user.role.value == 'client'}">
    <div class="text-center text-dark">
    <h5> Attention! When you booking a service, please select the value of minutes in '30' or '00'. Example of the correct date: '2020.01.01 11:00' or '2020.01.01 11:00' </h5>
</div>
</c:if>
    <c:if test="${error != null}">
        <div class="alert alert-danger error text-center">
                ${error}
            <c:forEach var="bookedTime" items="${bookedTime}">
                    <div class="text-center">
                        ${bookedTime}
                    </div>
            </c:forEach>
        </div>
    </c:if>


    <div class="list-group" id="list-group">
        <c:forEach var="product" items="${services}">
            <div class="list-group-item list-group-item-action flex-column align-items-start">
                <div class="d-flex w-100 justify-content-between">
                    <h3 class="mb-1">${product.name}</h3>
                    <h5 class="mb-1">Master: ${product.master}</h5>
                </div>
                <p class="mb-1">Service duration: ${product.duration} in minutes</p>
                <div class="d-flex w-100 justify-content-between mb-3">
                    <small class="mb-1">Master raiting: ${product.raiting}</small>
                    <small>Price: ${product.price} UAN</small>
                </div>
                <c:if test="${user.role.value == 'client'}">

                    <form action="add-appointment" method="post">
                        <label for="datetime"></label>
                        <input type="hidden" name="masterId" value="${product.masterId}">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="hidden" name="duration" value="${product.duration}">
                        <input type="datetime-local" id="datetime" name="startTime"  min="${minTimeForPicker}" max="${maxTimeForPicker}" required autofocus >
                        <span class="validity"></span>
                        <input type="submit" class="btn btn-outline-dark" value="Booking"/>
                    </form>
                </c:if>
                <c:if test="${user==null}">
                    <a href="signin.jsp" class="btn btn-outline-dark">Sing in for booking</a>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
