<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"  session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table style="width: 100%;">
<tr>
<td>

<a href="${pageContext.request.contextPath}/home">Home</a>
 
  | &nbsp;
  
   <a href="${pageContext.request.contextPath}/userList">User Management</a>
  | &nbsp;
   <a href="${pageContext.request.contextPath}/module/">Module Management</a>
<%--    <a href="${pageContext.request.contextPath}/loadJasper">User Report</a> --%>
   

</td><td align="right">

  <c:url value="/j_spring_security_logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
Welcome : ${pageContext.request.userPrincipal.name} | 
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>

</td>
</tr>
</table>
</body>
</html>