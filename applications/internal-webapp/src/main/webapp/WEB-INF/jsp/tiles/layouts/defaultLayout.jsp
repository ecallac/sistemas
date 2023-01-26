<%@ page import="com.common.EntidadRolAtributo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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

            //$('nav').stalker();
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

            var sidebar = document.getElementById("body-general");
            sidebar.setAttribute('data-theme', theme);
        }

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
</head>

<body data-theme="<%=theme %>" data-layout="fluid" data-sidebar-position="left" data-sidebar-behavior="sticky" id="body-general">

<div class="wrapper">
    <nav id="sidebar" class="sidebar">
        <div class="sidebar-content js-simplebar">
            <a class="sidebar-brand" href="${pageContext.request.contextPath}/">

                <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="20px" height="20px" viewBox="0 0 20 20" enable-background="new 0 0 20 20" xml:space="preserve">
            <path d="M19.4,4.1l-9-4C10.1,0,9.9,0,9.6,0.1l-9,4C0.2,4.2,0,4.6,0,5s0.2,0.8,0.6,0.9l9,4C9.7,10,9.9,10,10,10s0.3,0,0.4-0.1l9-4
              C19.8,5.8,20,5.4,20,5S19.8,4.2,19.4,4.1z"></path>
                    <path d="M10,15c-0.1,0-0.3,0-0.4-0.1l-9-4c-0.5-0.2-0.7-0.8-0.5-1.3c0.2-0.5,0.8-0.7,1.3-0.5l8.6,3.8l8.6-3.8c0.5-0.2,1.1,0,1.3,0.5
              c0.2,0.5,0,1.1-0.5,1.3l-9,4C10.3,15,10.1,15,10,15z"></path>
                    <path d="M10,20c-0.1,0-0.3,0-0.4-0.1l-9-4c-0.5-0.2-0.7-0.8-0.5-1.3c0.2-0.5,0.8-0.7,1.3-0.5l8.6,3.8l8.6-3.8c0.5-0.2,1.1,0,1.3,0.5
              c0.2,0.5,0,1.1-0.5,1.3l-9,4C10.3,20,10.1,20,10,20z"></path>
          </svg>

                <span class="align-middle me-3">EsiStack</span>
            </a>

            <ul class="sidebar-nav">
                <li class="sidebar-header">
                    Sitios
                </li>



                <c:forEach var="permission" items="${sessionScope.permissions}">
                    <c:choose>
                        <c:when test="${fn:length(permission.childPermissions) > 0}">
                            <!-- incluse child tree-->


                            <li class="sidebar-item">
                                <a data-bs-target="#${permission.name}${permission.id}" data-bs-toggle="collapse" class="sidebar-link collapsed">
                                    <i class="align-middle" data-feather="box"></i>${permission.description}
                                </a>
                                <c:choose>
                                    <c:when test="${! empty permission.parentPermission}">
                                        <ul id="${permission.name}${permission.id}" class="sidebar-dropdown list-unstyled collapse " data-bs-parent="#sidebar">
                                            <c:forEach var="child" items="${permission.childPermissions}">
                                                <li class="sidebar-item">
                                                    <a class="sidebar-link" href="${pageContext.request.contextPath}${child.path}"><i class="align-middle" data-feather="file"></i>${child.description}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:when>
                                    <c:otherwise>
                                        <ul id="${permission.name}${permission.id}" class="sidebar-dropdown list-unstyled collapse ">
                                            <c:forEach var="child" items="${permission.childPermissions}">
                                                <li class="sidebar-item">
                                                    <a class="sidebar-link" href="${pageContext.request.contextPath}${child.path}"><i class="align-middle" data-feather="file"></i>${child.description}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:otherwise>
                                </c:choose>
                            </li>


                        </c:when>
                        <c:otherwise>
                            <li class="sidebar-item">
                                <a class="sidebar-link"  href="${pageContext.request.contextPath}${permission.path}"><i class="align-middle" data-feather="file"></i>${permission.description}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>





                <!--li class="sidebar-item">
                https://thinkinginsoftware.blogspot.com/2011/06/jsp-jstl-to-render-tree-menu.html
                https://coderwall.com/p/umucpw/recursion-in-a-jsp
                https://coderanch.com/t/571625/java/Recursive-JSP-function
                    <a data-bs-target="#pages" data-bs-toggle="collapse" class="sidebar-link collapsed">
                        Pages
                    </a>
                    <ul id="pages" class="sidebar-dropdown list-unstyled collapse " data-bs-parent="#sidebar">

                        <li class="sidebar-item">
                            <a class="sidebar-link" href="pages-profile.html">Profile</a>
                        </li>



                        <li class="sidebar-item">
                            <a data-bs-target="#projects" data-bs-toggle="collapse" class="sidebar-link collapsed">
                                Projects
                            </a>
                            <ul id="projects" class="sidebar-dropdown list-unstyled collapse ">




                                <li class="sidebar-item">
                                    <a data-bs-target="#list" data-bs-toggle="collapse" class="sidebar-link collapsed">
                                        list
                                    </a>
                                    <ul id="list" class="sidebar-dropdown list-unstyled collapse ">
                                        <li class="sidebar-item">
                                            <a class="sidebar-link" href="pages-projects-list.html">List 1</a>
                                        </li>
                                    </ul>
                                </li>

                                <li class="sidebar-item">
                                    <a class="sidebar-link" href="pages-projects-detail.html">Detail </a>
                                </li>




                            </ul>
                        </li>
                        <li class="sidebar-item"><a class="sidebar-link" href="pages-invoice.html">Invoice</a></li>





                    </ul>




                </li-->





            </ul>


        </div>
    </nav>






















    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <form id="logout" action="${logoutUrl}" method="post" >
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <c:if test="${pageContext.request.userPrincipal.name != null}">

    <div class="main">
        <nav class="navbar navbar-expand navbar-light navbar-bg">
            <a class="sidebar-toggle">
                <i class="hamburger align-self-center"></i>
            </a>


            <div class="navbar-collapse collapse">
                <ul class="navbar-nav navbar-align">


                    <li class="nav-item dropdown">
                        <a class="nav-icon dropdown-toggle d-inline-block d-sm-none" href="#" data-bs-toggle="dropdown">
                            <i class="align-middle" data-feather="settings"></i>
                        </a>

                        <a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-bs-toggle="dropdown">
                            <div class="container">
                                <div class="row">
                                    <div class="col">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                        </svg>
                                    </div>
                                    <div class="col">
                                        <div class="row">
                                            <div class="col">
                                                <span>${pageContext.request.userPrincipal.name}</span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                              <span>
                                                <c:forEach var="roles" items="${sessionScope.user.roles}">
                                                    ${roles.description}
                                                </c:forEach>
                                              </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/profile"><i class="align-middle me-1" data-feather="user"></i> Profile</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="javascript:document.getElementById('logout').submit()">Sign out</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        </c:if>

        <main class="content">
            <tiles:insertAttribute name="body" />
        </main>

        <tiles:insertAttribute name="footer" />

    </div>
</div>


</div>







</body>
</html>