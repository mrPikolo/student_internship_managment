<%@page import="company_app.api.CompanyApi"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="company_app.dto.InternshipDTO" %>
<%@ page import="company_app.api.InternshipApi" %>
<%@ page import="company_app.dto.CompanyDTO" %>
<%@ page import="company_app.dto.LoginResponseDTO" %>
    
<%
	if(session.getAttribute("company") == null){
	    response.sendRedirect("login.jsp");
	    return;
	}

	CompanyDTO company = (CompanyDTO) session.getAttribute("company");
	
	String error =(String) session.getAttribute("error");
	session.removeAttribute("error");
	
	String action = request.getParameter("action");
	
	if("update".equals(action)) {
		
		try{
			String currentPassword = request.getParameter("current");
			String newPassword = request.getParameter("new");
			String confirmPassword = request.getParameter("confirmNew");
						
			if(!newPassword.equals(confirmPassword)){
                session.setAttribute("error", "New and confirmed passwords do not match!");
                response.sendRedirect("change-password.jsp");
                return;
            }
			else {
				LoginResponseDTO res = CompanyApi.changePassword(company.getId(), currentPassword, newPassword);
				
				if(res.getError() != null){
				    error = res.getError();
				    session.setAttribute("error", error);
					response.sendRedirect("change-password.jsp");
					return;
				}
				else if(res.getUser() != null){
					
					response.sendRedirect("dashboard.jsp");
			        return;
				}
				
			}
		}catch (Exception e) {
			session.setAttribute("error", "Server error!");
            response.sendRedirect("change-password.jsp");
            return;
	    }
		
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Change password</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
	<div class="container py-4">

		<div class="row justify-content-center">
			<div class="col-12 col-md-6">

				<div class="card shadow-sm border-0">
					<div class="card-body p-4">

						<!-- Header -->
						<div
							class="d-flex justify-content-between align-items-center mb-3">
							<div>
								<h4 class="fw-bold mb-0"><%=company.getName().toUpperCase()%>
								</h4>
								<small class="text-muted">Manage Password</small>
							</div>
							<a href="dashboard.jsp" class="btn btn-outline-secondary btn-sm">
								<i class="bi bi-arrow-left"></i>
							</a>
						</div>

						<form method="post" action="change-password.jsp">

							<input type="hidden" name="action" value="update">

							<div class="mb-3">
								<label class="form-label">Current password</label> <input
									type="text" name="current" class="form-control" required>
							</div>
							
							<% if(error != null){ %>
							    <div class="alert alert-danger">
							        <%=error%>
							    </div>
							<% } %>

							<div class="mb-3">
								<label class="form-label">New password</label> <input
									type="text" name="new" class="form-control" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Confirm new password</label> <input
									type="text" name="confirmNew" class="form-control" required>
							</div>

							<div class="d-grid gap-2 mt-4">
								<button type="submit" class="btn btn-primary">Change</button>
								<a href="dashboard.jsp" class="btn btn-outline-secondary">
									Cancel </a>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>