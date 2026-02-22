<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script> 
    <!-- Bootstrap icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <title>Dashboard</title>
</head>

<body class="bg-light">

<div class="container py-4">

    <!-- Header sa logout -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <div>
            <h4 class="fw-bold mb-0">Dashboard</h4>
            <small class="text-muted">Student intrenship managment</small>
        </div>

        <!-- Logout dugme -->
        <form method="post" action="logout" class="ms-3">
            <button type="submit" class="btn btn-outline-danger btn-sm">
                <i class="bi bi-box-arrow-right me-1"></i> Logout
            </button>
        </form>
    </div>

    <!-- Cards -->
    <div class="row g-5">

        <div class="col-12 col-md-6 d-flex">
            <a href="students" class="text-decoration-none w-100">
                <div class="card shadow-sm border-0 h-100 w-75">
                    <div class="card-body d-flex align-items-center">
                        <div class="me-3 fs-3 text-primary">
                            <i class="bi bi-people-fill"></i>
                        </div>
                        <div>
                            <h6 class="mb-1 text-dark">Students</h6>
                            <small class="text-muted">Review and manage students</small>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-12 col-md-6 d-flex">
            <a href="companies" class="text-decoration-none w-100">
                <div class="card shadow-sm border-0 h-100 w-75">
                    <div class="card-body d-flex align-items-center">
                        <div class="me-3 fs-3 text-success">
                            <i class="bi bi-building"></i>
                        </div>
                        <div>
                            <h6 class="mb-1 text-dark">Companies</h6>
                            <small class="text-muted">Company management</small>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-12 col-md-6 d-flex">
            <a href="internships" class="text-decoration-none w-100">
                <div class="card shadow-sm border-0 h-100 w-75">
                    <div class="card-body d-flex align-items-center">
                        <div class="me-3 fs-3 text-warning">
                            <i class="bi bi-briefcase-fill"></i>
                        </div>
                        <div>
                            <h6 class="mb-1 text-dark">Internships</h6>
                            <small class="text-muted">View all internship ads</small>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <div class="col-12 col-md-6 d-flex">
            <a href="reports" class="text-decoration-none w-100">
                <div class="card shadow-sm border-0 h-100 w-75">
                    <div class="card-body d-flex align-items-center">
                        <div class="me-3 fs-3 text-danger">
                            <i class="bi bi-bar-chart-fill"></i>
                        </div>
                        <div>
                            <h6 class="mb-1 text-dark">Monitoring student work</h6>
                            <small class="text-muted">Review of work logs, feedback received from
                                companies, student evaluation</small>
                        </div>
                    </div>
                </div>
            </a>
        </div>

    </div>
</div>

</body>
</html>