<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../includes/styles.jsp" />
<title>${title}</title>
</head>
<body>
<%--     <jsp:include page="../includes/menu.jsp" /> --%>

<div class="form-container">

			<div class="input-group input-sm">
				<h1 class="bd-title" id="content">User</h1>
			</div>
       <form:form method="POST" modelAttribute="user" action="/security-webapp/saveUser" class="form-horizontal">    
        <table >    
        <tr>  
        <td></td>    
         <td><form:hidden  path="id" /></td>  
         </tr>   
         <tr>    
          <td>Username : </td>   
          <td><form:input path="userName"  class="form-control" /><form:errors path="userName"/></td>  
         </tr>    
         <tr>    
          <td>Password :</td>    
          <td><form:password path="password" class="form-control" /><div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div></td>  
         </tr>   
         <tr>    
          <td>Question :</td>    
          <td><form:input path="question" class="form-control" /><div class="has-error">
                        <form:errors path="question" class="help-inline"/>
                    </div></td>  
         </tr>   
         <tr>    
          <td>Answer :</td>    
          <td><form:input path="answer" class="form-control" /><div class="has-error">
                        <form:errors path="answer" class="help-inline"/>
                    </div></td>  
         </tr>
         <tr>    
          <td>Status :</td>
          <td><form:select path="status" id="status" class="form-control input-sm">
                        <form:option value="">Select Country</form:option>
                        <form:options items="${states}" />
                    </form:select>
                    <div class="has-error">
                        <form:errors path="status" class="help-inline"/>
                    </div></td>  
         </tr> 
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Save" class="btn btn-block btn-primary btn-default"/></td>    
         </tr>    
        </table>    
       </form:form> 
       
</div>

</body>
</html>