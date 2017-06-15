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
            <div class="col s12 m6">
                <a href="${contextPath}/profile/add-domain"
                   class="btn-floating btn-small right waves-effect waves-light red"><i
                        class="material-icons">add</i></a>
                <table class="centered striped">
                    <thead>
                    <tr>
                        <th>Domains</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${domains}" var="item">
                        <tr>
                            <td>
                                <a class="left green-text" href="/profile/edit-domain-${item.id}">Edit</a>
                                    ${item.name}&nbsp;
                                <a class="right red-text" href="/profile/delete-domain-${item.id}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col s12 m6">
                <a href="${contextPath}/profile/add-wherefrom"
                   class="btn-floating btn-small right waves-effect waves-light red"><i
                        class="material-icons">add</i></a>
                <table class="centered striped">
                    <thead>
                    <tr>
                        <th>Wherefroms</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${wherefroms}" var="item">
                        <tr>
                            <td>
                                <a class="left green-text" href="/profile/edit-wherefrom-${item.id}">Edit</a>
                                    ${item.name}
                                <a class="right red-text" href="/profile/delete-wherefrom-${item.id}">Delete</a>
                            </td>
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
