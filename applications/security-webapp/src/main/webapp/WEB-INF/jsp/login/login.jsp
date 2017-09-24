<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="false"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../includes/styles.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login page</title>
<style type="text/css">
.EXTENDER {
    position: absolute;
    top: 0px;
    left: 0px;
    bottom: 0px;
    right: 0px;
    width: 100%;
    height: 100%;
    overflow-y: hidden;
    overflow-x: hidden;
    
}

.PADDER-CENTER {
    width: 100%;
    height: 100%;
    display: -webkit-box;
    display: -moz-box;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
    -webkit-box-pack: center;
    -moz-box-pack: center;
    -ms-flex-pack: center;
    -webkit-justify-content: center;
    justify-content: center;
    -webkit-box-align: center;
    -moz-box-align: center;
    -ms-flex-align: center;
    -webkit-align-items: center;
    align-items: center;
}
</style>
</head>
<body onload='document.loginForm.userName.focus();' >


<div class="EXTENDER">
  <div class="PADDER-CENTER">

<table style="width: 320px;" align="center" border="0">
<tr>
<td>
<div class="jumbotron">


<form name='loginForm' action='<c:url value="j_spring_security_check"/>' method='POST' class="form-horizontal">
	<table align="center" border="0">
		<tbody>
		<tr>
			<td align="center"><h2 class="bd-title" id="content">Sign-On</h2></td>
		</tr>
		<tr>
			<td><c:if test="${not empty error}">
						<div class="alert alert-danger" role="alert">${error}</div>
					</c:if>
					<c:if test="${not empty message}">
						<div class="alert alert-info" role="alert">${message}</div>
					</c:if></td>
		</tr>
		<tr>
			<td><div class="input-group input-sm">
						<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
						<input type="text" class="form-control" id="userName" name="userName" placeholder="Enter Username" required>
					</div></td>
		</tr>
		<tr>
			<td><div class="input-group input-sm">
						<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
						<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
					</div>
		  <input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /></td>
		</tr>
		<tr>
			<td><div class="form-actions">
						<input type="submit"
							class="btn btn-block btn-primary btn-default" value="Log in">
					</div></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		</tbody>
	</table>
</form>



</div>

</td>
</tr>
</table>
			
  </div>
</div>
	
</body>
</html>