<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<meta name="_csrf_name" content="${_csrf.parameterName}"/>
	<title></title>
	<script type="text/javascript">
	
	$('#AddUser').on('shown.bs.modal', function () {})
	$('#EditUser').on('shown.bs.modal', function () {})
	$('#EditUserPassword').on('shown.bs.modal', function () {})
	$('#EditUserRole').on('shown.bs.modal', function () {})
	
	var contexPath = "<%=request.getContextPath() %>";
	
    function save(){
		var formData= { 
				userName: $('#AuserName').val(),
				password: $('#Apassword').val(),
				passwordAgain: $('#ApasswordAgain').val(),
				status: $('#Astatus').val(),
               	question: $('#Aquestion').val(),
               	answer: $('#Aanswer').val()
		}
		$('.bindingError').remove();
		
    	var ajaxUrl = contexPath+'/user/saveNew.json';
    	var successFunction = function(response){
     	   if(response.validated){
               //Set response
    		   if(response.status=="OK"){
         			showSuccessMessage(response.message);
         			load();
         			$('#AddUser').modal('hide');
         		}else{
         			showErrorMessage(response.message);
         		}
            }else{
              //Set error messages
              $.each(response.messages,function(key,value){
  	            $('input[id=A'+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
  	          	$('select[id=A'+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
              });
            }
    	   
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
              
    }
    
    function saveEdit(){
		var formData= {
				id: parseInt($("#Eid").val()), 
				userName: $('#EuserName').val(),
				status: $('#Estatus').val(),
               	question: $('#Equestion').val(),
               	answer: $('#Eanswer').val()
		}
		$('.bindingError').remove();
		
    	var ajaxUrl = contexPath+'/user/saveEdit.json';
    	var successFunction = function(response){
     	   if(response.validated){
               //Set response
    		   if(response.status=="OK"){
         			showSuccessMessage(response.message);
         			load();
         			$('#EditUser').modal('hide');
         		}else{
         			showErrorMessage(response.message);
         		}
            }else{
              //Set error messages
              $.each(response.messages,function(key,value){
  	            $('input[id=E'+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
  	          	$('select[id=E'+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
              });
            }
    	   
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
              
    }
    
    function saveEditPassword(){
		var formData= {
				id: parseInt($("#Pid").val()), 
				userName: $('#PuserName').val(),
				password: $('#Ppassword').val(),
				passwordAgain: $('#PpasswordAgain').val()
		}
		$('.bindingError').remove();
		
    	var ajaxUrl = contexPath+'/user/saveEditPassword.json';
    	var successFunction = function(response){
     	   if(response.validated){
               //Set response
    		   if(response.status=="OK"){
         			showSuccessMessage(response.message);
         			load();
         			$('#EditUserPassword').modal('hide');
         		}else{
         			showErrorMessage(response.message);
         		}
            }else{
              //Set error messages
              $.each(response.messages,function(key,value){
  	            $('input[id=P'+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
  	          	$('select[id=P'+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
              });
            }
    	   
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
              
    }
    
    
    
    
    
    
    function saveEditUserRole(){
		var formData= {
				id: parseInt($("#userId").val()) 
// 				roleId: $('#roleId').val()
		}
		$('.bindingError').remove();
		
    	var ajaxUrl = contexPath+'/user/saveEditUserRole.json';
    	var successFunction = function(response){
     	   if(response.validated){
               //Set response
    		   if(response.status=="OK"){
         			showSuccessMessage(response.message);
         			load();
         			$('#EditUserRole').modal('hide');
         		}else{
         			showErrorMessage(response.message);
         		}
            }else{
              //Set error messages
              $.each(response.messages,function(key,value){
  	            $('input[id='+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
              });
            }
    	   
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
              
    }
    
    
    function remove(idVal){
    	var formData= {
				id: idVal
		}
    	var ajaxUrl = contexPath+'/user/delete.json';
    	var successFunction = function(response){
       		if(response.status=="OK"){
       			showSuccessMessage(response.message);
       			load();
       		}else{
       			showErrorMessage(response.message);
       		}
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
    }
    
    function edit(idVal){
    	var formData= {
				id: idVal
		}
    	$('.bindingError').remove();
    	var ajaxUrl = contexPath+'/user/loadEdit.json';
    	var successFunction = function(response){
       		if(response.status=="OK"){
       			$("#Eid").val(response.viewBean.id);
       			$("#EuserName").val(response.viewBean.userName);
       			$("#Estatus").val(response.viewBean.status);
       			$("#Equestion").val(response.viewBean.question);
       			$("#Eanswer").val(response.viewBean.answer);
       		}
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
    }
    
    function editPassword(idVal){
    	var formData= {
				id: idVal
		}
    	$('.bindingError').remove();
    	var ajaxUrl = contexPath+'/user/loadEditPassword.json';
    	var successFunction = function(response){
       		if(response.status=="OK"){
       			$("#Pid").val(response.viewBean.id);
       			$("#PuserName").val(response.viewBean.userName);
       		}
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
    }
    
    function editUserRole(idVal){
    	var formData= {
				id: idVal
		}
    	$('.bindingError').remove();
    	var ajaxUrl = contexPath+'/user/loadEditUserRole.json';
    	var successFunction = function(response){
       		if(response.status=="OK"){
       			$("#userId").val(response.viewBean.id);
       			$("#RuserName").val(response.viewBean.userName);
       			loadRoles(idVal);
       		}
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
    }
    
    function assignRolesbyUser(object,userId,roleId){
    	var selected = "N";
    	if (object.checked) {
    		selected = "Y";
		}
    	var formData= {
    			userId: userId,
    			roleId: roleId,
    			selected: selected
		}
    	var ajaxUrl = contexPath+'/user/assignRolesbyUser.json';
    	var successFunction = function(response){
       		if(response.status=="OK"){
       			showSuccessMessage(response.message);
       		}else{
       			showErrorMessage(response.message);
       		}
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
    	
    	
    }
    
    function load(){
    	var ajaxUrl = contexPath+'/user/list.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			
       			var tableId = "#table";
       			var fileTitle = "User List";
       			var jsonData = response.data;
       			var jsonColumns = [
   		            { "data": "id" },
   		            { "data": "userName" },
   		            { "data": "status" },
   		            { "data": "question" },
   		            { "data": "answer" }
   		        ];
       			var columnsExport = [ 0, 1, 2, 3, 4 ];
       			var jsonColumnDefs = [
       	        	{
       	            	"targets": 5,
       	                "render": function ( data, type, row ) {
       	                    return "<td><button title='Edit' onclick='edit("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#EditUser'><img src='<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />'></button> | "+
           	                 "<button title='Delete' onclick='remove("+row.id+")' type='button' class='btn btn-link btn-xs toltip'><img src='<c:url value='/resources/img/icons/black/trash_icon&16.png' />'></button> | "+
           	              	"<button title='Change Password' onclick='editPassword("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#EditUserPassword'><img src='<c:url value='/resources/img/icons/black/key_icon&16.png' />'></button></td>"+
           	             "<button title='Change Roles by User' onclick='editUserRole("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#EditUserRole'><img src='<c:url value='/resources/img/icons/black/cogs_icon&16.png' />'></button></td>"
           	              		;
       	                }
       	            }
       	        ];
       			
       			createTable(tableId,fileTitle,jsonData,jsonColumns,jsonColumnDefs,columnsExport);
       			
       		}
       };
       
       ajaxPostWithoutForm(ajaxUrl,successFunction);
       
    }
    
    
    function loadRoles(idVal){
    	var formData= {
				id: idVal
		}
    	$('.bindingError').remove();
    	var ajaxUrl = contexPath+'/user/enabledRolesByUser.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			
       			var tableId = "#roles";
       			var jsonData = response.data;
       			var jsonColumns = [
   		            { "data": "roleId" },
   		            { "data": "roleDescription" }
   		        ];
       			var jsonColumnDefs = [
       				{
       	            	"targets": 2,
       	                "render": function ( data, type, row) {
      	                var checkedActive='';
                         if (row.selected == 'Y'){
                         	checkedActive = "checked='true'";
                         }
      	                    return "<td><input type='checkbox' name='roleSelected' id='roleSelected' "+checkedActive+" value='"+row.roleId+"' onclick='assignRolesbyUser(this,"+row.userId+","+row.roleId+");'></td>";
       	                }
       	            }
       	        ];
       			
       			createTableWithoutButtons(tableId,jsonData,jsonColumns,jsonColumnDefs);
       			
       		}
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
    }
    
    function populateStatusSelect(){
    	var URL_USER_STATUS_LIST = "<%=request.getSession().getAttribute("URL_USER_STATUS_LIST") %>";
    	var ajaxUrl = URL_USER_STATUS_LIST;
    	var successFunction = function(response){
       		if(response.objectBean.length>0){
       			$.each(response.objectBean, function(i, row) {
                    $('#Astatus').append('<option value="' + row.id + '">' + row.codigo + '</option>');
                    $('#Estatus').append('<option value="' + row.id + '">' + row.codigo + '</option>');
                });
       		}
       };
       ajaxWithoutForm(ajaxUrl,"GET",successFunction);
    }
    
    function clearFields(){
		$('.bindingError').remove();
		$("#Aid").val("");
		$("#AuserName").val("");
		$("#Apassword").val("");
		$("#ApasswordAgain").val("");
		$('#Aquestion').val("");
		$("#Aanswer").val("");
		$("#Astatus").val("");
	}

	$(document).ready(function(){
    	load();
    	populateStatusSelect();
    	
    });
	</script>
	<style type="text/css">
	
	</style>

</head>
<body>


<div class="container">





<h1>Users</h1>



<div class="panel panel-default">
<!-- <div class="panel-heading">User List Display tag</div> -->
  <div class="panel-body">   
   
   <sec:authorize access="hasRole('ROLE_ADMIN')">
<div id="button_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">
<div class="dt-buttons btn-group">
	<button data-target="#AddUser" title="Add a new user" type="button" class="btn btn-default toltip" data-toggle="modal" onclick="clearFields();">
		<img src="<c:url value='/resources/img/icons/black/user_icon&16.png' />"> Add New User
	</button>
<!-- 	<button data-target="#AllChangePassword" title="Change Unknown User Password" type="button" class="btn btn-default toltip" data-toggle="modal" onclick="clearPassFields();"> -->
<%-- 		<img src="<c:url value='/resources/img/icons/black/key_icon&16.png' />"> Change Password --%>
<!-- 	</button> -->
</div>
</div>
<br>
   </sec:authorize>
    

 <table id="table" align="center" class="table table-striped table-hover table-bordered">  
<thead>
<tr><th>Id</th><th>Username</th><th>status</th><th>question</th><th>answer</th><th>Actions</th></tr>  
</thead>
</table>

<br/> 
   Custom Export:  
   <a href="reportUserList/pdf">PDF</a> | <a href="reportUserListXls">Excel</a>
   
</div></div>


</div>

<div class="modal fade" id="AddUser" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
  
 <%--  <form id="moduleView" method="post" > --%>
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">User</h4>
      </div>
      <div class="modal-body">

		<input type="hidden" id="Aid" name="Aid"/>

		<div class="form-container">
		        
		        
		        
		        
		        <form class="form-horizontal">
				  <div class="form-group">
				    <label for="AuserName" class="col-sm-3 control-label">Username</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="AuserName" placeholder="Username">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="Apassword" class="col-sm-3 control-label">Password</label>
				    <div class="col-sm-7">
				      <input type="password" class="form-control" id="Apassword" placeholder="Password">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="ApasswordAgain" class="col-sm-3 control-label">Password Again</label>
				    <div class="col-sm-7">
				      <input type="password" class="form-control" id="ApasswordAgain" placeholder="Password Again">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="Aquestion" class="col-sm-3 control-label">Question</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="Aquestion" placeholder="Question">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="Aanswer" class="col-sm-3 control-label">Answer</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="Aanswer" placeholder="Answer">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="Astatus" class="col-sm-3 control-label">Status</label>
				    <div class="col-sm-7">
				      <select id="Astatus" class="form-control input-sm">
					      	<option value="">-- Select Option --</option>
					      </select>
				    </div>
				  </div>
			  </form>
		        
		        
		        
		       
		</div>



      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="save();">Save</button>
      </div>
      
    </div>
    
    <%-- </form> --%>
  </div>
</div>



<div class="modal fade" id="EditUser" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
  
 <%--  <form id="moduleView" method="post" > --%>
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">User</h4>
      </div>
      <div class="modal-body">

		<input type="hidden" id="Eid" name="Eid"/>

		<div class="form-container">
		        
		        
		        
		        
		        <form class="form-horizontal">
				  <div class="form-group">
				    <label for="EuserName" class="col-sm-3 control-label">Username</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="EuserName" placeholder="Username">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="Equestion" class="col-sm-3 control-label">Question</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="Equestion" placeholder="Question">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="Eanswer" class="col-sm-3 control-label">Answer</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="Eanswer" placeholder="Answer">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="Estatus" class="col-sm-3 control-label">Status</label>
				    <div class="col-sm-7">
				      <select id="Estatus" class="form-control input-sm">
					      	<option value="">-- Select Option --</option>
					      </select>
				    </div>
				  </div>
			  </form>
		        
		        
		        
		       
		</div>



      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="saveEdit();">Save</button>
      </div>
      
    </div>
    
    <%-- </form> --%>
  </div>
</div>








<div class="modal fade" id="EditUserPassword" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
  
 <%--  <form id="moduleView" method="post" > --%>
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Change Password</h4>
      </div>
      <div class="modal-body">

		<input type="hidden" id="Pid" name="Pid"/>

		<div class="form-container">
		        
		        
		        
		        
		        <form class="form-horizontal">
				  <div class="form-group">
				    <label for="PuserName" class="col-sm-3 control-label">Username</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="PuserName" placeholder="Username">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="Ppassword" class="col-sm-3 control-label">Password</label>
				    <div class="col-sm-7">
				      <input type="password" class="form-control" id="Ppassword" placeholder="New Password">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="PpasswordAgain" class="col-sm-3 control-label">Password Again</label>
				    <div class="col-sm-7">
				      <input type="password" class="form-control" id="PpasswordAgain" placeholder="New Password Again">
				    </div>
				  </div>
			  </form>
		        
		        
		        
		       
		</div>



      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="saveEditPassword();">Save</button>
      </div>
      
    </div>
    
    <%-- </form> --%>
  </div>
</div>







<div class="modal fade" id="EditUserRole" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
  
 <%--  <form id="moduleView" method="post" > --%>
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Edit Role of User</h4>
      </div>
      <div class="modal-body">

		<input type="hidden" id="userId" name="userId"/>

		<div class="form-container">
		        
		        <form class="form-horizontal">
				  <div class="form-group">
				    <label for="RuserName" class="col-sm-3 control-label">Username</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="RuserName" placeholder="Username">
				    </div>
				  </div>
			  
		         <table id="roles" align="center" class="table table-striped table-hover table-bordered">  
				<thead>
				<tr><th>Id</th><th>Description</th><th>Select</th></tr>  
				</thead>
				</table>
		        

		       </form> 
		        
		        
		       
		</div>



      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
      
    </div>
    
    <%-- </form> --%>
  </div>
</div>





</body>
</html>