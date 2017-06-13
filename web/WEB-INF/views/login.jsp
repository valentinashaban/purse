<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Login Page</title>
    <%-- CSS --%>
    <%@include file="components/css.html"%>
</head>
<body>
<%-- Header --%>
<%@include file="components/header.jsp"%>

<%-- Body --%>
<main>
    <div class="container">
        <h3 class="center-align teal-text text-lighten-1">Login</h3>

        <div class="row">
            <form name="f" class="col m6 s12 offset-m3" action="/login" method="POST">
                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">account_circle</i>
                        <input id="username" name="username" type="text" class="validate">
                        <label for="username">Username</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">lock_outline</i>
                        <input id="password" name="password" type="password" class="validate">
                        <label for="password">Password</label>
                    </div>
                </div>
                <button class="btn waves-effect waves-light right" type="submit" name="submit">Submit
                    <i class="material-icons right">send</i>
                </button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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