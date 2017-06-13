<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Expenses</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%-- CSS --%>
    <%@include file="components/css.html"%>
</head>
<body>
<%-- Header --%>
<%@include file="components/header.jsp"%>

<main>
    <h3 class="center-align teal-text text-lighten-1">Expenses</h3>

    <c:forEach items="${expenses}" var="item">
        <tr>
            <td>${item.type}<br /></td>
            <td>${item.amount}<br /></td>
            <td>${item.date}<br /></td>
            <td>${item.description}<br /></td>
        </tr>
    </c:forEach>
</main>

<%-- Footer --%>
<%@include file="components/footer.jsp"%>

<%-- JS --%>
<%@include file="components/js.html"%>
</body>
</html>
