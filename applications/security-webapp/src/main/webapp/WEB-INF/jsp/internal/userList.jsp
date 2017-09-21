<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
<%-- <jsp:include page="../includes/styles.jsp" /> --%>

<!-- 	<script src="/resources/js/jquery.1.10.2.min.js"></script> -->
<!--     <script src="/resources/bootstrap/js/bootstrap.min.js"></script> -->
<title>${title}</title>

<style type="text/css">
.PADDER-CENTER {
    margin-left: auto;
    margin-right: auto;
}
</style>

</head>
<body>
<%--     <jsp:include page="../includes/menu.jsp" /> --%>

<h1>User List</h1>  


<div class="panel panel-default col-xs-9 PADDER-CENTER">
<!-- <div class="panel-heading">User List</div> -->
  <div class="panel-body">

<table border="2" width="70%" cellpadding="2" align="center" class="table table-striped">  
<tr><th>Id</th><th>Username</th><th>status</th><th>question</th><th>answer</th><th>Edit</th><th>Change pass</th><th>Delete</th><th>Roles</th></tr>  
   <c:forEach var="user" items="${list}">   
   <tr>  
   <td>${user.id}</td>  
   <td>${user.userName}</td>  
   <td>${user.status}</td>  
   <td>${user.question}</td>
   <td>${user.answer}</td>
   <td><a href="editUser/${user.id}">Edit</a></td> 
   <td><a href="resetPasswordForm/${user.id}">Change</a></td>
   <td><a href="deleteUser/${user.id}">Delete</a></td>
   <td><a href="assigneRoleUser/${user.id}">Assign Roles</a></td>  
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
   <sec:authorize access="hasRole('ROLE_ADMIN')">
   <a href="userForm">Add New User</a> 
   <a href="resetPasswordForm">Change Password</a>
   </sec:authorize>
   <br>Custom Export in : 
   <a href="reportUserList/pdf">pdf</a>
   <a href="reportUserListXls">xlsx</a>
   
</div></div>
   
   <br/><br/><br/>
   
   
<div class="panel panel-default col-xs-9">
<!-- <div class="panel-heading">User List Display tag</div> -->
  <div class="panel-body">   
   
   <spring:url value="userList" var="listURL"/>
   <display:table name="list" sort="list" requestURI="${listURL}" export="true" pagesize="10" defaultsort="1" defaultorder="descending" class="table table-striped">
      <display:column property="id" title="ID" sortable="true"/>
      <display:column property="userName" sortable="true"/>
      <display:column property="status" sortable="true"/>
      <display:column property="question"/>
      <display:column property="answer"/>
      <display:setProperty name="export.pdf" value="true" />
      <display:setProperty name="export.xml" value="false" />
      <display:setProperty name="export.pdf.filename" value="userList.pdf"/>
		<display:setProperty name="export.excel.filename" value="userList.xls"/>
		<display:setProperty name="export.csv.filename" value="userList.csv"/>
    </display:table>
    
</div></div>
    
</body>
</html>