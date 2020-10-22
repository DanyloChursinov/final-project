<%@ include file="jspf/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title><fmt:message key="error.page"/></title>

    <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/main-style.css" rel="stylesheet" type="text/css">
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>

</head>

<body>
<!-- Navigation -->
<%@ include file="jspf/navbar.jspf" %>

<div class="container-fluid text-center">
<img src="img/error/error-oops.jpg" class="img-fluid" width="582" >
</div>
<div class="container text-right">
    <p class="alert alert-danger error font-weight-bold text-center">${error}</p>
    <br><br>
    <button type="button" class="btn btn-outline-dark"><fmt:message key="error.back"/></button>
</div>

<br>
<footer class="py-5 bg-dark footer">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Beauty Salon 2020</p>
    </div>
</footer>
</body>
</html>
