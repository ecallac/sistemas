<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
$(document).ready(function(){
	load();
});

function setStyleSheet(url){
    var stylesheet = document.getElementById("style-file");
    stylesheet.setAttribute('href', sessionContexPath + url);
 }

function password_show_hide(id) {
	  var x = document.getElementById(id);
	  var show_eye = document.getElementById("show_eye_"+id);
	  var hide_eye = document.getElementById("hide_eye_"+id);
	  hide_eye.classList.remove("d-none");
	  if (x.type === 'password') {
	    x.type = "text";
	    show_eye.style.display = "none";
	    hide_eye.style.display = "block";
	  } else {
	    x.type = 'password';
	    show_eye.style.display = "block";
	    hide_eye.style.display = "none";
	  }
	}
	
function load(){
	
	
   var ajaxUrl = sessionContexPath+'/profile/load.json';
	var successFunction = function(response){
		if(response.status=="OK"){
			$("#userId").val(response.user.id);
  			$("#lusername").text(response.user.userName);
  			if(response.user.roles.length>0){
  				var i = 0;
       			$.each(response.user.roles, function(i, row) {
       				i++;
       				if (i>1) {
       					$('#lrole').append(", "); 
					}
                    $('#lrole').append(row.description); 
                });
       		}
  			
  			$("#lstatus").addClass("badge bg-"+response.view.userStatusType);
  			$("#lstatus").text(response.user.status);
  			
  			$("#lactivationdate").text(response.user.activationDate);
  			
  			$("#lfullname").text(response.persona.fullName);
  			
  			$("#ltipodcumento").text(response.persona.tipoDocumentoIdentificaion);
  			$("#lnumeroidentificacion").text(response.persona.numeroidentificacion);
  			$("#lnombres").text(response.persona.nombres);
  			$("#lapellidos").text(response.persona.apellidos);
  			$("#lestadocivil").text(response.persona.tipoEstadoCivil);
  			$("#lsexo").text(response.persona.sexo);
  			$("#lfechanacimiento").text(response.persona.fechanacimiento);
  			$("#lemail").text(response.persona.email);

			$.each(response.telefonos, function(i, row) {
				$('#ltelefonos').append("<div class='col'><span class='ms-2' id='ldir'>"+row.tipo+"</span></div><div class='col'><span class='ms-2' id='lval'>"+row.codigoarea+" "+row.numero+"</span></div>");
			});
			$.each(response.direcciones, function(i, row) {
				$('#ldirecciones').append("<div class='col'><span class='ms-2' id='ldir'>"+row.direccionexacta+" "+row.ubicaionTotal+" <span class='ms-2 badge bg-"+row.esprincipalStyle+"' id= 'dirprincipal'>"+row.esprincipal+"</span></span></div>");
			});
  		}
  };
  ajaxWithoutForm(ajaxUrl,"GET",successFunction);
}

function saveEditPassword(){
	var formData= {
			userId: parseInt($("#userId").val()), 
			oldPassword: $('#oldPassword').val(),
			newPassword: $('#newPassword').val(),
			newPasswordAgain: $('#newPasswordAgain').val()
	}
	$('.bindingError').remove();
	
	var ajaxUrl = sessionContexPath+'/profile/saveEditPassword.json';
	var successFunction = function(response){
 	   if(response.validated){
           //Set response
		   if(response.status=="OK"){
     			showSuccessMessage(response.message);
     			$('#EditUserPassword').modal('hide');
     		}else{
     			showErrorMessage(response.message);
     		}
        }else{
          //Set error messages
          $.each(response.messages,function(key,value){
        	  showErrorMessageByField('div' , "g_"+ key , value , '');
          });
        }
	   
   };
   
   ajaxPost(ajaxUrl,formData,successFunction);
          
}


function clearPasswordFields(){
	$('.bindingError').remove();
	$("#oldPassword").val("");
	$("#newPassword").val("");
	$("#newPasswordAgain").val("");
}

</script>

<title>home page</title>
</head>
<body>



				<div class="container-fluid p-0">

					<h1 class="h3 mb-3">Mi Perfil</h1>

					<div class="row">
						<div class="col-md-4 col-xl-4">
							<div class="card mb-3">
								<div class="card-header">
								
								<div class="card-actions float-end">
										<div class="dropdown position-relative">
											<a href="#" data-bs-toggle="dropdown" data-bs-display="static">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-horizontal align-middle"><circle cx="12" cy="12" r="1"></circle><circle cx="19" cy="12" r="1"></circle><circle cx="5" cy="12" r="1"></circle></svg>
            </a>

											<div class="dropdown-menu dropdown-menu-end">
												<a class="dropdown-item" href="#">Cambiar Foto</a>
												<a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target='#EditUserPassword' onclick="clearPasswordFields();">Cambiar Contraseña</a>
												<a class="dropdown-item" href="#">Cambiar Pregunta de Seguridad & Respuestas</a>
											</div>
										</div>
									</div>
								
									<h5 class="card-title mb-0">Informacion de Usuario</h5>
								</div>
								<div class="card-body text-center">
									
									<div>
<!-- 									<img src="img/avatars/avatar-4.jpg" alt="Stacie Hall" class="img-fluid rounded-circle mb-2" width="128" height="128"> -->
									<svg xmlns="http://www.w3.org/2000/svg" width="128" height="128" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
										  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
										  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
										</svg>
									</div>
									<h5 class="card-title mb-0"><span id="lfullname"></span></h5>
									<div class="text-muted mb-2">
										<c:forEach var="roles" items="${sessionScope.user.roles}">
								      		${roles.description}
								        </c:forEach>
									</div>

								</div>
								<hr class="my-0">
								<div class="card-body">
								
								
									<h5 class="h6 card-title">Detalle</h5>
									<div class="text-start">
                                            <p class="text-muted"><strong>Nombre de Usuario :</strong> <span class="ms-2" id="lusername"></span></p>
                                            <p class="text-muted"><strong>Rol :</strong><span class="ms-2" id="lrole"></span></p>
                                            <p class="text-muted"><strong>Estado :</strong> <span class="ms-2" id="lstatus"></span></p>
                                            <p class="text-muted"><strong>Fecha de Activacion :</strong> <span class="ms-2" id="lactivationdate"></span></p>
                                        </div>
								</div>
								<hr class="my-0">
								<div class="card-body">
									<h5 class="h6 card-title">Password & Seguridad</h5>
									<div class="text-start">
                                            <p class="text-muted"><strong>Contraseña </strong><a href="#" title="Cambiar Contraseña" data-bs-toggle="modal" data-bs-target='#EditUserPassword' onclick="clearPasswordFields();"><i class="bi bi-pencil-square"></i></a> </p>
											<p class="text-muted"><strong>Preguntas & Respuestas </strong> <a href="#" title="Cambiar Preguntas & Respuestas" data-bs-toggle="tooltip"><i class="bi bi-pencil-square"></i></a> </p>

                                        </div>
								</div>
								<hr class="my-0">
								<div class="card-body">
									<h5 class="h6 card-title">Ajustes</h5>
									<div class="text-start">
                                            <!--p class="text-muted"><strong>Tema :</strong> <span class="ms-2"><a onclick="setStyleSheet('/resources/css/light.css')" href="#">light</a><a onclick="setStyleSheet('/resources/css/dark.css')" href="#">dark</a></span></p-->
										<p class="text-muted"><strong>Tema :</strong> <span class="ms-2"><a onclick="setTheme('light')" href="#">light</a> <a onclick="setTheme('dark')" href="#">dark</a></span></p>
                                            </p>

                                        </div>
								</div>
							</div>
						</div>

						<div class="col-md-4 col-xl-4">
							<div class="card">
								<div class="card-header">
									<div class="card-actions float-end">
										<div class="dropdown position-relative">
											<a href="#" data-bs-toggle="dropdown" data-bs-display="static">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-horizontal align-middle"><circle cx="12" cy="12" r="1"></circle><circle cx="19" cy="12" r="1"></circle><circle cx="5" cy="12" r="1"></circle></svg>
            </a>

											<div class="dropdown-menu dropdown-menu-end">
												<a class="dropdown-item" href="#">Cambiar Informacion Personal</a>
												<a class="dropdown-item" href="#">Cambiar Telefono</a>
												<a class="dropdown-item" href="#">Cambiar Direccion</a>
												<a class="dropdown-item" href="#">Cambiar Cuanta Bancaria</a>
											</div>
										</div>
									</div>
									<h5 class="card-title mb-0">Informacion Personal</h5>
								</div>
								<div class="card-body">
									<div class="d-flex align-items-start">
										<div class="flex-grow-1">
										
										
												<div class="text-start">
			                                            <p class="text-muted"><strong><span id="ltipodcumento"></span> :</strong><span class="ms-2" id="lnumeroidentificacion"></span></p>
			                                            <p class="text-muted"><strong>Nombres :</strong><span class="ms-2" id="lnombres"></span></p>
			                                            <p class="text-muted"><strong>Apellidos :</strong><span class="ms-2" id="lapellidos"></span></p>
			                                            <p class="text-muted"><strong>Estado Civil :</strong><span class="ms-2" id="lestadocivil"></span></p>
			                                            <p class="text-muted"><strong>Sexo :</strong><span class="ms-2" id="lsexo"></span></p>
			                                            <p class="text-muted"><strong>Fecha de Nacimiento :</strong><span class="ms-2" id="lfechanacimiento"></span></p>
			                                            
			                                        </div>

										</div>
									</div>
								</div>
								<hr class="my-0">
								<div class="card-body">
									<h5 class="h6 card-title">Contacto</h5>
									<div class="text-start">
											<p class="text-muted"><strong>Email :</strong><span class="ms-2" id="lemail">asd</span></p>
                                            <p class="text-muted"><strong>Telefonos :</strong> </p>
                                            <div class="container">
											  <div class="row row-cols-auto" id="ltelefonos">
											  </div>
											</div>
                                        </div>
								</div>
								<hr class="my-0">
								<div class="card-body">
									<h5 class="h6 card-title">Direcciones</h5>
									<div class="text-start">
											
											<div class="container">

											  <div class="row row-cols-auto" id="ldirecciones">

											  </div>
											
                                    		</div>
									</div>
								</div>
								<hr class="my-0">
								<div class="card-body">
									<h5 class="h6 card-title">Cuenta Bancaria</h5>
									<div class="text-start">
											<p class="text-muted"><strong>Banco :</strong><span class="ms-2" id="lbanco">asd</span></p>
                                            <p class="text-muted"><strong>Numero :</strong><span class="ms-2" id="lcuentabancaria">asd</span></p>
                                           	<p class="text-muted"><strong>CCI :</strong><span class="ms-2" id="lcci">asd</span></p>
                                        </div>
								</div>
								<hr class="my-0">
								<div class="card-body">
									<h5 class="h6 card-title">Familia</h5>
									<div class="text-start">
											
											<div class="container">
											  <div class="row row-cols-auto">
											    <div class="col">
											      <span class="ms-2" id="lnombrefamilia">asd</span>
											    </div>
											  </div>
											</div>
											
                                        </div>
								</div>
							</div>
						</div>
						
						
						<div class="col-md-4 col-xl-4">
							<div class="card">
								<div class="card-header">
									<div class="card-actions float-end">
										<div class="dropdown position-relative">
											<a href="#" data-bs-toggle="dropdown" data-bs-display="static">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-horizontal align-middle"><circle cx="12" cy="12" r="1"></circle><circle cx="19" cy="12" r="1"></circle><circle cx="5" cy="12" r="1"></circle></svg>
            </a>

											<div class="dropdown-menu dropdown-menu-end">
												<a class="dropdown-item" href="#">Action</a>
												<a class="dropdown-item" href="#">Another action</a>
												<a class="dropdown-item" href="#">Something else here</a>
											</div>
										</div>
									</div>
									<h5 class="card-title mb-0">Informacion de Empleado</h5>
								</div>
								<div class="card-body h-100">

									<div class="d-flex align-items-start">
										<div class="flex-grow-1">
										
										
												<div class="text-start">
			                                            <p class="text-muted"><strong>Password :</strong> <span class="ms-2">Michael A. Franklin</span></p>
			                                            <p class="text-muted"><strong>Question & Answer :</strong><span class="ms-2">(+12) 123 1234 567</span></p>
			
			                                        </div>

										</div>
									</div>

									

									<hr class="my-0">

									<hr class="my-0">
									<div class="d-grid">
<!-- 										<a href="#" class="btn btn-primary">Load more</a> -->
									</div>
								</div>
							</div>
						</div>
						
						
						
						
					</div>

				</div>


<!-- Modal -->
<div class="modal fade" id="EditUserPassword" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Cambiar Contraseña</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       
<input type="hidden" id="userId" name="userId"/>

		<div class="form-container">
		        
		        
		        
		        
				  <div class="form-outline mb-4">
				    
				    <div class="input-group" id="g_oldPassword">
		              <span class="input-group-text" id="password-group">
		                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-shield-lock-fill" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M8 0c-.69 0-1.843.265-2.928.56-1.11.3-2.229.655-2.887.87a1.54 1.54 0 0 0-1.044 1.262c-.596 4.477.787 7.795 2.465 9.99a11.777 11.777 0 0 0 2.517 2.453c.386.273.744.482 1.048.625.28.132.581.24.829.24s.548-.108.829-.24a7.159 7.159 0 0 0 1.048-.625 11.775 11.775 0 0 0 2.517-2.453c1.678-2.195 3.061-5.513 2.465-9.99a1.541 1.541 0 0 0-1.044-1.263 62.467 62.467 0 0 0-2.887-.87C9.843.266 8.69 0 8 0zm0 5a1.5 1.5 0 0 1 .5 2.915l.385 1.99a.5.5 0 0 1-.491.595h-.788a.5.5 0 0 1-.49-.595l.384-1.99A1.5 1.5 0 0 1 8 5z"/>
						</svg>
		              </span>
		              <input type="password" id="oldPassword" name="oldPassword" class="form-control form-control-lg" placeholder="Contraseña Actual" aria-describedby="password-group"/>
		              <span class="input-group-text" onclick="password_show_hide('oldPassword');">
		                  <i class="fas fa-eye" id="show_eye_oldPassword"></i>
		                  <i class="fas fa-eye-slash d-none" id="hide_eye_oldPassword"></i>
		                </span>
		            </div>
				  </div>
				  <div class="form-outline mb-4">
				    
				    <div class="input-group" id="g_newPassword">
		              <span class="input-group-text" id="password-group">
		                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-shield-lock-fill" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M8 0c-.69 0-1.843.265-2.928.56-1.11.3-2.229.655-2.887.87a1.54 1.54 0 0 0-1.044 1.262c-.596 4.477.787 7.795 2.465 9.99a11.777 11.777 0 0 0 2.517 2.453c.386.273.744.482 1.048.625.28.132.581.24.829.24s.548-.108.829-.24a7.159 7.159 0 0 0 1.048-.625 11.775 11.775 0 0 0 2.517-2.453c1.678-2.195 3.061-5.513 2.465-9.99a1.541 1.541 0 0 0-1.044-1.263 62.467 62.467 0 0 0-2.887-.87C9.843.266 8.69 0 8 0zm0 5a1.5 1.5 0 0 1 .5 2.915l.385 1.99a.5.5 0 0 1-.491.595h-.788a.5.5 0 0 1-.49-.595l.384-1.99A1.5 1.5 0 0 1 8 5z"/>
						</svg>
		              </span>
		              <input type="password" id="newPassword" name="newPassword" class="form-control form-control-lg" placeholder="Nueva Contraseña" aria-describedby="password-group"/>
		              <span class="input-group-text" onclick="password_show_hide('newPassword');">
		                  <i class="fas fa-eye" id="show_eye_newPassword"></i>
		                  <i class="fas fa-eye-slash d-none" id="hide_eye_newPassword"></i>
		                </span>
		            </div>
				    
				  </div>
				  <div class="form-outline mb-4">
				    
				    <div class="input-group" id="g_newPasswordAgain">
		              <span class="input-group-text" id="password-group">
		                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-shield-lock-fill" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M8 0c-.69 0-1.843.265-2.928.56-1.11.3-2.229.655-2.887.87a1.54 1.54 0 0 0-1.044 1.262c-.596 4.477.787 7.795 2.465 9.99a11.777 11.777 0 0 0 2.517 2.453c.386.273.744.482 1.048.625.28.132.581.24.829.24s.548-.108.829-.24a7.159 7.159 0 0 0 1.048-.625 11.775 11.775 0 0 0 2.517-2.453c1.678-2.195 3.061-5.513 2.465-9.99a1.541 1.541 0 0 0-1.044-1.263 62.467 62.467 0 0 0-2.887-.87C9.843.266 8.69 0 8 0zm0 5a1.5 1.5 0 0 1 .5 2.915l.385 1.99a.5.5 0 0 1-.491.595h-.788a.5.5 0 0 1-.49-.595l.384-1.99A1.5 1.5 0 0 1 8 5z"/>
						</svg>
		              </span>
		              <input type="password" id="newPasswordAgain" name="newPasswordAgain" class="form-control form-control-lg" placeholder="Nueva Contraseña" aria-describedby="password-group"/>
		              <span class="input-group-text" onclick="password_show_hide('newPasswordAgain');">
		                  <i class="fas fa-eye" id="show_eye_newPasswordAgain"></i>
		                  <i class="fas fa-eye-slash d-none" id="hide_eye_newPasswordAgain"></i>
		                </span>
		            </div>
				    
				  </div>
		        
		        
		        
		       
		</div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-primary" onclick="saveEditPassword();">Guardar</button>
      </div>
    </div>
  </div>
</div>






</body>
</html>