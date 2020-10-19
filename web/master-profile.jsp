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
    <div class="list-group" id="list-group">

        <c:forEach var="appointment" items="${appointments}">
            <div class="list-group-item list-group-item-action flex-column align-items-start">
                <div class="d-flex w-100 justify-content-between">
                    <h3 class="mb-1">${appointment.startTime}</h3>
                    <h5 class="mb-1">Master: ${appointment.id}</h5>
                </div>
                <p class="mb-1">Service duration: ${appointment.appointmentDoneStatusForMaster} in minutes</p>
                <div class="d-flex w-100 justify-content-between mb-3">
                    <small class="mb-1">Master raiting: ${appointment.clientName}</small>
                    <small>Price: ${appointment.productPrice} UAN</small>
                </div>
                <form action="done-appointment" method="post">
                    <div class="dropdown">
                        <select class="custom-select" id="inputGroupSelect02" name="doneStatus">
                            <option value="not_done">Not done</option>
                            <option value="in_progress">In progress</option>
                            <option value="done">Done</option>
                        </select>
                    </div>
                    <input type="hidden" name="appointmentId" value="${appointment.id}">
                    <input type="submit" class="btn btn-outline-dark" value="Set status"/>
                </form>
            </div>
        </c:forEach>

    </div>
</div>


</body>
</html>
