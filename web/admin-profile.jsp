<%@ include file="jspf/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="navbar.profile"/></title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
    <link href="css/main-style.css" rel="stylesheet" type="text/css">
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>

</head>
<body>
<!-- Navigation -->
<%@ include file="jspf/navbar.jspf" %>

<div class="text-center text-secondary">
    <h3><fmt:message key="admin.changetime"/></h3>
    <form action="change-working-time" method="post">
        <input type="time" id="time" name="newStartHours" min="00:00" max="23:59" required autofocus>
        <input type="time" id="time1" name="newEndHours" required autofocus>
        <span class="validity"></span>
        <input type="submit" class="btn btn-outline-dark" value="<fmt:message key="button.setworkingtime"/>"/>
    </form>
</div>

<c:if test="${error != null}">
    <div class="alert alert-danger error text-center">
            ${error}
        <c:forEach var="bookedTime" items="${bookedTime}">
            <div class="text-center">
                    ${bookedTime}
            </div>
        </c:forEach>
        <c:forEach var="endWorkingDay" items="${endWorkingDay}">
            <div class="text-center">
                    ${startWorkingDay} - ${endWorkingDay}
            </div>
        </c:forEach>
    </div>
</c:if>
<div class="container" id="wrap">
    <div class="text-center text-secondary">
        <h4><fmt:message key="admin.notdone"/></h4>
    </div>
    <c:forEach var="notDoneAppointments" items="${notDoneAppointments}">
        <div class="list-group-item list-group-item-action list-group-item-warning flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1"><fmt:message key="client.from"/> ${notDoneAppointments.startTime}</h5>
                <h5 class="mb-1"><fmt:message key="client.to"/> ${notDoneAppointments.endTime}</h5>
            </div>
            <div class="d-flex w-100 justify-content-between">
                <p class="mb-1"><fmt:message key="services.mastername"/> ${notDoneAppointments.masterName} </p>
                <p class="mb-1"><fmt:message key="client.service"/> ${notDoneAppointments.productName} </p>
                <p class="mb-1"><fmt:message key="master.clientname"/> ${notDoneAppointments.clientName} </p>
            </div>
            <div class="d-flex w-100 justify-content-between mb-3">
                <small><fmt:message key="services.price"/> ${notDoneAppointments.productPrice} <fmt:message
                        key="services.pricevalue"/></small>
            </div>
            <div class="d-flex w-100 justify-content-between mb-3">
                <small class="mb-1"><fmt:message
                        key="client.paidstatus"/> ${notDoneAppointments.appointmentPaidStatusForAdmin.value}</small>
                <small><fmt:message
                        key="client.donestatus"/> ${notDoneAppointments.appointmentDoneStatusForMaster.value}</small>
            </div>
            <form action="change-time" class="form-inline d-flex w-75 justify-content-between" method="post">
                <label for="datetime"></label>
                <input type="hidden" name="masterId" value="${notDoneAppointments.masterId}">
                <input type="hidden" name="appointmentId" value="${notDoneAppointments.id}">
                <input type="hidden" name="duration" value="${notDoneAppointments.duration}">
                <input type="datetime-local" id="datetime" name="newStartTime" min="${minTimeForPicker}"
                       max="${maxTimeForPicker}" required autofocus>
                <span class="validity"></span>
                <input type="submit" class="btn btn-outline-dark" value="<fmt:message key="button.changetime"/>"/>
            </form>
            <form action="delete-appointment" class="text-center" method="post">
                <input type="hidden" name="appointmentDeleteId" value="${notDoneAppointments.id}">
                <input type="submit" class="btn btn-outline-dark" value="<fmt:message key="button.delete"/>"/>
            </form>
        </div>
    </c:forEach>
    <div class="text-center text-secondary">
        <h4><fmt:message key="admin.notpaidapp"/></h4>
    </div>
    <c:forEach var="notPaidAppointments" items="${notPaidAppointments}">
        <div class="list-group-item list-group-item-action list-group-item-warning flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1"><fmt:message key="client.from"/> ${notPaidAppointments.startTime}</h5>
                <h5 class="mb-1"><fmt:message key="client.to"/> ${notPaidAppointments.endTime}</h5>
            </div>
            <div class="d-flex w-100 justify-content-between">
                <p class="mb-1"><fmt:message key="services.mastername"/> ${notPaidAppointments.masterName} </p>
                <p class="mb-1"><fmt:message key="client.service"/> ${notPaidAppointments.productName} </p>
                <p class="mb-1"><fmt:message key="master.clientname"/> ${notPaidAppointments.clientName} </p>
            </div>
            <div class="d-flex w-100 justify-content-between mb-3">
                <small><fmt:message key="services.price"/> ${notPaidAppointments.productPrice} <fmt:message
                        key="services.pricevalue"/></small>
            </div>
            <div class="d-flex w-100 justify-content-between mb-3">
                <small class="mb-1"><fmt:message
                        key="client.paidstatus"/> ${notPaidAppointments.appointmentPaidStatusForAdmin.value}</small>
                <small><fmt:message
                        key="client.donestatus"/> ${notPaidAppointments.appointmentDoneStatusForMaster.value}</small>
            </div>
            <form action="paid-appointment" class="form-inline d-flex w-100 justify-content-between" method="post">
                <div class="dropdown">
                    <select class="custom-select" id="inputGroupSelect02" name="paidStatus">
                        <option value="not paid"><fmt:message key="admin.notpaid"/></option>
                        <option value="paid"><fmt:message key="admin.paid"/></option>
                    </select>
                </div>
                <input type="hidden" name="appointmentId" value="${notPaidAppointments.id}">
                <input type="submit" class="btn btn-outline-dark" value="<fmt:message key="button.setstatus"/>"/>
            </form>
        </div>
    </c:forEach>

</div>


</body>
</html>
