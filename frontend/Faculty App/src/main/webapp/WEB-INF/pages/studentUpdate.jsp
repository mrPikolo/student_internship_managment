<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="faculty_app.dto.Student" %>
    
<%
	Student student = (Student) request.getAttribute("student");
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
</head>

<body class="bg-light">
	<div class="container py-4">
		<div class="row justify-content-center">
			<div class="col-12 col-md-6">

				<div class="card shadow-sm border-0">
					<div class="card-body p-4">

						<h5 class="fw-bold mb-3">Edit Student</h5>

						<form method="post" action="students">
							<input type="hidden" name="action" value="update">
							 <input type="hidden" name="id" value="<%=student.getId()%>">

							<div class="mb-3">
								<label class="form-label">First Name</label> 
								<input type="text" name="firstName" class="form-control"
									value="<%=student.getFirstName()%>" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Last Name</label>
								<input type="text" name="lastName" class="form-control"
									value="<%=student.getLastName()%>" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Email</label>
								<input type="email"	name="email" class="form-control"
									value="<%=student.getEmail()%>" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Birth Date</label> 
								<input type="date" name="birthDate" class="form-control"
									value="<%=student.getBirthDate()%>" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Index number</label> 
								<input type="text" name="indexNumber" class="form-control"
									value="<%=student.getIndexNumber()%>" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Username</label> 
								<input type="text" name="username" class="form-control"
									value="<%=student.getUsername()%>" required>
							</div>

							<div class="d-grid gap-2 mt-4">
								<button type="submit" class="btn btn-primary">Update</button>
								<a href="students" class="btn btn-outline-secondary"> Cancel
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
