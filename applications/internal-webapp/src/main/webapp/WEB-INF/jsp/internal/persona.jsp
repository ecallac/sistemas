<%@ page import="com.internal.web.controller.PersonaController" %>
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
		var controllerName = "<%=PersonaController.NAME %>";
		var sEnabled="ENABLED";
		var sDisabled="DISABLED";

		function save(){
			var formData= {
				id: parseInt($("#id").val()),
				tipoDocumentoIdentificaion: $('#tipoDocumentoIdentificaion').val(),
				numeroidentificacion: $('#numeroidentificacion').val(),
				nombres: $('#nombres').val(),
				apellidos: $('#apellidos').val(),
				tipoEstadoCivil: $('#tipoEstadoCivil').val(),
				sexo: $('#sexo').val(),
				fechanacimiento: $('#fechanacimiento').val(),
				email: $('#email').val(),
				status: $('#status').val()
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
			$('#tipoDocumentoIdentificaion').val(response.viewBean.tipoDocumentoIdentificaion).change();
			$('#numeroidentificacion').val(response.viewBean.numeroidentificacion);
			$('#nombres').val(response.viewBean.nombres);
			$('#apellidos').val(response.viewBean.apellidos);
			$('#tipoEstadoCivil').val(response.viewBean.tipoEstadoCivil).change();
			$('#sexo').val(response.viewBean.sexo).change();
			$("#fechanacimiento").val(response.viewBean.fechanacimiento);
			$('#email').val(response.viewBean.email);
			$('#status').val(response.viewBean.status).change();
		}

		function enableAndDisable(idVal,status){
			var formData= {
				id: idVal,
				status:status
			}
			enableAndDisableCRUD(controllerName,formData);
		}

		function enableAndDisableList(status){
			var rowIds= datatableCheckboxSelectedRowList();
			if (rowIds.length>0){
				var formData= {
					ids: rowIds,
					status:status
				}
				enableAndDisableCRUD(controllerName,formData);
			}
		}

		function enableDisableButtons(){
			const arrayKey = ["activar","inactivar"];
			datatableEnableDisableButtons(arrayKey);
		}


		function load(){
			var ajaxUrl = contexPath+'/'+controllerName+'/findByPage.json';
			var status = $('#statusSearch').val();
			var tipoDocumentoIdentificaion = $('#tipoDocumentoIdentificaionSearch').val();
			var tipoEstadoCivil = $('#tipoEstadoCivilSearch').val();
			var formData= {
				status: status,
				tipoDocumentoIdentificaion: tipoDocumentoIdentificaion,
				tipoEstadoCivil: tipoEstadoCivil
			}
			var tableId = "#table";
			var fileTitle = "Persona";
			// var jsonData = response.data;
			var jsonColumns = [
				{ "data": "id" },
				{ "data": "tipoDocumentoIdentificaionDescripcion","defaultContent": ""},
				{ "data": "numeroidentificacion","defaultContent": ""},
				{ "data": "nombres","defaultContent": ""},
				{ "data": "apellidos","defaultContent": ""},
				{ "data": "tipoEstadoCivilDescripcion","defaultContent": ""},
				{ "data": "sexo","defaultContent": ""},
				{ "data": "fechanacimiento","defaultContent": ""},
				{ "data": "email","defaultContent": ""},
			];
			var columnsExport = [ 1,2,3,4,5,6,7,8,9];
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
					"targets": 9,
					"render": function ( data, type, row ) {
						if (type === "export"){
							return row.statusDescripcion;
						}else{
							return "<td><span class='badge rounded-pill bg-"+row.statusType+"'>"+row.statusDescripcion+"</span></td>";
						}
					}
				},
				{
					data: null,
					"targets": 10,
					"render": function ( data, type, row ) {
						var value = "";
						if (row.status===sEnabled){
							value = makeButton("Inactivar","enableAndDisable("+row.id+",sDisabled)","",imgEyeOff)
						}else if (row.status===sDisabled){
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
					return columnIdx === 9 ? "Estado" : data;
				},
				body: function (data, row, column, node) {
					return data;
				}
			}
			createTableAjax(tableId,fileTitle,ajaxUrl,formData,jsonColumns,jsonColumnDefs,columnsExport,exportColumnCustom);

		}

		function search(){
			load();
			$('#Filter').modal('hide');
		}

		function clearFields(){
			$('.bindingError').remove();
			$("#id").val("");
			$("#tipoDocumentoIdentificaion").val("").change();
			$("#numeroidentificacion").val("");
			$("#nombres").val("");
			$("#apellidos").val("");
			$("#tipoEstadoCivil").val("").change();
			$("#sexo").val("").change();
			$("#fechanacimiento").val("");
			$("#email").val("");
			$('#status').val("").change();
		}

		function clearFilter(){
			$('#tipoDocumentoIdentificaionSearch').val("").change();
			$('#tipoEstadoCivilSearch').val("").change();
			$('#statusSearch').val("").change();
		}

		$(document).ready(function(){
			load('');
			populateSelectByCategoriaType('status','TYPE_SWITCH');
			populateSelectByCategoriaType('statusSearch','TYPE_SWITCH');
			populateSelectByCategoriaType('tipoDocumentoIdentificaion','TYPE_PERSONA_DOCUMENTO');
			populateSelectByCategoriaType('tipoDocumentoIdentificaionSearch','TYPE_PERSONA_DOCUMENTO');
			populateSelectByCategoriaType('tipoEstadoCivil','TYPE_PERSONA_ESTADO_CIVIL');
			populateSelectByCategoriaType('tipoEstadoCivilSearch','TYPE_PERSONA_ESTADO_CIVIL');

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





<h1>Persona</h1>



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
					<tr><th></th><th>Tipo de Documento</th><th>Nro identificacion</th><th>Nombres</th><th>Apellidos</th><th>Estado Civil</th><th>Sexo</th><th>Fecha de Nacimiento</th><th>Email</th><th>Estado</th><th>Acciones</th></tr>
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
				<h4 class="modal-title">Persona</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<input type="hidden" id="id" name="id"/>

				<div class="form-container">




					<form class="form-horizontal">
						<div class="mb-3 row">
							<label for="tipoDocumentoIdentificaion" class="col-sm-3 col-form-label">Tipo de Documento</label>
							<div class="col-sm-7">
								<select id="tipoDocumentoIdentificaion" class="form-control input-sm selectForm">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row" id="div-codigo">
							<label for="numeroidentificacion" class="col-sm-3 col-form-label">Nro Identificacion</label>
							<div class="col-sm-7">
								<input type="text" class="form-control onlyText" id="numeroidentificacion" placeholder="Nro Identificacion">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="nombres" class="col-sm-3 col-form-label">Nombres</label>
							<div class="col-sm-7">
								<input type="text" class="form-control onlyText" id="nombres" placeholder="Nombres">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="apellidos" class="col-sm-3 col-form-label">Apellidos</label>
							<div class="col-sm-7">
								<input type="text" class="form-control onlyText" id="apellidos" placeholder="Apellidos">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="tipoEstadoCivil" class="col-sm-3 col-form-label">Estado Civil</label>
							<div class="col-sm-7">
								<select id="tipoEstadoCivil" class="form-control input-sm selectForm">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="sexo" class="col-sm-3 col-form-label">Sexo</label>
							<div class="col-sm-7">
								<select id="sexo" class="form-control input-sm selectForm">
									<option value="">-- Seleccionar --</option>
									<option value="M">Masculino</option>
									<option value="F">Femenino</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="fechanacimiento" class="col-sm-3 col-form-label">Fecha de Nacimiento</label>
							<div class="col-sm-7">
								<input type="text" class="form-control datePicker" id="fechanacimiento" placeholder="Fecha de Nacimiento" data-mask="0000/00/00">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="email" class="col-sm-3 col-form-label">Email</label>
							<div class="col-sm-7">
								<input type="text" class="form-control onlyText" id="email" placeholder="Email">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="status" class="col-sm-3 col-form-label">Estado</label>
							<div class="col-sm-7">
								<select id="status" class="form-control input-sm selectForm">
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
							<label for="tipoDocumentoIdentificaionSearch" class="col-sm-3 col-form-label">Tipo de Documento</label>
							<div class="col-sm-7">
								<select id="tipoDocumentoIdentificaionSearch" class="form-control input-sm selectFilter">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="tipoEstadoCivilSearch" class="col-sm-3 col-form-label">Estado Civil</label>
							<div class="col-sm-7">
								<select id="tipoEstadoCivilSearch" class="form-control input-sm selectFilter">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="statusSearch" class="col-sm-3 col-form-label">Estado</label>
							<div class="col-sm-7">
								<select id="statusSearch" class="form-control input-sm selectFilter">
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










</body>
</html>