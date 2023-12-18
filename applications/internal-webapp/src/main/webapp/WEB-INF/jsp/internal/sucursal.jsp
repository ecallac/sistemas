<%@ page import="com.internal.web.controller.SucursalController" %>
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
		var controllerName = "<%=SucursalController.NAME %>";
		var sEnabled="ENABLED";
		var sDisabled="DISABLED";

		function save(){
			var formData= {
				id: parseInt($("#id").val()),
				nombre: $('#nombre').val(),
				nombrecorto: $('#nombrecorto').val(),
				tiposucursal: $('#tiposucursal').val(),
				organizacionId: $('#organizacionId').val(),
				direccionId: $('#direccionId').val(),
				estado: $('#estado').val()
			}
			saveCRUD(controllerName,formData,"Form");
		}

		function edit(idVal){
			var formData= {
				id: idVal
			}
			editCRUD(controllerName,formData);

		}
		function setFormFieldsFromServiceResponse(response){
			$("#id").val(response.viewBean.id);
			$('#nombre').val(response.viewBean.nombre),
			$("#nombrecorto").val(response.viewBean.nombrecorto);
			$('#tiposucursal').val(response.viewBean.tiposucursal).change();
			$('#organizacionId').val(response.viewBean.organizacionId).change();

			$('#direccionId').val(response.viewBean.direccionId).change();
			$('#estado').val(response.viewBean.estado).change();
		}

		function enableAndDisable(idVal,estado){
			var formData= {
				id: idVal,
				estado:estado
			}
			enableAndDisableCRUD(controllerName,formData);
		}

		function enableAndDisableList(estado){
			var rowIds= datatableCheckboxSelectedRowList();
			if (rowIds.length>0){
				var formData= {
					ids: rowIds,
					estado:estado
				}
				enableAndDisableCRUD(controllerName,formData);
			}
		}

		function verifyNombre(object){
			verifyField(controllerName,'verifyNombre','nombre',object.id,object.value);
		}

		function enableDisableButtons(){
			const arrayKey = ["activar","inactivar"];
			datatableEnableDisableButtons(arrayKey);
		}

		function load(){
			var estado = $("#estadoSearch").val();
			var tiposucursal = $("#tiposucursalSearch").val();
			var organizacionId = $("#organizacionIdSearch").val();
			var direccionId = $("#direccionIdSearch").val();
			var ajaxUrl = contexPath+'/'+controllerName+'/findByPage.json';
			var formData= {
				estado: estado,
				tiposucursal: tiposucursal,
				organizacionId: organizacionId,
				direccionId: direccionId
			}
			var tableId = "#table";
			var fileTitle = "Sucursal";
			// var jsonData = response.data;
			var jsonColumns = [
				{ "data": "id" },
				{ "data": "nombre"},
				{ "data": "nombrecorto"},
				{ "data": "tiposucursal","defaultContent": ""},
				{ "data": "organizacion.razonsocial","defaultContent": ""},
				{ "data": "direccion.ubicaionTotal","defaultContent": ""},
			];
			var columnsExport = [ 1,2,3,4,5,6];
			var jsonColumnDefs = [
				{
					data: null,
					"targets": 0,
					'checkboxes': {
						'selectRow': true
					}
				},
				{
					data: null,
					"targets": 6,
					"render": function ( data, type, row ) {
						if (type === "export"){
							return row.estadoDescripcion;
						}else{
							return "<td><span class='badge rounded-pill bg-"+row.estadoType+"'>"+row.estadoDescripcion+"</span></td>";
						}
					}
				},
				{
					data: null,
					"targets": 7,
					"render": function ( data, type, row ) {
						var value = "";
						if (row.estado===sEnabled){
							value = makeButton("Inactivar","enableAndDisable("+row.id+",sDisabled)","",imgEyeOff)
						}else if (row.estado===sDisabled){
							value = makeButton("Activar","enableAndDisable("+row.id+",sEnabled)","",imgEye)
						}
						return "<td>"+
								makeButton("Edit","edit("+row.id+")","data-bs-toggle='modal' data-bs-target='#Form'",imgEdit)+
								value+
								"</td>" ;

					}
				}
			];

			var exportColumnCustom = {
				header: function (data, columnIdx) {
					return columnIdx === 6 ? "Estado" : data;
				},
				body: function (data, row, column, node) {
					return data;
				}
			}
			createTableAjax(tableId,fileTitle,ajaxUrl,formData,jsonColumns,jsonColumnDefs,columnsExport,exportColumnCustom);

			// populateSelect('organizacion','enabledOrganizacions','organizacionId');
			// populateSelect('organizacion','enabledOrganizacions','organizacionIdSearch');
		}

		function search(){
			load();
			$('#Filter').modal('hide');
		}

		function clearFields(){
			$('.bindingError').remove();
			$("#nombre").removeClass("is-invalid").removeClass("is-valid");
			$("#id").val("");
			$("#nombre").val("");
			$("#nombrecorto").val("");
			$('#tiposucursal').val("").change();
			$('#organizacionId').val("").change();
			$('#direccionId').val("").change();
			$('#estado').val("").change();
		}

		function clearFilter(){
			$('#tiposucursalSearch').val("").change();
			$('#organizacionIdSearch').val("").change();
			$('#direccionIdSearch').val("").change();
			$('#estadoSearch').val("").change();
		}

		function clearDireccionFields(){

		}

		function populateDireccion(){
			var parentId = $('#organizacionId').val();


			// populateSelectByParent('direccion','enabledDireccions','direccionId','organizacionId');
			// var parentId = $('#organizacionId').val();
			if(parentId!=''){
				var options = {
					controller: 'direccion',
					service: 'enabledDireccions',
					key: 'direccionId',
					paramsAndValues: 'parentId='+parentId,
					addEachfunction: function (i, row) {
						// tu lógica personalizada para el each
						$('#direccionId').append('<option value="' + row.id + '">' + row.direccionexacta + '</option>');
					},
					addResponseTask: function (response) {
						// tu lógica personalizada para el response
						// if(response.entidadId!=null){
						// 	$("#entidadId").val(response.entidadId);
						// }
						if(response.organizacion.entidad.id!=null){
							$("#entidadId").val(response.organizacion.entidad.id);
							$("#Dorg").text(response.organizacion.razonsocial);
						}
					}
				};
				populateSelectBox(options);
				$("#nuevadireccion").attr("disabled", false);
			}else{
				$('#direccionId').empty();
				$('#direccionId').append('<option value="">-- Seleccionar --</option>');
				$("#nuevadireccion").attr("disabled", true);
			}
		}

		function populateDireccionSearch(){
			var parentId = $('#organizacionIdSearch').val();
			if(parentId!=''){
				var options = {
					controller: 'direccion',
					service: 'enabledDireccions',
					key: 'direccionIdSearch',
					paramsAndValues: 'parentId='+parentId,
					addEachfunction: function (i, row) {
						// tu lógica personalizada para el each
						$('#direccionIdSearch').append('<option value="' + row.id + '">' + row.direccionexacta + '</option>');
					}
				};
				populateSelectBox(options);
				// populateSelectByParent('direccion','enabledDireccions','direccionIdSearch','organizacionIdSearch');

			}else{
				$('#direccionIdSearch').empty();
				$('#direccionIdSearch').append('<option value="">-- Seleccionar --</option>');
				$("#nuevadireccion").attr("disabled", true);
			}
		}

		function saveDireccion(){
			var formData= {
				// id: parseInt($("#id").val()),
				direccionexacta: $('#direccionexacta').val(),
				codigopostal: $('#codigopostal').val(),
				entidadId: $('#entidadId').val(),
				ubigeoId: $('#ubigeoId').val(),
				esprincipal: $('#esprincipal').val(),
				referencia: $('#referencia').val(),
				estado: $('#Destado').val()
			}
			saveCRUD('direccion',formData,"direccionForm");
			populateDireccion();
			populateDireccionSearch();
		}

		function populatedepartamento(){
			var parentId = $('#pais').val();
			if(parentId!=''){
				var options = {
					controller: 'ubigeo',
					service: 'enabledUbigeos',
					key: 'departamento',
					paramsAndValues: 'parentubigeoId='+parentId,
					addEachfunction: function (i, row) {
						// tu lógica personalizada para el each
						$('#departamento').append('<option value="' + row.id + '">' + row.descripcion + '</option>');
					}
				};
				populateSelectBox(options);
				$('#provincia').empty();
				$('#provincia').append('<option value="">-- Seleccionar --</option>');
				$('#ubigeoId').empty();
				$('#ubigeoId').append('<option value="">-- Seleccionar --</option>');
			}else{
				$('#departamento').empty();
				$('#departamento').append('<option value="">-- Seleccionar --</option>');
			}

		}
		function populateprovincia(){
			var parentId = $('#departamento').val();
			if(parentId!=''){
				var options = {
					controller: 'ubigeo',
					service: 'enabledUbigeos',
					key: 'provincia',
					paramsAndValues: 'parentubigeoId='+parentId,
					addEachfunction: function (i, row) {
						// tu lógica personalizada para el each
						$('#provincia').append('<option value="' + row.id + '">' + row.descripcion + '</option>');
					}
				};
				populateSelectBox(options);
				$('#ubigeoId').empty();
				$('#ubigeoId').append('<option value="">-- Seleccionar --</option>');
			}else{
				$('#provincia').empty();
				$('#provincia').append('<option value="">-- Seleccionar --</option>');
			}
		}
		function populatedistrito(){
			var parentId = $('#provincia').val();
			if(parentId!=''){
				var options = {
					controller: 'ubigeo',
					service: 'enabledUbigeos',
					key: 'ubigeoId',
					paramsAndValues: 'parentubigeoId='+parentId,
					addEachfunction: function (i, row) {
						// tu lógica personalizada para el each
						$('#ubigeoId').append('<option value="' + row.id + '">' + row.descripcion + '</option>');
					}
				};
				populateSelectBox(options);
			}else{
				$('#ubigeoId').empty();
				$('#ubigeoId').append('<option value="">-- Seleccionar --</option>');
			}
		}

		$(document).ready(function(){
			load();
			populateSelectByCategoriaType('estado','TYPE_SWITCH');
			populateSelectByCategoriaType('estadoSearch','TYPE_SWITCH');

			populateSelectByCategoriaType('tiposucursal','TYPE_SUCURSAL');
			populateSelectByCategoriaType('tiposucursalSearch','TYPE_SUCURSAL');

			// var eachfunction=function(i, row) {
			// 	$('#organizacionId').append('<option value="' + row.id + '">' + row.razonsocial + '</option>');
			// }
			// populateSelectUsingEachFunction('organizacion','enabledOrganizacions','organizacionId',eachfunction);
			// eachfunction=function(i, row) {
			// 	$('#organizacionIdSearch').append('<option value="' + row.id + '">' + row.razonsocial + '</option>');
			// }
			// populateSelectUsingEachFunction('organizacion','enabledOrganizacions','organizacionIdSearch',eachfunction);

			var optionsorganizacionId = {
				controller: 'organizacion',
				service: 'enabledOrganizacions',
				key: 'organizacionId',
				addEachfunction: function (i, row) {
					// tu lógica personalizada para el each
					$('#organizacionId').append('<option value="' + row.id + '">' + row.razonsocial + '</option>');
				}
			};
			populateSelectBox(optionsorganizacionId);
			var optionsorganizacionIdSearch = {
				controller: 'organizacion',
				service: 'enabledOrganizacions',
				key: 'organizacionIdSearch',
				addEachfunction: function (i, row) {
					// tu lógica personalizada para el each
					$('#organizacionIdSearch').append('<option value="' + row.id + '">' + row.razonsocial + '</option>');
				}
			};
			populateSelectBox(optionsorganizacionIdSearch);

			var optionspais = {
				controller: 'ubigeo',
				service: 'enabledUbigeos',
				key: 'pais',
				// paramsAndValues: 'parentubigeoId='+null,
				addEachfunction: function (i, row) {
					// tu lógica personalizada para el each
					$('#pais').append('<option value="' + row.id + '">' + row.descripcion + '</option>');
				}
			};
			populateSelectBox(optionspais);

			populateSelectByCategoriaType('Destado','TYPE_SWITCH');


			$('.select').select2({
				width: '100%',
				theme: 'bootstrap4'
			});
			//modal select
			$('.selectForm').select2({
				dropdownParent: $('#Form'),
				width: '100%',
				theme: 'bootstrap4'
			});
			$('.selectFilter').select2({
				dropdownParent: $('#Filter'),
				width: '100%',
				theme: 'bootstrap4'
			});
			$('.selectDireccion').select2({
				dropdownParent: $('#direccionForm'),
				width: '100%',
				theme: 'bootstrap4'
			});


			$('#table').change(function() {
				enableDisableButtons();

			});
		});


	</script>
	<style type="text/css">

	</style>

</head>
<body>


<!-- <div class="container"> -->





<h1>Sucursal</h1>



<div class="panel panel-default">
	<!-- <div class="panel-heading">User List Display tag</div> -->
	<div class="panel-body">

		<div class="card flex-fill">
			<div class="card-header">
				<div class="dt-buttons btn-group">
					<button data-bs-target="#Filter" title="Filtrar" type="button" class="btn btn-outline-primary toltip" data-bs-toggle="modal">
						<svg viewBox='0 0 24 24' width='16' height='16' stroke='currentColor' stroke-width='2' fill='none' stroke-linecap='round' stroke-linejoin='round' class='css-i6dzq1'><polygon points='22 3 2 3 10 12.46 10 19 14 21 14 12.46 22 3'></polygon></svg>
						Filtrar
					</button>
					<button id="activar" title="Activar" type="button" class="btn btn-outline-primary toltip" onclick="enableAndDisableList(sEnabled);" disabled="true">
						<svg viewBox='0 0 24 24' width='16' height='16' stroke='currentColor' stroke-width='2' fill='none' stroke-linecap='round' stroke-linejoin='round' class='css-i6dzq1'><path d='M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z'></path><circle cx='12' cy='12' r='3'></circle></svg>
						Activar
					</button>
					<button id="inactivar" title="Inactivar" type="button" class="btn btn-outline-primary toltip" onclick="enableAndDisableList(sDisabled);" disabled="true">
						<svg viewBox='0 0 24 24' width='16' height='16' stroke='currentColor' stroke-width='2' fill='none' stroke-linecap='round' stroke-linejoin='round' class='css-i6dzq1'><path d='M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24'></path><line x1='1' y1='1' x2='23' y2='23'></line></svg>
						Inactivar
					</button>
					<button data-bs-target="#Form" title="Agregar Nuevo" type="button" class="btn btn-outline-primary toltip" data-bs-toggle="modal" onclick="clearFields();">
						<svg viewBox="0 0 24 24" width="16" height="16" stroke="currentColor" stroke-width="1.5" fill="none" stroke-linecap="round" stroke-linejoin="round" class="css-i6dzq1"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path><polyline points="14 2 14 8 20 8"></polyline><line x1="12" y1="18" x2="12" y2="12"></line><line x1="9" y1="15" x2="15" y2="15"></line></svg>
						Agregar Nuevo
					</button>
				</div>
			</div>
			<div class="card-body">
				<table id="table" align="center" class="table table-striped table-sm table-hover" style="width: 100%">
					<thead>
					<tr><th></th><th>Nombre</th><th>Nombre Corto</th><th>Tipo de Sucursal</th><th>Organizacion</th><th>Direccion</th><th>Estado</th><th>Acciones</th></tr>
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
				<h4 class="modal-title">Sucursal</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<input type="hidden" id="id" name="id"/>

				<div class="form-container">




					<form class="form-horizontal">
						<div class="mb-3 row" id="div-codigo">
							<label for="nombre" class="col-sm-3 col-form-label">Nombre</label>
							<div class="col-sm-7">
								<input type="text" class="form-control onlyText" id="nombre" placeholder="Nombre">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="nombrecorto" class="col-sm-3 col-form-label">Nombre Corto</label>
							<div class="col-sm-7">
								<input type="text" class="form-control onlyText" id="nombrecorto" placeholder="Nombre Corto">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="tiposucursal" class="col-sm-3 col-form-label">Tipo de Sucursal</label>
							<div class="col-sm-7">
								<select id="tiposucursal" class="form-control input-sm selectForm">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="organizacionId" class="col-sm-3 col-form-label">Organizacion</label>
							<div class="col-sm-7">
								<select id="organizacionId" class="form-control input-sm selectForm" onchange="populateDireccion();">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="direccionId" class="col-sm-3 col-form-label">Direccion</label>
							<div class="col-sm-7">
								<select id="direccionId" class="form-control input-sm selectForm">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
							<div class="col-sm-1">
								<button id="nuevadireccion" data-bs-target="#direccionForm" title="Agregar Direccion" type="button" class="btn btn-outline-primary toltip" data-bs-toggle="modal" onclick="clearDireccionFields();">
									<svg viewBox="0 0 24 24" width="16" height="16" stroke="currentColor" stroke-width="1.5" fill="none" stroke-linecap="round" stroke-linejoin="round" class="css-i6dzq1"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path><polyline points="14 2 14 8 20 8"></polyline><line x1="12" y1="18" x2="12" y2="12"></line><line x1="9" y1="15" x2="15" y2="15"></line></svg>
								</button>
							</div>

						</div>
						<div class="mb-3 row">
							<label for="estado" class="col-sm-3 col-form-label">Estado</label>
							<div class="col-sm-7">
								<select id="estado" class="form-control input-sm selectForm">
									<option value="">-- Seleccionar --</option>
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
				<h4 class="modal-title">Filtrar</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<div class="form-container">




					<form class="form-horizontal">
						<div class="mb-3 row">
							<label for="tiposucursalSearch" class="col-sm-3 col-form-label">Tipo de Sucursal</label>
							<div class="col-sm-7">
								<select id="tiposucursalSearch" class="form-control input-sm selectFilter">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="organizacionIdSearch" class="col-sm-3 col-form-label">Organizacion</label>
							<div class="col-sm-7">
								<select id="organizacionIdSearch" class="form-control input-sm selectFilter" onchange="populateDireccionSearch();">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="direccionIdSearch" class="col-sm-3 col-form-label">Direccion</label>
							<div class="col-sm-7">
								<select id="direccionIdSearch" class="form-control input-sm selectFilter">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="estadoSearch" class="col-sm-3 col-form-label">Estado</label>
							<div class="col-sm-7">
								<select id="estadoSearch" class="form-control input-sm selectFilter">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
					</form>




				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-secondary" onclick="clearFilter();">Limpiar</button>
				<button type="button" class="btn btn-primary" onclick="search();">Aplicar</button>
			</div>
		</div>
	</div>
</div>





<div class="modal fade" id="direccionForm" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Direccion</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<div class="form-container">

					<input type="hidden" id="entidadId" name="entidadId"/>


					<form class="form-horizontal">
						<div class="mb-3 row">
							<label for="Dorg" class="col-sm-3 col-form-label">Organizacion</label>
							<div class="col-sm-7">
								<span class="ms-2" id="Dorg"></span>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="pais" class="col-sm-3 col-form-label">Pais</label>
							<div class="col-sm-7">
								<select id="pais" class="form-control input-sm selectDireccion" onchange="populatedepartamento();">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="departamento" class="col-sm-3 col-form-label">Departamento</label>
							<div class="col-sm-7">
								<select id="departamento" class="form-control input-sm selectDireccion" onchange="populateprovincia();">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="provincia" class="col-sm-3 col-form-label">Provincia</label>
							<div class="col-sm-7">
								<select id="provincia" class="form-control input-sm selectDireccion" onchange="populatedistrito();">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="ubigeoId" class="col-sm-3 col-form-label">Distrito</label>
							<div class="col-sm-7">
								<select id="ubigeoId" class="form-control input-sm selectDireccion">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="direccionexacta" class="col-sm-3 col-form-label">Direccion Exacta</label>
							<div class="col-sm-7">
								<input type="text" class="form-control onlyText" id="direccionexacta" placeholder="Direccion Exacta">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="codigopostal" class="col-sm-3 col-form-label">Codigo Postal</label>
							<div class="col-sm-7">
								<input type="text" class="form-control onlyText" id="codigopostal" placeholder="Codigo Postal">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="referencia" class="col-sm-3 col-form-label">Referencia</label>
							<div class="col-sm-7">
								<input type="text" class="form-control onlyText" id="referencia" placeholder="Referencia">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="esprincipal" class="col-sm-3 col-form-label">Es Principal</label>
							<div class="col-sm-7">
								<select id="esprincipal" class="form-control input-sm selectDireccion">
									<option value="NO">NO</option>
									<option value="SI">SI</option>
								</select>
							</div>
						</div>

						<div class="mb-3 row">
							<label for="Destado" class="col-sm-3 col-form-label">Estado</label>
							<div class="col-sm-7">
								<select id="Destado" class="form-control input-sm selectDireccion">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
					</form>




				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-primary" onclick="saveDireccion();">Guardar</button>
			</div>
		</div>
	</div>
</div>







</body>
</html>