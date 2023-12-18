const imgEye="<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round' class='feather feather-eye align-middle me-2'><path d='M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z'></path><circle cx='12' cy='12' r='3'></circle></svg>";
const imgEyeOff="<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round' class='feather feather-eye-off align-middle me-2'><path d='M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24'></path><line x1='1' y1='1' x2='23' y2='23'></line></svg>";
const imgEdit="<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round' class='feather feather-edit align-middle me-2'><path d='M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7'></path><path d='M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z'></path></svg>";
const imgList="<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round' class='feather feather-list align-middle me-2'><line x1='8' y1='6' x2='21' y2='6'></line><line x1='8' y1='12' x2='21' y2='12'></line><line x1='8' y1='18' x2='21' y2='18'></line><line x1='3' y1='6' x2='3.01' y2='6'></line><line x1='3' y1='12' x2='3.01' y2='12'></line><line x1='3' y1='18' x2='3.01' y2='18'></line></svg>";

function showSuccessMessage(message){
//    	$.bootstrapGrowl("<b>Success!</b> "+message, { type: 'success' });
		var notyf = new Notyf();
    	notyf.success({
		    message: message,
		    dismissible: true,
		    ripple: false,
		   duration:5000,
		   position: {
			x: 'center',
			y: 'top'
		}
		  });
    }
    function showErrorMessage(message){
//    	$.bootstrapGrowl("<b>Error!</b> "+message, { type: 'danger' });
    	var notyf = new Notyf();
    	notyf.error({
			    message: message,
			    dismissible: true,
			    ripple: false,
			   duration:15000,
			   position: {
				x: 'center',
				y: 'top'
			}
			  });
    }
    
    function alertError(message){
		$.confirm({
		    title: 'Encontre un error!',
		    content: message,
		    type: 'red',
		    typeAnimated: true,
		    buttons: {
		        ok: {
		            text: 'OK',
		            btnClass: 'btn-red',
		            action: function(){
		            }
		        }
		    }
		});
	}
    
    function processjqXHR(jqXHR){
		console.log(jqXHR);
 	   if (jqXHR.status === 0) {
 		   alertError('Error: Not connect.\n Verify Network.');
        } else if (jqXHR.status == 404) {
        	alertError('Error: Requested page not found. [404]');
        } else if (jqXHR.status == 500) {
        	alertError('Error: Internal Server Error [500].');
        } else {
        	alertError('Error: Uncaught Error.\n' + jqXHR.responseText);
        }
	}
    
    function showErrorMessageByField(object,key,message,extra){
    	$(object+'[id='+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;" id="bindingError'+key+'" '+extra+'>'+message+'</span>');
    }
    function makeButton(title,onclick,extra,icon){
    	return "<td><button title='"+title+"' onclick='"+onclick+"' type='button' class='btn btn-link btn-xs toltip' "+extra+">"+icon+"</button>";
    }
    
    function post(path, params, method) {
        method = method || "post"; // Set method to post by default if not specified.

        // The rest of this code assumes you are not using a library.
        // It can be made less wordy if you use one.
        var form = document.createElement("form");
        form.setAttribute("method", method);
        form.setAttribute("action", path);

        for(var key in params) {
            if(params.hasOwnProperty(key)) {
//             	alert(key + " - " +params[key]);
                var hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", key);
                hiddenField.setAttribute("value", params[key]);

                form.appendChild(hiddenField);
             }
        }

        document.body.appendChild(form);
        form.submit();
    }
	
	function ajaxPost(ajaxUrl,formData,successFunction){
		$.ajax({
           	type:'POST',
               dataType: 'json',
               contentType: 'application/json',
               url:ajaxUrl,
               data:JSON.stringify(formData),
			beforeSend: function(xhr) {
		        // setting a timeout
		        var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
		        xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.setRequestHeader(header, token);
		    },
               success: successFunction,
               error: function (jqXHR, exception) {
            	   processjqXHR(jqXHR);
		  }  
        });
	}
	
	function ajaxPostWithoutForm(ajaxUrl,successFunction){
		
		$.ajax({
           	type:'POST',
               dataType: 'json',
               contentType: 'application/json',
               url:ajaxUrl,
			beforeSend: function(xhr) {
		        // setting a timeout
		        var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
		        xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.setRequestHeader(header, token);
		    },
               success: successFunction,
               error: function (jqXHR, exception) {
            	   processjqXHR(jqXHR);
		  }  
        });
	}
	
	function ajaxWithoutForm(ajaxUrl,type,successFunction){
		$.ajax({
           	type:type,
               dataType: 'json',
               contentType: 'application/json',
               url:ajaxUrl,
			beforeSend: function(xhr) {
		        // setting a timeout
		        var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
		        xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.setRequestHeader(header, token);
		    },
               success: successFunction,
               error: function (jqXHR, exception) {
            	   processjqXHR(jqXHR);
		  }  
        });
	}

//
// 	function createTable(tableId,fileTitle,jsonData,jsonColumns,jsonColumnDefs,columnsExport,exportFormat){
// 		if (exportFormat==null) {
// 			exportFormat = {
// 				body: function (data,row,column,node) {
// 					//Strip S from salary column to make it numeric
// 					//return column === 5?data.replace(/[$,]/g, ''):data
// 					return data;
// 				}
// 			}
// 		}
// 		var exportTittle=fileTitle;
// 		var buttonCommon = {
// 			exportOptions: {
// 				orthogonal: "export",
// 				format: exportFormat,
// 				columns: columnsExport
// 			},
// 			title: exportTittle
// 		};
// 		var printCounter = 0;
// 		var table = $(tableId).DataTable( {
// 			//dom: 'Bfrtip',
// 			"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Todo"]],
// 			destroy: true,
// //    				"bProcessing" : true,
// 			"data": jsonData,
// 			lengthChange: false,
// 			responsive: true,
// 			buttons: [
// 				'pageLength',
// 				{
// 					extend: 'collection',
// 					text: 'Exportar',
// 					className: "custom-html-collection",
// 					buttons:[
// 						$.extend( true, {}, buttonCommon, {
// 							extend: 'copyHtml5',
// 							text: 'Copiar'
// 						} ),
// 						$.extend( true, {}, buttonCommon, {
// 							extend: 'excelHtml5'
// 						} ),
// 						$.extend( true, {}, buttonCommon, {
// 							extend: 'pdfHtml5',
// 							download: 'open'
// 						} ),
// 						$.extend( true, {}, buttonCommon, {
// 							extend: 'csvHtml5',
// 							text: 'CSV',
// 							fieldSeparator: '\t',
// 							extension: '.csv',
// 						} ),
// 						$.extend( true, {}, buttonCommon, {
// 							extend: 'print',
// 							text: 'Imprimir',
// 							// messageTop: function () {
// 							// 	printCounter++;
// 							//
// 							// 	if ( printCounter === 1 ) {
// 							// 		return 'This is the first time you have printed this document.';
// 							// 	}
// 							// 	else {
// 							// 		return 'You have printed this document '+printCounter+' times';
// 							// 	}
// 							// },
// 							messageBottom: null,
// 							autoPrint: true
// 						} )
// 					]
// 				}
// 			],
// 			"columns": jsonColumns,
// 			"columnDefs": jsonColumnDefs,
// 			"language": {
// 				"decimal":        "",
// 				"emptyTable":     "No hay datos disponibles en la table",
// 				"info":           "Mostrando _START_ a _END_ de _TOTAL_ registros",
// 				"infoEmpty":      "Mostrando 0 a 0 de 0 registros",
// 				"infoFiltered":   "(filtrado de _MAX_ registros en total)",
// 				"infoPostFix":    "",
// 				"thousands":      ",",
// 				"lengthMenu":     "Mostrar _MENU_ Registros",
// 				"loadingRecords": "Cargando...",
// 				"processing":     "",
// 				"search":         "Buscar:",
// 				"zeroRecords":    "No se encontraron registros coincidentes",
// 				"paginate": {
// 					"first":      "Primero",
// 					"last":       "Ultimo",
// 					"next":       "Siguiente",
// 					"previous":   "Anterior"
// 				},
// 				"aria": {
// 					"sortAscending":  ": activar para ordenar columna ascendenteg",
// 					"sortDescending": ": activar para ordenar la columna descendente"
// 				},
// 				buttons : {
// 					pageLength: {_: "Mostrar %d filas",
// 						'-1': "Mostrar todo"}
// 				}
// 			}
// 		} );
// 		table.buttons().container().appendTo( tableId+"_wrapper .col-md-6:eq(0)");
// 	}

	function createTableWithoutButtons(tableId,jsonData,jsonColumns,jsonColumnDefs){
		var printCounter = 0;
		var table = $(tableId).DataTable( {
			// dom: 'lfr<""t>ip',
			"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Todo"]],
			destroy: true,
//    				"bProcessing" : true,
			"data": jsonData,
			lengthChange: true,
			responsive: true,
			"columns": jsonColumns,
			"columnDefs": jsonColumnDefs,
			"language": {
				"decimal":        "",
				"emptyTable":     "No hay datos disponibles en la table",
				"info":           "Mostrando _START_ a _END_ de _TOTAL_ registros",
				"infoEmpty":      "Mostrando 0 a 0 de 0 registros",
				"infoFiltered":   "(filtrado de _MAX_ registros en total)",
				"infoPostFix":    "",
				"thousands":      ",",
				"lengthMenu":     "Mostrar _MENU_ Registros",
				"loadingRecords": "Cargando...",
				"processing":     "",
				"search":         "Buscar:",
				"zeroRecords":    "No se encontraron registros coincidentes",
				"paginate": {
					"first":      "Primero",
					"last":       "Ultimo",
					"next":       "Siguiente",
					"previous":   "Anterior"
				},
				"aria": {
					"sortAscending":  ": activar para ordenar columna ascendenteg",
					"sortDescending": ": activar para ordenar la columna descendente"
				},
				buttons : {
					pageLength: {_: "Mostrar %d filas",
						'-1': "Mostrar todo"}
				}
			}
		} );
		table.buttons().container().appendTo( tableId+"_wrapper .col-md-6:eq(0)");
	}

	function onlyDecimal(){
		$('.onlyDecimal').bind("change keyup input", function() {
			var position = this.selectionStart - 1;
			//remove all but number and .
			var fixed = this.value.replace(/[^0-9\.]/g, "");
			if (fixed.charAt(0) === ".")
				//can't start with .
				fixed = fixed.slice(1);

			var pos = fixed.indexOf(".") + 1;
			if (pos >= 0)
				//avoid more than one .
				fixed = fixed.substr(0, pos) + fixed.slice(pos).replace(".", "");

			if (this.value !== fixed) {
				this.value = fixed;
				this.selectionStart = position;
				this.selectionEnd = position;
			}
		});
	}
	function onlyText(){
		$('.onlyText').bind('change keyup input',function(){
			var node = $(this);
			node.val(node.val().replace(/[^a-zA-Z ]/g,'') ); }
		);
	}
	function onlyInteger(){
		$('.onlyInteger').bind('change keyup input',function(){
			var node = $(this);
			node.val(node.val().replace(/[^0-9]/g,'') ); }
		);
	}

	var table;
	function createTableAjax(tableId,fileTitle,ajaxUrl,formData,jsonColumns,jsonColumnDefs,columnsExport,exportFormat){
		if (exportFormat==null) {
			exportFormat = {
				body: function (data,row,column,node) {
					//Strip S from salary column to make it numeric
					//return column === 5?data.replace(/[$,]/g, ''):data
					return data;
				}
			}
		}
		var exportTittle=fileTitle;
		var buttonCommon = {
			exportOptions: {
				orthogonal: "export",
				format: exportFormat,
				columns: columnsExport
			},
			title: exportTittle
		};
		var printCounter = 0;
		table = $(tableId).DataTable( {
			// dom: 'Bfrtip',
			dom: '<<"row"<"col"B><"col"f>>>rt<<"row"<"col"i><"col"p>>>',
			"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Todo"]],
			destroy: true,
//    				"bProcessing" : true,
			lengthChange: false,
			responsive: false,

			'processing': true,
			'serverSide': true,
			'serverMethod': 'post',
			'ajax': {
				type:'GET',
				url:ajaxUrl,
				data:formData,
				error: function (jqXHR, exception) {
					processjqXHR(jqXHR);
				}
			},
			buttons: [
				'pageLength',
				{
					extend: 'collection',
					text: 'Exportar',
					className: "custom-html-collection",
					buttons:[
						$.extend( true, {}, buttonCommon, {
							extend: 'copyHtml5',
							text: 'Copiar'
						} ),
						$.extend( true, {}, buttonCommon, {
							extend: 'excelHtml5'
						} ),
						$.extend( true, {}, buttonCommon, {
							extend: 'pdfHtml5',
							download: 'open'
						} ),
						$.extend( true, {}, buttonCommon, {
							extend: 'csvHtml5',
							text: 'CSV',
							fieldSeparator: '\t',
							extension: '.csv',
						} ),
						$.extend( true, {}, buttonCommon, {
							extend: 'print',
							text: 'Imprimir',
							// messageTop: function () {
							// 	printCounter++;
							//
							// 	if ( printCounter === 1 ) {
							// 		return 'This is the first time you have printed this document.';
							// 	}
							// 	else {
							// 		return 'You have printed this document '+printCounter+' times';
							// 	}
							// },
							messageBottom: null,
							autoPrint: true
						} )
					]
				}
			],
			"columns": jsonColumns,
			"columnDefs": jsonColumnDefs,
			"language": {
				"decimal":        "",
				"emptyTable":     "No hay datos disponibles en la table",
				"info":           "Mostrando _START_ a _END_ de _TOTAL_ registros",
				"infoEmpty":      "Mostrando 0 a 0 de 0 registros",
				"infoFiltered":   "(filtrado de _MAX_ registros en total)",
				"infoPostFix":    "",
				"thousands":      ",",
				"lengthMenu":     "Mostrar _MENU_ Registros",
				"loadingRecords": "Cargando...",
				"processing":     "Cargando. Por favor espera...",
				"search":         "Buscar:",
				"zeroRecords":    "No se encontraron registros coincidentes",
				"paginate": {
					"first":      "Primero",
					"last":       "Ultimo",
					"next":       "Siguiente",
					"previous":   "Anterior"
				},
				"aria": {
					"sortAscending":  ": activar para ordenar columna ascendenteg",
					"sortDescending": ": activar para ordenar la columna descendente"
				},
				buttons : {
					pageLength: {
						_: "Mostrar %d filas",
						'-1': "Mostrar todo"}
				}
			},
			'select': {
				'style': 'multi'
			}
			//,'order': [[1, 'asc']]
		} );
		table.buttons().container().appendTo( tableId+"_wrapper .col-md-6:eq(0)");
	}

	function populateSelectByCategoriaType(key, categoria){
		var ajaxUrl = contexPath+'/tipoBase/listByCategoria?categoria='+categoria;
		var successFunction = function(response){
			if(response.status=="OK"){
				if(response.data.length>0){
					$.each(response.data, function (i,row){
						$('#'+key).append('<option value="' + row.codigo + '">' + row.descripcion + '</option>');
						// $('#'+key+'Search').append('<option value="' + row.codigo + '">' + row.descripcion + '</option>');
					});
				}
			}else{
				showErrorMessage(response.message);
			}
		};
		ajaxPostWithoutForm(ajaxUrl,successFunction);
	}









	function populateSelect(controller,service,key){
		var ajaxUrl = contexPath+'/'+controller+'/'+service;
		var successFunction = function(response){
			if(response.status=="OK"){
				if(response.data.length>0){
					$('#'+key).empty();
					$('#'+key).append('<option value="">-- Seleccionar --</option>');
					$.each(response.data, function(i, row) {
						$('#'+key).append('<option value="' + row.id + '">' + row.nombre + '</option>');
					});
				}
			}else{
				showErrorMessage(response.message);
			}

		};
		ajaxPostWithoutForm(ajaxUrl,successFunction);
	}
	// function populateSelectUsingEachFunction(controller,service,key,eachfunction){
	// 	var ajaxUrl = contexPath+'/'+controller+'/'+service;
	// 	var successFunction = function(response){
	// 		if(response.status=="OK"){
	// 			if(response.data.length>0){
	// 				$('#'+key).empty();
	// 				$('#'+key).append('<option value="">-- Seleccionar --</option>');
	// 				$.each(response.data, eachfunction);
	// 			}
	// 		}else{
	// 			showErrorMessage(response.message);
	// 		}
	// 	};
	// 	ajaxPostWithoutForm(ajaxUrl,successFunction);
	// }

	// function populateSelectByParent(controller,service,key,parentField){
	// 	var parentId = $('#'+parentField).val();
	// 	if(parentId!=''){
	// 		var ajaxUrl = contexPath+'/'+controller+'/'+service+'?parentId='+parentId;
	// 		var successFunction = function(response){
	// 			if(response.status=="OK"){
	// 				if(response.data.length>0){
	// 					$('#'+key).empty();
	// 					$('#'+key).append('<option value="">-- Seleccionar --</option>');
	// 					$.each(response.data, function(i, row) {
	// 						$('#'+key).append('<option value="' + row.id + '">' + row.nombre + '</option>');
	// 					});
	// 				}
	// 				if(response.entidadId!=null){
	// 					$("#entidadId").val(response.entidadId);
	// 				}
	// 			}else{
	// 				showErrorMessage(response.message);
	// 			}
	//
	// 		};
	// 		ajaxPostWithoutForm(ajaxUrl,successFunction);
	// 	}else{
	// 		$('#'+key).empty();
	// 		$('#'+key).append('<option value="">-- Seleccionar --</option>');
	// 	}
	// }



// var options = {
// 	controller: 'tuControlador',
// 	service: 'tuServicio',
// 	key: 'tuKey',
//  paramsAndValues: 'tuParametroyvaloradicional'
// 	eachfunction: function (i, row) {
// 		// tu lógica personalizada para el each
// 	},
// 	addResponseTask: function (response) {
// 		// tu lógica personalizada para el response
// 	}
// };
// populateSelectBox(options);

/**
 * Pobla un cuadro de selección (select box) mediante una solicitud AJAX.
 * @param {Object} options - Objeto que contiene las opciones para la función.
 * @param {string} options.controller - Nombre del controlador para la solicitud.
 * @param {string} options.service - Nombre del servicio para la solicitud.
 * @param {string} options.key - ID del cuadro de selección que se llenará.
 * @param {string} [options.paramsAndValues] - Parámetros y valores adicionales para la URL de la solicitud.
 * @param {function} [options.addEachfunction] - Función personalizada a aplicar a cada elemento de la respuesta.
 * @param {function} [options.addResponseTask] - Función personalizada para ejecutarse como tarea adicional de la respuesta.
 */
function populateSelectBox(options) {
	// Verifica si hay parámetros y valores adicionales
	var localparamsAndValues = options.paramsAndValues || '';
	var parameterSeparator = localparamsAndValues !== '' ? '?' : '';

	// Construye la URL de la solicitud AJAX
	var ajaxUrl = contexPath + '/' + options.controller + '/' + options.service + parameterSeparator + localparamsAndValues;

	// Función de éxito que se ejecuta cuando la solicitud AJAX tiene éxito
	var successFunction = function (response) {
		if (response.status === "OK") {
			$('#' + options.key).empty();
			$('#' + options.key).append('<option value="">-- Seleccionar --</option>');
			// Llena el cuadro de selección con las opciones recibidas
			if (response.data.length > 0) {
				// Aplica la función personalizada a cada elemento si está definida
				if (typeof options.addEachfunction === 'function') {
					$.each(response.data, options.addEachfunction);
				} else {
					// De lo contrario, agrega opciones predeterminadas al cuadro de selección
					$.each(response.data, function (i, row) {
						$('#' + options.key).append('<option value="' + row.id + '">' + row.nombre + '</option>');
					});
				}
			}

			// Actualiza el valor de entidadId si está presente en la respuesta
			// if (response.entidadId !== null) {
			// 	$("#entidadId").val(response.entidadId);
			// }

			if (typeof options.addResponseTask === 'function') {
				options.addResponseTask(response);
			}
		} else {
			// Muestra un mensaje de error en caso de que la respuesta no sea exitosa
			showErrorMessage(response.message);
		}
	};

	// Realiza la solicitud AJAX utilizando la URL y la función de éxito especificadas
	ajaxPostWithoutForm(ajaxUrl, successFunction);
}








	function datatableEnableDisableButtons(arrayKey){
		var cheked=false;
		const collection = document.getElementsByClassName("dt-checkboxes");
		for (let i = 0; i < collection.length; i++) {
			// console.log(collection[i].checked);
			if(collection[i].checked){
				cheked=true;
			}
		}
		if(cheked){
			for (const key of arrayKey) {
				$("#"+key).attr("disabled", false);
			}
		}else{
			for (const key of arrayKey) {
				$("#"+key).attr("disabled", true);
			}
		}
	}

	function saveCRUD(controllerName,formData,modalId){
		$('.bindingError').remove();

		var ajaxUrl = contexPath+'/'+controllerName+'/save.json';
		var successFunction = function(response){
			if(response.validated){
				//Set response
				if(response.status=="OK"){
					showSuccessMessage(response.message);
					load();
					$('#'+modalId).modal('hide');
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

	function enableAndDisableCRUD(controllerName,formData){
		var ajaxUrl = contexPath+'/'+controllerName+'/enableDisable.json';
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


	function verifyField(controllerName,serviceName,parameterName,key,value){
		$('.bindingError'+key).remove();
		var ajaxUrl = contexPath+'/'+controllerName+'/'+serviceName+'?'+parameterName+'='+value;
		var successFunction = function(response){
			if(response.status=="OK"){
				$("#"+key).removeClass("is-invalid").addClass("is-valid");
			}else{
				$("#"+key).removeClass("is-valid").addClass("is-invalid");
				showErrorMessageByField('input' , key , response.message , '');
			}
		};

		ajaxWithoutForm(ajaxUrl,"GET",successFunction);
	}

	function datatableCheckboxSelectedRowList(){
		var rowIds= [];
		$.each(table.column(0).checkboxes.selected(), function(index, rowId){
			rowIds  [index]= rowId;
		});
		return rowIds;
	}



	function editCRUD(controllerName,formData){
		$('.bindingError').remove();
		var ajaxUrl = contexPath+'/'+controllerName+'/load.json';
		var successFunction = function(response){
			if(response.status=="OK"){
				clearFields();
				setFormFieldsFromServiceResponse(response);
				// $("#id").val(response.viewBean.id);
				// $('#nombre').val(response.viewBean.nombre);
				// $('#activo').val(response.viewBean.activo).change();
				// $("#parentAreaId").val(response.viewBean.parentArea.id).change();
			}else{
				showErrorMessage(response.message);
			}
		};

		ajaxPost(ajaxUrl,formData,successFunction);
	}