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
                 console.log(jqXHR);
			  alert('Error: ' + jqXHR);
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
                 console.log(jqXHR);
			  alert('Error: ' + jqXHR);
		  }  
        });
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
    	$(tableId).DataTable( {
    		dom: 'Bfrtip',
    		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
    		destroy: true,
//    				"bProcessing" : true,
    		"data": jsonData,
    		
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
    }