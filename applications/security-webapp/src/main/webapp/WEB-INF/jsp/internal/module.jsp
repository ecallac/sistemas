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
               	author: $('#author').val(),
               	moduleVersion: $('#moduleVersion').val()
		}
		$('.bindingError').remove();
		
    	var ajaxUrl = contexPath+'/module/save.json';
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
    	var ajaxUrl = contexPath+'/module/delete.json';
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
    	var ajaxUrl = contexPath+'/module/load.json';
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
       			$("#author").val(response.viewBean.author);
       			$("#moduleVersion").val(response.viewBean.moduleVersion);
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
    	var ajaxUrl = contexPath+'/module/enableDisable.json';
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
    	var ajaxUrl = contexPath+'/module/list.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			
       			var tableId = "#table";
       			var fileTitle = "Module List";
       			var jsonData = response.data;
       			var jsonColumns = [
   		            { "data": "id" },
   		            { "data": "name" },
   		            { "data": "description" },
   		            { "data": "author" },
   		            { "data": "moduleVersion" }
   		        ];
       			var columnsExport = [ 0, 1, 2, 3, 4 ];
       			var jsonColumnDefs = [
       	            {
       	            	"targets": 5,
       	                "render": function ( data, type, row ) {
      	                var checkedActive='';
                         if (row.enabled == 'Y'){
                         	checkedActive = "checked='true'";
                         }
      	                    return "<td><input type='checkbox' name='select' id='select' "+checkedActive+" onclick='enableAndDisable(this,"+row.id+");'></td>";
       	                }
       	            },
       	        	{
       	            	"targets": 6,
       	                "render": function ( data, type, row ) {
       	                    return "<td>"+
       	                 		makeButton("Edit","edit("+row.id+")","data-toggle='modal' data-target='#Form'","<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />")+
       	              			makeButton("Delete","remove("+row.id+")","","<c:url value='/resources/img/icons/black/trash_icon&16.png' />")+
       	              
//        	                    "<button title='Edit' onclick='edit("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#Form'><img src='<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />'></button>"+
//            	                 "<button title='Delete' onclick='remove("+row.id+")' type='button' class='btn btn-link btn-xs toltip'><img src='<c:url value='/resources/img/icons/black/trash_icon&16.png' />'></button>
           	              "</td>";
       	                }
       	            }
       	        ];
       			
       			createTable(tableId,fileTitle,jsonData,jsonColumns,jsonColumnDefs,columnsExport);
       			
       		}
       };
       
       ajaxPostWithoutForm(ajaxUrl,successFunction);
       
    }
    
	function getPermissions(idVal){
    	
    }
    
    function clearFields(){
		$('.bindingError').remove();
		$("#id").val("");
		$("#name").val("");
		$("#description").val("");
		$('#enabled').prop('checked', false);
		$("#author").val("");
		$("#moduleVersion").val("");
	}
    
	</script>
	<style type="text/css">
	
	</style>

</head>
<body onload="load();">


<!-- <div class="container"> -->





<h1>Modules</h1>



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
<tr><th>Id</th><th>Name</th><th>Description</th><th>Author</th><th>Version</th><th>Enabled</th><th>Actions</th></tr>  
</thead>
</table>

</div>
</div>


<!-- </div> -->

<div class="modal fade" id="Form" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
  
 <%--  <form id="moduleView" method="post" > --%>
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Module</h4>
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
				    <label for="author" class="col-sm-3 control-label">Author</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="author" placeholder="Author">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="moduleVersion" class="col-sm-3 control-label">Module Version</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="moduleVersion" placeholder="Module Version">
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









<div class="modal fade" id="Permissions" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
  
 <%--  <form id="moduleView" method="post" > --%>
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Permissions by Module</h4>
      </div>
      <div class="modal-body">







<div id="treeview_container" class="hummingbird-treeview well h-scroll-large">
          <!-- <div id="treeview_container" class="hummingbird-treeview"> -->
          <ul id="treeview" class="hummingbird-base">

        <li><i class="fa fa-minus"></i> <label> <input id="node-0" data-id="custom-0" type="checkbox"> node-0</label>
            <ul style="display: block;">
          <li><i class="fa fa-plus"></i> <label> <input id="node-0-1" data-id="custom-1" type="checkbox">  node-0-1</label>
<ul><li><i class="fa fa-plus"></i> <label> <input id="node-0-1-1" data-id="custom-1-1" type="checkbox"> node-0-1-1</label>
<ul><li><label> <input class="hummingbirdNoParent" id="node-0-1-1-1" data-id="custom-1-1-1" type="checkbox"> node-0-1-1-1</label>
</li><li><label> <input class="hummingbirdNoParent" id="node-0-1-1-2" data-id="custom-1-1-2" type="checkbox"> node-0-1-1-2</label>
</li><li><label> <input class="hummingbirdNoParent" id="node-0-1-1-3" data-id="custom-1-1-3" type="checkbox"> node-0-1-1-3</label>
</li><li><label> <input class="hummingbirdNoParent" id="double1" data-id="custom-1-1-3" type="checkbox">  node-0-1-1-3</label>
</li><li><label> <input class="hummingbirdNoParent" id="double2" data-id="custom-1-1-3" type="checkbox">  node-0-1-1-3</label>
</li></ul></li><li><i class="fa fa-plus"></i> <label> <input id="node-0-1-2" data-id="custom-1-2" type="checkbox"> node-0-1-2</label>
<ul><li style="color: rgb(200, 200, 200);"><label> <input class="hummingbirdNoParent" id="node-0-1-2-1" data-id="custom-1-2-1" type="checkbox" disabled=""> node-0-1-2-1</label>
</li><li><label> <input class="hummingbirdNoParent" id="node-0-1-2-2" data-id="custom-1-2-2" type="checkbox"> node-0-1-2-2</label>
</li><li><label> <input class="hummingbirdNoParent" id="node-0-1-2-3" data-id="custom-1-2-3" type="checkbox"> node-0-1-2-3</label>
</li><li><label> <input class="hummingbirdNoParent" id="double3" data-id="custom-1-1-3" type="checkbox">  node-0-1-1-3</label>
</li></ul></li></ul></li><li><i class="fa fa-plus"></i> <label> <input id="node-0-2" data-id="custom-2" type="checkbox">  node-0-2</label>
<ul><li><i class="fa fa-plus"></i> <label> <input id="node-0-2-1" data-id="custom-2-1" type="checkbox"> node-0-2-1</label>
<ul><li><label> <input class="hummingbirdNoParent" id="node-0-2-1-1" data-id="custom-2-1-1" type="checkbox"> node-0-2-1-1</label>
</li><li><label> <input class="hummingbirdNoParent" id="node-0-2-1-2" data-id="custom-2-1-2" type="checkbox"> node-0-2-1-2</label>
</li><li><label> <input class="hummingbirdNoParent" id="node-0-2-1-3" data-id="custom-2-1-3" type="checkbox"> node-0-2-1-3</label>
</li></ul></li><li><i class="fa fa-plus"></i> <label> <input id="node-0-2-2" data-id="custom-2-2" type="checkbox"> node-0-2-2</label>
<ul><li><label> <input class="hummingbirdNoParent" id="node-0-2-2-1" data-id="custom-2-2-1" type="checkbox"> node-0-2-2-1</label>
</li><li><label> <input class="hummingbirdNoParent" id="node-0-2-2-2" data-id="custom-2-2-2" type="checkbox"> node-0-2-2-2</label>
</li><li><label> <input class="hummingbirdNoParent" id="node-0-2-2-3" data-id="custom-2-2-3" type="checkbox"> node-0-2-2-3</label>
</li></ul></li></ul></li>
            </ul>
        </li>
		
        <li><i class="fa fa-plus"></i> <label> <input id="node-1" data-id="custom-1" type="checkbox"> node-1</label>
            <ul>
          <li><label> <input class="hummingbirdNoParent" id="node-1-1" data-id="custom-1-1" type="checkbox">  node-1-1</label>
</li><li><label> <input class="hummingbirdNoParent" id="node-1-2" data-id="custom-1-2" type="checkbox">  node-1-2</label>
</li>
            </ul>
        </li>
          </ul>
      </div>
  <button class="btn btn-primary" id="checkAll">Check All</button>
  <button class="btn btn-primary" id="uncheckAll">Uncheck All</button>
  <button class="btn btn-danger" id="checkNode">Check Node 0-2-2</button>


<script>
$("#treeview").hummingbird();
$( "#checkAll" ).click(function() {
  $("#treeview").hummingbird("checkAll");
});
$( "#uncheckAll" ).click(function() {
  $("#treeview").hummingbird("uncheckAll");
});
$( "#collapseAll" ).click(function() {
  $("#treeview").hummingbird("collapseAll");
});
$( "#checkNode" ).click(function() {
  $("#treeview").hummingbird("checkNode",{attr:"id",name: "node-0-2-2",expandParents:false});
});
</script>






http://www.jqueryscript.net/other/Searchable-Multi-selectable-Tree-jQuery-SearchAreaControl.html
http://www.jqueryscript.net/other/Collapsible-Tree-View-Checkboxes-jQuery-hummingbird.html
http://www.jqueryscript.net/other/Ajax-File-Tree-Plugin-jQuery-jsFiler.html
http://www.jqueryscript.net/other/Folding-Tree-Structures-jQuery-file-explore.html



      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="save();">Save</button>
      </div>
      
    </div>
    
  </div>
</div>

</body>
</html>