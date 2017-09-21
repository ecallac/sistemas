<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../includes/styles.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script> -->
<script type='text/javascript' src='<c:url value='/resources/js/jquery.1.10.2.min.js' />'></script>
<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body onload="load();">
<%-- <jsp:include page="../includes/menu.jsp" /> --%>

<%-- <form:form modelAttribute="moduleView" method="post" action="save" > --%>
		<input type="hidden" id="id">
        Name: <input type="text" id="name" required="required" name="name"><br>
        description: <input type="text" id="description" required="required" name="description"><br>
        status: <input type="text" id="status" required="required" name="status"><br>
        
        <button type="button" onclick="submit();">Submit</button>
<%-- </form:form>      --%>
     
 
        <table id="table" border=1>
            <tr> <th> Name </th> <th> Description </th> <th> status </th> <th> Edit </th> <th> Delete </th> </tr>
         
        </table>
        
        https://www.youtube.com/watch?v=8Gq-fEsN80U
        http://sindhitutorials.com/blog/spring-mvc-hibernate-eclipse-maven/
		http://fruzenshtein.com/spring-mvc-ajax-jquery/
        
<script type="text/javascript">
//         $(function() {
//         	init();
//         });

//         function init() {
//         	$('input:button').button();
//         	$('#submit').button();
//         }
        
        var contexPath = "<%=request.getContextPath() %>";
    data = "";
    submit = function(){
	    	$(function () {
	    		var token = $("meta[name='_csrf']").attr("content");
	    		var header = $("meta[name='_csrf_header']").attr("content");
	    		$(document).ajaxSend(function(e, xhr, options) {
	    			xhr.setRequestHeader(header, token);
	    		});
    		});
            $.ajax({
                url:contexPath+'/module/save.json',
                type:'POST',
                data:"id=" + $("#id").val() + "&name=" + $('#name').val()+ "&description=" + $('#description').val()+ "&status=" + $('#status').val(),
                success: function(response){
                        alert(response.message);
                        load();    
                }              
            });        
    }
     
    delete_ = function(id){
    	$(function () {
    		var token = $("meta[name='_csrf']").attr("content");
    		var header = $("meta[name='_csrf_header']").attr("content");
    		$(document).ajaxSend(function(e, xhr, options) {
    			xhr.setRequestHeader(header, token);
    		});
		});
         $.ajax({
            url:contexPath+'/module/delete.json',
            type:'POST',
            data:"id="+id,
            success: function(response){
                    alert(response.message);
                    load();
            }              
        });
	}
     
 
    edit = function (index){
        $("#id").val(data[index].id);
        $("#name").val(data[index].name);
        $("#description").val(data[index].description);
        $("#status").val(data[index].status);
    }
     
     
    load = function(){
    	$(function () {
    		var token = $("meta[name='_csrf']").attr("content");
    		var header = $("meta[name='_csrf_header']").attr("content");
    		$(document).ajaxSend(function(e, xhr, options) {
    			xhr.setRequestHeader(header, token);
    		});
		});
//     	alert("aqui "+contexPath);
        $.ajax({
            url: contexPath+'/module/list.json',
            type:'GET',
            success: function(response){
                    data = response.data;
                    $('.tr').remove();
                    for(i=0; i<response.data.length; i++){                  
                        $("#table").append("<tr class='tr'> <td> "+response.data[i].name+" </td> <td> "+response.data[i].description+" </td> <td> "+response.data[i].status+" </td> <td> <a href='#' onclick= edit("+i+");> Edit </a>  </td> </td> <td> <a href='#' onclick='delete_("+response.data[i].id+");'> Delete </a>  </td> </tr>");
                    }          
            }              
        });
         
    }
         
    </script>
</body>
</html>