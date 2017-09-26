<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
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
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<title></title>
	<script type="text/javascript">
		$('#Add').on('shown.bs.modal', function () {
		  
		})
		
		
	var contexPath = "<%=request.getContextPath() %>";
    data = "";
    submit = function(){
// 	    	$(function () {
// 	    		var token = $("meta[name='_csrf']").attr("content");
// 	    		var header = $("meta[name='_csrf_header']").attr("content");
// 	    		$(document).ajaxSend(function(e, xhr, options) {
// 	    			xhr.setRequestHeader("Accept", "application/json");
// 	    	        xhr.setRequestHeader("Content-Type", "application/json");
// 	    			xhr.setRequestHeader(header, token);
// 	    		});
//     		});
			var formData= {
					id: $("#id").val(), 
                	name: $('#name').val(),
                	description: $('#description').val(),
                	status: $('#status').val(),
                	author: $('#author').val(),
                	moduleVersion: $('#moduleVersion').val()
			}
            $.ajax({
                url:contexPath+'/module/save.json',
                type:'POST',
                dataType: 'json',
                contentType: 'application/json',
                data:JSON.stringify(formData),
//                 	"id=" + $("#id").val() + 
//                 	"&name=" + $('#name').val()+ 
//                 	"&description=" + $('#description').val()+ 
//                 	"&status=" + $('#status').val()+
//                 	"&author=" + $('#author').val()+
//                 	"&moduleVersion=" + $('#moduleVersion').val(),
                success: function(response){
                        alert(response.message);
                        document.location.href = contexPath+"/module?status=&message=";
                } ,
            	error: function(e){  
				  alert('Error: ' + e);  
			  }  
            });        
    }
    
    
	</script>
	<style type="text/css">
	
	</style>

</head>
<body>

<h1>User List ${_csrf.token} ${_csrf.headerName}</h1>

<div class="panel panel-default col-xs-9">
<!-- <div class="panel-heading">User List Display tag</div> -->
  <div class="panel-body">   
   
   <sec:authorize access="hasRole('ROLE_ADMIN')">
   <button data-target="#Add" title="Add New" type="button" class="btn btn-default toltip" data-toggle="modal">
		<img src="<c:url value='/resources/img/icons/black/user_icon&16.png' />"> Add New
	</button>
   </sec:authorize>
    <br/> <br/>
    
   <spring:url value="module" var="listURL"/>
   <display:table name="list" id="module" sort="list" requestURI="${listURL}" export="true" pagesize="10" defaultsort="1" defaultorder="descending" class="table table-striped">
      <display:column property="id" title="ID" sortable="true"/>
      <display:column property="name" sortable="true"/>
      <display:column property="description" sortable="true"/>
      <display:column property="status" sortable="true"/>
      <display:column property="author" sortable="true"/>
      <display:column property="moduleVersion" sortable="true"/>
      <display:column media="html" title="Select">
         <input type="checkbox" name="select" name="select" value="Y" >
      </display:column>
      <display:column media="html" title="Actions">
      <button  title="Edit" type="button" class="btn btn-link btn-xs toltip" data-toggle="tooltip"><img src="<c:url value='/resources/img/icons/black/doc_edit_icon&16.png' />"></button> | 
      <button  title="Delete" type="button" class="btn btn-link btn-xs toltip" data-toggle="tooltip"><img src="<c:url value='/resources/img/icons/black/trash_icon&16.png' />"></button>
      </display:column>
      <display:setProperty name="export.xml" value="false" />
      <display:setProperty name="export.pdf.filename" value="userList.pdf"/>
		<display:setProperty name="export.excel.filename" value="userList.xls"/>
		<display:setProperty name="export.csv.filename" value="userList.csv"/>
    </display:table>
</div></div>




<div class="modal fade" id="Add" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
  
  <form:form modelAttribute="moduleView" method="post" action="module/save" >
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">New</h4>
      </div>
      <div class="modal-body">

		

		<div class="form-container">
		  
		        <table >   
		         <tr>    
		          <td>Name : </td>   
		          <td><input type="text" id="name" name="name" class="form-control"/></td>  
		         </tr>    
		         <tr>    
		          <td>Description :</td>    
		          <td><input type="text" id="description" name="description" class="form-control"/></td>  
		         </tr>   
		         <tr>    
		          <td>Status :</td>    
		          <td><input type="text" id="status" name="status" class="form-control"/></td>  
		         </tr>   
		         <tr>    
		          <td>Author :</td>    
		          <td><input type="text" id="author" name="author" class="form-control"/></td>  
		         </tr>
		         <tr>    
		          <td>Module Version :</td>    
		          <td><input type="text" id="moduleVersion" name="moduleVersion" class="form-control"/></td>  
		         </tr>    
		        </table>    
		       
		</div>



      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="submit();">Save</button>
      </div>
      
    </div>
    
    </form:form>
  </div>
</div>

</body>
</html>