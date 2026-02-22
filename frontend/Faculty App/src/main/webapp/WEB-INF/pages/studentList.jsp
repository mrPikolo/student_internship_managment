<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="faculty_app.dto.Student" %>
    
<%
	List<Student> studentsList = (List<Student>) request.getAttribute("studentsList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Students</title>

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
				<h4 class="fw-bold mb-0">Students</h4>
				<small class="text-muted">Manage registered students</small>
			</div>
			<a href="dashboard" class="btn btn-outline-secondary btn-sm"> <i
				class="bi bi-arrow-left"></i>
			</a>
		</div>

		<!-- Buttons -->
		<div class="d-grid gap-2 mb-3">
			<a href="students?action=add" class="btn btn-primary"> <i
				class="bi bi-plus-circle me-1"></i> Add Student
			</a>

			<button class="btn btn-outline-success" data-bs-toggle="collapse"
				data-bs-target="#uploadSection">
				<i class="bi bi-upload me-1"></i> Upload CSV
			</button>
		</div>

		<!-- CSV Upload -->
		<div class="collapse mb-3" id="uploadSection">
			<div class="card shadow-sm border-0">
				<div class="card-body">
					<form method="post" action="students"
						enctype="multipart/form-data">
						<input type="hidden" name="action" value="upload-csv">
						<div class="mb-3">
							<label class="form-label">Select CSV file</label> 
							<input type="file" name="file" class="form-control" 
								accept=".csv" required>
						</div>
						<button type="submit" class="btn btn-success w-100">
							Upload File</button>
					</form>
				</div>
			</div>
		</div>

		<!-- Student list -->
		<div class="row g-3">

			<%
			for (Student s : studentsList) {
			%>
			<!-- Student Card -->
			<div class="col-12 col-md-6 d-flex">
				<div class="card shadow-sm border-0 h-100 w-100">
					<div class="card-body">

						<h6 class="fw-bold mb-1"><%=s.getFirstName() + " " + s.getLastName()%></h6>
						<small class="text-muted d-block">index: <%=s.getIndexNumber()%></small>
						<small class="text-muted d-block mb-3"><%=s.getEmail()%></small>

						<div class="d-flex gap-2">
							<a href="students?action=edit&id=<%=s.getId()%>" 
								class="btn btn-sm btn-outline-primary flex-fill"> Edit </a>
							 <form method="post" action="students" class="flex-fill">
							 	<input type="hidden" name="action" value="delete">
							 	<input type="hidden" name="id" value="<%=s.getId()%>">
							 	<button type="submit" class="btn btn-sm btn-outline-danger w-100">Delete</button>
							 </form>
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
