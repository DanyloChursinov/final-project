<%@ include file="jspf/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Service</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
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
<div class="container" id="wrap">
    <h3>Not done appointments</h3>
    <c:forEach var="notDoneAppointment" items="${notDoneAppointments}">
        <div class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
                <h3 class="mb-1">${notDoneAppointment.startTime}</h3>
                <h5 class="mb-1">Master: ${notDoneAppointment.id}</h5>
            </div>
            <p class="mb-1">Service duration: ${notDoneAppointment.appointmentDoneStatusForMaster} in minutes</p>
            <div class="d-flex w-100 justify-content-between mb-3">
                <small class="mb-1">Master raiting: ${notDoneAppointment.clientName}</small>
                <small>Price: ${notDoneAppointment.productPrice} UAN</small>
            </div>
            <form action="change-time" method="post">
                <label for="datetime"></label>
                <input type="hidden" name="masterId" value="${notDoneAppointment.masterId}">
                <input type="hidden" name="appointmentId" value="${notDoneAppointment.id}">
                <input type="hidden" name="duration" value="${notDoneAppointment.duration}">
                <input type="datetime-local" id="datetime" name="newStartTime" min="${minTimeForPicker}"
                       max="${maxTimeForPicker}" required autofocus>
                <span class="validity"></span>
                <input type="submit" class="btn btn-outline-dark" value="Change time"/>
            </form>
            <form action="delete-appointment" method="post">
                <input type="hidden" name="appointmentDeleteId" value="${notDoneAppointment.id}">
                <input type="submit" class="btn btn-outline-dark" value="Delete appointment"/>
            </form>
        </div>
    </c:forEach>
    <h3>Not paid appointments</h3>
    <c:forEach var="notPaidAppointment" items="${notPaidAppointments}">
        <div class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
                <h3 class="mb-1">${notPaidAppointment.startTime}</h3>
                <h5 class="mb-1">Master: ${notPaidAppointment.id}</h5>
            </div>
            <p class="mb-1">Service duration: ${notPaidAppointment.appointmentDoneStatusForMaster} in minutes</p>
            <div class="d-flex w-100 justify-content-between mb-3">
                <small class="mb-1">Master raiting: ${notPaidAppointment.clientName}</small>
                <small>Price: ${notPaidAppointment.productPrice} UAN</small>
            </div>
            <form action="paid-appointment" method="post">
                <div class="dropdown">
                    <select class="custom-select" id="inputGroupSelect02" name="paidStatus">
                        <option value="not_paid">Not paid</option>
                        <option value="paid">Paid</option>
                    </select>
                </div>
                <input type="hidden" name="appointmentId" value="${notPaidAppointment.id}">
                <input type="submit" class="btn btn-outline-dark" value="Set status"/>
            </form>
        </div>
    </c:forEach>

</div>


</body>
</html>
