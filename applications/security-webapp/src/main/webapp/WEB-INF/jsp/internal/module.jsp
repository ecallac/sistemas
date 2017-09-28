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
	

	function post(path, params, method) {
        method = method || "post"; // Set method to post by default if not specified.

        // The rest of this code assumes you are not using a library.
        // It can be made less wordy if you use one.
        var form = document.createElement("form");
        form.setAttribute("method", method);
        form.setAttribute("action", path);

        for(var key in params) {
            if(params.hasOwnProperty(key)) {
//             	alert(key + " - " +params[key]);
                var hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", key);
                hiddenField.setAttribute("value", params[key]);

                form.appendChild(hiddenField);
             }
        }

        document.body.appendChild(form);
        form.submit();
    }
		
		
	var contexPath = "<%=request.getContextPath() %>";
    data = "";
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
        $.ajax({
           	type:'POST',
               dataType: 'json',
               contentType: 'application/json',
               url:contexPath+'/module/save.json',
               data:JSON.stringify(formData),
			beforeSend: function(xhr) {
		        // setting a timeout
		        var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
		        xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.setRequestHeader(header, token);
		    },
               success: function(response){
               	var token = $("meta[name='_csrf']").attr("content");
           	    post(
           	    		contexPath+"/module", {
           	    			status: response.status,
           	    			message: response.message,
           	    			_csrf:token
           	    });
               } ,
               error: function (jqXHR, exception) {
                 console.log(jqXHR);
			  alert('Error: ' + jqXHR);
		  }  
        });        
    }
    
    
    
    function remove(id){
    	var token = $("meta[name='_csrf']").attr("content");
	    post(
	    		contexPath+"/module/delete", {
	    			id: id,
	    			_csrf:token
	    });
    }
    
    function edit(idVal){
    	var formData= {
				id: idVal
		}
        $.ajax({
           	type:'POST',
               dataType: 'json',
               contentType: 'application/json',
               url:contexPath+'/module/load.json',
               data:JSON.stringify(formData),
			beforeSend: function(xhr) {
		        // setting a timeout
		        var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
		        xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.setRequestHeader(header, token);
		    },
               success: function(response){
               		if(response.status=="OK"){
               			$("#id").val(response.module.id);
               			$("#name").val(response.module.name);
               			$("#description").val(response.module.description);
               			if (response.module.enabled == 'Y'){
               				$('#enabled').prop('checked', true);
               			}else{
               				$('#enabled').prop('checked', false);
               			}
               			$("#author").val(response.module.author);
               			$("#moduleVersion").val(response.module.moduleVersion);
               		}
               } ,
               error: function (jqXHR, exception) {
                 console.log(jqXHR);
			  alert('Error: ' + jqXHR);
		  }  
        });
    }
    
    function clearFields(){
    		$("#id").val("");
			$("#name").val("");
			$("#description").val("");
			$('#enabled').attr('checked', false);
			$("#author").val("");
			$("#moduleVersion").val("");
    }
    
    function getPermissions(idVal){
    	
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
        $.ajax({
           	type:'POST',
               dataType: 'json',
               contentType: 'application/json',
               url:contexPath+'/module/enableDisable.json',
               data:JSON.stringify(formData),
			beforeSend: function(xhr) {
		        // setting a timeout
		        var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
		        xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.setRequestHeader(header, token);
		    },
               success: function(response){
               		if(response.status=="OK"){
               			$('#success').css({'display': ''});
               			$('#error').css({'display': 'none'});
               			$("#successMessage").text(response.message);
               			$("#success").delay( 2000 ).fadeOut( 500, "linear");
               		}else{
               			$('#success').css({'display': 'none'});
               			$('#error').css({'display': ''});
               			$("#errorMessage").text(response.message);
               			$("#error").delay( 2000 ).fadeOut( 500, "linear");
               		}
               } ,
               error: function (jqXHR, exception) {
                 console.log(jqXHR);
			  alert('Error: ' + jqXHR);
		  }  
        });
    	
    	
    }
    
    
    
    function cli(){
    	$('#Permissions').modal('hide');
    }
    
    
    $(document).ready(function() {
	    $('#datatable').DataTable();
	} );
	</script>
	<style type="text/css">
	
	</style>

</head>
<body>

<h1>Modules</h1>

<div class="alert alert-success" style="display: none;" id="success">
		<strong>Success!</strong> <span id="successMessage"></span>.
</div>
<div class="alert alert-danger" style="display: none;" id="error">
		<strong>Error!</strong> <span id="errorMessage"></span>.
	</div>

<c:if test="${status eq 'OK'}">
	<div class="alert alert-success alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong>Success!</strong> ${message}.
	</div>
</c:if>
<c:if test="${status eq 'ERROR'}">
	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong>Error!</strong> ${message}.
	</div>
</c:if>


<div class="panel panel-default col-xs-9">
<!-- <div class="panel-heading">User List Display tag</div> -->
  <div class="panel-body">   
   
   <sec:authorize access="hasRole('ROLE_ADMIN')">
   <button data-target="#Form" title="Add New" type="button" class="btn btn-default toltip" data-toggle="modal" onclick="clearFields();">
		<img src="<c:url value='/resources/img/icons/black/doc_new_icon&16.png' />"> Add New
	</button>
   </sec:authorize>
    <br/> <br/>
    
    
    
    <table id="datatable" align="center" class="table table-striped table-hover table-bordered">  
<thead>
<tr><th>Id</th><th>Name</th><th>Description</th><th>Author</th><th>Version</th><th>Enabled</th><th>Actions</th></tr>  
</thead>
<tbody>
   <c:forEach var="module" items="${list}">   
   <tr>  
   <td>${module.id}</td>  
   <td>${module.name}</td>  
   <td>${module.description}</td>  
   <td>${module.author}</td>
   <td>${module.moduleVersion}</td>
   <td>
   
   <c:set var="checkedActive" value=""></c:set>
		<c:if test="${'Y' eq module.enabled}">
			<c:set var="checkedActive" value="checked='true'"></c:set>
		</c:if>
         <input type="checkbox" name="select" id="select" ${checkedActive} onclick="enableAndDisable(this,${module.id});">
   
   </td>
   <td>
   
   <button title="Edit" onclick="edit(${module.id})" type="button" class="btn btn-link btn-xs toltip" data-toggle="modal" data-target="#Form"><img src="<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />"></button> | 
      <button title="Delete" onclick="remove(${module.id})" type="button" class="btn btn-link btn-xs toltip"><img src="<c:url value='/resources/img/icons/black/trash_icon&16.png' />"></button> | 
      <button title="Permissions by Module" onclick="getPermissions(${module.id})" type="button" class="btn btn-link btn-xs toltip" data-toggle="modal" data-target="#Permissions"><img src="<c:url value='/resources/img/icons/black/cogs_icon&16.png' />"></button>
      
   </td>
   </tr>  
   </c:forEach>  
   </tbody>
   </table>
   
   
   
   
    
    
    
<%--    <spring:url value="module" var="listURL"/> --%>
<%--    <display:table name="list" id="module" sort="list" requestURI="${listURL}" export="true" pagesize="10" defaultsort="1" defaultorder="descending" class="table table-striped"> --%>
<%--       <display:column property="id" title="ID" sortable="true"/> --%>
<%--       <display:column property="name" sortable="true"/> --%>
<%--       <display:column property="description" sortable="true"/> --%>
<%--       <display:column property="author" sortable="true"/> --%>
<%--       <display:column property="moduleVersion" sortable="true"/> --%>
<%--       <display:column media="html" title="Enabled"> --%>
<%--       	<c:set var="checkedActive" value=""></c:set> --%>
<%-- 		<c:if test="${'Y' eq module.enabled}"> --%>
<%-- 			<c:set var="checkedActive" value="checked='true'"></c:set> --%>
<%-- 		</c:if> --%>
<%--          <input type="checkbox" name="select" id="select" ${checkedActive} onclick="enableAndDisable(this,${module.id});"> --%>
<%--       </display:column> --%>
<%--       <display:column media="html" title="Actions"> --%>
<%--       <button title="Edit" onclick="edit(${module.id})" type="button" class="btn btn-link btn-xs toltip" data-toggle="modal" data-target="#Form"><img src="<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />"></button> |  --%>
<%--       <button title="Delete" onclick="remove(${module.id})" type="button" class="btn btn-link btn-xs toltip"><img src="<c:url value='/resources/img/icons/black/trash_icon&16.png' />"></button> |  --%>
<%--       <button title="Permissions by Module" onclick="getPermissions(${module.id})" type="button" class="btn btn-link btn-xs toltip" data-toggle="modal" data-target="#Permissions"><img src="<c:url value='/resources/img/icons/black/cogs_icon&16.png' />"></button> --%>
<%--       </display:column> --%>
<%--       <display:setProperty name="export.xml" value="false" /> --%>
<%--       <display:setProperty name="export.pdf.filename" value="userList.pdf"/> --%>
<%-- 		<display:setProperty name="export.excel.filename" value="userList.xls"/> --%>
<%-- 		<display:setProperty name="export.csv.filename" value="userList.csv"/> --%>
<%--     </display:table> --%>
</div></div>




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


http://www.jqueryscript.net/other/Searchable-Multi-selectable-Tree-jQuery-SearchAreaControl.html
http://www.jqueryscript.net/other/Collapsible-Tree-View-Checkboxes-jQuery-hummingbird.html
http://www.jqueryscript.net/other/Ajax-File-Tree-Plugin-jQuery-jsFiler.html
http://www.jqueryscript.net/other/Folding-Tree-Structures-jQuery-file-explore.html



      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" onclick="cli();">hide</button>
        <button type="button" class="btn btn-primary" onclick="save();">Save</button>
      </div>
      
    </div>
    
  </div>
</div>

</body>
</html>