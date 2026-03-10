<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.List" %>
<%@ page import="faculty_app.dto.Internship" %>
    
<%
	List<Internship> internshipList = (List<Internship>) request.getAttribute("internshipList");
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
<script src="scripts/search-filter.js"></script>

</head>

<body class="bg-light">

	<div class="container py-4">

		<!-- Header -->
		<div class="d-flex justify-content-between align-items-center mb-3">
			<div>
				<h4 class="fw-bold mb-0">Internships</h4>
			</div>
			<a href="dashboard" class="btn btn-outline-secondary btn-sm"> <i
				class="bi bi-arrow-left"></i>
			</a>
		</div>
		
<!-- Search -->
		<div class="card shadow-sm border-0 mb-3">
			<div class="card-body">

				<form method="get" action="internships" id="searchForm">

					<div class="row g-2">

						<div class="col-md-3">
							<select name="searchBy" class="form-select">
								<option value="company">Company</option>
								<option value="technologies">Technologies</option>
								<option value="startDate">Start date</option>
							</select>
						</div>

						<div class="col-md-5">
							<input type="text" name="searchValue" class="form-control"
								placeholder="Enter search value">
						</div>

						<div class="col-md-2 d-grid">
							<button class="btn btn-primary">
								<i class="bi bi-search"></i> Search
							</button>
						</div>

						<div class="col-md-2 d-grid">
							<a href="internships" class="btn btn-outline-secondary"> <i
								class="bi bi-x-circle"></i> Clear
							</a>
						</div>

					</div>

				</form>

			</div>
		</div>


		<!-- Internships list -->
		<div class="row g-3">

			<%
			for (Internship i : internshipList) {
			%>
			<!-- Internship Card -->
			<div class="col-12 col-md-4 d-flex">
				<div class="card shadow-sm border-0 h-100 w-100">
					<div class="card-body">
						
						<p class="mb-2">
							<i class="bi bi-building"></i>
							<strong>Company:</strong> <%=i.getCompanyName()%>
						</p>
						
						<p class="mb-1">
							<i class="bi bi-code-slash"></i>
							<strong>Technologies:</strong> <%=i.getTechnologies()%>
						</p>
						
						<p class="mb-1">
							<i class="bi bi-calendar"></i>
							<strong>Min student year:</strong> <%=i.getMinYear()%>
						</p>
						
						<p class="mb-1">
							<i class="bi bi-calendar"></i>
							<strong>Start date:</strong> <%=i.getStartDate()%>
						</p>
						
						<p class="mb-1">
							<i class="bi bi-people"></i>
							<strong>Max students:</strong> <%=i.getMaxStudents()%>
						</p>

						<div class="d-flex gap-2">
							<a href="internships?action=details&id=<%=i.getId()%>" 
								class="btn btn-sm btn-outline-primary flex-fill"> Details </a>
						</div>

					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>

	</div>

</body>
</html>
