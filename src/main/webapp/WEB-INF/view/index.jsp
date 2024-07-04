<%@page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
    <t:head title='Faros Sandwichbar'/>
    <body id="root">
        <t:menu/>
        <div id="home-page-container" class="home-page-container">
            <p>
                Welcome
                <security:authorize access="isAuthenticated()">
                    <b> <security:authentication property="principal.name" /> </b>
                </security:authorize>
                in our sandwichbar!
            </p>
            <jsp:include flush="true" page="products.jsp"/>
            <div class='sandwichbar-background'></div>
        </div>
    </body>
</html>