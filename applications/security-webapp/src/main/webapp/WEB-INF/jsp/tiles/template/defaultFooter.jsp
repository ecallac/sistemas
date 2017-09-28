<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
$(document).ready(function() {
    $('.footer').scrollToFixed( {
        bottom: 0,
        limit: $('.footer').offset().top
    });
});

</script>

<style type="text/css">
 .footer { 
/*      background-color: #000000;  */
/*      color: #B4B4B4;   */
     font: bold; 
     font-family: Verdana; 
     font-size: 10px; 
     border-top: 1px solid #929292;  
     padding: 100%; 
     width: 100%; 
     height: 50px; 
     /* font-family: Verdana, Arial, Helvetica, sans-serif; */ 
 } 
</style>
</head>
<body>


<table class="footer" style="width:100%;height:50%" border="0">
<tbody>
<tr>
	<td align="center">Copyright © 2017 - Ecallac</td>
</tr>
</tbody>
</table>


</body>
</html>