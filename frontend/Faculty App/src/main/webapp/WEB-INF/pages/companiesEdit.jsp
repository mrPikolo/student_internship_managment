<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="faculty_app.dto.Company" %>

<%
	List<Company> companiesList = (List<Company>) request.getAttribute("companiesList");
%>
	
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Bootstrap icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">

<script src="scripts/companysEditScript.js"></script>
<title>Companies</title>
</head>

<body class="bg-light">

	<div class="container py-4">

		<!-- Header -->
		<div class="d-flex justify-content-between align-items-center mb-3">
			<div>
				<h4 class="fw-bold mb-0">Companies</h4>
				<small class="text-muted">Manage registered Companies</small>
			</div>
			<a href="dashboard" class="btn btn-outline-secondary btn-sm"> <i
				class="bi bi-arrow-left"></i>
			</a>
		</div>

		<!-- Action Buttons -->
		<div class="d-grid gap-2 mb-3">
			<a href="companies?action=add" class="btn btn-primary"> <i
				class="bi bi-plus-circle me-1"></i> Add Company
			</a>
		</div>

		<!-- ================= MOBILE VIEW ================= -->
		<div class="d-md-none">

		<%for (Company company: companiesList) {
		%>
			<!-- Card -->
			<div class="card shadow-sm mb-3">
				<div class="card-body">

					<div class="d-flex justify-content-between">
						<h6 class="card-title mb-1"><%=company.getName() %></h6>
						
						<%if(company.isAccountStatus()) {%>
						 	<span class="badge bg-success">Active</span>
						<%} else {%>
							<span class="badge bg-danger">Deactivated</span>
						<%}%>	
					</div>
					<p class="small text-muted mb-2"><%=company.getDescription() %></p>

					<div class="d-grid gap-2">
					<%if(company.isAccountStatus()) {%>
						<button class="btn btn-outline-danger btn-sm"
							data-bs-toggle="modal" data-bs-target="#statusModal" data-id="<%=company.getId() %>"
							data-name="<%=company.getName() %>" data-status="deactivate">
							Deactivate</button>
					<%} else {%>
						<button class="btn btn-outline-success btn-sm"
							data-bs-toggle="modal" data-bs-target="#statusModal" data-id="<%=company.getId() %>"
							data-name="<%=company.getName() %>" data-status="activate">
							Activate</button>
					<%}%>
					</div>

				</div>
			</div>
		<%} %>
			</div> 
		<!-- ================= DESKTOP VIEW ================= -->
		<div class="table-responsive d-none d-md-block">
			<table class="table table-hover align-middle">
				<thead class="table-light">
					<tr>
						<th>Company name</th>
						<th>Description</th>
						<th>Status</th>
						<th width="220">Actions</th>
					</tr>
				</thead>
				<tbody>
					<%for (Company company: companiesList) {%>
					<tr>
						<td><strong><%=company.getName() %></strong></td>
						<td><%=company.getDescription() %></td>
						<td>
							<%if(company.isAccountStatus()) {%>
						 	<span class="badge bg-success">Active</span>
						<%} else {%>
							<span class="badge bg-danger">Deactivated</span>
						<%}%>	
						</td>
						<td>
							<div class="d-flex gap-2">
								<%if(company.isAccountStatus()) {%>
									<button class="btn btn-sm btn-outline-danger w-100"
										data-bs-toggle="modal" data-bs-target="#statusModal"
										data-id="<%=company.getId() %>" 
										data-name="<%=company.getName() %>" 
										data-status="deactivate">
										Deactivate</button>
								<%} else {%>
									<button class="btn btn-sm btn-outline-success w-100"
										data-bs-toggle="modal" data-bs-target="#statusModal"
										data-id="<%=company.getId() %>" 
										data-name="<%=company.getName() %>" 
										data-status="activate">
										Activate</button>
								<%}%>
							</div>
						</td>
					</tr>
					<%} %>
				</tbody>
			</table>
		</div>

		<!-- ================= MODAL ================= -->
		<div class="modal fade" id="statusModal" tabindex="-1">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">

					<div class="modal-header">
						<h5 class="modal-title">Account menagment</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>

					<div class="modal-body">
						<p id="modalMessage" class="mb-0"></p>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancel</button>

						<form method="post" action="companies">
							<input type="hidden" name="companyId" id="modalCompanyId">
							<input type="hidden" name="action" id="modalAction">
							<button type="submit" id="modalConfirmBtn" class="btn">
								Confirm</button>
						</form>
					</div>

				</div>
			</div>
		</div>
</body>

</html>