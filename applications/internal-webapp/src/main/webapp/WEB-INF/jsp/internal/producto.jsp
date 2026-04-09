<%@ page import="com.internal.web.controller.ProductoController" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		var contexPath = "<%=request.getContextPath() %>";
		var controllerName = "<%=ProductoController.NAME %>";

		function numberOrNull(value){
			return value === "" ? null : parseFloat(value);
		}

		function integerOrNull(value){
			return value === "" ? null : parseInt(value);
		}

		function save(){
			var formData = {
				id: integerOrNull($("#id").val()),
				nombre: $("#nombre").val(),
				categoriaId: $("#categoriaId").val(),
				marcaId: $("#marcaId").val(),
				laboratorioId: $("#laboratorioId").val(),
				tipoproducto: $("#tipoproducto").val(),
				tipopresentacion: $("#tipopresentacion").val(),
				tipounidadmedida: $("#tipounidadmedida").val(),
				descripcion: $("#descripcion").val(),
				uso: $("#uso").val(),
				generico: $("#generico").val(),
				puedevenderse: $("#puedevenderse").val(),
				puedecomprarse: $("#puedecomprarse").val(),
				puedetransferirse: $("#puedetransferirse").val(),
				referenciainterna: $("#referenciainterna").val(),
				codigobarras: $("#codigobarras").val(),
				peso: numberOrNull($("#peso").val()),
				volumen: $("#volumen").val(),
				stockinicial: integerOrNull($("#stockinicial").val()),
				fraccioninicial: integerOrNull($("#fraccioninicial").val())
			}
			saveCRUD(controllerName, formData, "Form");
		}

		function edit(idVal){
			editCRUD(controllerName, { id: idVal });
		}

		function setFormFieldsFromServiceResponse(response){
			$("#id").val(response.viewBean.id);
			$("#nombre").val(response.viewBean.nombre);
			$("#categoriaId").val(response.viewBean.categoriaId).change();
			$("#marcaId").val(response.viewBean.marcaId).change();
			$("#laboratorioId").val(response.viewBean.laboratorioId).change();
			$("#tipoproducto").val(response.viewBean.tipoproducto);
			$("#tipopresentacion").val(response.viewBean.tipopresentacion);
			$("#tipounidadmedida").val(response.viewBean.tipounidadmedida);
			$("#descripcion").val(response.viewBean.descripcion);
			$("#uso").val(response.viewBean.uso);
			$("#generico").val(response.viewBean.generico);
			$("#puedevenderse").val(response.viewBean.puedevenderse);
			$("#puedecomprarse").val(response.viewBean.puedecomprarse);
			$("#puedetransferirse").val(response.viewBean.puedetransferirse);
			$("#referenciainterna").val(response.viewBean.referenciainterna);
			$("#codigobarras").val(response.viewBean.codigobarras);
			$("#peso").val(response.viewBean.peso);
			$("#volumen").val(response.viewBean.volumen);
			$("#stockinicial").val(response.viewBean.stockinicial);
			$("#fraccioninicial").val(response.viewBean.fraccioninicial);
		}

		function verifyNombre(object){
			verifyField(controllerName, "verifyNombre", "nombre", object.id, object.value);
		}

		function load(){
			var ajaxUrl = contexPath + "/" + controllerName + "/findByPage.json";
			var tableId = "#table";
			var fileTitle = "Producto";
			var jsonColumns = [
				{ "data": "id" },
				{ "data": "nombre" },
				{ "data": "categoria.nombre", "defaultContent": "" },
				{ "data": "marca.nombre", "defaultContent": "" },
				{ "data": "laboratorio.razonsocial", "defaultContent": "" },
				{ "data": "codigobarras", "defaultContent": "" },
				{ "data": "tipoproducto", "defaultContent": "" },
				{ "data": "stockinicial", "defaultContent": "" }
			];
			var columnsExport = [1,2,3,4,5,6,7];
			var jsonColumnDefs = [
				{
					data: null,
					"targets": 8,
					"render": function (data, type, row) {
						return "<td>" +
								makeButton("Edit", "edit(" + row.id + ")", "data-bs-toggle='modal' data-bs-target='#Form'", imgEdit) +
								"</td>";
					}
				}
			];
			createTableAjax(tableId, fileTitle, ajaxUrl, {}, jsonColumns, jsonColumnDefs, columnsExport, null);
			populateSelect("categoria", "enabledCategorias", "categoriaId");
			populateSelect("marca", "enabledMarcas", "marcaId");
			populateSelect("organizacion", "enabledOrganizacions", "laboratorioId");
		}

		function clearFields(){
			$(".bindingError").remove();
			$("#nombre").removeClass("is-invalid").removeClass("is-valid");
			$("#id").val("");
			$("#nombre").val("");
			$("#categoriaId").val("").change();
			$("#marcaId").val("").change();
			$("#laboratorioId").val("").change();
			$("#tipoproducto").val("");
			$("#tipopresentacion").val("");
			$("#tipounidadmedida").val("");
			$("#descripcion").val("");
			$("#uso").val("");
			$("#generico").val("");
			$("#puedevenderse").val("");
			$("#puedecomprarse").val("");
			$("#puedetransferirse").val("");
			$("#referenciainterna").val("");
			$("#codigobarras").val("");
			$("#peso").val("");
			$("#volumen").val("");
			$("#stockinicial").val("");
			$("#fraccioninicial").val("");
		}

		$(document).ready(function(){
			load();
			$(".selectForm").select2({
				dropdownParent: $("#Form"),
				width: "100%",
				theme: "bootstrap4"
			});
		});
	</script>
</head>
<body>

<h1>Producto</h1>

<div class="panel panel-default">
	<div class="panel-body">
		<div class="card flex-fill">
			<div class="card-header">
				<div class="dt-buttons btn-group">
					<button data-bs-target="#Form" title="Agregar Nuevo" type="button" class="btn btn-outline-primary toltip" data-bs-toggle="modal" onclick="clearFields();">
						<svg viewBox="0 0 24 24" width="16" height="16" stroke="currentColor" stroke-width="1.5" fill="none" stroke-linecap="round" stroke-linejoin="round" class="css-i6dzq1"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path><polyline points="14 2 14 8 20 8"></polyline><line x1="12" y1="18" x2="12" y2="12"></line><line x1="9" y1="15" x2="15" y2="15"></line></svg>
						Agregar Nuevo
					</button>
				</div>
			</div>
			<div class="card-body">
				<table id="table" align="center" class="table table-striped table-sm table-hover" style="width: 100%">
					<thead>
					<tr><th>Id</th><th>Nombre</th><th>Categoria</th><th>Marca</th><th>Laboratorio</th><th>Codigo de Barras</th><th>Tipo Producto</th><th>Stock Inicial</th><th>Acciones</th></tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="Form" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Producto</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<input type="hidden" id="id" name="id"/>

				<div class="form-container">
					<form class="form-horizontal">
						<div class="mb-3 row">
							<label for="nombre" class="col-sm-3 col-form-label">Nombre</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="nombre" placeholder="Nombre" onchange="verifyNombre(this);">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="categoriaId" class="col-sm-3 col-form-label">Categoria</label>
							<div class="col-sm-8">
								<select id="categoriaId" class="form-control input-sm selectForm">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="marcaId" class="col-sm-3 col-form-label">Marca</label>
							<div class="col-sm-8">
								<select id="marcaId" class="form-control input-sm selectForm">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="laboratorioId" class="col-sm-3 col-form-label">Laboratorio</label>
							<div class="col-sm-8">
								<select id="laboratorioId" class="form-control input-sm selectForm">
									<option value="">-- Seleccionar --</option>
								</select>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="tipoproducto" class="col-sm-3 col-form-label">Tipo Producto</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="tipoproducto" placeholder="Tipo Producto">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="tipopresentacion" class="col-sm-3 col-form-label">Tipo Presentacion</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="tipopresentacion" placeholder="Tipo Presentacion">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="tipounidadmedida" class="col-sm-3 col-form-label">Unidad Medida</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="tipounidadmedida" placeholder="Unidad de Medida">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="descripcion" class="col-sm-3 col-form-label">Descripcion</label>
							<div class="col-sm-8">
								<textarea class="form-control" id="descripcion" placeholder="Descripcion"></textarea>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="uso" class="col-sm-3 col-form-label">Uso</label>
							<div class="col-sm-8">
								<textarea class="form-control" id="uso" placeholder="Uso"></textarea>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="generico" class="col-sm-3 col-form-label">Generico</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="generico" placeholder="Generico">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="puedevenderse" class="col-sm-3 col-form-label">Puede Venderse</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="puedevenderse" placeholder="Puede Venderse">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="puedecomprarse" class="col-sm-3 col-form-label">Puede Comprarse</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="puedecomprarse" placeholder="Puede Comprarse">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="puedetransferirse" class="col-sm-3 col-form-label">Puede Transferirse</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="puedetransferirse" placeholder="Puede Transferirse">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="referenciainterna" class="col-sm-3 col-form-label">Referencia Interna</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="referenciainterna" placeholder="Referencia Interna">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="codigobarras" class="col-sm-3 col-form-label">Codigo Barras</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="codigobarras" placeholder="Codigo Barras">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="peso" class="col-sm-3 col-form-label">Peso</label>
							<div class="col-sm-8">
								<input type="text" class="form-control onlyDecimal" id="peso" placeholder="Peso">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="volumen" class="col-sm-3 col-form-label">Volumen</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="volumen" placeholder="Volumen">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="stockinicial" class="col-sm-3 col-form-label">Stock Inicial</label>
							<div class="col-sm-8">
								<input type="text" class="form-control onlyInteger" id="stockinicial" placeholder="Stock Inicial">
							</div>
						</div>
						<div class="mb-3 row">
							<label for="fraccioninicial" class="col-sm-3 col-form-label">Fraccion Inicial</label>
							<div class="col-sm-8">
								<input type="text" class="form-control onlyInteger" id="fraccioninicial" placeholder="Fraccion Inicial">
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
