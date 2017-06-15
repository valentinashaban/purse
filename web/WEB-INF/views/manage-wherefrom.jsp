<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Manage Wherefrom</title>
    <%-- CSS --%>
    <%@include file="components/css.html" %>
</head>
<body>
<%-- Header --%>
<%@include file="components/header.jsp" %>

<%-- Body --%>
<main>
    <div class="container">
        <h3 class="center-align teal-text text-lighten-1">Manage Wherefrom</h3>

        <div class="row">
            <form:form method="post" modelAttribute="wherefrom">
                <div class="input-field col m6 offset-m3 s12">
                    <label for="name">Wherefrom</label>
                    <form:input path="name" id="name" type="text" class="validate"/>
                </div>
                <div class="col m6 offset-m3 s12">
                    <button class="btn waves-effect waves-light left" type="submit" name="submit">Manage Domain
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</main>

<%-- Footer --%>
<%@include file="components/footer.jsp" %>

<%-- JS --%>
<%@include file="components/js.html" %>
</body>
</html>
