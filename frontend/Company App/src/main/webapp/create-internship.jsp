<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="company_app.dto.InternshipDTO" %>
<%@ page import="company_app.api.InternshipApi" %>
<%@ page import="company_app.api.CompanyApi" %>
<%@ page import="company_app.dto.CompanyDTO" %>
    
<%
	if(session.getAttribute("company") == null){
		response.sendRedirect("login.jsp");
		return;
	}

	CompanyDTO company = (CompanyDTO) session.getAttribute("company");
	String companyName = CompanyApi.getCompanyName(company.getId());
	company.setCompanyName(companyName);
	session.setAttribute("company", company);
	
			
	String action = request.getParameter("action");
	if("create".equals(action)) {
		try{
			InternshipDTO i = new InternshipDTO();
			i.setId(0L);
			i.setCompanyName(request.getParameter("companyName"));
			i.setTitle(request.getParameter("title"));
			i.setDescription(request.getParameter("description"));
			i.setTechnologies(request.getParameter("technologies"));
			i.setMinYear(Integer.parseInt(request.getParameter("minYear")));
			i.setMaxStudents(Integer.parseInt(request.getParameter("maxStudents")));
			i.setStartDate(request.getParameter("startDate"));
			i.setEndDate(request.getParameter("endDate"));
			
			System.out.println("CREATE Internship : " + i);
			
			InternshipApi.create(i);
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
<title>Add internship</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">
	<link 
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"
	 rel="stylesheet">
	 

</head>
<body class="bg-light">
	<div class="container py-4">
		<div class="row justify-content-center">
			<div class="col-12 col-md-6">

				<div class="card shadow-sm border-0">
					<div class="card-body p-4">

						<h5 class="fw-bold mb-3">New Internship</h5>

						<form method="post" action="create-internship.jsp">
							<input type="hidden" name="action" value="create">

							<div class="mb-3">
								<input type="hidden" name="companyName" class="form-control" readonly value="<%=companyName%>">
							</div>
							
							<div class="mb-3">
								<label class="form-label">Title</label>
								<input type="text" name="title" class="form-control" required>
								
							</div>

							<div class="mb-3">
								<label class="form-label">Description</label>
								<textarea rows="6" cols="70" name="description" class="form-control" required></textarea>
								
							</div>

							<div class="mb-3">
								<label class="form-label">Technologies</label>
								<input type="text"	name="technologies" class="form-control" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Min Year</label> 
								<input type="number" name="minYear" class="form-control" min=1 max=6 required>
							</div>
							
							<div class="mb-3">
								<label class="form-label">Max Students</label> 
								<input type="number" name="maxStudents" class="form-control" min=1  required>
							</div>
							
							<div class="mb-3">
							    <label class="form-label">Internship Period</label>
							
							    <div class="input-group">
							        <span class="input-group-text">
							            <i class="bi bi-calendar3"></i>
							        </span>
							
							        <input type="text"
							               class="form-control"
							               id="daterange"
							               placeholder="dd.mm.yyyy - dd.mm.yyyy"
							               readonly>
							    </div>
							
							    <input type="hidden" name="startDate" id="startDate">
							    <input type="hidden" name="endDate" id="endDate">
							</div>
							
							<div class="d-grid gap-2 mt-4">
								<button type="submit" class="btn btn-primary">Create</button>
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
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

	<script src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	
	<script src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	
	<script>
$(function(){

    $('#daterange').daterangepicker({
        minDate: moment(),
        locale: {
            format: 'DD.MM.YYYY'
        },
        autoUpdateInput: false
    });

    $('#daterange').on('apply.daterangepicker', function(ev, picker){

        $(this).val(
            picker.startDate.format('DD.MM.YYYY') +
            ' - ' +
            picker.endDate.format('DD.MM.YYYY')
        );

        $('#startDate').val(
            picker.startDate.format('YYYY-MM-DD')
        );

        $('#endDate').val(
            picker.endDate.format('YYYY-MM-DD')
        );

    });
    
    $('#internshipForm').on('submit', function(e){

        if($('#startDate').val() === "" || $('#endDate').val() === ""){
            alert("Please select internship period from calendar.");
            e.preventDefault();
        }

    });

});
</script>
</body>
</html>