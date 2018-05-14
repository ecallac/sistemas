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
	
	$('#Add').on('shown.bs.modal', function () {
	  
	})
	
	var contexPath = "<%=request.getContextPath() %>";
	
    function save(){
		var formData= {
				id: parseInt($("#id").val()), 
				userName: $('#userName').val(),
				status: $('#status').val(),
               	question: $('#question').val(),
               	answer: $('#answer').val()
		}
		$('.bindingError').remove();
		
    	var ajaxUrl = contexPath+'/user/save.json';
    	var successFunction = function(response){
     	   if(response.validated){
               //Set response
    		   if(response.status=="OK"){
         			showSuccessMessage(response.message);
         			load();
         			$('#Form').modal('hide');
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
    	var ajaxUrl = contexPath+'/user/load.json';
    	var successFunction = function(response){
       		if(response.status=="OK"){
       			$("#id").val(response.viewBean.id);
       			$("#userName").val(response.viewBean.userName);
       			$("#status").val(response.viewBean.status);
       			$("#question").val(response.viewBean.question);
       			$("#answer").val(response.viewBean.answer);
       		}
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
    }
    
//     function enableAndDisable(object,idVal){
//     	var elabled = "N";
//     	if (object.checked) {
// 			elabled = "Y";
// 		}
//     	var formData= {
// 				id: idVal,
// 				enabled:elabled
// 		}
//     	var ajaxUrl = contexPath+'/module/enableDisable.json';
//     	var successFunction = function(response){
//        		if(response.status=="OK"){
//        			showSuccessMessage(response.message);
//        		}else{
//        			showErrorMessage(response.message);
//        		}
//        };
       
//        ajaxPost(ajaxUrl,formData,successFunction);
    	
    	
//     }
    
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
       	                    return "<td><button title='Edit' onclick='edit("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#AddUser'><img src='<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />'></button> | "+
           	                 "<button title='Delete' onclick='remove("+row.id+")' type='button' class='btn btn-link btn-xs toltip'><img src='<c:url value='/resources/img/icons/black/trash_icon&16.png' />'></button> | "//+
           	              		//"<button title='Permissions by Module' onclick='getPermissions("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#Permissions'><img src='<c:url value='/resources/img/icons/black/cogs_icon&16.png' />'></button></td>"
           	              		;
       	                }
       	            }
       	        ];
       			
       			createTable(tableId,fileTitle,jsonData,jsonColumns,jsonColumnDefs,columnsExport);
       			
       		}
       };
       
       ajaxPostWithoutForm(ajaxUrl,successFunction);
       
    }
    
    function populateStatusSelect(){
    	var ajaxUrl = contexPath+'/user/enabledStatus.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			$.each(response.data, function(i, row) {
//                     $('#status').append('<option value="' + row.id + '">' + row.name + '</option>');
                    $('#status').append('<option value="' + row + '">' + row + '</option>');
                });
       		}
       };
       ajaxPostWithoutForm(ajaxUrl,successFunction);
    }
    
// 	function getPermissions(idVal){
    	
//     }
    
    function clearFields(){
		$('.bindingError').remove();
		$("#id").val("");
		$("#userName").val("");
		$("#password").val("");
		$('#question').val("");
		$("#answer").val("");
		$("#status").val("");
	}
//     function clearPassFields(){
// 		$('.bindingError').remove();
// 		$("#id").val("");
// 		$("#userName").val("");
// 		$("#password").val("");
// 		$('#newPasswordAgain').val("");
// 	}

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
	<button data-target="#AllChangePassword" title="Change Unknown User Password" type="button" class="btn btn-default toltip" data-toggle="modal" onclick="clearPassFields();">
		<img src="<c:url value='/resources/img/icons/black/key_icon&16.png' />"> Change Password
	</button>
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

		<input type="hidden" id="id" name="id"/>

		<div class="form-container">
		        
		        
		        
		        
		        <form class="form-horizontal">
				  <div class="form-group">
				    <label for="userName" class="col-sm-3 control-label">Username</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="userName" placeholder="Username">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="password" class="col-sm-3 control-label">Password</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="password" placeholder="Password">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="question" class="col-sm-3 control-label">Question</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="question" placeholder="Question">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="answer" class="col-sm-3 control-label">Answer</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="answer" placeholder="Answer">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="status" class="col-sm-3 control-label">Status</label>
				    <div class="col-sm-7">
				      <select id="status" class="form-control input-sm">
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









</body>
</html>