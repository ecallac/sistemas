<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <jsp:include page="../includes/styles.jsp" /> --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="<c:url value='/dwr/engine.js' />"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js' />"></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/ajaxController.js' />"></script>

<script type="text/javascript">
function echo(){
	var e = document.getElementById("TEXT_ID");
	ajaxController.test(e.value,echoCallBack);
}

function echoCallBack(response){
	var e = document.getElementById("RESPONSE_ID");
	e.innerHTML = response;
}

</script>

<title>home page</title>
</head>
<body>
<%-- <jsp:include page="../includes/menu.jsp" /> --%>

				<h3>Welcome home page</h3>
<input type="button" value = "Test the alert" onclick="alert('Alert this pages');" />

http://jkoder.com/java-xml-based-configuration-dwr-3-with-spring-4-and-annotations/
https://github.com/burris/dwr/blob/master/ui/dwr/demo/web/reverseajax
https://github.com/burris/dwr/tree/master/ui/dwr/demo/java/com/example/dwr/reverseajax

<br>
<input type="text" id="TEXT_ID" >
<a onclick="echo();" href="#">echo</a>
<br>
<p id="RESPONSE_ID"></p>

</body>
</html>