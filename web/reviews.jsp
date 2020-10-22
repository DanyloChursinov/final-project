<%@ include file="jspf/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="navbar.review"/></title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" />
    <link href="css/main-style.css" rel="stylesheet" type="text/css">
    <link href="css/reviewform.css" rel="stylesheet" type="text/css">
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>
    <script src="js/bootstrap/bootstrap.bundle.min.js"></script>

</head>
<body>
<!-- Navigation -->
<%@ include file="jspf/navbar.jspf" %>

<div class="container contact-form">
    <form method="post" action="add-review">
        <h3><fmt:message key="review.please"/></h3>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <input type="text" name="txtName" class="form-control" placeholder="<fmt:message key="review.yourname"/>" value="" required autofocus maxlength="30" />
                </div>
                <div class="form-group">
                    <p class="font-italic">
                        <fmt:message key="review.selectevaluation"/>
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
                        <fmt:message key="review.selectname"/>
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
                    <input type="submit" name="btnSubmit" class="btnContact" value="<fmt:message key="button.send"/>" />
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <textarea name="txtMsg" class="form-control" placeholder="<fmt:message key="review.yourtext"/>" style="width: 100%; height: 150px;" required autofocus maxlength="700"></textarea>
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
        <div class="list-group-item list-group-item-action list-group-item-warning flex-column align-items-start">
            <div class="d-flex justify-content-around">
            <h5 class="mb-1 font-italic"><fmt:message key="reviev.username"/> ${reviews.user}</h5>
            <h5 class="mb-1 font-italic"><fmt:message key="review.mastername"/> ${reviews.master}</h5>
            </div>
            <div class="d-flex justify-content-center">
                <h6 class="mb-1 font-italic"><fmt:message key="review.evaluation"/> ${reviews.evaluation}</h6>
            </div>
            <div class="mx-auto" style="width: 600px;">
            <p class="font-italic">
                 ${reviews.message}
            </p>
            </div>
        </div>
    </c:forEach>
</div>
</div>
</body>
</html>
