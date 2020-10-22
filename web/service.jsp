<%@ include file="jspf/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="navbar.service"/></title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
    <link href="css/main-style.css" rel="stylesheet" type="text/css">
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>

</head>
<body>
<!-- Navigation -->
<%@ include file="jspf/navbar.jspf" %>

<div class="container" id="wrap">
    <form class="form-search" action="search-products">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="inputGroupSelect01"><fmt:message key="services.sortby"/></label>
            </div>
            <select class="custom-select" id="inputGroupSelect02" name="sortBy">
                <option value="sortByMastersA-Z" selected><fmt:message key="services.sortmastersaz"/></option>
                <option value="sortByMastersZ-A"><fmt:message key="services.sortmastersza"/></option>
                <option value="sortByRaitingDescending"><fmt:message key="services.sortraiting"/></option>
            </select>
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="inputGroupSelect01"><fmt:message key="services.searchby"/></label>
            </div>
            <select class="custom-select" id="inputGroupSelect01" name="searchBy">
                <option value="all" selected><fmt:message key="services.searchdefault"/></option>
                <option value="masters"><fmt:message key="services.searchmasters"/></option>
                <option value="services"><fmt:message key="services.searchnames"/></option>
            </select>
        </div>
        <div class="text-center">
            <input type="text" class="form-control search-margin" placeholder="<fmt:message key="services.search"/>"
                   name="searchText" maxlength="20">
            <input type="submit" class="btn btn-outline-dark" value="<fmt:message key="button.searchsort"/>"/>
        </div>
    </form>
    <c:if test="${user.role.value == 'client'}">
        <div class="text-center text-secondary mr-3 ml-3">
            <h5 > <fmt:message key="services.attention"/></h5>
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
            <c:forEach var="endWorkingDay" items="${endWorkingDay}">
                <div class="text-center">
                        ${startWorkingDay} - ${endWorkingDay}
                </div>
            </c:forEach>
        </div>
    </c:if>


    <div class="list-group" id="list-group">
        <c:forEach var="product" items="${services}">
            <div class="list-group-item list-group-item-action list-group-item-warning flex-column align-items-start">
                <div class="d-flex w-100 justify-content-between">
                    <h3 class="mb-1">${product.name}</h3>
                    <h5 class="mb-1"><fmt:message key="services.mastername"/> ${product.master}</h5>
                </div>
                <p class="mb-1"><fmt:message key="services.duration"/> ${product.duration} <fmt:message key="services.minutes"/></p>
                <div class="d-flex w-100 justify-content-between mb-3">
                    <small class="mb-1"><fmt:message key="services.raiting"/> ${product.raiting}</small>
                    <small><fmt:message key="services.price"/> ${product.price} <fmt:message key="services.pricevalue"/></small>
                </div>
                <c:if test="${user.role.value == 'client'}">

                    <form action="add-appointment" method="post">
                        <label for="datetime"></label>
                        <input type="hidden" name="masterId" value="${product.masterId}">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="hidden" name="duration" value="${product.duration}">
                        <input type="datetime-local" id="datetime" name="startTime" min="${minTimeForPicker}"
                               max="${maxTimeForPicker}" required autofocus>
                        <span class="validity"></span>
                        <input type="submit" class="btn btn-outline-dark" value="<fmt:message key="button.booking"/>"/>
                    </form>
                </c:if>
                <c:if test="${user==null}">
                    <a href="signin.jsp" class="btn btn-outline-dark"><fmt:message key="button.signinbooking"/></a>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
