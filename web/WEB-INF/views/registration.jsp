<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <div class="red-text center">${message}</div>
        <form:form class="col m6 s12 offset-m3" id="reg-form" method="post" modelAttribute="userDto">
            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">account_circle</i>
                    <form:input path="login" id="login" type="text" class="validate" required="true"/>
                    <label for="login">Username</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">email</i>
                    <form:input path="email" id="email" type="email" class="validate" required="true"/>
                    <label for="email">Email</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">lock_outline</i>
                    <form:input path="password" id="password" type="password" class="validate" minlength="6" required="true"/>
                    <label for="password">Password</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">lock_outline</i>
                    <form:input path="repeatPassword" id="r_password" type="password" class="validate" minlength="6" required="true"/>
                    <label for="r_password">Repeat Password</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field">
                    <button class="right btn btn-large btn-register waves-effect waves-light" type="submit" name="action">Register
                        <i class="material-icons right">done</i>
                    </button>
                </div>
            </div>
        </form:form>
    </div>
</div>
</main>

<%-- Footer --%>
<%@include file="components/footer.jsp"%>

<%-- JS --%>
<%@include file="components/js.html"%>

</body>
</html>
