<%@ page import="com.common.EntidadRolAtributo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>Insert title here</title>

<style type="text/css">
/* .navbar{ */
/*     box-shadow: 0 0 2rem 0 rgb(41 48 66 / 10%); */
/*     } */
</style>
</head>

<body>








<nav class="navbar navbar-expand-lg navbar-light navbar-bg">
  <div class="container-fluid">
  	
    <a class="navbar-brand" href="${pageContext.request.contextPath}/"><i class="bi bi-windows"></i></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <c:forEach var="permission" items="${sessionScope.permissions}">
      		<li class="nav-item"><a class="nav-link"  href="${pageContext.request.contextPath}${permission.path}">${permission.description}</a></li>
        </c:forEach>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Buscar</button>
      </form>
      
      
      <c:url value="/j_spring_security_logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<c:if test="${pageContext.request.userPrincipal.name != null}">
 <ul class="navbar-nav d-flex mb-2 mb-lg-0">
      <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
             
			
			<div class="container">
			  <div class="row">
			    <div class="col">
			      <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
					  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
					  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
					</svg>
			    </div>
			    <div class="col">
			      <div class="row">
			        <div class="col">
			          <span>${pageContext.request.userPrincipal.name}</span>
			        </div>
			      </div>
			      <div class="row">
			        <div class="col">
			          <span>
			          	<c:forEach var="roles" items="${sessionScope.user.roles}">
				      		${roles.description}
				        </c:forEach>
			          </span>
			        </div>
			      </div>
			    </div>
			  </div>
			</div>
          </a>
          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
            
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile"><i class="bi bi-person-circle"></i> Mi Cuenta</a></li>
<!--             <li><a class="dropdown-item" href="#">Ajustes</a></li> -->
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="javascript:document.getElementById('logout').submit()" title="Cerrar sesión" data-bs-toggle="tooltip" data-bs-placement="bottom"><i class="bi bi-power"></i> Cerrar sesión </a></li>
            
          </ul>
        </li>
</ul>


</c:if>
      
      
    </div>
  </div>
</nav>



</body>
</html>