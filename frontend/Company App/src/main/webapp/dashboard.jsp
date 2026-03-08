<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	if(session.getAttribute("company") == null)
		response.sendRedirect("login.jsp");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script> 
    <!-- Bootstrap icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    
    <title>Dashboard</title>
</head>

<body class="bg-light">
	<div class="container py-4">

	    <!-- Header sa logout -->
	    <div class="d-flex justify-content-between align-items-center mb-4">
	        <div>
	            <h4 class="fw-bold mb-0">Dashboard</h4>
	            <small class="text-muted">Company intrenship managment</small>
	        </div>
	
	        <!-- Logout dugme -->
	        <form method="post" action="logout" class="ms-3">
	            <button type="submit" class="btn btn-outline-danger btn-sm">
	                <i class="bi bi-box-arrow-right me-1"></i> Logout
	            </button>
	        </form>
	    </div>
	    
	    <!-- Cards -->
	    <div class="row g-5">
	
	        <div class="col-12 col-md-6 d-flex">
	            <a href="internships.jsp" class="text-decoration-none w-100">
	                <div class="card shadow-sm border-0 h-100 w-75">
	                    <div class="card-body d-flex align-items-center">
	                        <div class="me-3 fs-3 text-primary">
	                            <i class="bi bi-briefcase-fill"></i>
	                        </div>
	                        <div>
	                            <h6 class="mb-1 text-dark">Internships</h6>
	                            <small class="text-muted">Internship management</small>
	                        </div>
	                    </div>
	                </div>
	            </a>
	        </div>
	        
	        <div class="col-12 col-md-6 d-flex">
	            <a href="applications.jsp" class="text-decoration-none w-100">
	                <div class="card shadow-sm border-0 h-100 w-75">
	                    <div class="card-body d-flex align-items-center">
	                        <div class="me-3 fs-3 text-info">
	                            <i class="bi bi-people-fill"></i>
	                        </div>
	                        <div>
	                            <h6 class="mb-1 text-dark">Application</h6>
	                            <small class="text-muted">Overview of registered students for internships</small>
	                        </div>
	                    </div>
	                </div>
	            </a>
	        </div>
	        
	        <div class="col-12 col-md-6 d-flex">
	            <a href="evaluations.jsp" class="text-decoration-none w-100">
	                <div class="card shadow-sm border-0 h-100 w-75">
	                    <div class="card-body d-flex align-items-center">
	                        <div class="me-3 fs-3 text-success">
	                            <i class="bi bi-clipboard-check-fill"></i>
	                        </div>
	                        <div>
	                            <h6 class="mb-1 text-dark">Evaluation</h6>
	                            <small class="text-muted">Evaluation of students who go on internship</small>
	                        </div>
	                    </div>
	                </div>
	            </a>
	        </div>
	        
	        <div class="col-12 col-md-6 d-flex">
	            <a href="change-password.jsp" class="text-decoration-none w-100">
	                <div class="card shadow-sm border-0 h-100 w-75">
	                    <div class="card-body d-flex align-items-center">
	                        <div class="me-3 fs-3 text-secondary">
	                            <i class="bi bi-key-fill"></i>
	                        </div>
	                        <div>
	                            <h6 class="mb-1 text-dark">Change password</h6>
	                            <small class="text-muted">Password management</small>
	                        </div>
	                    </div>
	                </div>
	            </a>
	        </div>
	
	
	    </div>
	 </div>
	  
    

</body>
</html>