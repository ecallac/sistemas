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
		var formData= {
				id: parseInt($("#id").val()), 
               	nombre: $('#nombre').val(),
               	activo: $('#activo').val(),
               	parentAreaId: $('#parentAreaId').val()
		}
		$('.bindingError').remove();
		
    	var ajaxUrl = contexPath+'/area/save.json';
    	var successFunction = function(response){
     	   if(response.validated){
               //Set response
    		   if(response.status=="OK"){
         			showSuccessMessage(response.message);
         			load();
				   populateParentAreaSelect();
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
    	var ajaxUrl = contexPath+'/area/load.json';
    	var successFunction = function(response){
       		if(response.status=="OK"){
				clearFields();
       			$("#id").val(response.areaView.id);
       			$("#nombre").val(response.areaView.nombre);
       			$('#activo').val(response.areaView.activo).change();
       			$("#parentAreaId").val(response.areaView.parentArea.id).change();
       		}else{
				showErrorMessage(response.message);
			}
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
    }
    
    function enableAndDisable(idVal,status){
    	var activo = "DISABLED";
    	if (status===1) {
			activo = "ENABLED";
		}
    	var formData= {
				id: idVal,
				activo:activo
		}
    	var ajaxUrl = contexPath+'/area/enableDisable.json';
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
    function verifyNombre(object){
		$('.bindingError').remove();
		var ajaxUrl = contexPath+'/area/verifyNombre?nombre='+object.value;
		var key = object.id;
		var successFunction = function(response){
			if(response.status=="OK"){
				$("#"+id).removeClass("is-invalid").addClass("is-valid");
			}else{
				$("#"+id).removeClass("is-valid").addClass("is-invalid");
				showErrorMessageByField('input' , key , response.message , '');
			}
		};

		ajaxWithoutForm(ajaxUrl,"GET",successFunction);
	}

	function populateStatusSelect(){
		var ajaxUrl = contexPath+'/tipoBase/listStatus.json';
		var successFunction = function(response){
			if(response.status=="OK"){
				if(response.data.length>0){
					$('#activo').append('<option value="' + row.codigo + '">' + row.descripcion + '</option>');
					$('#activoSearch').append('<option value="' + row.codigo + '">' + row.descripcion + '</option>');
				}
			}else{
				showErrorMessage(response.message);
			}
		};
		ajaxPostWithoutForm(ajaxUrl,successFunction);
	}

	function populateParentAreaSelect(){
		var ajaxUrl = contexPath+'/area/enabledAreas.json';
		var successFunction = function(response){
			if(response.status=="OK"){
				if(response.data.length>0){
					$('#parentAreaId').empty();
					$('#parentAreaId').append('<option value="">-- Seleccionar --</option>');
					$('#parentSearch').empty();
					$('#parentSearch').append('<option value="">-- Seleccionar --</option>');
					$.each(response.data, function(i, row) {
						$('#parentAreaId').append('<option value="' + row.id + '">' + row.nombre + '</option>');
						$('#parentSearch').append('<option value="' + row.id + '">' + row.nombre + '</option>');
					});
				}
			}else{
				showErrorMessage(response.message);
			}
		};
		ajaxPostWithoutForm(ajaxUrl,successFunction);
	}

    function load(){
		var activoSearch = $('#activoSearch').val();
		var parentSearch = $('#parentSearch').val();
    	var ajaxUrl = contexPath+'/area/findByPage.json';
		var formData = {
			activo: activoSearch,
			parentAreaId: parentSearch
		}

		var tableId = "#table";
		var fileTitle = "Areas";
		var jsonColumns = [
			{ "data": "id" },
			{ "data": "nombre"},
			{ "data": "parentArea.nombre" ,"defaultContent": ""}
		];
		var columnsExport = [ 0, 1, 2,3];
		var jsonColumnDefs = [
			{
				data: null,
				"targets": 3,
				"render": function ( data, type, row ) {
					if (type==="export"){
						return row.activoDescripcion;
					}else{
						return "<td><span class='badge rounded-pill bg-"+row.activoType+"'>"+row.activoDescripcion+"</span></td>";
					}
				}
			},
			{
				data: null,
				"targets": 4,
				"render": function ( data, type, row ) {
					var value = "";
					if (row.activo==="ENABLED"){
						value = makeButton("Disable","enableAndDisable("+row.id+",0)","","Disable")
					}else if (row.activo==="DISABLED"){
						value = makeButton("Enable","enableAndDisable("+row.id+",1)","","Enable")
					}
					return "<td>"+
							makeButton("Edit","edit("+row.id+")","data-bs-toggle='modal' data-bs-target='#Form'","<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round' class='feather feather-edit align-middle me-2'><path d='M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7'></path><path d='M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z'></path></svg>")+
							value+
							"</td>" ;

				}
			}
		];
		var exportColumnCustom = {
			header: function (data, columnIdx) {
				return columnIdx === 3 ? "Estado" : data;
			},
			body: function (data, row, column, node) {
				return data;
			}
		}
		createTableAjax(tableId,fileTitle,ajaxUrl,jsonData,jsonColumns,jsonColumnDefs,columnsExport,exportColumnCustom);
    }

	function search(){
		load();
	}


    function clearFields(){
		$('.bindingError').remove();
		$("#nombre").removeClass("is-invalid").removeClass("is-valid");
		$("#id").val("");
		$("#nombre").val("");
		$('#activo').val("").change();
		$('#parentAreaId').val("").change();
	}

	function cleanFilter(){
		$('#activoSearch').val("").change();
		$('#parentSearch').val("").change();
	}
    
    $(document).ready(function(){

    	
    	load();
		populateStatusSelect();
		populateParentAreaSelect();

		// all select
		$('.select').select2({
            width: '100%',
            theme: 'bootstrap4'
        });
		//modal select
		$('.selectform').select2({
			dropdownParent: $('#Form'),
			width: '100%',
			theme: 'bootstrap4'
		});
		$('.selectfilter').select2({
			dropdownParent: $('#Form'),
			width: '100%',
			theme: 'bootstrap4'
		});
    });
    
    
	</script>
	<style type="text/css">
	
	</style>

</head>
<body>


<!-- <div class="container"> -->





<h1>Areas</h1>



<div class="panel panel-default">
<!-- <div class="panel-heading">User List Display tag</div> -->
  <div class="panel-body">

	  <div class="card flex-fill">
		  <div class="card-header">
			  <div class="dt-buttons btn-group">
				  <button data-bs-target="#Filter" title="Filtrar" type="button" class="btn btn-outline-primary toltip" data-bs-toggle="modal">
					  <svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round' class='feather feather-file-plus align-middle me-2'><path d='M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z'></path><polyline points='14 2 14 8 20 8'></polyline><line x1='12' y1='18' x2='12' y2='12'></line><line x1='9' y1='15' x2='15' y2='15'></line></svg>
					  Filtrar
				  </button>
				  <button data-bs-target="#Form" title="Agregar Nuevo" type="button" class="btn btn-outline-primary toltip" data-bs-toggle="modal" onclick="clearFields();">
					  <svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round' class='feather feather-file-plus align-middle me-2'><path d='M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z'></path><polyline points='14 2 14 8 20 8'></polyline><line x1='12' y1='18' x2='12' y2='12'></line><line x1='9' y1='15' x2='15' y2='15'></line></svg>
					  Agregar Nuevo
				  </button>
			  </div>
		  </div>
		  <div class="card-body">
			  <table id="table" align="center" class="table table-striped table-sm table-hover" style="width: 100%">
				  <thead>
				  <tr><th>Id</th><th>Nombre</th><th>Parent</th><th>Estado</th><th>Acciones</th></tr>
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
				<h4 class="modal-title">Area</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<input type="hidden" id="id" name="id"/>

				<div class="form-container">




					<form class="form-horizontal">
						<div class="mb-3 row">
							<label for="parentAreaId" class="col-sm-3 col-form-label">Parent</label>
							<div class="col-sm-7">
								<select id="parentAreaId" class="form-control input-sm selectform">
									<option value="">-- Select Option --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="nombre" class="col-sm-3 col-form-label">Nombre</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="nombre" placeholder="Nombre" onchange="verifyNombre(this);">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="activo" class="col-sm-3 col-form-label">Estado</label>
							<div class="col-sm-7">
								<select id="activo" class="form-control input-sm selectform">
								</select>
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



<div class="modal fade" id="Filter" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Filter</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<div class="form-container">




					<form class="form-horizontal">
						<div class="mb-3 row">
							<label for="activoSearch" class="col-sm-3 col-form-label">Estado</label>
							<div class="col-sm-7">
								<select id="activoSearch" class="form-control input-sm selectfilter">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="parentSearch" class="col-sm-3 col-form-label">Parent</label>
							<div class="col-sm-7">
								<select id="parentSearch" class="form-control input-sm selectfilter">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
					</form>




				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-secondary" onclick="cleanFilter();">Limpiar</button>
				<button type="button" class="btn btn-primary" onclick="search();">Aplicar</button>
			</div>
		</div>
	</div>
</div>










</body>
</html>