<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<header>
    <nav class="top-nav">
        <div class="container">
            <div class="nav-wrapper">
                <a href="${contextPath}/" class="brand-logo right"><i class="large material-icons">trending_up</i></a>
                <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                <ul class="left hide-on-med-and-down">
                    <li><a href="${contextPath}/">Home</a></li>

                    <sec:authorize access="isAnonymous()">
                        <li><a href="${contextPath}/registration">Registration</a></li>
                        <li><a href="${contextPath}/login">Log In</a></li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li><a href="${contextPath}/expenses">Expenses</a></li>
                        <li><a href="${contextPath}/incomes">Incomes</a></li>
                        <li><a href="${contextPath}/money-transfer">Add money transfer</a></li>
                        <li><a href="${contextPath}/profile">Profile</a></li>
                        <li><a href="${contextPath}/login?logout">Log Out</a></li>
                    </sec:authorize>
                </ul>
                <ul class="side-nav" id="mobile-demo">
                    <li><a href="${contextPath}/">Home</a></li>

                    <sec:authorize access="isAnonymous()">
                        <li><a href="${contextPath}/registration">Registration</a></li>
                        <li><a href="${contextPath}/login">Log In</a></li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li><a href="${contextPath}/expenses">Expenses</a></li>
                        <li><a href="${contextPath}/incomes">Incomes</a></li>
                        <li><a href="${contextPath}/money-transfer">Add money transfer</a></li>
                        <li><a href="${contextPath}/profile">Profile</a></li>
                        <li><a href="${contextPath}/login?logout">Log Out</a></li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
</header>