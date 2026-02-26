package faculty_app.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import faculty_app.dto.Company;
import faculty_app.dto.CreateCompanyRequest;
import faculty_app.dto.Student;
import faculty_app.dto.UpdateStudentRequest;
import faculty_app.net.HttpClientProvider;
import faculty_app.net.JsonManager;

/**
 * Servlet implementation class CompanysController
 */
@WebServlet("/companies")
public class CompaniesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompaniesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String action = request.getParameter("action");

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		 if (action == null) {
		        showComapnys(request, response);
		        return;
		    }

		    switch (action) {
		        case "add":
		            showAddForm(request, response);
		            break;
		        default:
		        	showComapnys(request, response);
		    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

	    switch (action) {
	        case "create":
	            createCompany(request, response);
	            break;
	        case "activate":
	        	activateCompany(request, response);
	            break;
	        case "deactivate":
	        	deactivateCompany(request, response);
	            break;
	    }
	}
	
	private void deactivateCompany(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		
		String id = request.getParameter("companyId");
		// data from api
		String url = "http://localhost:8085/api/companies/" + id + "/deactivate" ;
		try {
			HttpClient client = HttpClientProvider.getClient();
			HttpRequest apiRequest = HttpRequest.
					newBuilder()
					.uri(URI.create(url))
					.POST(HttpRequest.BodyPublishers.ofString(""))
					.build();

			HttpResponse<String> apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());
			response.sendRedirect("companies");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void activateCompany(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		String id = request.getParameter("companyId");
		// data from api
		String url = "http://localhost:8085/api/companies/" + id + "/activate" ;
		try {
			HttpClient client = HttpClientProvider.getClient();
			HttpRequest apiRequest = HttpRequest.
					newBuilder()
					.uri(URI.create(url))
					.POST(HttpRequest.BodyPublishers.ofString(""))
					.build();

			HttpResponse<String> apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());
			response.sendRedirect("companies");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void createCompany(HttpServletRequest request, HttpServletResponse response) {
		
		HttpClient client = HttpClientProvider.getClient();
		
		String jsonBody = JsonManager.createCompanyToJson(
				new CreateCompanyRequest(
						request.getParameter("name"),
						request.getParameter("description"),
						request.getParameter("username"),
						request.getParameter("password")
						));
		HttpRequest apiRequest = HttpRequest
				.newBuilder()
				.uri(URI.create("http://localhost:8085/api/companies"))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(jsonBody))
				.build();
	    
	        
	        try {
				HttpResponse<String> apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());
				
				response.sendRedirect("companies");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
		
	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response) {
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/pages/companyCreate.jsp");
				try {
					dispatcher.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

	private void showComapnys(HttpServletRequest request, HttpServletResponse response) {
		
		// data from api
		String url = "http://localhost:8085/api/companies";
		List<Company> list = null;
		try {
			HttpClient client = HttpClientProvider.getClient();
			HttpRequest apiRequest = HttpRequest.
					newBuilder()
					.uri(URI.create(url))
					.GET().build();

			HttpResponse<String> apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());

			int statusCode = apiResponse.statusCode();
			String apiResult = apiResponse.body();
			list = JsonManager.responseCompanyList(apiResult);
			
			request.setAttribute("companiesList", list);	

			
			RequestDispatcher dispatcher =
			request.getRequestDispatcher("/WEB-INF/pages/companiesEdit.jsp");
			dispatcher.forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
