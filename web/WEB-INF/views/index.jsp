<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <p>Hello, user!</p>

    <table>

        <tr>
            <th>Wherefroms</th>
            <th>Domains</th>
        </tr>
        <tr>
            <td>
                <c:forEach items="${wherefroms}" var="item">
                    ${item.name}<br />
                </c:forEach>
            </td>
            <td>
                <c:forEach items="${domains}" var="item">
                    ${item.name}<br />
                </c:forEach>
            </td>
        </tr>
    </table>

    <br />Money Transfers: <br />
    <table>
        <tr>
            <th>Type</th>
            <th>Amount</th>
            <th>Date</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${moneyTransfers}" var="item">
            <tr>
                <td>${item.type}<br /></td>
                <td>${item.amount}<br /></td>
                <td>${item.date}<br /></td>
                <td>${item.description}<br /></td>
            </tr>
        </c:forEach>
    </table>
    <a href="<c:url value='/money-transfer/add' />"><input type="submit" value="Add money transfer"/></a>
</body>
</html>
