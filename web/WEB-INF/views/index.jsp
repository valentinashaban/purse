<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Home Page</title>
    <%-- CSS --%>
    <%@include file="components/css.html" %>
</head>
<body>
<%-- Header --%>
<%@include file="components/header.jsp" %>

<%-- Body --%>
<main>
    <div class="container">
        <h3 class="center-align teal-text text-lighten-1">Home page</h3>
        <div class="row">
            <p class="center-align col s8 push-s2">MyPurse application is created in order to
                store personal
                money transfers and all the operations with cash.
                You are able to add to your page expenses and incomes and view them on diagrams. All money transfers can
                be divided
                by its categories.
            </p>
        </div>
        <div class="row">
            <img class="col s3 push-s5" src="/resources/images/wallet-2.png" alt="wallet"/>
        </div>
    </div>
</main>

<%-- Footer --%>
<%@include file="components/footer.jsp" %>

<%-- JS --%>
<%@include file="components/js.html" %>
</body>
</html>