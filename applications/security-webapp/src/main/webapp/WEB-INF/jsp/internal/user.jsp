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
	<title></title>
	<script type="text/javascript">
	
	$('#AddUser').on('shown.bs.modal', function () {})
	$('#EditUser').on('shown.bs.modal', function () {})
	$('#EditUserPassword').on('shown.bs.modal', function () {})
	$('#EditUserRole').on('shown.bs.modal', function () {})
	$('#AddPerson').on('shown.bs.modal', function () {})
	
	var contexPath = "<%=request.getContextPath() %>";
	
    
    function save(){
		var formData= { 
				fullName: $('#AfullName').val(),
				entidadId: $('#entidadId').val(),
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
            	  showErrorMessageByField('input','A'+key,value,'');
            	  showErrorMessageByField('select','A'+key,value,'');
//   	            $('input[id=A'+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
//   	          	$('select[id=A'+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
              });
            }
    	   
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
              
    }
    
    function savePerson(){
		var formData= { 
				tipoEntidadId: $('#tipoEntidadId').val(),
				tipoDocumentoIdentificaion: $('#tipoDocumentoIdentificaion').val(),
				numeroidentificacion: $('#numeroidentificacion').val(),
				nombres: $('#nombres').val(),
				apellidos: $('#apellidos').val(),
				tipoEstadoCivil: $('#tipoEstadoCivil').val(),
				sexo: $('#sexo').val(),
				fechanacimiento: $('#fechanacimiento').val(),
               	email: $('#email').val()
		}
		$('.bindingError').remove();
		
    	var ajaxUrl = contexPath+'/user/saveNewPerson.json';
    	var successFunction = function(response){
     	   if(response.validated){
               //Set response
    		   if(response.status=="OK"){
    			   $("#AfullName").val(response.viewBean.fullName);
    			   $("#entidadId").val(response.viewBean.entidad.id);
         			showSuccessMessage(response.message);
//          			load();
         			$('#AddPerson').modal('hide');
//          			clearPersonFields();
         		}else{
         			showErrorMessage(response.message);
         		}
            }else{
              //Set error messages
              $.each(response.messages,function(key,value){
            	  showErrorMessageByField('input',key,value,'');
            	  showErrorMessageByField('select',key,value,'');
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
            	  showErrorMessageByField('input','E'+key,value,'');
            	  showErrorMessageByField('select','E'+key,value,'');
//   	            $('input[id=E'+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
//   	          	$('select[id=E'+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
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
            	  showErrorMessageByField('input' , 'P'+key , value , '');
            	  showErrorMessageByField('select' , 'P'+key , value , '');
//   	            $('input[id=P'+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
//   	          	$('select[id=P'+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
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
            	  showErrorMessageByField('input' , key , value , '');
//   	            $('input[id='+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
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
       			$("#RuserName").text(response.viewBean.userName);
       			$("#RpersonaFullName").text(response.persona.fullName);
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
   		         	{ "data": "entityName" },
   		            { "data": "userName" },
   		            { "data": "status" },
   		            { "data": "question" },
   		            { "data": "answer" }
   		        ];
       			var columnsExport = [ 0, 1, 2, 3, 4 , 5];
       			var jsonColumnDefs = [
       	        	{
       	            	"targets": 6,
       	                "render": function ( data, type, row ) {
//        	                    return "<td><button title='Edit' onclick='edit("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#EditUser'><img src='<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />'></button>"+
//            	                 "<button title='Delete' onclick='remove("+row.id+")' type='button' class='btn btn-link btn-xs toltip'><img src='<c:url value='/resources/img/icons/black/trash_icon&16.png' />'></button>"+
//            	              	"<button title='Change Password' onclick='editPassword("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#EditUserPassword'><img src='<c:url value='/resources/img/icons/black/key_icon&16.png' />'></button>"+
//            	             "<button title='Change Roles by User' onclick='editUserRole("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#EditUserRole'><img src='<c:url value='/resources/img/icons/black/cogs_icon&16.png' />'></button></td>"
//            	              ;
       	                 return "<td>"+
       	                	makeButton("Edit","edit("+row.id+")","data-toggle='modal' data-target='#EditUser'","<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />")+
//        	              		makeButton("Delete","remove("+row.id+")","","<c:url value='/resources/img/icons/black/trash_icon&16.png' />")+
       	           			makeButton("Change Password","editPassword("+row.id+")","data-toggle='modal' data-target='#EditUserPassword'","<c:url value='/resources/img/icons/black/key_icon&16.png' />")+
       	       				makeButton("Change Roles by User","editUserRole("+row.id+")","data-toggle='modal' data-target='#EditUserRole'","<c:url value='/resources/img/icons/black/cogs_icon&16.png' />")
       	        			"</td>";
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
    
    function verifyUserName(object){
    	$('#bindingError'+object.id).remove();
    	var ajaxUrl = contexPath+'/user/verifyUserName?userName='+object.value;
    	var id = object.id;
    	var successFunction = function(response){
       		if(response.status=='OK'){
       			$( "#div-"+id ).removeClass( "has-error" ).addClass( "has-success" );
       		}else{
       			$( "#div-"+id ).removeClass( "has-success" ).addClass( "has-error" );
       			showErrorMessageByField('input' , id , response.message , '');
//        			$('input[id='+id+']').after('<span id="bindingErrorUserName" class="bindingError" style="color:red;font-weight: bold;">'+response.message+'</span>');
       		}
       };
       
       ajaxWithoutForm(ajaxUrl,"GET",successFunction);
    }
    
    
    function populateStatusSelect(){
    	var ajaxUrl = contexPath+'/user/userStatusType.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			$.each(response.data, function(i, row) {
                    $('#Astatus').append('<option value="' + row.id + '">' + row.codigo + '</option>');
                    $('#Estatus').append('<option value="' + row.id + '">' + row.codigo + '</option>');
                });
       		}
       };
       ajaxWithoutForm(ajaxUrl,"GET",successFunction);
    }
    
    function populateTypeDocumentSelect(){
    	var ajaxUrl = contexPath+'/user/personaDocumentoType.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			$.each(response.data, function(i, row) {
                    $('#tipoDocumentoIdentificaion').append('<option value="' + row.id + '">' + row.descripcion + '</option>');
                });
       		}
       };
       ajaxWithoutForm(ajaxUrl,"GET",successFunction);
    }
    
    function populateTypeEstadoCivilSelect(){
    	var ajaxUrl = contexPath+'/user/personaEstadoCivilType.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			$.each(response.data, function(i, row) {
                    $('#tipoEstadoCivil').append('<option value="' + row.id + '">' + row.descripcion + '</option>');
                });
       		}
       };
       ajaxWithoutForm(ajaxUrl,"GET",successFunction);
    }
    
    function autocompletePerson(){
    	$("#AfullName").autocomplete({
    	    minLength: 2,
    		source: function(request,response){
    			$("#entidadId").val("");
    			$.ajax({
    				url: contexPath+'/user/personaPorTermino.json?termino='+request.term,
    				type:'GET',
    				dataType: "json",
    				data: {
    					term: request.term
    				},
    				success: function (data){
    					response($.map(data, function (el) {
    	                     return {
    	                         label: el.fullName,
    	                         value: el.entidad.id
    	                     };
    	                 }));
    				}
    			})
    		},
    	    select: function(event, ui){
    	    	$("#entidadId").val(ui.item.value);
    	        this.value = ui.item.label;
    	        $(this).next("input").val(ui.item.value);
    	        event.preventDefault();
    	    }
    	});
    }
    
    function validateEmail(email) {
	    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(email);
    }
    
    function clearFields(){
		$('.bindingError').remove();
		$("#Aid").val("");
		$("#AfullName").val("");
		$("#entidadId").val("");
		$("#AuserName").val("");
		$("#Apassword").val("");
		$("#ApasswordAgain").val("");
		$('#Aquestion').val("");
		$("#Aanswer").val("");
		$("#Astatus").val("");
		$( "#div-AuserName" ).removeClass( "has-error" );
		$( "#div-AuserName" ).removeClass( "has-success" );
	}
    
    function clearPersonFields(){
		$('.bindingError').remove();
		$('#tipoDocumentoIdentificaion').val(""),
		$('#numeroidentificacion').val(""),
		$('#nombres').val(""),
		$('#apellidos').val(""),
		$('#tipoEstadoCivil').val(""),
		$('#sexo').val(""),
		$('#fechanacimiento').val(""),
       	$('#email').val("")
	}

	$(document).ready(function(){
    	load();
    	populateStatusSelect();
    	autocompletePerson();
    	populateTypeDocumentSelect();
    	populateTypeEstadoCivilSelect();
    	
    	$('#fechanacimiento').datepicker({
			format: 'yyyy/mm/dd',
            //todayBtn: 'linked',
            autoclose: true,
            language: 'es',
            todayHighlight: false
		});
    });
	
	
	
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

	</style>

</head>
<body>


<!-- <div class="container"> -->





<h1>Users</h1>



<div class="panel panel-default">
<!-- <div class="panel-heading">User List Display tag</div> -->
  <div class="panel-body">   
   
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
    

 <table id="table" align="center" class="table table-striped table-hover table-bordered">  
<thead>
<tr><th>Id</th><th>Entity Name</th><th>Username</th><th>status</th><th>question</th><th>answer</th><th>Actions</th></tr>  
</thead>
</table>

<br/> 
   Custom Export:  
   <a href="reportUserList/pdf">PDF</a> | <a href="reportUserListXls">Excel</a>
   
</div></div>


<!-- </div> -->

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
				    <label for="AfullName" class="col-sm-3 control-label">Full Name</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="AfullName" placeholder="Full Name"><button type="button" data-toggle="modal" class="btn btn-link" title="Register New" data-target="#AddPerson" onclick="clearPersonFields();">Register New</button>
				      <input type="hidden" class="form-control" id="entidadId">
				    </div>
				  </div>
				  <div class="form-group" id="div-AuserName">
				    <label for="AuserName" class="col-sm-3 control-label">Username</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="AuserName" placeholder="Username" onchange="verifyUserName(this);">
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
				  <div class="form-group" id="div-EuserName">
				    <label for="EuserName" class="col-sm-3 control-label">Username</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="EuserName" placeholder="Username" readonly="readonly">
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
				      <input type="text" class="form-control" id="PuserName" placeholder="Username" readonly="readonly">
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
  <div class="modal-dialog modal-lg" role="document">
  
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
				    <label for="RuserName" class="col-sm-3 control-label">User : </label>
				    <div class="col-sm-7">
				     <label id="RpersonaFullName"  class="control-label"></label> ( <label id="RuserName"  class="control-label"></label> ) 
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








<div class="modal fade" id="AddPerson" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
  
 <%--  <form id="moduleView" method="post" > --%>
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Add Person</h4>
      </div>
      <div class="modal-body">

		<div class="form-container">
		        
		        
		        
		        
		        <form class="form-horizontal">
		        <input type="hidden" id="tipoEntidadId" value="${tipoEntidadId}">
		        
		        <div class="form-group">
				    <label for="tipoDocumentoIdentificaion" class="col-sm-3 control-label">Document Type</label>
				    <div class="col-sm-7">
				      <select id="tipoDocumentoIdentificaion" class="form-control input-sm">
					      	<option value="">-- Select Option --</option>
					      </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="numeroidentificacion" class="col-sm-3 control-label">Identification Number</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="numeroidentificacion" placeholder="Identification Number">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="nombres" class="col-sm-3 control-label">First Name</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="nombres" placeholder="First Name">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="apellidos" class="col-sm-3 control-label">Last Name</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="apellidos" placeholder="Last Name">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="tipoEstadoCivil" class="col-sm-3 control-label">Civil Status</label>
				    <div class="col-sm-7">
				      <select id="tipoEstadoCivil" class="form-control input-sm">
					      	<option value="">-- Select Option --</option>
					      </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="sexo" class="col-sm-3 control-label">Gender</label>
				    <div class="col-sm-7">
				      <select id="sexo" class="form-control input-sm">
					      	<option value="">-- Select Option --</option>
					      	<option value="M">Male</option>
					      	<option value="F">Female</option>
					      </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="fechanacimiento" class="col-sm-3 control-label">Birth Date</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="fechanacimiento" placeholder="Bith Date">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="email" class="col-sm-3 control-label">E-mail</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="email" placeholder="E-mail">
				    </div>
				  </div>
			  </form>
		        
		        
		        
		       
		</div>



      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="savePerson();">Save</button>
      </div>
      
    </div>
    
    <%-- </form> --%>
  </div>
</div>





</body>
</html>