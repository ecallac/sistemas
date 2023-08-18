<%@ page import="com.internal.web.controller.TipoBaseController" %>
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
		var controllerName = "<%=TipoBaseController.NAME %>";
		var sEnabled="ENABLED";
		var sDisabled="DISABLED";

		function save(){
			var formData= {
				id: parseInt($("#id").val()),
				categoria: $('#categoria').val(),
				codigo: $('#codigo').val(),
				descripcion: $('#descripcion').val(),
				activo: $('#activo').val()
			}
			$('.bindingError').remove();

			var ajaxUrl = contexPath+'/'+controllerName+'/save.json';
			var successFunction = function(response){
				if(response.validated){
					//Set response
					if(response.status=="OK"){
						showSuccessMessage(response.message);
						var activo = $('#activoSearch').val();
						load(activo);
						$('#Form').modal('hide');
					}else{
						showErrorMessage(response.message);
					}
				}else{
					//Set error messages
					$.each(response.messages,function(key,value){
						showErrorMessageByField('input' , key , value , '');
						showErrorMessageByField('select' , key , value , '');
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
			var ajaxUrl = contexPath+'/'+controllerName+'/load.json';
			var successFunction = function(response){
				if(response.status=="OK"){
					clearFields();
					$("#id").val(response.viewBean.id);
					$('#categoria').val(response.viewBean.categoria),
							$('#codigo').val(response.viewBean.codigo),
							$("#descripcion").val(response.viewBean.descripcion);
					$('#activo').val(response.viewBean.activo).change();
				}else{
					showErrorMessage(response.message);
				}
			};

			ajaxPost(ajaxUrl,formData,successFunction);
		}

		function enableAndDisable(idVal,status){
			var formData= {
				id: idVal,
				activo:status
			}
			var ajaxUrl = contexPath+'/'+controllerName+'/enableDisable.json';
			var successFunction = function(response){
				if(response.status=="OK"){
					showSuccessMessage(response.message);
					var activo = $('#activoSearch').val();
					load(activo);
				}else{
					showErrorMessage(response.message);
				}
			};

			ajaxPost(ajaxUrl,formData,successFunction);


		}

		function enableAndDisableList(status){
			var rowIds= [];
			$.each(table.column(0).checkboxes.selected(), function(index, rowId){
				rowIds  [index]= rowId;
			});
			if (rowIds.length>0){
				var formData= {
					ids: rowIds,
					activo:status
				}
				var ajaxUrl = contexPath+'/'+controllerName+'/enableDisable.json';
				var successFunction = function(response){
					if(response.status=="OK"){
						showSuccessMessage(response.message);
						var activo = $('#activoSearch').val();
						load(activo);
					}else{
						showErrorMessage(response.message);
					}
				};

				ajaxPost(ajaxUrl,formData,successFunction);
			}

		}

		function verifyCodigo(object){
			$('.bindingError'+object.id).remove();
			var ajaxUrl = contexPath+'/'+controllerName+'/verifyCodigo?codigo='+object.value;
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
						$.each(response.data, function (i,row){
							$('#activo').append('<option value="' + row.codigo + '">' + row.descripcion + '</option>');
							$('#activoSearch').append('<option value="' + row.codigo + '">' + row.descripcion + '</option>');
						});
					}
				}else{
					showErrorMessage(response.message);
				}
			};
			ajaxPostWithoutForm(ajaxUrl,successFunction);
		}

		function load(activoSearch){
			var ajaxUrl = contexPath+'/'+controllerName+'/findByPage.json';
			var formData= {
				activo: activoSearch
			}
			var tableId = "#table";
			var fileTitle = "Tipo de Entidades";
			// var jsonData = response.data;
			var jsonColumns = [
				{ "data": "id" },
				{ "data": "categoria" },
				{ "data": "codigo"},
				{ "data": "descripcion"},
			];
			var columnsExport = [ 1,2,3,4];
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
					"targets": 4,
					"render": function ( data, type, row ) {
						if (type === "export"){
							return row.activoDescripcion;
						}else{
							return "<td><span class='badge rounded-pill bg-"+row.activoType+"'>"+row.activoDescripcion+"</span></td>";
						}
					}
				},
				{
					data: null,
					"targets": 5,
					"render": function ( data, type, row ) {
						var value = "";
						if (row.activo===sEnabled){
							value = makeButton("Inactivar","enableAndDisable("+row.id+",sDisabled)","","<svg viewBox='0 0 24 24' width='16' height='16' stroke='currentColor' stroke-width='2' fill='none' stroke-linecap='round' stroke-linejoin='round' class='css-i6dzq1'><path d='M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24'></path><line x1='1' y1='1' x2='23' y2='23'></line></svg>")
						}else if (row.activo===sDisabled){
							value = makeButton("Activar","enableAndDisable("+row.id+",sEnabled)","","<svg viewBox='0 0 24 24' width='16' height='16' stroke='currentColor' stroke-width='2' fill='none' stroke-linecap='round' stroke-linejoin='round' class='css-i6dzq1'><path d='M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z'></path><circle cx='12' cy='12' r='3'></circle></svg>")
						}
						return "<td>"+
								makeButton("Edit","edit("+row.id+")","data-bs-toggle='modal' data-bs-target='#Form'","<svg viewBox='0 0 24 24' width='16' height='16' stroke='currentColor' stroke-width='1.5' fill='none' stroke-linecap='round' stroke-linejoin='round' class='css-i6dzq1'><path d='M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7'></path><path d='M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z'></path></svg>")+
								value+
								"</td>" ;

					}
				}
			];

			var exportColumnCustom = {
				header: function (data, columnIdx) {
					return columnIdx === 4 ? "Estado" : data;
				},
				body: function (data, row, column, node) {
					return data;
				}
			}
			createTableAjax(tableId,fileTitle,ajaxUrl,formData,jsonColumns,jsonColumnDefs,columnsExport,exportColumnCustom);

		}

		function search(){
			var activo = $("#activoSearch").val();
			load(activo);
			$('#Filter').modal('hide');
		}

		function clearFields(){
			$('.bindingError').remove();
			$("#codigo").removeClass("is-invalid").removeClass("is-valid");
			$("#id").val("");
			$("#categoria").val("");
			$("#codigo").val("");
			$("#descripcion").val("");
			$('#activo').val("").change();
		}

		function clearFilter(){
			$('#activoSearch').val("").change();
		}
		function enableDisableButtons(){
			var cheked=false;
			const collection = document.getElementsByClassName("dt-checkboxes");
			for (let i = 0; i < collection.length; i++) {
				// console.log(collection[i].checked);
				if(collection[i].checked){
					cheked=true;
				}
			}
			if(cheked){
				$("#activar").attr("disabled", false);
				$("#inactivar").attr("disabled", false);
			}else{
				$("#activar").attr("disabled", true);
				$("#inactivar").attr("disabled", true);
			}
		}

		$(document).ready(function(){
			load('');
			populateStatusSelect();

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





<h1>Tipo de Atributos</h1>



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
					<tr><th></th><th>Categoria</th><th>Codigo</th><th>Descripcion</th><th>Estado</th><th>Acciones</th></tr>
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
				<h4 class="modal-title">Tipo de Atributo</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<input type="hidden" id="id" name="id"/>

				<div class="form-container">




					<form class="form-horizontal">
						<div class="mb-3 row">
							<label for="categoria" class="col-sm-3 col-form-label">Categoria</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="categoria" placeholder="Categoria">
							</div>
						</div>
						<div class="mb-3 row" id="div-codigo">
							<label for="codigo" class="col-sm-3 col-form-label">Codigo</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="codigo" placeholder="Codigo" onchange="verifyCodigo(this);">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="descripcion" class="col-sm-3 col-form-label">Descripcion</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="descripcion" placeholder="Descripcion">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="activo" class="col-sm-3 col-form-label">Estado</label>
							<div class="col-sm-7">
								<select id="activo" class="form-control input-sm selectForm">
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
							<label for="activoSearch" class="col-sm-3 col-form-label">Estado</label>
							<div class="col-sm-7">
								<select id="activoSearch" class="form-control input-sm selectFilter">
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