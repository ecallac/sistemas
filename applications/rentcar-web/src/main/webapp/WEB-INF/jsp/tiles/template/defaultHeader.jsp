<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"  session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>Insert title here</title>

<style type="text/css">
.navbar-inverse {
  background-color: #337ab7;
  border-color: #286090;
}
.navbar-inverse .navbar-brand {
  color: #CDDAE7;
}
.navbar-inverse .navbar-brand:hover, .navbar-inverse .navbar-brand:focus {
  color: #ffffff;
}
.navbar-inverse .navbar-text {
  color: #CDDAE7;
}
.navbar-inverse .navbar-nav > li > a {
  color: #CDDAE7;
}
.navbar-inverse .navbar-nav > li > a:hover, .navbar-inverse .navbar-nav > li > a:focus {
  color: #ffffff;
}

.navbar-inverse .navbar-nav > .active > a, .navbar-inverse .navbar-nav > .active > a:hover, .navbar-inverse .navbar-nav > .active > a:focus {
  color: #ffffff;
  background-color: #286090;
}
.navbar-inverse .navbar-nav > .open > a, .navbar-inverse .navbar-nav > .open > a:hover, .navbar-inverse .navbar-nav > .open > a:focus {
  color: #ffffff;
  background-color: #286090;
}
.navbar-inverse .navbar-toggle {
  border-color: #286090;
}
.navbar-inverse .navbar-toggle:hover, .navbar-inverse .navbar-toggle:focus {
  background-color: #286090;
}
.navbar-inverse .navbar-toggle .icon-bar {
  background-color: #CDDAE7;
}
.navbar-inverse .navbar-collapse,
.navbar-inverse .navbar-form {
  border-color: #CDDAE7;
}
.navbar-inverse .navbar-link {
  color: #CDDAE7;
}
.navbar-inverse .navbar-link:hover {
  color: #ffffff;
}

@media (max-width: 767px) {
  .navbar-inverse .navbar-nav .open .dropdown-menu > li > a {
    color: #CDDAE7;
  }
  .navbar-inverse .navbar-nav .open .dropdown-menu > li > a:hover, .navbar-inverse .navbar-nav .open .dropdown-menu > li > a:focus {
    color: #ffffff;
  }
  .navbar-inverse .navbar-nav .open .dropdown-menu > .active > a, .navbar-inverse .navbar-nav .open .dropdown-menu > .active > a:hover, .navbar-inverse .navbar-nav .open .dropdown-menu > .active > a:focus {
    color: #ffffff;
    background-color: #286090;
  }
}
</style>

</head>
<body>

<c:url value="/j_spring_security_logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/">Home</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Mantenimiento <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/marca">Marca</a></li>
            <li><a href="${pageContext.request.contextPath}/modelo">Modelo</a></li>
            <li><a href="${pageContext.request.contextPath}/clase">Clase</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath}/vehiculo">Vehiculo</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath}/conductor">Conductor</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Configuracion <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/categoria">Categoria</a></li>
            <li><a href="${pageContext.request.contextPath}/descripsion">Descripsion</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath}/tipo">Tipo</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Procesos <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/alquiler">Alquiler</a></li>
            <li><a href="${pageContext.request.contextPath}/operacion">Operacion Diaria</a></li>
            <li><a href="${pageContext.request.contextPath}/cierre">Cierre</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reportes <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/lista">Lista</a></li>
          </ul>
        </li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
		<li>
		<form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
      </form>
      </li>

<c:if test="${pageContext.request.userPrincipal.name != null}">
<li><p class="navbar-text">Welcome : ${pageContext.request.userPrincipal.name} | <a href="javascript:document.getElementById('logout').submit()" title="Logout" class="toltip"><img src="<c:url value='/resources/static/img/icons/white/on-off_icon&16.png' />"> </a></p></li>

</c:if>

      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>




</body>
</html>