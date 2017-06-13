<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%-- CSS --%>
    <%@include file="components/css.html"%>
</head>
<body>
<%-- Header --%>
<%@include file="components/header.jsp"%>

<main>
<div class="container">
    <h3 class="center-align teal-text text-lighten-1">Registration</h3>
    <div class="row ">
        <form class="col m6 s12 offset-m3" id="reg-form">
            <div class="row">
                <div class="input-field col s6">
                    <input id="first_name" type="text" class="validate" required>
                    <label for="first_name">First Name</label>
                </div>
                <div class="input-field col s6">
                    <input id="last_name" type="text" class="validate" required>
                    <label for="last_name">Last Name</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="email" type="email" class="validate" required>
                    <label for="email">Email</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="password" type="password" class="validate" minlength="6" required>
                    <label for="password">Password</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <button class="btn btn-large btn-register waves-effect waves-light" type="submit" name="action">Register
                        <i class="material-icons right">done</i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</main>

<%-- Footer --%>
<%@include file="components/footer.jsp"%>

<%-- JS --%>
<%@include file="components/js.html"%>

</body>
</html>
