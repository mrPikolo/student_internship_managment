<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="faculty_app.dto.Internship" %>

<%
    Internship internship = (Internship) request.getAttribute("internship");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Internship Details</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
    .detail-label { font-weight: 600; }
    .badge-tech { font-size: 0.9rem; margin-right: 3px; }
</style>
</head>

<body class="bg-light">

<div class="container py-4">

    <!-- Header -->
    <div class="d-flex justify-content-between align-items-center mb-3 flex-wrap">
        <h4 class="fw-bold mb-2 mb-md-0">Internship Details</h4>
        <a href="internships" class="btn btn-outline-secondary btn-sm">
            <i class="bi bi-arrow-left"></i> Back
        </a>
    </div>

    <%
    if(internship == null){
    %>
    <div class="alert alert-danger">Internship not found.</div>
    <%
    } else {
    %>

    <div class="card shadow-sm border-0 w-100">

        <div class="card-body">

            <!-- Company + Title -->
            <div class="mb-3">
                <h5 class="fw-bold mb-1 fs-5">
                    <i class="bi bi-building"></i> <%= internship.getCompanyName() %>
                </h5>
                <h6 class="text-muted mb-0 fs-6">
                    <i class="bi bi-journal-text"></i> <%= internship.getTitle() %>
                </h6>
            </div>

            <!-- Description -->
            <div class="mb-3">
                <p class="detail-label"><i class="bi bi-card-text"></i> Description:</p>
                <p class="ps-2 text-muted fs-6"><%= internship.getDescription() %></p>
            </div>

            <!-- Details Grid -->
            <div class="row g-2 text-muted fs-6">

                <div class="col-12 col-sm-6">
                    <span class="detail-label"><i class="bi bi-code-slash"></i> Technologies:</span><br>
                    <%
                    String[] techs = internship.getTechnologies().split(",");
                    for(String t : techs){
                    %>
                        <span class="badge bg-primary badge-tech"><%= t.trim() %></span>
                    <%
                    }
                    %>
                </div>

                <div class="col-6 col-sm-3">
                    <span class="detail-label"><i class="bi bi-mortarboard"></i> Min year:</span>
                    <div><%= internship.getMinYear() %></div>
                </div>

                <div class="col-6 col-sm-3">
                    <span class="detail-label"><i class="bi bi-people"></i> Max students:</span>
                    <div><%= internship.getMaxStudents() %></div>
                </div>

                <div class="col-6 col-sm-3">
                    <span class="detail-label"><i class="bi bi-calendar"></i> Start date:</span>
                    <div><%= internship.getStartDate() %></div>
                </div>
                
                <div class="col-6 col-sm-3">
                    <span class="detail-label"><i class="bi bi-calendar"></i> End date:</span>
                    <div><%= internship.getEndDate() %></div>
                </div>

            </div>

        </div>
    </div>

    <%
    }
    %>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>