<%@ include file="jspf/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title><fmt:message key="navbar.signup"/></title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" />
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>
    <script src="js/signin/popper.min.js"></script>
    <link href="css/signin/swapcss.css" rel="stylesheet">
    <link rel="stylesheet" href="css/signin/materialdesignicons.min.css">
    <link rel="stylesheet" href="css/signin/login.css">

</head>
<body>
<!-- Navigation -->
<%@ include file="jspf/navbar.jspf" %>

<main>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6 login-section-wrapper">
                <div class="brand-wrapper">
                    <img src="img/signin/logo.jpg">
                </div>
                <div class="login-wrapper my-auto">
                    <h1 class="login-title text-center"><fmt:message key="signup.title"/></h1>
                    <form action="signup" method="post">
                        <div class="form-group">
                            <label for="name"><fmt:message key="signup.name"/></label>
                            <input type="name" name="name" id="name" class="form-control" placeholder="<fmt:message key="signup.inputname"/>" required autofocus>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" name="email" id="email" class="form-control" placeholder="email@example.com" required autofocus>
                        </div>
                        <div class="form-group mb-4">
                            <label for="password"><fmt:message key="signup.password"/></label>
                            <input type="password" name="password" id="password" class="form-control" placeholder="<fmt:message key="signup.inputpassword"/>" required autofocus>
                        </div>
                        <input name="Register" id="Register" class="btn btn-block login-btn" type="submit" value="<fmt:message key="signup.button"/>">
                    </form>
                    <c:if test="${error != null}">
                        <div class="alert alert-danger error">
                                ${error}
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="col-sm-6 px-0 d-none d-sm-block">
                <img src="img/signin/photologin.jpg" alt="login image" class="login-img">
            </div>
        </div>
    </div>
</main>
</body>
</html>
