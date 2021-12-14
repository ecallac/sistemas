<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../includes/styles.jsp" />
<title>Access Denied</title>
</head>
<body>
 <div class="d-flex justify-content-center w-100">
		<main class="content d-flex p-0">
			<div class="container d-flex flex-column">
				<div class="row h-100">
					<div class="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-50">
						<div class="d-table-cell align-middle">

							<div class="text-center">
								<h1 class="display-1 fw-bold">403</h1>
								<p class="h1">Page not found.</p>
								<p class="h2 fw-normal mt-3 mb-4"><h3 style="color:red;">${message}</h3></p>
							</div>

						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
    
</body>
</html>