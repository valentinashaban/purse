<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Add new Money Transfer</title>
    <%-- CSS --%>
    <%@include file="components/css.html"%>
</head>
<body>
<%-- Header --%>
<%@include file="components/header.jsp"%>

<%-- Body --%>
<main>
    <div class="container">
        <h3 class="center-align teal-text text-lighten-1">Add money transfer</h3>

        <div class="row">
            <form:form class="col s12" method="post" modelAttribute="moneyTransfer">
                <div class="row">
                    <div class="input-field col m6 s12">
                        <form:select id="transfer-type" path="type" items="${transferTypes}" class="validate"/>
                        <form:label for="transfer-type" path="type">Transfer type</form:label>
                    </div>
                    <div class="input-field col m6 s12">
                        <label for="date">Date</label>
                        <form:input path="date" id="date" type="date" class="datepicker"/>
                    </div>
                    <div class="input-field col m6 s12">
                        <label for="amount">Amount</label>
                        <form:input path="amount" id="amount" type="text" class="validate"/>
                    </div>
                    <div class="input-field col m6 s12" id="wherefrom-block">
                        <form:select path="wherefrom" id="wherefrom" items="${wherefroms}" class="validate" itemLabel="name" itemValue="id"/>
                        <form:label for="wherefrom" path="wherefrom">Wherefrom</form:label>
                    </div>
                    <div class="input-field col m6 s12" id="domain-block">
                        <form:select path="domain" id="domain" items="${domains}" class="validate" itemLabel="name" itemValue="id"/>
                        <form:label for="domain" path="domain">Domain</form:label>
                    </div>
                    <div class="input-field col s12">
                        <form:label for="description" path="description">Description</form:label>
                        <form:textarea path="description" id="description" class="materialize-textarea" cols="60" rows="10"/>
                    </div>
                    <button class="btn waves-effect waves-light right" type="submit" name="submit">Add money transfer
                        <i class="material-icons right">send</i>
                    </button>
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
