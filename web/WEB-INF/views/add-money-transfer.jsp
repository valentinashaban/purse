<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add new Money Transfer</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/resources/js/functions.js"></script>
</head>
<body>
<div>${name}</div>
<form:form method="post" modelAttribute="moneyTransfer">
    <form:label path="type">
        Transfer type: <form:select id="transfer-type" path="type" items="${transferTypes}"/>
    </form:label>
    <br/>
    <form:label path="date">
        Date: <form:input path="date" type="text" />
    </form:label>
    <br/>
    <form:label path="amount">
        Amount: <form:input path="amount" type="text"/>
    </form:label>
    <br/>

    <form:label id="wherefrom-block" path="wherefrom">
        Wherefrom: <form:select path="wherefrom" items="${wherefroms}" itemLabel="name" itemValue="id"/>
    </form:label>
    <br/>

    <form:label id="domain-block" path="domain">
        Domain: <form:select path="domain" items="${domains}" itemLabel="name" itemValue="id" />
    </form:label>
    <br/>

    <form:label path="description">
        Description:<br/>
        <form:textarea path="description" cols="60" rows="10"/>
    </form:label>
    <br/>
    <form:button type="submit">Submit</form:button>
</form:form>
</body>
</html>
