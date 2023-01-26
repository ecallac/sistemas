<%@ page import="com.common.EntidadRolAtributo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<%

    List<EntidadRolAtributo> atributos = (ArrayList<EntidadRolAtributo>)request.getSession().getAttribute("atributos");
    String theme = "light";
    if (atributos!=null){
        for (EntidadRolAtributo atributo:atributos ) {
            if (atributo.getTipoAtributo().equals("ERA_THEME")){
                theme = atributo.getValor();
            }
        }
    }



%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<meta name="_csrf_name" content="${_csrf.parameterName}"/>
	
    <title><tiles:getAsString name="title" /></title>
    <jsp:include page="../../includes/styles.jsp" />

    <%-- <link href="<c:url value='/resources/css/dark.css' />"  rel="stylesheet"></link> --%>
    <link href="<%=request.getContextPath() %>/resources/css/<%=theme %>.css"  rel="stylesheet" id="style-file" type="text/css"></link>

    <style type="text/css">
    *{
	  margin: 0;
	  padding: 0;
	  box-sizing: border-box;
	  font-family: "Poppins" , sans-serif;
	}

    .content {
	    direction: ltr;
	    flex: 1;
	    padding: 2.5rem 2.5rem 1.5rem;
	}
	
	.sidebar {
	    background: #293042;
	    direction: ltr;
	    transition: margin-left .35s ease-in-out,left .35s ease-in-out,margin-right .35s ease-in-out,right .35s ease-in-out;
	}
	
	.main {
	    display: flex;
	    flex-direction: column;
	    min-height: 100vh;
	    min-width: 0;
	    transition: margin-left .35s ease-in-out,left .35s ease-in-out,margin-right .35s ease-in-out,right .35s ease-in-out;
	    width: 100%;
	    padding-right: 0 !important;
	    padding-left: 0 !important;
	}
	

    table{
    	font-size: small;
    }
    </style>
    
    <script type="text/javascript">
    var sessionContexPath = "<%=request.getContextPath() %>";
    
    $(document).ready(function(){
    	var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
        var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
          return new bootstrap.Tooltip(tooltipTriggerEl)
        });
    	
    	$('nav').stalker();
        //setTheme('');
//         var menu_btn = document.querySelector("#menu-btn");
//         var sidebar = document.querySelector("#sidebar");
//         var container = document.querySelector(".main");
        
//         $("#menu-btn").click(function() {
//         	$("#sidebar").animate({width: 'toggle'}, "slow");
//           });
    });
    function setTheme(theme){
        if (theme !=null && theme!=""){
            //guardar tema y cargar a session
            var formData= {
                valor: theme
            }
            var ajaxUrl = sessionContexPath+'/session/saveTheme.json';
            var successFunction = function(response){
                if(response.status=="OK"){
//    			$("#id").val(response.viewBean.id);
                }
            };
            ajaxPost(ajaxUrl,formData,successFunction);

        }else{
            //extraer tema de session
            theme = "<%=theme %>";
        }
        var themeurl = '/resources/css/'+theme+'.css';
        var stylesheet = document.getElementById("style-file");
        stylesheet.setAttribute('href', sessionContexPath + themeurl);
    }
    </script>
</head>
  
<body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-behavior="sticky">

<div class="wrapper">

<div class="container-fluid">
    <div class="row flex-nowrap">
        
        
<%--         <tiles:insertAttribute name="menu" /> --%>
        
        <div class="main">
        
	        <div class="header"><tiles:insertAttribute name="header" /></div>
	        <div class="navigation"><tiles:insertAttribute name="navigation" /></div>
	        <div class="content"><tiles:insertAttribute name="body" /></div>
	        <div class="footer"><tiles:insertAttribute name="footer" /></div>
            
            
            




        </div>
    </div>
</div>


</div>





    

</body>
</html>