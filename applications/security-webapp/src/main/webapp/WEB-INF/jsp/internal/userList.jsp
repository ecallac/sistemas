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


<!--     <script src="/resources/bootstrap/js/bootstrap.min.js"></script> -->
<title>${title}</title>

<script type="text/javascript">
	$('#AllChangePassword').on('shown.bs.modal', function () {
	  
	})
	$('#AddUser').on('shown.bs.modal', function () {
	  
	})
	
	$(document).ready(function() {
	    $('#example').DataTable();
	} );
</script>

<style type="text/css">
.PADDER-CENTER {
    margin-left: auto;
    margin-right: auto;
}
.table-striped tr:hover{background-color:#f5f5f5}
</style>

</head>
<body>
<%--     <jsp:include page="../includes/menu.jsp" /> --%>

<h1>User List</h1>  


<div class="panel panel-default col-xs-9">
<!--  <div class="panel-heading">User List</div> -->
  <div class="panel-body">



 <sec:authorize access="hasRole('ROLE_ADMIN')">
 
 <div id="button_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">
<div class="dt-buttons btn-group">              
<!-- <a href="userForm">Add New User</a> -->
   <button data-target="#AddUser" title="Add a new user" type="button" class="btn btn-default toltip" data-toggle="modal">
		<img src="<c:url value='/resources/img/icons/black/user_icon&16.png' />"> Add New User
	</button>
   <!-- <a href="resetPasswordForm">Change Password</a> -->
   
   <button data-target="#AllChangePassword" title="Change Unknown User Password" type="button" class="btn btn-default toltip" data-toggle="modal">
		<img src="<c:url value='/resources/img/icons/black/key_icon&16.png' />"> Change Password
   </button>
</div>
</div>
<br>
   </sec:authorize>
    

<table id="example" align="center" class="table table-striped table-hover table-bordered">  
<thead>
<tr><th>Id</th><th>Username</th><th>status</th><th>question</th><th>answer</th><th>Actions</th></tr>  
</thead>
<tbody>
   <c:forEach var="user" items="${list}">   
   <tr>  
   <td>${user.id}</td>  
   <td>${user.userName}</td>  
   <td>${user.status}</td>  
   <td>${user.question}</td>
   <td>${user.answer}</td>
   <td>
   <button  title="Edit" type="button" class="btn btn-link btn-xs" data-toggle="tooltip" data-placement="bottom"><img src="<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />"></button>
        <a href="editUser/${user.id}" title="Edit"  data-toggle="tooltip" data-placement="bottom"><img src="<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />"></a>
        | <a href="resetPasswordForm/${user.id}" title="Reset Password"  data-toggle="tooltip" data-placement="bottom" ><img src="<c:url value='/resources/img/icons/black/key_icon&16.png' />"></a>
        | <a href="deleteUser/${user.id}" title="Delete"  data-toggle="tooltip" data-placement="bottom" ><img src="<c:url value='/resources/img/icons/black/trash_icon&16.png"' />"></a>
        | <a href="assigneRoleUser/${user.id}" title="Assing Roles"  data-toggle="tooltip" data-placement="bottom" ><img src="<c:url value='/resources/img/icons/black/users_icon&16.png"' />"></a>
      
   </td>
   </tr>  
   </c:forEach>  
   </tbody>
   </table>  
   
   <br/> 
   Custom Export:  
   <a href="reportUserList/pdf">PDF</a> | <a href="reportUserListXls">Excel</a>
   
</div></div>
   
   




<div class="modal fade" id="AddUser" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">New User</h4>
      </div>
      <div class="modal-body">



		<div class="form-container">
		  
		        <table >   
		         <tr>    
		          <td>Username : </td>   
		          <td><input type="text" name="userName" id="userName" class="form-control"/></td>  
		         </tr>    
		         <tr>    
		          <td>Password :</td>    
		          <td><input type="text" name="form-control" class="form-control"/></td>  
		         </tr>   
		         <tr>    
		          <td>Question :</td>    
		          <td><input type="text" name="question" class="form-control"/></td>  
		         </tr>   
		         <tr>    
		          <td>Answer :</td>    
		          <td><input type="text" nameh="answer" class="form-control"/></td>  
		         </tr>
		         <tr>    
		          <td>Status :</td>
		          <td><select name="status" id="status" class="form-control input-sm">
		                        <form:option value="">Select Country</form:option>
		                        <form:options items="${states}" />
		                    <select></td>  
		         </tr>    
		        </table>    
		       
		</div>



      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save</button>
      </div>
    </div>
  </div>
</div>




   
<div class="modal fade" id="AllChangePassword" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Change Password</h4>
      </div>
      <div class="modal-body">
        

		<div class="form-container">
		
				<table >
				<tr>
					<td colspan="2"><c:if test="${not empty error}">
								<div class="alert alert-danger" role="alert">${error}</div>
							</c:if>
							<c:if test="${not empty message}">
								<div class="alert alert-info" role="alert">${message}</div>
							</c:if></td>
				</tr>
		         <tr>    
		          <td>Username : </td>   
		          <td><input type="text" name="userName" id="userName" class="form-control" /></td>  
		         </tr>    
		         <tr>    
		          <td>Password :</td>    
		          <td><input type="text" name="password" class="form-control" /></td>  
		         </tr> 
		         <tr>    
		          <td>Password Again:</td>    
		          <td><input type="text" name="newPasswordAgain" class="form-control" /></td>  
		         </tr>    
		        </table>
		       
		</div>


      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save</button>
      </div>
    </div>
  </div>
</div>


</body>
</html>