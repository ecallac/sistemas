<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"  session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>Insert title here</title>

</head>
<script type="text/javascript">
var sessionContexPath = "<%=request.getContextPath() %>";
$(document).ready(function(){
	getIp();
});
function getIp(){
	$.getJSON("https://api.ipify.org?format=json",
            function(data) {
		saveSession(data.ip);
		})
}

function saveSession(ip){
	var formData= {
			hostAddress: ip
	}
	var ajaxUrl = sessionContexPath+'/session/save.json';
	var successFunction = function(response){
   		if(response.status=="OK"){
//    			$("#id").val(response.viewBean.id);
   		}
   };
   
   ajaxPost(ajaxUrl,formData,successFunction);
}


</script>
<body>

<c:url value="/j_spring_security_logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/">Root</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      <c:forEach var="permission" items="${sessionScope.permissions}">
      		<li><a href="${pageContext.request.contextPath}${permission.path}">${permission.description}</a></li>
            </c:forEach>
<!--         <li class="dropdown"> -->
<!--           <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a> -->
<!--           <ul class="dropdown-menu"> -->
<!--             <li><a href="#">Action</a></li> -->
<!--             <li><a href="#">Another action</a></li> -->
<!--             <li><a href="#">Something else here</a></li> -->
<!--             <li role="separator" class="divider"></li> -->
<!--             <li><a href="#">Separated link</a></li> -->
<!--             <li role="separator" class="divider"></li> -->
<!--             <li><a href="#">One more separated link</a></li> -->
<!--           </ul> -->
<!--         </li> -->
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
		<li>
		<form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
      </form>
      </li>

<c:if test="${pageContext.request.userPrincipal.name != null}">
<li><p class="navbar-text">Welcome : ${pageContext.request.userPrincipal.name} | <a href="javascript:document.getElementById('logout').submit()" title="Logout" class="toltip"><img src="<c:url value='/resources/img/icons/white/on-off_icon&16.png' />"> </a></p></li>

</c:if>

      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>




</body>
</html>