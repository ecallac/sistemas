<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <jsp:include page="../includes/styles.jsp" /> --%>
<%-- <script type='text/javascript' src='<c:url value='/resources/js/jquery.1.10.2.min.js' />'></script> --%>
	<meta charset="utf-8" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>
	<script type="text/javascript">
/* 	$(document).ready(function(){
		$('#Add').on('shown.bs.modal', function () {
			  
		})
    }); */
	
    
	
	
	var contexPath = "<%=request.getContextPath() %>";
	
    function save(){
    	var elabled = "N";
    	if ($('#enabled').is(':checked')) {
			elabled = "Y";
		}
		var formData= {
				id: parseInt($("#id").val()), 
               	name: $('#name').val(),
               	description: $('#description').val(),
               	enabled: elabled
		}
		$('.bindingError').remove();
		
    	var ajaxUrl = contexPath+'/role/save.json';
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
            	  showErrorMessageByField('input' , key , value , '');
//   	            $('input[id='+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
              });
            }
    	   
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
              
    }
    
    
    
    function remove(idVal){
//     	var token = $("meta[name='_csrf']").attr("content");
// 	    post(
// 	    		contexPath+"/module/delete", {
// 	    			id: id,
// 	    			_csrf:token
// 	    });
    	var formData= {
				id: idVal
		}
    	var ajaxUrl = contexPath+'/role/delete.json';
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
    	var ajaxUrl = contexPath+'/role/load.json';
    	var successFunction = function(response){
       		if(response.status=="OK"){
       			$("#id").val(response.viewBean.id);
       			$("#name").val(response.viewBean.name);
       			$("#description").val(response.viewBean.description);
       			if (response.viewBean.enabled == 'Y'){
       				$('#enabled').prop('checked', true);
       			}else{
       				$('#enabled').prop('checked', false);
       			}
       		}
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
    }
    
    function enableAndDisable(object,idVal){
    	var elabled = "N";
    	if (object.checked) {
			elabled = "Y";
		}
    	var formData= {
				id: idVal,
				enabled:elabled
		}
    	var ajaxUrl = contexPath+'/role/enableDisable.json';
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
    	var ajaxUrl = contexPath+'/role/list.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			
       			var tableId = "#table";
       			var fileTitle = "Role List";
       			var jsonData = response.data;
       			var jsonColumns = [
   		            { "data": "id" },
   		            { "data": "name" },
   		            { "data": "description" }
   		        ];
       			var columnsExport = [ 0, 1, 2 ];
       			var jsonColumnDefs = [
       	            {
						data: null,
       	            	"targets": 3,
       	                "render": function ( data, type, row ) {
      	                var checkedActive='';
                         if (row.enabled == 'Y'){
                         	checkedActive = "checked='true'";
                         }
      	                    return "<td><input type='checkbox' name='select' id='select' "+checkedActive+" onclick='enableAndDisable(this,"+row.id+");'></td>";
       	                }
       	            },
       	        	{
						data: null,
       	            	"targets": 4,
       	                "render": function ( data, type, row ) {
       	                    return "<td>"+
		       	                 makeButton("Edit","edit("+row.id+")","data-bs-toggle='modal' data-bs-target='#Form'","<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />")+
// 		       	                 makeButton("Delete","remove("+row.id+")","","<c:url value='/resources/img/icons/black/trash_icon&16.png' />")+
		       	                 makeButton("Permissions by Role","loadToAssign("+row.id+")","data-bs-toggle='modal' data-bs-target='#assignForm'","<c:url value='/resources/img/icons/black/cogs_icon&16.png' />")
	       	                 "</td>";
//        	                    "<button title='Edit' onclick='edit("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#Form'><img src='<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />'></button>"+
//            	                 "<button title='Delete' onclick='remove("+row.id+")' type='button' class='btn btn-link btn-xs toltip'><img src='<c:url value='/resources/img/icons/black/trash_icon&16.png' />'></button>"+
//            	              	"<button title='Permissions by Role' onclick='getPermissions("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#Permissions'><img src='<c:url value='/resources/img/icons/black/cogs_icon&16.png' />'></button></td>"+
//            	             	"<button title='Users by Role' onclick='getUsers("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#Users'><img src='<c:url value='/resources/img/icons/black/user_icon&16.png' />'></button></td>";
       	                }
       	            }
       	        ];
       			
       			createTable(tableId,fileTitle,jsonData,jsonColumns,jsonColumnDefs,columnsExport);
       			
       		}
       };
       
       ajaxPostWithoutForm(ajaxUrl,successFunction);
       
    }
    
    function loadToAssign(idVal){
		$("#jstree_demo_div").jstree('destroy');
		var formData= {
				roleId: idVal
		}
		var ajaxUrl = contexPath+'/role/loadPermissions.json';
		var successFunction = function(response){
	   		if(response.status=="OK"){
	   			$("#id").val(response.viewBean.id);
				$("#role").html( "<strong>" + response.viewBean.description + " [ "+response.viewBean.name+ " ]</strong>" );
				
				createTree(response.permissions);
				
// 				$("#assignForm").modal('show');
	   		}
	   };
	   
	   ajaxPost(ajaxUrl,formData,successFunction);
	}
    function searchPermissionsByModule(){
		$("#jstree_demo_div").jstree('destroy');
		var moduleId = $('#assignModuleId').val();
		var mod;
			if(moduleId!='' ||moduleId!=null){
				mod = moduleId;
			}
			var idVal = $('#id').val();
//			if(moduleId!=''){
			var formData= {
					roleId: idVal,
	    			moduleId: mod
			}
			
			var ajaxUrl = contexPath+'/role/loadPermissions.json';
	    	var successFunction = function(response){
	    		if(response.status=="OK"){
	    			//console.log(response.permissions);
	    			$("#id").val(response.viewBean.id);
					$("#role").html( "<strong>" + response.viewBean.description + " [ "+response.viewBean.name+ " ]</strong>" );
	    			
					createTree(response.permissions);
	       		}
	       };
	       ajaxPost(ajaxUrl,formData,successFunction);
	}
	
	function createTree(data){
		$("#jstree_demo_div").jstree({
			'core' : {
				  'data' : data,
					'animation': false,
				    'expand_selected_onload': true,
					},
		    	"checkbox" : {
			      	"keep_selected_style" : true,
			    	'three_state':false
		    	},
		    	"plugins" : [ "checkbox","wholerow" ]
		  	})
		.on("ready.jstree", function (e, data) {
		                 // hide all icons
				$('#jstree_demo_div').jstree().hide_icons();
		    // hide all dots
		    //$('#jstree').jstree().hide_dots();
		     });
	}

	function savePermissionsAssigned(){
		
		var permissionIds = [];
		
		var undeterminedNodes = $('#jstree_demo_div').jstree("get_undetermined", true);
		$.each(undeterminedNodes, function() {
			permissionIds.push(this.id);
		});
		var selectedNodes = $('#jstree_demo_div').jstree("get_selected", true);
		$.each(selectedNodes, function() {
			permissionIds.push(this.id);
		});
		
		
		var moduleId = $('#assignModuleId').val();
		var mod;
			if(moduleId!='' ||moduleId!=null){
				mod = moduleId;
			}
			
		var formData= {
				permissionIds: permissionIds,
				roleId: $('#id').val(),
				moduleId: mod
		}
		var ajaxUrl = contexPath+'/role/saveAssignedPermissions.json';
		var successFunction = function(response){
	 	   if(response.validated){
			   if(response.status=="OK"){
	     			showSuccessMessage(response.message);
	                 $('#assignForm').modal('hide');
	     		}else{
	     			showErrorMessage(response.message);
	     		}
	        }else{
	          alert("There is an error validating fields.");
	        }
		   
	   };
	   
	   ajaxPost(ajaxUrl,formData,successFunction);
	}
	function populateAssignModulesSelect(){
    	var ajaxUrl = contexPath+'/module/enabledModules.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			$('#assignModuleId').empty();
       			$('#assignModuleId').append('<option value="">-- Select Option --</option>');
       			$.each(response.data, function(i, row) {
                    $('#assignModuleId').append('<option value="' + row.id + '">' + row.description + '</option>');
                });
       		}
       };
       ajaxPostWithoutForm(ajaxUrl,successFunction);
	}
    
    function clearFields(){
		$('.bindingError').remove();
		$("#id").val("");
		$("#name").val("");
		$("#description").val("");
		$('#enabled').prop('checked', false)
	}


    $(document).ready(function(){
    	load();
    	populateAssignModulesSelect();
    });
	</script>
	<style type="text/css">
	</style>

</head>
<body>

<!-- <div class="container"> -->





<h1>Roles</h1>




<div class="panel panel-default">
<!-- <div class="panel-heading">User List Display tag</div> -->
  <div class="panel-body">



	  <div class="card flex-fill">
		  <div class="card-header">
			  <div class="dt-buttons btn-group">
				  <button data-bs-target="#Form" title="Add New" type="button" class="btn btn-light toltip" data-bs-toggle="modal" onclick="clearFields();">
					  <img src="<c:url value='/resources/img/icons/black/doc_new_icon&16.png' />"> Add New
				  </button>
			  </div>
		  </div>
		  <div class="card-body">
			  <table id="table" align="center" class="table table-striped table-hover table-bordered" style="width: 100%">
				  <thead>
				  <tr><th>Id</th><th>Name</th><th>Description</th><th>Enabled</th><th>Actions</th></tr>
				  </thead>
			  </table>
		  </div>
	  </div>
	  

</div></div>












<!-- Modal -->
<div class="modal fade" id="Form" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Role</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<input type="hidden" id="id" name="id"/>

				<div class="form-container">




					<form class="form-horizontal">
						<div class="mb-3 row">
							<label for="name" class="col-sm-3 col-form-label">Name</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="name" placeholder="Name">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="description" class="col-sm-3 col-form-label">Description</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="description" placeholder="Description">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="enabled" class="col-sm-3 col-form-label">Enabled</label>
							<div class="col-sm-7">
								<div class="checkbox">
									<label>
										<input type="checkbox" id="enabled" name="enabled" />
									</label>
								</div>
							</div>
						</div>
					</form>




				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-primary" onclick="save();">Guardar</button>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="assignForm" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Assign Role</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<div class="form-container">




					<form class="form-horizontal">

						<div class="mb-3 row">
							<label for="role" class="col-sm-3 col-form-label">Role</label>
							<div class="col-sm-7">
								<label id="role"  class="col-form-label"></label>
							</div>
						</div>

						<div class="mb-3 row">
							<label for="assignModuleId" class="col-sm-3 col-form-label">Module</label>
							<div class="col-sm-7">
								<select id="assignModuleId" class="form-control input-sm" onchange="searchPermissionsByModule();">
									<option value="">-- Select Option --</option>
								</select>
							</div>
						</div>

						<div class="mb-3 row">

							<div id="jstree_demo_div" class="container"></div>


						</div>
					</form>




				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-primary" onclick="savePermissionsAssigned();">Guardar</button>
			</div>
		</div>
	</div>
</div>






</body>
</html>