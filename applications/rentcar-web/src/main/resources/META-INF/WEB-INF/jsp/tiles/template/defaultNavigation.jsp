<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<ol class="breadcrumb">
<c:forEach items="${links}" var="item">
<c:choose>
  <c:when test="${item.active}">
    <li class="active">${item.name}</li>
  </c:when>
  <c:otherwise>
    <li><a href="${pageContext.request.contextPath}${item.path}">${item.name}</a></li>
  </c:otherwise>
</c:choose>
</c:forEach>
</ol>

<!-- <ol class="breadcrumb"> -->
<%-- 	<li class="active"><h1>${tittle}</h1></li> --%>
<!-- </ol> -->