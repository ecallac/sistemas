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
	
	var contexPath = "<%=request.getContextPath() %>/tipo";
	
    function save(){
		var formData= {
				id: parseInt($("#id").val()), 
				descripsion: $('#descripsion').val(), 
				categoria: $('#categoria').val(), 
				codigo: $('#codigo').val()
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
       			$("#descripsion").val(response.viewBean.descripsion);
       			$("#categoria").val(response.viewBean.categoria);
       			$("#codigo").val(response.viewBean.codigo);
       		}
       };
       
       ajaxPost(ajaxUrl,formData,successFunction);
    }
    
    function load(){
    	var ajaxUrl = contexPath+'/list.json';
    	var successFunction = function(response){
       		if(response.data.length>0){
       			
       			var tableId = "#table";
       			var fileTitle = "Tipo List";
       			var jsonData = response.data;
       			var jsonColumns = [
   		            { "data": "id" },
   		            { "data": "categoria" },
   		            { "data": "codigo" },
   		            { "data": "descripsion" }
   		        ];
       			var columnsExport = [ 0, 1, 2, 3];
       			var jsonColumnDefs = [
       	        	{
       	            	"targets": 4,
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
    
    function clearFields(){
		$('.bindingError').remove();
		$("#id").val("");
		$("#categoria").val("");
		$("#codigo").val("");
		$("#descripsion").val("");
	}
    
	</script>
	<style type="text/css">
	
	</style>

</head>
<body onload="load();">


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
<th>categoria</th>
<th>codigo</th>
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
				    <label for="categoria" class="col-sm-3 control-label">Categoria</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="categoria" placeholder="Categoria">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="codigo" class="col-sm-3 control-label">Codigo</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="codigo" placeholder="Codigo">
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