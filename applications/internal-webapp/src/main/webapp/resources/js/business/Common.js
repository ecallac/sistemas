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


	function createTable(tableId,fileTitle,jsonData,jsonColumns,jsonColumnDefs,columnsExport,exportFormat){
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
		var table = $(tableId).DataTable( {
			//dom: 'Bfrtip',
			"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Todo"]],
			destroy: true,
//    				"bProcessing" : true,
			"data": jsonData,
			lengthChange: false,
			responsive: true,
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

	function onlyDecimal(id){
		$(id).bind("change keyup input", function() {
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

	function onlyInteger(id){
		$(id).bind("change keyup input", function() {
			var position = this.selectionStart - 1;
			//remove all but number and .
			var fixed = this.value.replace(/[^0-9]/g, "");

			if (this.value !== fixed) {
				this.value = fixed;
				this.selectionStart = position;
				this.selectionEnd = position;
			}
		});
	}

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
		$(tableId).DataTable( {
			// dom: 'Bfrtip',
			dom: '<<"row"<"col"B><"col"f>>>rt<<"row"<"col"i><"col"p>>>',
			"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Todo"]],
			destroy: true,
//    				"bProcessing" : true,
			lengthChange: false,
			responsive: true,

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
			}
		} );
	}