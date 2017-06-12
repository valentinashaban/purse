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
        <h1>Add money transfer</h1>

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
    </div>
</main>

<%-- Footer --%>
<%@include file="components/footer.jsp"%>

<%-- JS --%>
<%@include file="components/js.html"%>
</body>
</html>
