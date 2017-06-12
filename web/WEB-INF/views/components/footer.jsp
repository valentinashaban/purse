<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<footer class="page-footer">
    <div class="container">
        <div class="row">
            <div class="col m5 s12">
                <h5 class="white-text">MyPurse</h5>
                <p class="grey-text text-lighten-4">
                    Application created to store your financial transfers. Don't keep everything in mind. Use purse app.
                </p>
            </div>
            <div class="col m3 offset-m1 s6">
                <h5 class="white-text">Info</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="${contextPath}/">Home</a></li>
                    <li><a class="grey-text text-lighten-3" href="${contextPath}/#">Link 1</a></li>
                    <li><a class="grey-text text-lighten-3" href="${contextPath}/#">Link 2</a></li>
                </ul>
            </div>
            <div class="col m3 s6">
                <h5 class="white-text">Menu</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="${contextPath}/#">Link 3</a></li>
                    <c:choose>
                        <c:when test="${not empty user}">
                            <li><a href="${contextPath}/profile" class="grey-text text-lighten-3">Profile</a></li>
                            <li><a href="${contextPath}/login?logout" class="grey-text text-lighten-3">Log Out</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${contextPath}/registration" class="grey-text text-lighten-3">Registration</a></li>
                            <li><a href="${contextPath}/login" class="grey-text text-lighten-3">Log In</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            &copy; ${year} Purse App
        </div>
    </div>
</footer>