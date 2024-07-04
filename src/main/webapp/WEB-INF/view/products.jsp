<%@page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

 <div id="menucard">
     <h1>Menucard</h1>
     <div class="menu-item-container">
         <h2>Sandwiches</h2>
         <ul class="menu-item-items">
             <c:forEach items="${products}" var="product">
                 <p>${product.name}</p>
             </c:forEach>
         </ul>
     </div>
     <div class="menu-item-container">
         <h2>Drinks</h2>
         <ul class="menu-item-items">

         </ul>
     </div>
 </div>
