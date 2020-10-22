<%@ include file="jspf/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="navbar.profile"/></title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" />
    <link href="css/main-style.css" rel="stylesheet" type="text/css">
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>
</head>

<body>
<!-- Navigation -->
<%@ include file="jspf/navbar.jspf" %>

<div class="container" id="wrap">
    <div class="list-group" id="list-group">

        <c:forEach var="appointments" items="${appointments}">
            <div class="list-group-item list-group-item-action list-group-item-warning flex-column align-items-start">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1"><fmt:message key="client.from"/> ${appointments.startTime}</h5>
                    <h5 class="mb-1"><fmt:message key="client.to"/> ${appointments.endTime}</h5>
                </div>
                <div class="d-flex w-100 justify-content-between">
                    <p class="mb-1"><fmt:message key="master.clientname"/> ${appointments.clientName} </p>
                    <p class="mb-1"><fmt:message key="client.service"/> ${appointments.productName} </p>
                </div>
                <div class="d-flex w-100 justify-content-between mb-3">
                    <small><fmt:message key="services.price"/> ${appointments.productPrice} <fmt:message key="services.pricevalue"/></small>
                </div>
                <div class="d-flex w-100 justify-content-between mb-3">
                    <small class="mb-1"><fmt:message key="client.paidstatus"/> ${appointments.appointmentPaidStatusForAdmin.value}</small>
                    <small><fmt:message key="client.donestatus"/> ${appointments.appointmentDoneStatusForMaster.value}</small>
                </div>
                    <form action="done-appointment" class="form-inline d-flex w-100 justify-content-between" method="post">
                        <select class="custom-select" id="inputGroupSelect02" name="doneStatus">
                            <option value="Not done"><fmt:message key="master.notdone"/></option>
                            <option value="In progress"><fmt:message key="master.inprogress"/></option>
                            <option value="Done"><fmt:message key="master.done"/></option>
                        </select>
                        <input type="hidden" name="appointmentId" value="${appointments.id}">
                        <input type="submit" class="btn btn-outline-dark" value="<fmt:message key="button.setstatus"/>"/>
                    </form>
            </div>
        </c:forEach>

    </div>
</div>


</body>
</html>
