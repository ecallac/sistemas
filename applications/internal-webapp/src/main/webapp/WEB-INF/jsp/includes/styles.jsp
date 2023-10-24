<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script src="<%=request.getContextPath() %>/resources/js/app.js"></script>


<!-- internal project -->
<script src="<c:url value='/resources/js/business/Common.js' />"></script>

<link href="<c:url value='/resources/css/global.css' />"  rel="stylesheet"></link>





<!-- alert defaut -->
<script src="<c:url value='/resources/js/AlertJS.js' />"></script>

<link href="<c:url value='/resources/css/AlertJS.css' />" rel="stylesheet" type="text/css">





<!-- 	jquery -->
	<script src="<c:url value='/resources/js/jquery-3.2.1.js' />"></script>
<!-- 	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script> -->
	<script src="<c:url value='/resources/js/jquery-ui.min.js' />"></script>
	
	

<!-- stalker -->
<script src="<c:url value='/resources/js/jquery.stalker.js' />"></script>
	
	
<!-- 	scroll -->
<%-- 	<script src="<c:url value='/resources/js/jquery-scrolltofixed.js' />"></script> --%>
	
	
	
	
	
<!-- bootstrap -->
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> -->

<!-- conflict with app.js -->
<!-- script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">

<%-- <script src="<c:url value='/resources/bootstrap5/js/bootstrap.js' />"></script> --%>
<%-- <link href="<c:url value='/resources/bootstrap5/css/bootstrap.css' />"  rel="stylesheet"></link> --%>


<%-- <link href="<c:url value='/resources/css/dark.css' />"  rel="stylesheet"></link> --%>
<%--link href="<c:url value='/resources/css/light.css' />"  rel="stylesheet" id="style-file"></link-->

<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script> -->
<%-- <link href="<c:url value='/resources/bootstrap/css/bootstrap.css' />"  rel="stylesheet"></link> --%>
<!-- <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
	
	
	
	
	
	
<!-- 	datatables -->
 	<script src="<c:url value='/resources/js/datatables.min.js' />"></script>
 	<script src="<c:url value='/resources/js/dataTables.bootstrap5.min.js' />"></script>
	<script src="<c:url value='/resources/js/dataTables.responsive.js' />"></script>
 	<script src="<c:url value='/resources/js/dataTables.buttons.min.js' />"></script>
 	<script src="<c:url value='/resources/js/buttons.bootstrap5.min.js' />"></script>
 	<script src="<c:url value='/resources/js/jszip.min.js' />"></script>
 	<script src="<c:url value='/resources/js/pdfmake.min.js' />"></script>
 	<script src="<c:url value='/resources/js/vfs_fonts.js' />"></script>
 	<script src="<c:url value='/resources/js/buttons.html5.min.js' />"></script>
 	<script src="<c:url value='/resources/js/buttons.print.min.js' />"></script>
	<script src="https://cdn.datatables.net/responsive/2.4.0/js/responsive.bootstrap5.min.js"></script>
	
 	<link href="<c:url value='/resources/css/dataTables.bootstrap5.min.css' />"  rel="stylesheet"></link>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
 	<link href="<c:url value='/resources/css/buttons.bootstrap5.min.css' />"  rel="stylesheet"></link>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.4.0/css/responsive.bootstrap5.min.css">
	
	
	
	
	
<!-- notification boottrap messages -->
<%-- <script src="<c:url value='/resources/js/jquery.bootstrap-growl.js' />"></script> --%>
<script src="https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.js"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.css">





<!-- calendario -->
 <script src="<c:url value='/resources/js/bootstrap-datepicker.js' />"></script>
 <script src="<c:url value='/resources/js/locales/bootstrap-datepicker.es.js' />" charset="UTF-8"></script>

 <link href="<c:url value='/resources/css/datepicker.css' />" rel="stylesheet" type="text/css">






<!-- alert de confirmacion -->
<script src="<c:url value='/resources/js/jquery-confirm.min.js' />"></script>

<link href="<c:url value='/resources/css/jquery-confirm.min.css' />" rel="stylesheet" type="text/css">



<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/img/favicon.ico">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4,1,0-rc.0/dist/css/select2.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css" />
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

<script src="<c:url value='/resources/js/dataTables.checkboxes.min.js' />"></script>
<link href="<c:url value='/resources/css/dataTables.checkboxes.css' />"  rel="stylesheet"></link>

<%--<script src=?https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js?></script>--%>
<%--<script src="<c:url value='/resources/js/daterangepicker.js' />"></script>--%>
<%--<link href="<c:url value='/resources/css/daterangepicker.css' />"  rel="stylesheet"></link>--%>