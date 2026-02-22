<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>

<% 
	String error = (String) session.getAttribute("notification");
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>

<!-- Bootstrap 5 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap icons -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
</head>

<body class="bg-light d-flex align-items-center" style="min-height: 100vh;">

	<div class="container">
		<div class="row justify-content-center">

			<div class="col-12 col-sm-10 col-md-6 col-lg-4">
				<div class="card shadow-sm border-0">
					<div class="card-body p-4">

						<!-- Header -->
						<div class="text-center mb-4">
							<h4 class="fw-bold mb-1">Internship Management System</h4>
						</div>

						<!-- Form -->
						<form action="login?action=login" method="post">
							<!-- Username -->
							<div class="mb-3">
								<label class="form-label">Username</label>
								<div class="input-group">
									<span class="input-group-text bg-white"> <i
										class="bi bi-person"></i>
									</span> <input type="text" name="username" class="form-control"
										required>
								</div>
							</div>

							<!-- Password -->
							<div class="mb-3">
								<label class="form-label">Password</label>
								<div class="input-group">
									<span class="input-group-text bg-white"> <i
										class="bi bi-lock"></i>
									</span> <input type="password" name="password" class="form-control"
										required>
								</div>
							</div>

							<!-- Submit -->
							<div class="d-grid mt-4">
								<button type="submit" class="btn btn-primary">Sign In</button>
							</div>

							<% if(error != null) {                	               	
                %>
							<div class="mb-3 mt-3">
								<p class="text-danger"><%= error %></p>
							</div>
							<%  }  %>

						</form>
					</div>

				</div>
			</div>
		</div>
	</div>

</body>

</html>
