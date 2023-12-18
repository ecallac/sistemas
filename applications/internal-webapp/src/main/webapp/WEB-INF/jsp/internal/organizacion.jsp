<%@ page import="com.internal.web.controller.OrganizacionController" %>
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
		var controllerName = "<%=OrganizacionController.NAME %>";
		var sEnabled="ENABLED";
		var sDisabled="DISABLED";

		function save(){
			var formData= {
				id: parseInt($("#id").val()),
				tipoOrganizacion: $('#tipoOrganizacion').val(),
				numeroidentificacion: $('#numeroidentificacion').val(),
				razonsocial: $('#razonsocial').val(),
				logo: $('#logo').val(),
				url: $('#url').val(),
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
			$('#tipoOrganizacion').val(response.viewBean.tipoOrganizacion).change();
			$('#numeroidentificacion').val(response.viewBean.numeroidentificacion);
			$('#razonsocial').val(response.viewBean.razonsocial);
			$('#logo').val(response.viewBean.logo);
			$('#url').val(response.viewBean.url);
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
			var tipoOrganizacion = $('#tipoOrganizacionSearch').val();
			var formData= {
				status: status,
				tipoOrganizacion: tipoOrganizacion
			}
			var tableId = "#table";
			var fileTitle = "Organizacion";
			// var jsonData = response.data;
			var jsonColumns = [
				{ "data": "id" },
				{ "data": "tipoOrganizacionDescripcion","defaultContent": ""},
				{ "data": "numeroidentificacion","defaultContent": ""},
				{ "data": "razonsocial","defaultContent": ""},
				{ "data": "logo","defaultContent": "",
					"render": function(data, type, row, meta){
						if(type == 'display'){
							data = "<img src='"+data+"' alt='logo' height='40px'>";
						}
						return data;
					}},
				{ "data": "url","defaultContent": "",
					"render": function(data, type, row, meta){
						if(type == 'display'){
							data = '<a href="' + data + '" target="_blank">' + data + '</a>';
						}
						return data;
					}},
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
							return row.statusDescripcion;
						}else{
							return "<td><span class='badge rounded-pill bg-"+row.statusType+"'>"+row.statusDescripcion+"</span></td>";
						}
					}
				},
				{
					data: null,
					"targets": 7,
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
								makeButton("Administrar Sucursales","managesucursales("+row.id+")","",imgList)+
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

		}

		function search(){
			load();
			$('#Filter').modal('hide');
		}

		function clearFields(){
			$('.bindingError').remove();
			$("#id").val("");
			$("#tipoOrganizacion").val("").change();
			$("#numeroidentificacion").val("");
			$("#razonsocial").val("");
			$("#logo").val("");
			$("#url").val("");
			$('#status').val("").change();
		}

		function clearFilter(){
			$('#tipoOrganizacionSearch').val("").change();
			$('#statusSearch').val("").change();
		}

		$(document).ready(function(){
			load('');
			populateSelectByCategoriaType('status','TYPE_SWITCH');
			populateSelectByCategoriaType('statusSearch','TYPE_SWITCH');
			populateSelectByCategoriaType('tipoOrganizacion','TYPE_ORGANIZACION');
			populateSelectByCategoriaType('tipoOrganizacionSearch','TYPE_ORGANIZACION');

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

		function managesucursales(id){
			window.location.href = contexPath+"/sucursal?organizacionId="+id;
		}
	</script>
	<style type="text/css">

	</style>

</head>
<body>


<!-- <div class="container"> -->





<h1>Organizacion</h1>



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
					<tr><th></th><th>Tipo</th><th>Nro identificacion</th><th>Razon Social</th><th>Logo</th><th>Url</th><th>Estado</th><th>Acciones</th></tr>
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
				<h4 class="modal-title">Organizacion</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<input type="hidden" id="id" name="id"/>

				<div class="form-container">




					<form class="form-horizontal">
						<div class="mb-3 row">
							<label for="tipoOrganizacion" class="col-sm-3 col-form-label">Tipo</label>
							<div class="col-sm-7">
								<select id="tipoOrganizacion" class="form-control input-sm selectForm">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row" id="div-codigo">
							<label for="numeroidentificacion" class="col-sm-3 col-form-label">Nro Identificacion</label>
							<div class="col-sm-7">
								<input type="text" class="form-control onlyInteger" id="numeroidentificacion" placeholder="Nro Identificacion">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="razonsocial" class="col-sm-3 col-form-label">Razon Social</label>
							<div class="col-sm-7">
								<input type="text" class="form-control onlyText" id="razonsocial" placeholder="Razon Social">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="logo" class="col-sm-3 col-form-label">Logo</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="logo" placeholder="Logo">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="url" class="col-sm-3 col-form-label">Url</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="url" placeholder="Url">
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
							<label for="tipoOrganizacionSearch" class="col-sm-3 col-form-label">Tipo</label>
							<div class="col-sm-7">
								<select id="tipoOrganizacionSearch" class="form-control input-sm selectFilter">
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