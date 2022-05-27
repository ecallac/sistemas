<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="false"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../includes/styles.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login page</title>
	<style type="text/css">

	</style>
	<script type="text/javascript">

		function password_show_hide() {
			var x = document.getElementById("password");
			var show_eye = document.getElementById("show_eye");
			var hide_eye = document.getElementById("hide_eye");
			hide_eye.classList.remove("d-none");
			if (x.type === "password") {
				x.type = "text";
				show_eye.style.display = "none";
				hide_eye.style.display = "block";
			} else {
				x.type = "password";
				show_eye.style.display = "block";
				hide_eye.style.display = "none";
			}
		}

		$(function() {

		});
	</script>
</head>
<body onload='document.loginForm.userName.focus();' >

<form name='loginForm' action='<c:url value="j_spring_security_check"/>' method='POST' class="form-horizontal">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<section class="vh-100">
		<div class="container py-5 h-100">
			<div class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-8 col-lg-6 col-xl-5">
					<div class="card shadow-2-strong" style="border-radius: 1rem;">
						<div class="card-body p-5 text-center">

							<h3 class="mb-5">Bienvenido de nuevo, inicia sesión</h3>
							<c:if test="${not empty error}">
								<div class="alert alert-danger alert-dismissible" role="alert">
									<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
									<div class="alert-icon">
										<i class="bi bi-bell"></i></i>
									</div>
									<div class="alert-message">
										<strong>${error}</strong>
									</div>
								</div>

								<%-- 						<div class="alert alert-danger mb-4" role="alert">${error}</div> --%>
							</c:if>
							<c:if test="${not empty message}">
								<div class="alert alert-info alert-dismissible" role="alert">
									<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
									<div class="alert-icon">
										<i class="bi bi-bell"></i></i>
									</div>
									<div class="alert-message">
										<strong>${message}</strong>
									</div>
								</div>
								<%-- 						<div class="alert alert-info mb-4" role="alert">${message}</div> --%>
							</c:if>
							<div class="form-outline mb-4">

								<div class="input-group">
		              <span class="input-group-text" id="username-group">
		                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
						  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"></path>
						  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"></path>
						</svg>
		              </span>
									<input type="text" id="userName" name="userName" class="form-control form-control-lg" placeholder="Nombre de Usuario" aria-describedby="username-group"/>
								</div>



							</div>
							<div class="form-outline mb-4">

								<div class="input-group">
		              <span class="input-group-text" id="password-group">
		                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-shield-lock-fill" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M8 0c-.69 0-1.843.265-2.928.56-1.11.3-2.229.655-2.887.87a1.54 1.54 0 0 0-1.044 1.262c-.596 4.477.787 7.795 2.465 9.99a11.777 11.777 0 0 0 2.517 2.453c.386.273.744.482 1.048.625.28.132.581.24.829.24s.548-.108.829-.24a7.159 7.159 0 0 0 1.048-.625 11.775 11.775 0 0 0 2.517-2.453c1.678-2.195 3.061-5.513 2.465-9.99a1.541 1.541 0 0 0-1.044-1.263 62.467 62.467 0 0 0-2.887-.87C9.843.266 8.69 0 8 0zm0 5a1.5 1.5 0 0 1 .5 2.915l.385 1.99a.5.5 0 0 1-.491.595h-.788a.5.5 0 0 1-.49-.595l.384-1.99A1.5 1.5 0 0 1 8 5z"/>
						</svg>
		              </span>
									<input type="password" id="password" name="password" class="form-control form-control-lg" placeholder="contraseña" aria-describedby="password-group"/>
									<span class="input-group-text" onclick="password_show_hide();">
		                  <i class="fas fa-eye" id="show_eye"></i>
		                  <i class="fas fa-eye-slash d-none" id="hide_eye"></i>
		                </span>
								</div>


							</div>
							<!-- Checkbox -->
							<div class="form-check d-flex justify-content-center mb-4">
								<label class="form-check-label ">
									<input
											class="form-check-input"
											type="checkbox"
											id="remember-me"
									/> Recuerdame | <a class="text-decoration-none" href="#!">Olvidaste la contraseña?</a></label>

							</div>


							<div class="d-grid gap-2 mt-3">
								<button class="btn btn-primary btn-lg btn-block" type="submit">Ingresar</button>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</form>
	
</body>
</html>