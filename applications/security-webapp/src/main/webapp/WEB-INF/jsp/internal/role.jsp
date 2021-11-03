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
       	            	"targets": 4,
       	                "render": function ( data, type, row ) {
       	                    return "<td>"+
		       	                 makeButton("Edit","edit("+row.id+")","data-toggle='modal' data-target='#Form'","<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />")+
// 		       	                 makeButton("Delete","remove("+row.id+")","","<c:url value='/resources/img/icons/black/trash_icon&16.png' />")+
		       	                 makeButton("Permissions by Role","loadToAssign("+row.id+")","data-toggle='modal' data-target='#assignForm'","<c:url value='/resources/img/icons/black/cogs_icon&16.png' />")
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
//     $(function() {
//     	  var availableTags = [
//     	    "ActionScript",
//     	    "AppleScript",
//     	    "Asp",
//     	    "BASIC",
//     	    "C",
//     	    "C++",
//     	    "Clojure",
//     	    "COBOL",
//     	    "ColdFusion",
//     	    "Erlang",
//     	    "Fortran",
//     	    "Groovy",
//     	    "Haskell",
//     	    "Java",
//     	    "JavaScript",
//     	    "Lisp",
//     	    "Perl",
//     	    "PHP",
//     	    "Python",
//     	    "Ruby",
//     	    "Scala",
//     	    "Scheme"
//     	  ];
//     	  $("#tags").autocomplete({
//     	    source: availableTags
//     	  });
//     	});

	</script>
	<style type="text/css">
.ui-autocomplete {
  position: absolute;
  top: 100%;
  left: 0;
/*   z-index: 1000; -- normal*/
  display: none;
  float: left;
  min-width: 160px;
  padding: 5px 0;
  margin: 2px 0 0;
  list-style: none;
  font-size: 14px;
  text-align: left;
  background-color: #ffffff;
  border: 1px solid #cccccc;
  border: 1px solid rgba(0, 0, 0, 0.15);
  border-radius: 4px;
  -webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
  background-clip: padding-box;
  z-index:2147483647; /* -- for modal */
}

.ui-autocomplete > li > div {
  display: block;
  padding: 3px 20px;
  clear: both;
  font-weight: normal;
  line-height: 1.42857143;
  color: #333333;
  white-space: nowrap;
}

.ui-state-hover,
.ui-state-active,
.ui-state-focus {
  text-decoration: none;
  color: #262626;
  background-color: #f5f5f5;
  cursor: pointer;
}

.ui-helper-hidden-accessible {
  border: 0;
  clip: rect(0 0 0 0);
  height: 1px;
  margin: -1px;
  overflow: hidden;
  padding: 0;
  position: absolute;
  width: 1px;
}

.container{
  float: left;
  margin: 30px;
  width: 840px;
  border: 0px solid black;
  }
	</style>

</head>
<body>

<!-- <div class="container"> -->





<h1>Roles</h1>




<div class="panel panel-default">
<!-- <div class="panel-heading">User List Display tag</div> -->
  <div class="panel-body">   
   

   
<div id="button_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">
<div class="dt-buttons btn-group">              
<button data-target="#Form" title="Add New" type="button" class="btn btn-default toltip" data-toggle="modal" onclick="clearFields();">
		<img src="<c:url value='/resources/img/icons/black/doc_new_icon&16.png' />"> Add New
	</button>
</div>
</div>
<br>
    

 <table id="table" align="center" class="table table-striped table-hover table-bordered">  
<thead>
<tr><th>Id</th><th>Name</th><th>Description</th><th>Enabled</th><th>Actions</th></tr>  
</thead>
</table>

</div></div>


<!-- </div> -->

<div class="modal fade" id="Form" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
  
 <%--  <form id="moduleView" method="post" > --%>
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Role</h4>
      </div>
      <div class="modal-body">

		<input type="hidden" id="id" name="id"/>

		<div class="form-container">
		        
		        
		        
		        
		        <form class="form-horizontal">
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









<div class="modal fade" id="assignForm" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg" role="document">
  
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Assign Permission to Role</h4>
      </div>
      <div class="modal-body">

		<input type="hidden" id="id" name="id"/>

		<div class="form-container">
		        
		        
		        
		        
		        <form class="form-horizontal">
				  
				  <div class="form-group">
				    <label for="role" class="col-sm-3 control-label">Role</label>
				    <div class="col-sm-7">
				    <label id="role"  class="control-label"></label>
				    </div>
				  </div>

				  <div class="form-group">
				    <label for="assignModuleId" class="col-sm-3 control-label">Module</label>
				    <div class="col-sm-7">
				      <select id="assignModuleId" class="form-control input-sm" onchange="searchPermissionsByModule();">
										  <option value="">-- Select Option --</option>
									  </select>
				    </div>
				  </div>
				  
				  <div class="form-group">
				  
				  <div id="jstree_demo_div" class="container"></div>
					 
					 
				  </div>
			  </form>
		        
		        
		        
		       
		</div>



      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="savePermissionsAssigned();">Save</button>
      </div>
      
    </div>
    
  </div>
</div>






</body>
</html>