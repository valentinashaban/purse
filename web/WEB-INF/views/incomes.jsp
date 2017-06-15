<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Incomes</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%-- CSS --%>
    <%@include file="components/css.html" %>
</head>
<body>
<%-- Header --%>
<%@include file="components/header.jsp" %>

<main>
    <h3 class="center-align teal-text text-lighten-1">Incomes</h3>

    <div>
        <div class="row">
            <div class="col m6 s12 offset-m3">
                <c:choose>
                    <c:when test="${not empty expenses}">
                        <table class="striped centered">
                            <thead>
                            <tr>
                                <th>Type</th>
                                <th>Amount</th>
                                <th>Date</th>
                                <th>Domain</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${expenses}" var="item">
                                <tr>
                                    <td>${item.type}</td>
                                    <td>${item.amount} $</td>
                                    <td>${item.date}</td>
                                    <td>${item.description}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <h5 class="center-align cyan-text text-accent-4">
                            Whoops... You don't have incomes =(
                        </h5>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </div>
</main>

<%-- Footer --%>
<%@include file="components/footer.jsp" %>

<%-- JS --%>
<%@include file="components/js.html" %>
</body>
</html>
