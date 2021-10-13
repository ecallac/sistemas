<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
	
	$('#Form').draggable({
    	handle: ".modal-header"
	}); 
	
	var contexPath = "<%=request.getContextPath() %>/vehiculo";
	
    function save(){
		var formData= {
				id: parseInt($("#id").val()), 
				modeloId: $('#modeloId').val(), 
				claseId: $('#claseId').val(), 
				matricula: $('#matricula').val(), 
				nasientos: $('#nasientos').val(), 
				transmision: $('#transmision').val(), 
				cuentaxdia: parseFloat($('#cuentaxdia').val()), 
				cilindrada: parseInt($('#cilindrada').val()), 
				anio: parseInt($('#anio').val()), 
				combustible: $('#combustible').val(), 
				color: $('#color').val(), 
				descripsion: $('#descripsion').val()
		}
		$('.bindingError').remove();
		
    	var ajaxUrl = contexPath+'/save.json';
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
            	  showErrorMessageByField('select' , key , value , '');
              });
            }
    	   
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
              
    }
    
    
    
    function remove(idVal){
    	var formData= {
				id: idVal
		}
    	var ajaxUrl = contexPath+'/delete.json';
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
    	var ajaxUrl = contexPath+'/load.json';
    	var successFunction = function(response){
       		if(response.status=="OK"){
       			$("#id").val(response.viewBean.id);
       			$("#modeloId").val(response.viewBean.modeloId);
       			$("#claseId").val(response.viewBean.claseId);
       			$("#matricula").val(response.viewBean.matricula);
       			$("#nasientos").val(response.viewBean.nasientos);
       			$("#transmision").val(response.viewBean.transmision);
       			$("#cuentaxdia").val(response.viewBean.cuentaxdia);
       			$("#cilindrada").val(response.viewBean.cilindrada);
       			$("#anio").val(response.viewBean.anio);
       			$("#combustible").val(response.viewBean.combustible);
       			$("#color").val(response.viewBean.color);
       			$("#descripsion").val(response.viewBean.descripsion);
       		}
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
    }
    
    function load(){
    	var ajaxUrl = contexPath+'/list.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			
       			var tableId = "#table";
       			var fileTitle = "Marca List";
       			var jsonData = response.data;
       			var jsonColumns = [
   		            { "data": "id" },
   		            { "data": "modelo.descripsion" },
   		            { "data": "clase.descripsion" },
   		            { "data": "matricula" },
   		            { "data": "nasientos" },
   		            { "data": "transmision" },
   		            { "data": "cuentaxdia" },
   		            { "data": "cilindrada" },
   		            { "data": "anio" },
   		            { "data": "combustible" },
   		            { "data": "color" },
   		            { "data": "descripsion" }
   		        ];
       			var columnsExport = [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];
       			var jsonColumnDefs = [
       	        	{
       	            	"targets": 12,
       	                "render": function ( data, type, row ) {
       	                    return "<td>"+
       	                 		makeButton("Edit","edit("+row.id+")","data-toggle='modal' data-target='#Form'","<c:url value='/resources/static/img/icons/black/doc_edit_icon&16.png' />")+
       	              			makeButton("Delete","remove("+row.id+")","","<c:url value='/resources/static/img/icons/black/trash_icon&16.png' />")+
           	              		"</td>";
       	                }
       	            }
       	        ];
       			
       			createTable(tableId,fileTitle,jsonData,jsonColumns,jsonColumnDefs,columnsExport);
       			
       		}
       };
       
       ajaxPostWithoutForm(ajaxUrl,successFunction);
       
    }
    
	var contexPathSource = "<%=request.getContextPath() %>";
    
    function populateMarcasSelect(){
    	var ajaxUrl = contexPathSource+'/marca/list.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			$.each(response.data, function(i, row) {
                    $('#marcaId').append('<option value="' + row.id + '">' + row.descripsion + '</option>');
                });
       		}
       };
       ajaxPostWithoutForm(ajaxUrl,successFunction);
    }
    function populateClasesSelect(){
    	var ajaxUrl = contexPathSource+'/clase/list.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			$.each(response.data, function(i, row) {
                    $('#claseId').append('<option value="' + row.id + '">' + row.descripsion + '</option>');
                });
       		}
       };
       ajaxPostWithoutForm(ajaxUrl,successFunction);
    }
    
    function populateModelosSelect(marcaIdVal){
    	$('#modeloId')
	        .find('option')
	        .remove()
	        .end()
	        .append('<option value="">-- Select Option --</option>')
	        .val('')
	    ;
    	var formData= {
				marcaId: marcaIdVal
		}
    	var ajaxUrl = contexPathSource+'/modelo/listByMarca.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			$.each(response.data, function(i, row) {
                    $('#modeloId').append('<option value="' + row.id + '">' + row.descripsion + '</option>');
                });
       		}
       };
       ajaxPost(ajaxUrl,formData,successFunction);
    }
    
    function clearFields(){
		$('.bindingError').remove();
		$("#id").val("");
		$("#marcaId").val("");
		$("#modeloId").val("");
		$("#claseId").val("");
		$("#matricula").val("");
		$("#nasientos").val("");
		$("#transmision").val("");
		$("#cuentaxdia").val("");
		$("#cilindrada").val("");
		$("#anio").val("");
		$("#combustible").val("");
		$("#color").val("");
		$("#descripsion").val("");
	}
    
    $(document).ready(function(){
    	load();
    	populateMarcasSelect();
    	populateClasesSelect()
    });
    
	</script>
	<style type="text/css">
	
	</style>

</head>
<body>


<!-- <div class="container"> -->





<h1>${tittle}s</h1>



<div class="panel panel-default">
  <div class="panel-body">   
   
<div id="button_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">
<div class="dt-buttons btn-group">              
<button data-target="#Form" title="Add New" type="button" class="btn btn-default toltip" data-toggle="modal" onclick="clearFields();">
		<img src="<c:url value='/resources/static/img/icons/black/doc_new_icon&16.png' />"> Add New
	</button>
</div>
</div>
<br>
    

 <table id="table" align="center" class="table table-striped table-hover table-bordered">  
<thead>
<tr>
	<th>Id</th>
	<th>Modelo</th>
	<th>Clase</th>
	<th>Matricula</th>
	<th>No asientos</th>
	<th>Transmision</th>
	<th>Cuenta Por Dia</th>
	<th>Cilindrada</th>
	<th>Anio</th>
	<th>Combustible</th>
	<th>Color</th>
	<th>Descripsion</th>
	<th>Actions</th>
</tr>  
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
        <h4 class="modal-title">${tittle}</h4>
      </div>
      <div class="modal-body">

		<input type="hidden" id="id" name="id"/>

		<div class="form-container">
		        
		        
		        
		        
		        <form class="form-horizontal">
				  
				  <div class="form-group">
				    <label for="marcaId" class="col-sm-3 control-label">Marca</label>
					    <div class="col-sm-7">
					      <select id="marcaId" class="form-control input-sm" onchange="populateModelosSelect(this.value)">
					      	<option value="">-- Select Option --</option>
					      </select>
					    </div>
				  </div>
				  <div class="form-group">
				    <label for="modeloId" class="col-sm-3 control-label">Modelo</label>
					    <div class="col-sm-7">
					      <select id="modeloId" class="form-control input-sm">
					      	<option value="">-- Select Option --</option>
					      </select>
					    </div>
				  </div>
				  <div class="form-group">
				    <label for="claseId" class="col-sm-3 control-label">Clase</label>
					    <div class="col-sm-7">
					      <select id="claseId" class="form-control input-sm">
					      	<option value="">-- Select Option --</option>
					      </select>
					    </div>
				  </div>
				  <div class="form-group">
				    <label for="matricula" class="col-sm-3 control-label">Matricula</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="matricula" placeholder="Matricula">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="nasientos" class="col-sm-3 control-label">No Asientos</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="nasientos" placeholder="No Asientos">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="transmision" class="col-sm-3 control-label">Transmision</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="transmision" placeholder="Transmision">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="cuentaxdia" class="col-sm-3 control-label">Cuenta Por Dia</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="cuentaxdia" placeholder="Cuenta Por Dia">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="cilindrada" class="col-sm-3 control-label">Cilindrada</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="cilindrada" placeholder="Cilindrada">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="anio" class="col-sm-3 control-label">Anio</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="anio" placeholder="Anio">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="combustible" class="col-sm-3 control-label">Combustible</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="combustible" placeholder="Combustible">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="color" class="col-sm-3 control-label">Color</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="color" placeholder="Color">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="descripsion" class="col-sm-3 control-label">Descripsion</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="descripsion" placeholder="Descripsion">
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


</body>
</html>