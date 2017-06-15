<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Expenses</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%-- CSS --%>
    <%@include file="components/css.html" %>
</head>
<body>
<%-- Header --%>
<%@include file="components/header.jsp" %>

<main>
    <h3 class="center-align teal-text text-lighten-1">Expenses</h3>

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
                        <td>50</td>
                        <td>2015-12-17</td>
                        <td>Food</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>150</td>
                        <td>2015-12-05</td>
                        <td>Transport</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>2500</td>
                        <td>2016-03-05</td>
                        <td>Apartment</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>12.50</td>
                        <td>2017-02-16</td>
                        <td>Shopping</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>96</td>
                        <td>2002-09-01</td>
                        <td>Food</td>
                    </tr>
                    <tr>
                        <td>6</td>
                        <td>30</td>
                        <td>2005-06-26</td>
                        <td>Transport</td>
                    </tr>
                    <tr>
                        <td>7</td>
                        <td>666</td>
                        <td>2017-03-13</td>
                        <td>Apartment</td>
                    </tr>
                    <tr>
                        <td>8</td>
                        <td>800</td>
                        <td>1994-06-10</td>
                        <td>Travels</td>
                    </tr>
                    <tr>
                        <td>9</td>
                        <td>1500</td>
                        <td>2017-06-01</td>
                        <td>Shopping</td>
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
