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
	
	
	
	var contexPath = "<%=request.getContextPath() %>";
	
    function save(){
    	var activo = "N";
    	if ($('#activo').is(':checked')) {
			activo = "Y";
		}
		var formData= {
				id: parseInt($("#id").val()), 
               	nombre: $('#nombre').val(),
               	activo: activo,
				salariomin: parseFloat($('#salariomin').val()),
				salariomax: parseFloat($('#salariomax').val()),
               	parentCargoId: $('#parentCargoId').val()
		}
		$('.bindingError').remove();
		
    	var ajaxUrl = contexPath+'/cargo/save.json';
    	var successFunction = function(response){
     	   if(response.validated){
               //Set response
    		   if(response.status=="OK"){
         			showSuccessMessage(response.message);
         			load();
				   populateParentCargoSelect();
         			$('#Form').modal('hide');
         		}else{
         			showErrorMessage(response.message);
         		}
            }else{
              //Set error messages
              $.each(response.messages,function(key,value){
            	  showErrorMessageByField('input' , key , value , '');
            	  showErrorMessageByField('select' , key , value , '');
//   	            $('input[id='+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
//   	          	$('select[id='+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;">'+value+'</span>');
              });
            }
    	   
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
              
    }

    
    function edit(idVal){
    	var formData= {
				id: idVal
		}
    	$('.bindingError').remove();
    	var ajaxUrl = contexPath+'/cargo/load.json';
    	var successFunction = function(response){
       		if(response.status=="OK"){
       			$("#id").val(response.cargoView.id);
       			$("#nombre").val(response.cargoView.nombre);
       			if (response.cargoView.activo == 'Y'){
       				$('#activo').prop('checked', true);
       			}else{
       				$('#activo').prop('checked', false);
       			}
				$("#salariomin").val(response.cargoView.salariomin);
				$("#salariomax").val(response.cargoView.salariomax);
       			$("#parentCargoId").val(response.cargoView.parentCargo.id);
       			//populateParentCargoSelectByModuleId(response.cargoView.module.id, response.cargoView.parentCargo.id);
       		}else{
				showErrorMessage(response.message);
			}
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
    }
    
    function enableAndDisable(object,idVal){
    	var activo = "N";
    	if (object.checked) {
			activo = "Y";
		}
    	var formData= {
				id: idVal,
				activo:activo
		}
    	var ajaxUrl = contexPath+'/cargo/enableDisable.json';
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
    	var ajaxUrl = contexPath+'/cargo/list.json';
    	var successFunction = function(response){
			if(response.status=="OK"){
				if(response.data.length>0){

					var tableId = "#table";
					var fileTitle = "Cargos";
					var jsonData = response.data;
					var jsonColumns = [
						{ "data": "id" },
						{ "data": "nombre"},
						{ "data": "salariomin"},
						{ "data": "salariomax"},
						{ "data": "parentCargo.nombre" ,"defaultContent": ""}
					];
					var columnsExport = [ 0, 1, 2, 3 ,4];
					var jsonColumnDefs = [
						{
							data: null,
							"targets": 5,
							"render": function ( data, type, row ) {
								var checkedActive='';
								if (row.activo == 'Y'){
									checkedActive = "checked='true'";
								}
								return "<td><input type='checkbox' name='select' id='select' "+checkedActive+" onclick='enableAndDisable(this,"+row.id+");'></td>";
							}
						},
						{
							data: null,
							"targets": 6,
							"render": function ( data, type, row ) {
								return "<td>"+
										makeButton("Edit","edit("+row.id+")","data-bs-toggle='modal' data-bs-target='#Form'","<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round' class='feather feather-edit align-middle me-2'><path d='M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7'></path><path d='M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z'></path></svg>")+
										// 		       	                 makeButton("Delete","remove("+row.id+")","","<c:url value='/resources/img/icons/black/trash_icon&16.png' />")+
										//        	                    "<button title='Edit' onclick='edit("+row.id+")' type='button' class='btn btn-link btn-xs toltip' data-toggle='modal' data-target='#Form'><img src='<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />'></button>"+
										//            	                 "<button title='Delete' onclick='remove("+row.id+")' type='button' class='btn btn-link btn-xs toltip'><img src='<c:url value='/resources/img/icons/black/trash_icon&16.png' />'></button> "
										"</td>" ;

							}
						}
					];

					createTable(tableId,fileTitle,jsonData,jsonColumns,jsonColumnDefs,columnsExport);

				}
			}else{
				showErrorMessage(response.message);
			}
			populateParentCargoSelect();
       };
       ajaxPostWithoutForm(ajaxUrl,successFunction);
    }

	function populateParentCargoSelect(){
		var ajaxUrl = contexPath+'/cargo/enabledCargos.json';
		var successFunction = function(response){
			if(response.status=="OK"){
				if(response.data.length>0){
					$('#parentCargoId').empty();
					$('#parentCargoId').append('<option value="">-- Select Option --</option>');
					$.each(response.data, function(i, row) {
						$('#parentCargoId').append('<option value="' + row.id + '">' + row.nombre + '</option>');
					});
				}
			}else{
				showErrorMessage(response.message);
			}

		};
		ajaxPostWithoutForm(ajaxUrl,successFunction);
	}

    function clearFields(){
		$('.bindingError').remove();
		$("#id").val("");
		$("#nombre").val("");
		$("#salariomin").val("");
		$("#salariomax").val("");
		$('#activo').prop('checked', false);
		populateParentCargoSelect();
	}
    
    $(document).ready(function(){
//     	$('select').select2({
//             dropdownParent: $('#Form'),
//             theme: 'bootstrap4'
//         });
    	
    	load();
		onlyDecimal('#salariomin');
		onlyDecimal('#salariomax');


    });
    
    
	</script>
	<style type="text/css">
	
	</style>

</head>
<body>


<!-- <div class="container"> -->





<h1>Cargos</h1>



<div class="panel panel-default">
<!-- <div class="panel-heading">User List Display tag</div> -->
  <div class="panel-body">

	  <div class="card flex-fill">
		  <div class="card-header">
			  <div class="dt-buttons btn-group">
				  <button data-bs-target="#Form" title="Agregar Nuevo" type="button" class="btn btn-outline-primary toltip" data-bs-toggle="modal" onclick="clearFields();">
					  <svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round' class='feather feather-file-plus align-middle me-2'><path d='M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z'></path><polyline points='14 2 14 8 20 8'></polyline><line x1='12' y1='18' x2='12' y2='12'></line><line x1='9' y1='15' x2='15' y2='15'></line></svg>
					  Agregar Nuevo
				  </button>
			  </div>
		  </div>
		  <div class="card-body">
			  <table id="table" align="center" class="table table-striped table-sm table-hover" style="width: 100%">
				  <thead>
				  <tr><th>Id</th><th>Nombre</th><th>Salario Minimo</th><th>Salario Maximo</th><th>Parent</th><th>Enabled</th><th>Acciones</th></tr>
				  </thead>
			  </table>
		  </div>
	  </div>



</div></div>


<!-- </div> -->






<!-- Modal -->
<div class="modal fade" id="Form" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Cargo</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<input type="hidden" id="id" name="id"/>

				<div class="form-container">




					<form class="form-horizontal">
						<div class="mb-3 row">
							<label for="parentCargoId" class="col-sm-3 col-form-label">Parent</label>
							<div class="col-sm-7">
								<select id="parentCargoId" class="form-control">
									<option value="">-- Select Option --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="nombre" class="col-sm-3 col-form-label">Nombre</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="nombre" placeholder="Nombre">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="salariomin" class="col-sm-3 col-form-label">Salario Minimo</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="salariomin" placeholder="Salario Minimo">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="salariomax" class="col-sm-3 col-form-label">Salario Maximo</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="salariomax" placeholder="Salario Maximo">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="activo" class="col-sm-3 col-form-label">Activo</label>
							<div class="col-sm-7">
								<div class="checkbox">
									<label>
										<input type="checkbox" id="activo" name="activo" />
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













</body>
</html>