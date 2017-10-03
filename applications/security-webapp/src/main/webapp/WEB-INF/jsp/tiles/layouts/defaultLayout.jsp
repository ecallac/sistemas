<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"  session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><tiles:getAsString name="title" /></title>
    <jsp:include page="../../includes/styles.jsp" />
    <style type="text/css">
    table{
    	font-size: small;
    }
    </style>
    
    <script type="text/javascript">
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
        $('.toltip').tooltip({placement : 'bottom'});
    });
    </script>
</head>
  
<body>


<%-- <table width="100%" height="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
        <td valign="top" rowspan='5' style="width:1px">
          <tiles:insertAttribute name="menu" />
        </td>
      </tr>
      <tr>
        <td style="height:50px">
          <tiles:insertAttribute name="header" />
        </td>
      </tr>
      <tr>
        <td style="height:15px">
          <tiles:insertAttribute name="navigation" />
        </td>
      </tr>
      <tr>
        <td valign="top" style="height:100%">
          <tiles:insertAttribute name="body" />
        </td>
      </tr>
      <tr>
        <td style="height:15px">
          <tiles:insertAttribute name="footer" />
        </td>
      </tr>
    </table> --%>
    
    <table border="0" width="100%" height=100%;" cellspacing="0" cellpadding="0">
<tbody>
<tr>
  <td style="height: 50px;" colspan="2"><tiles:insertAttribute name="header" /></td>
</tr>
<tr>
<td style="width: 150px;height: 100%;"  valign="top" rowspan = "3"><tiles:insertAttribute name="menu" /></td>
</tr>
<tr>
<td style="height: 15px;"><tiles:insertAttribute name="navigation" /></td>
</tr>
<tr>
<td style="height: 100%;" valign="top"><tiles:insertAttribute name="body" /></td>
</tr>
<tr>
<td style="height: 15px;" colspan="2"><tiles:insertAttribute name="footer" /></td>
</tr>
</tbody>
</table>
</body>
</html>