<%@ tag description="menu" pageEncoding="UTF-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<div class='header'>
    <nav class="navbar navbar-expand-lg navbar-dark  bg-dark p-2">
        <div class="container-fluid">
            <img class="header-logo" src="${pageContext.request.contextPath}/resources/images/header-logo.png" />
            <h1 class="navbar-brand">
                <a href="/" class="nav-link">
                    Faros Sandwich-bar
                </a>
            </h1>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a href="/" class="nav-link">
                            Menucard
                        </a>
                    </li>
                </ul>
                <span class="navbar-text">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item" id="login-link">
                            <security:authorize access="isAnonymous()">
                                <a href="http://localhost:8080/oauth2/authorization/spring" class="nav-link">Login</a>
                            </security:authorize>
                            <security:authorize access="isAuthenticated()">
                                <a href="http://localhost:8080/logout" class="nav-link sub-menu-link">Logout</a>
                            </security:authorize>
                        </li>
                    </ul>
                </span>
            </div>
        </div>
    </nav>
</div>
