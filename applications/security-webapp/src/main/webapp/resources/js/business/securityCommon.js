	function showSuccessMessage(message){
    	$.bootstrapGrowl("<b>Success!</b> "+message, { type: 'success' , align:'center', width:500});
    	
    }
    function showErrorMessage(message){
    	$.bootstrapGrowl("<b>Error!</b> "+message, { type: 'danger' });
    	
    }
    function showErrorMessageByField(object,key,message,extra){
    	$(object+'[id='+key+']').after('<span class="bindingError" style="color:red;font-weight: bold;" id="bindingError'+key+'" '+extra+'>'+message+'</span>');
    }
    function makeButton(title,onclick,extra,icon){
    	return "<td><button title='"+title+"' onclick='"+onclick+"' type='button' class='btn btn-link btn-xs toltip' "+extra+"><img src='"+icon+"'></button>";
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
	
	function alertError(message){
		$.confirm({
		    title: 'I found an error!',
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
        } else if (exception === 'parsererror') {
        	alertError('Error: Requested JSON parse failed.');
        } else if (exception === 'timeout') {
        	alertError('Error: Time out error.');
        } else if (exception === 'abort') {
        	alertError('Error: Ajax request aborted.');
        } else {
        	alertError('Error: Uncaught Error.\n' + jqXHR.responseText);
        }
	}
	
	function createTable(tableId,fileTitle,jsonData,jsonColumns,jsonColumnDefs,columnsExport){
    	var exportTittle=fileTitle;
    	var buttonCommon = {
    		exportOptions: {
    			format: {
    				body: function ( data, row, column, node ) {
    						// Strip $ from salary column to make it numeric
//         	                    return column === 5 ?
//         	                        data.replace( /[$,]/g, '' ) :
//         	                        data;
    							return data;
    				}
    			},
    			columns: columnsExport
    		},
    		title: exportTittle
    	};
    	var printCounter = 0;
    	var table = $(tableId).DataTable( {
    		//dom: 'Bfrtip',
    		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
    		destroy: true,
//    				"bProcessing" : true,
    		"data": jsonData,
			lengthChange: false,
			responsive: true,
    		buttons: [
    			'pageLength',
    			$.extend( true, {}, buttonCommon, {
    				extend: 'copyHtml5'
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
    				messageTop: function () {
    					printCounter++;
     
    					if ( printCounter === 1 ) {
    						return 'This is the first time you have printed this document.';
    					}
    					else {
    						return 'You have printed this document '+printCounter+' times';
    					}
    				},
    				messageBottom: null,
    				autoPrint: true
    			} )
    		],
    		"columns": jsonColumns,
    		"columnDefs": jsonColumnDefs
    	} );
		table.buttons().container().appendTo( tableId+"_wrapper .col-md-6:eq(0)");
    }
	
	function createTableWithoutButtons(tableId,jsonData,jsonColumns,jsonColumnDefs){
    	var printCounter = 0;
		var table = $(tableId).DataTable( {
    		// dom: 'lfr<""t>ip',
    		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
    		destroy: true,
//    				"bProcessing" : true,
    		"data": jsonData,
			lengthChange: true,
			responsive: true,
    		"columns": jsonColumns,
    		"columnDefs": jsonColumnDefs
    	} );
		table.buttons().container().appendTo( tableId+"_wrapper .col-md-6:eq(0)");
    }