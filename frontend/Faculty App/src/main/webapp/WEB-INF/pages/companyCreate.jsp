<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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

						<h5 class="fw-bold mb-3">New Company</h5>

						<form method="post" action="companies">
							<input type="hidden" name="action" value="create">

							<div class="mb-3">
								<label class="form-label">Name</label> 
								<input type="text" name="name" class="form-control" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Description</label>
								<textarea rows="6" cols="70" name="description" class="form-control" required></textarea>
								
							</div>

							<div class="mb-3">
								<label class="form-label">Username</label>
								<input type="text"	name="username" class="form-control" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Password</label> 
								<input type="text" name="password" class="form-control" required>
							</div>
							<div class="d-grid gap-2 mt-4">
								<button type="submit" class="btn btn-primary">Create</button>
								<a href="companies" class="btn btn-outline-secondary"> Cancel
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