<%@page import="company_app.dto.UpdateInternshipDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="company_app.dto.InternshipDTO" %>
<%@ page import="company_app.api.InternshipApi" %>
<%@ page import="company_app.dto.CompanyDTO" %>
    
<%
	if(session.getAttribute("company") == null){
		response.sendRedirect("login.jsp");
		return;
	}

	CompanyDTO company = (CompanyDTO) session.getAttribute("company");
	
	Long id = Long.parseLong(request.getParameter("id"));
	InternshipDTO internship = InternshipApi.getById(id);
	
	String action = request.getParameter("action");
	if("update".equals(action)) {
		try{
			UpdateInternshipDTO i = new UpdateInternshipDTO();
			
			i.setTechnologies(request.getParameter("technologies"));
			i.setMinYear(Integer.parseInt(request.getParameter("minYear")));
			i.setMaxStudents(Integer.parseInt(request.getParameter("maxStudents")));
			i.setStartDate(request.getParameter("startDate"));
			i.setEndDate(request.getParameter("endDate"));
			
			InternshipApi.update(id,i);
			response.sendRedirect("internships.jsp");
	        return;
		}catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Update internship</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container py-4">
		<div class="row justify-content-center">
			<div class="col-12 col-md-6">

				<div class="card shadow-sm border-0">
					<div class="card-body p-4">

						<h5 class="fw-bold mb-3">Update Internship</h5>
						<h5 class="mb-5"><%=internship.getTitle() %></h5>

						<form method="post" action="update-internship.jsp">
							<input type="hidden" name="action" value="update">
							<input type="hidden" name="id" value="<%=internship.getId()%>">

							<div class="mb-3">
								<input type="hidden" name="companyName" class="form-control" readonly value="<%=company.getCompanyName()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">Technologies</label>
								<input type="text"	name="technologies" class="form-control" 
									value="<%=internship.getTechnologies()%>" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Min Year</label> 
								<input type="number" name="minYear" class="form-control" min=1 max=6 
									value="<%=internship.getMinYear()%>" required>
							</div>
							
							<div class="mb-3">
								<label class="form-label">Max Students</label> 
								<input type="number" name="maxStudents" class="form-control" min=1 
								value="<%=internship.getMaxStudents()%>"  required>
							</div>
							
							<div class="mb-3">
								<label class="form-label">Start Date</label> 
								<input type="date" name="startDate" class="form-control" 
								value="<%=internship.getStartDate()%>" required>
							</div>
							
							<div class="mb-3">
								<label class="form-label">End Date</label> 
								<input type="date" name="endDate" class="form-control" 
								value="<%=internship.getEndDate()%>" required>
							</div>
							
							<div class="d-grid gap-2 mt-4">
								<button type="submit" class="btn btn-primary">Update</button>
								<a href="internships.jsp" class="btn btn-outline-secondary"> Cancel
								</a>
							</div>

						</form>

					</div>
				</div>

			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>