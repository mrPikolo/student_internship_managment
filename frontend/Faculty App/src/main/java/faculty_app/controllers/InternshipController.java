package faculty_app.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import faculty_app.dto.Internship;
import faculty_app.dto.Student;
import faculty_app.net.HttpClientProvider;
import faculty_app.net.JsonManager;

/**
 * Servlet implementation class InternshipController
 */
@WebServlet("/internships")
public class InternshipController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InternshipController() {
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
		        listInternships(request, response);
		        return;
		    }

		    switch (action) {
		        case "details":
		            showInternship(request, response);
		            break;
		        default:
		        	listInternships(request, response);
		    }
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void listInternships(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String searchBy = request.getParameter("searchBy");
	    String searchValue = request.getParameter("searchValue");

	    List<Internship> internships = internshipsFromApi();
	    List<Internship> filtered = new ArrayList<>();

	    if(searchValue == null || searchValue.trim().isEmpty()){
	        filtered = internships;
	    } else {

	        for(Internship i : internships){

	            switch(searchBy){

	            case "company":
	                if(i.getCompanyName().toLowerCase()
	                        .contains(searchValue.toLowerCase())){
	                    filtered.add(i);
	                }
	                break;

	            case "technologies":
	                if(i.getTechnologies().toLowerCase()
	                        .contains(searchValue.toLowerCase())){
	                    filtered.add(i);
	                }
	                break;

	            case "startDate":
	                if(i.getStartDate().equals(searchValue)){
	                    filtered.add(i);
	                }
	                break;

	            default:
	                filtered.add(i);
	            }
	        }
	    }

	    request.setAttribute("internshipList", filtered);

	    RequestDispatcher dispatcher =
	            request.getRequestDispatcher("/WEB-INF/pages/internships.jsp");

	    dispatcher.forward(request, response);
	}
		
		

	private List<Internship> internshipsFromApi() {
		String url = "http://localhost:8085/api/internships";
		List<Internship> list = null;
		try {
			HttpClient client = HttpClientProvider.getClient();
			HttpRequest apiRequest = HttpRequest.
					newBuilder()
					.uri(URI.create(url))
					.GET().build();

			HttpResponse<String> apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());

			int statusCode = apiResponse.statusCode();
			String apiResult = apiResponse.body();
			
			list = JsonManager.responseInternshipList(apiResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private Internship getById(Long id) {
		String url = "http://localhost:8085/api/internships/" + id;
		Internship internship = null;
		try {
			HttpClient client = HttpClientProvider.getClient();
			HttpRequest apiRequest = HttpRequest.
					newBuilder()
					.uri(URI.create(url))
					.GET().build();

			HttpResponse<String> apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());

			int statusCode = apiResponse.statusCode();
			String apiResult = apiResponse.body();
			
			internship = JsonManager.responseInternship(apiResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return internship;
	}

	private void showInternship(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("id");
		if (idParam == null) {
			response.sendRedirect("internships");
			return;
		}

		try {
			Long id = Long.parseLong(idParam);
			Internship internship = getById(id);

			request.setAttribute("internship", internship);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/internship-details.jsp");
			rd.forward(request, response);

		} catch (NumberFormatException e) {
			response.sendRedirect("internships");
		} catch (Exception e) {
			throw new ServletException("Error fetching internship details", e);
		}

	}

}
