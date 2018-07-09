<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.panel-title{
font-size: small;
}
.panel-collapse{
font-size: small;
}
</style>
</head>
<body>


<!-- <div class="panel panel-default col-md-4">
  <div class="panel-body"> -->
    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" style="display: none;">
	  <div class="panel panel-default ">
	    <div class="panel-heading" role="tab" id="headingOne">
	      <h4 class="panel-title">
	        <a role="button" href="${pageContext.request.contextPath}/home" >
	          Home
	        </a>
	      </h4>
	    </div>
	    <!-- <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
	      <div class="panel-body">
	        Anim pariatur.
	      </div>
	    </div> -->
	  </div>
	  <div class="panel panel-default">
	    <div class="panel-heading" role="tab" id="headingTwo">
	      <h4 class="panel-title">
	        <a class="collapsed" role="button" href="${pageContext.request.contextPath}/module" >
	          Module
	        </a>
	      </h4>
	    </div>
	    <!-- <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
	      <div class="panel-body">
	        Anim pariatur.
	      </div>
	    </div> -->
	  </div>
	  <div class="panel panel-default">
	    <div class="panel-heading" role="tab" id="headingThree">
	      <h4 class="panel-title">
	        <a class="collapsed" role="button" href="${pageContext.request.contextPath}/role">
	          Role
	        </a>
	      </h4>
	    </div>
	    <!-- <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
	      <div class="panel-body">
	        Anim pariatur.
	      </div>
	    </div> -->
	  </div>
	  <div class="panel panel-default">
	    <div class="panel-heading" role="tab" id="headingFour">
	      <h4 class="panel-title">
	        <a class="collapsed" role="button" href="${pageContext.request.contextPath}/user" >
	          User
	        </a>
	      </h4>
	    </div>
	    <!-- <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
	      <div class="panel-body">
	        Anim pariatur.
	      </div>
	    </div> -->
	   </div>
	   <div class="panel panel-default">
	    <div class="panel-heading" role="tab" id="headingFour">
	      <h4 class="panel-title">
	        <a class="collapsed" role="button" href="${pageContext.request.contextPath}/permission" >
	          Permission
	        </a>
	      </h4>
	    </div>
	  </div>
	  <div class="panel panel-default">
	    <div class="panel-heading" role="tab" id="headingFive">
	      <h4 class="panel-title">
	        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
	          Collapsible Group Item #3
	        </a>
	      </h4>
	    </div>
	    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
	      <div class="panel-body">
	        Anim pariatur.
	      </div>
	    </div>
	  </div>
	</div>
<!--   </div>
</div> -->


<%-- <table>
<tbody>
<tr>
<td><a href="${pageContext.request.contextPath}/home">Home</a></td>
</tr>
<tr>
<td><a href="${pageContext.request.contextPath}/module">Module Management</a></td>
</tr>
<tr>
<td><a href="${pageContext.request.contextPath}/role">Role Management</a></td>
</tr>
<tr>
<td><a href="${pageContext.request.contextPath}/userList">User Management</a></td>
</tr>
</tbody>
</table> --%>
</body>
</html>