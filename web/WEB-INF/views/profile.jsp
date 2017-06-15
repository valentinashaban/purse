<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My profile</title>
    <%-- CSS --%>
    <%@include file="components/css.html" %>
</head>
<body>
<%-- Header --%>
<%@include file="components/header.jsp" %>


<%-- Body --%>
<main>
    <div class="container">
        <h3 class="center-align teal-text text-lighten-1">My profile</h3>

        <div class="row">
            <div class="col s6 offset-s3">
                <table class="centered striped">
                    <thead>
                    <tr>
                        <th>Domains</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${domains}" var="item">
                        <tr>
                            <td>${item.name}<br/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <%--<c:forEach items="${wherefroms}" var="item">--%>
                    <%--<tr>--%>
                    <%--<td>${item.name}<br /></td>--%>
                    <%--</tr>--%>
                    <%--</c:forEach>--%>
                </table>
                <br/>
                <br/>
                <table class="centered striped">
                    <thead>
                    <tr>
                        <th>Wherefroms</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${wherefroms}" var="item">
                        <tr>
                            <td>${item.name}<br/></td>
                        </tr>
                    </c:forEach>
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
