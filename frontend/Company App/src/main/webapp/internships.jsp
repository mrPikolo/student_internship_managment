<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.List" %>
<%@ page import="company_app.dto.InternshipDTO" %>
<%@ page import="company_app.api.InternshipApi" %>
<%@ page import="company_app.dto.CompanyDTO" %>
    
<%
	if(session.getAttribute("company") == null){
		response.sendRedirect("login.jsp");
		return;
	}

	CompanyDTO company = (CompanyDTO) session.getAttribute("company");

	String action = request.getParameter("action");
	if("delete".equals(action)) {
		try{
			Long id = Long.parseLong(request.getParameter("id"));
			InternshipApi.delete(id);
			response.sendRedirect("internships.jsp");
	        return;
		}catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	List<InternshipDTO> listInternships = InternshipApi.getByCompany(company.getId());
%>	


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Internships</title>

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
	
		<!-- Header -->
		<div class="d-flex justify-content-between align-items-center mb-3">
			<div>
				<h4 class="fw-bold mb-0"><%=company.getName().toUpperCase()%> - Internships</h4>
				<small class="text-muted">Manage Internships</small>
			</div>
			<a href="dashboard.jsp" class="btn btn-outline-secondary btn-sm"> <i
				class="bi bi-arrow-left"></i>
			</a>
		</div>
		
		<!-- Button -->
		<div class="d-grid gap-2 mb-3">
			<a href="create-internship.jsp" class="btn btn-primary"> <i
				class="bi bi-plus-circle me-1"></i> Add Internship
			</a>
		</div>
		
		<!-- Internships list -->
		<div class="row g-3">
			<% if(listInternships != null) { 
				for(InternshipDTO i: listInternships){	
					company.setCompanyName(i.getCompanyName());
			%>
				
				<!-- Internship Card -->
			<div class="col-12 col-md-6 d-flex">
				<div class="card shadow-sm border-0 h-100 w-100">
					<div class="card-body">

						<h5 class="fw-bold mb-1"><%=i.getTitle()%></h5>
						<small class="text-muted d-block mb-3"><%=i.getDescription()%></small>
						<small class="text-muted d-block mb-2">Technologies:  <%=i.getTechnologies()%></small>
						<small class="text-muted d-block mb-2">Start:  <%=i.getStartDate()%></small>

						<div class="d-flex gap-2">
							<a href="update-internship.jsp?id=<%=i.getId()%>" 
								class="btn btn-sm btn-outline-primary flex-fill"> Edit </a>
							 <form method="post" action="internships.jsp" class="flex-fill">
							 	<input type="hidden" name="action" value="delete">
							 	<input type="hidden" name="id" value="<%=i.getId()%>">
							 	<button type="submit" class="btn btn-sm btn-outline-danger w-100">Delete</button>
							 </form>
						</div>

					</div>
				</div>
			</div>
				
			<%}
			} %>
		</div>
	
	</div>
</body>
</html>