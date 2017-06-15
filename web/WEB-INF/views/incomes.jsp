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

    <%--<c:forEach items="${expenses}" var="item">--%>
    <%--<tr>--%>
    <%--<td>${item.type}<br /></td>--%>
    <%--<td>${item.amount}<br /></td>--%>
    <%--<td>${item.date}<br /></td>--%>
    <%--<td>${item.description}<br /></td>--%>
    <%--</tr>--%>
    <%--</c:forEach>--%>

    <div>
        <div class="row">
            <div class="col m6 s12 offset-m3">
                <table class="striped centered">
                    <thead>
                    <tr>
                        <th>N</th>
                        <th>Amount</th>
                        <th>Date</th>
                        <th>Domain</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>3000</td>
                        <td>2016-12-15</td>
                        <td>Salary</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>1000</td>
                        <td>2016-12-31</td>
                        <td>Premium</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>10000</td>
                        <td>2016-12-31</td>
                        <td>Gift</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>4000</td>
                        <td>2017-01-15</td>
                        <td>Salary</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>3000</td>
                        <td>2017-03-15</td>
                        <td>Salary</td>
                    </tr>
                    <tr>
                        <td>6</td>
                        <td>1500</td>
                        <td>2017-03-08</td>
                        <td>Premium</td>
                    </tr>
                    <tr>
                        <td>7</td>
                        <td>200</td>
                        <td>2017-03-08</td>
                        <td>Gift</td>
                    </tr>
                    <tr>
                        <td>8</td>
                        <td>5000</td>
                        <td>2017-04-15</td>
                        <td>Salary</td>
                    </tr>
                    <tr>
                        <td>9</td>
                        <td>1850</td>
                        <td>2017-06-01</td>
                        <td>Gift</td>
                    </tr>
                    </tbody>
                </table>
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
