<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
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
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<meta name="_csrf_name" content="${_csrf.parameterName}"/>
	<title></title>
	<script type="text/javascript">
	
	$('#Add').on('shown.bs.modal', function () {
	  
	})
	
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
               	enabled: elabled,
               	path: $('#path').val(),
               	moduleId: $('#moduleId').val(),
               	parentPermissionId: $('#parentPermissionId').val(),
               	type:$('#type').val()
		}
		$('.bindingError').remove();
		
    	var ajaxUrl = contexPath+'/permission/save.json';
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
  	          	$('select[id='+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
              });
            }
    	   
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
              
    }
    
    
    
    function remove(idVal){
//     	var token = $("meta[name='_csrf']").attr("content");
// 	    post(
// 	    		contexPath+"/permission/delete", {
// 	    			id: id,
// 	    			_csrf:token
// 	    });
    	var formData= {
				id: idVal
		}
    	var ajaxUrl = contexPath+'/permission/delete.json';
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
    	var ajaxUrl = contexPath+'/permission/load.json';
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
       			$("#path").val(response.viewBean.path);
       			$("#moduleId").val(response.viewBean.module.id);
       			$("#parentPermissionId").val(response.viewBean.parentPermission.id);
       			$("#type").val(response.viewBean.type);
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
    	var ajaxUrl = contexPath+'/permission/enableDisable.json';
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
    	var ajaxUrl = contexPath+'/permission/list.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			
       			var tableId = "#table";
       			var fileTitle = "Permission List";
       			var jsonData = response.data;
       			var jsonColumns = [
   		            { "data": "id" },
   		         	{ "data": "module.name"},
   		         	{ "data": "parentPermission.name" ,"defaultContent": ""},
   		            { "data": "name" },
   		            { "data": "description" },
   		            { "data": "path" },
   		         	{ "data": "type" ,"defaultContent": ""}
   		        ];
       			var columnsExport = [ 0, 1, 2, 3, 4, 5, 6];
       			var jsonColumnDefs = [
       	            {
       	            	"targets": 7,
       	                "render": function ( data, type, row ) {
      	                var checkedActive='';
                         if (row.enabled == 'Y'){
                         	checkedActive = "checked='true'";
                         }
      	                    return "<td><input type='checkbox' name='select' id='select' "+checkedActive+" onclick='enableAndDisable(this,"+row.id+");'></td>";
       	                }
       	            },
       	        	{
       	            	"targets": 8,
       	                "render": function ( data, type, row ) {
       	                    return "<td><button title='Edit' onclick='edit("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#Form'><img src='<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />'></button>"+
           	                 "<button title='Delete' onclick='remove("+row.id+")' type='button' class='btn btn-link btn-xs toltip'><img src='<c:url value='/resources/img/icons/black/trash_icon&16.png' />'></button> ";
           	              
       	                }
       	            }
       	        ];
       			
       			createTable(tableId,fileTitle,jsonData,jsonColumns,jsonColumnDefs,columnsExport);
       			
       		}
       };
       ajaxPostWithoutForm(ajaxUrl,successFunction);
    }
    
    
    function populateModulesSelect(){
    	var ajaxUrl = contexPath+'/module/enabledModules.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			$.each(response.data, function(i, row) {
                    $('#moduleId').append('<option value="' + row.id + '">' + row.name + '</option>');
                });
       		}
       };
       ajaxPostWithoutForm(ajaxUrl,successFunction);
    }
    
    function populateParentPermissionSelect(){
    	var ajaxUrl = contexPath+'/permission/enabledPermissions.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			$.each(response.data, function(i, row) {
                    $('#parentPermissionId').append('<option value="' + row.id + '">' + row.name + '</option>');
                });
       		}
       };
       ajaxPostWithoutForm(ajaxUrl,successFunction);
    }
    
    function populatePermissionTypeSelect(){
    	var URL_TYPE_PERMISSION_LIST = "<%=request.getSession().getAttribute("URL_TYPE_PERMISSION_LIST") %>";
    	var ajaxUrl = URL_TYPE_PERMISSION_LIST;
    	var successFunction = function(response){
       		if(response.objectBean.length>0){
       			$.each(response.objectBean, function(i, row) {
                    $('#type').append('<option value="' + row.codigo + '">' + row.codigo + '</option>');
                });
       		}
       };
       ajaxWithoutForm(ajaxUrl,"GET",successFunction);
    }
    
    
    function clearFields(){
		$('.bindingError').remove();
		$("#id").val("");
		$("#name").val("");
		$("#description").val("");
		$('#enabled').prop('checked', false);
		$("#path").val("");
		$("#moduleId").val("");
		$("#parentPermissionId").val("");
		$("#type").val("");
	}
    
    $(document).ready(function(){
    	load();
    	populateModulesSelect();
    	populateParentPermissionSelect();
    	populatePermissionTypeSelect();
    });
	</script>
	<style type="text/css">
	
	</style>

</head>
<body>


<!-- <div class="container"> -->





<h1>Permissions</h1>



<div class="panel panel-default">
<!-- <div class="panel-heading">User List Display tag</div> -->
  <div class="panel-body">   
   
   <sec:authorize access="hasRole('ROLE_ADMIN')">
<div id="button_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">
<div class="dt-buttons btn-group">              
<button data-target="#Form" title="Add New" type="button" class="btn btn-default toltip" data-toggle="modal" onclick="clearFields();">
		<img src="<c:url value='/resources/img/icons/black/doc_new_icon&16.png' />"> Add New
	</button>
</div>
</div>
<br>
   
   </sec:authorize>
    

 <table id="table" align="center" class="table table-striped table-hover table-bordered">  
<thead>
<tr><th>Id</th><th>Module</th><th>Parent Permission</th><th>Name</th><th>Description</th><th>Path</th><th>Type</th><th>Enabled</th><th>Actions</th></tr>  
</thead>
</table>

</div></div>


<!-- </div> -->

<div class="modal fade" id="Form" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
  
 <%--  <form id="permissionView" method="post" > --%>
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Permission</h4>
      </div>
      <div class="modal-body">

		<input type="hidden" id="id" name="id"/>

		<div class="form-container">
		        
		        
		        
		        
		        <form class="form-horizontal">
		        	<div class="form-group">
					    <label for="moduleId" class="col-sm-3 control-label">Module</label>
					    <div class="col-sm-7">
					      <select id="moduleId" class="form-control input-sm">
					      	<option value="">-- Select Option --</option>
					      </select>
					    </div>
					</div>
					<div class="form-group">
					    <label for="parentPermissionId" class="col-sm-3 control-label">Parent Permission</label>
					    <div class="col-sm-7">
					      <select id="parentPermissionId" class="form-control input-sm">
					      	<option value="">-- Select Option --</option>
					      </select>
					    </div>
					</div>
					<div class="form-group">
					    <label for="type" class="col-sm-3 control-label">Permission Type</label>
					    <div class="col-sm-7">
					      <select id="type" class="form-control input-sm">
					      	<option value="">-- Select Option --</option>
					      </select>
					    </div>
					</div>
				  <div class="form-group">
				    <label for="name" class="col-sm-3 control-label">Name</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="name" placeholder="Name">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="description" class="col-sm-3 control-label">Description</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="description" placeholder="Description">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="author" class="col-sm-3 control-label">Path</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="path" placeholder="Path">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="enabled" class="col-sm-3 control-label">Enabled</label>
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
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="save();">Save</button>
      </div>
      
    </div>
    
    <%-- </form> --%>
  </div>
</div>










</body>
</html>