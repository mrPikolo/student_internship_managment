package faculty_app.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import faculty_app.dto.LoginRequest;
import faculty_app.dto.Student;
import faculty_app.dto.UpdateStudentRequest;
import faculty_app.net.HttpClientProvider;
import faculty_app.net.JsonManager;

/**
 * Servlet implementation class StudentsController
 */
@WebServlet("/students")
@MultipartConfig
public class StudentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String action = request.getParameter("action");

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		 if (action == null) {
		        listStudents(request, response);
		        return;
		    }

		    switch (action) {
		        case "edit":
		            showEditForm(request, response);
		            break;
		        case "add":
		            showAddForm(request, response);
		            break;
		        default:
		            listStudents(request, response);
		    }

		
			
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");

	    switch (action) {
	        case "create":
	            createStudent(request, response);
	            break;

	        case "update":
	            updateStudent(request, response);
	            break;
	            
	        case "delete":
	            deleteStudent(request, response);
	            break;
	        case "upload-csv":
	            uploadCSV(request, response);
	            break;
	    }
	}
	
	private void uploadCSV(HttpServletRequest request, HttpServletResponse response) {
		
		
		Part filePart;
		try {
			filePart = request.getPart("file");
			if (filePart == null || filePart.getSize() == 0) {
				
				response.sendRedirect("students");
				return;
			}
			

		    String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();

		    HttpClient client = HttpClient.newHttpClient();

		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    PrintWriter writer = new PrintWriter(new OutputStreamWriter(baos, "UTF-8"), true);

		    // ---- file part
		 // --- START boundary
		    writer.append("--").append(boundary).append("\r\n");
		    writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
		            .append(filePart.getSubmittedFileName()).append("\"\r\n");
		    writer.append("Content-Type: text/csv\r\n");
		    writer.append("\r\n");
		    writer.flush();

		    // --- file content
		    InputStream inputStream = filePart.getInputStream();
		    inputStream.transferTo(baos);
		    baos.flush();

		    writer.append("\r\n");
		    writer.flush();
		    
		    // --- END boundary
		    writer.append("--").append(boundary).append("--").append("\r\n");
		    writer.close();
		    
		    HttpRequest apiRequest = HttpRequest.newBuilder()
		            .uri(URI.create("http://localhost:8085/api/students/upload"))
		            .header("Content-Type", "multipart/form-data; boundary=" + boundary)
		            .POST(HttpRequest.BodyPublishers.ofByteArray(baos.toByteArray()))
		            .build();
		    HttpResponse<String> apiResponse =
	                client.send(apiRequest, HttpResponse.BodyHandlers.ofString());

	        //System.out.println("API response: " + apiResponse.body());
	        
			/*
			 * request.setAttribute("studentsList", listStudentsFromApi());
			 * 
			 * request.getRequestDispatcher("/WEB-INF/pages/studentList.jsp")
			 * .forward(request, response);
			 */
		    listStudents(request, response);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		System.out.println("DELETE: id=" + id);
		HttpClient client = HttpClientProvider.getClient();

		HttpRequest apiRequest = HttpRequest
				.newBuilder()
				.uri(URI.create("http://localhost:8085/api/students/" + id ))
				.DELETE()
				.build();
		
		
        try {
        	HttpResponse<String> apiResponse = client.send(apiRequest,
	                        HttpResponse.BodyHandlers.ofString());
        	
			/*
			 * request.setAttribute("studentsList", listStudentsFromApi());
			 * 
			 * request.getRequestDispatcher("/WEB-INF/pages/studentList.jsp")
			 * .forward(request, response);
			 */
        	listStudents(request, response);
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		
		HttpClient client = HttpClientProvider.getClient();
		String jsonBody = JsonManager.updateStudentToJson(
				new UpdateStudentRequest(
						request.getParameter("firstName"),
						request.getParameter("lastName"),
						request.getParameter("email"),
						LocalDate.parse( request.getParameter("birthDate")),
						request.getParameter("indexNumber"),
						request.getParameter("username")
						));

		HttpRequest apiRequest = HttpRequest
				.newBuilder()
				.uri(URI.create("http://localhost:8085/api/students/" + id))
				.header("Content-Type", "application/json")
				.PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
				.build();
	    try {
	        HttpResponse<String> apiResponse =
	                client.send(apiRequest,
	                        HttpResponse.BodyHandlers.ofString());
	        
			/*
			 * request.setAttribute("studentsList", listStudentsFromApi());
			 * request.getRequestDispatcher("/WEB-INF/pages/studentList.jsp")
			 * .forward(request, response);
			 */
	        listStudents(request, response);

	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	    
		
	}

	private void createStudent(HttpServletRequest request, HttpServletResponse response) {
		
		HttpClient client = HttpClientProvider.getClient();
		String jsonBody = JsonManager.updateStudentToJson(
				new UpdateStudentRequest(
						request.getParameter("firstName"),
						request.getParameter("lastName"),
						request.getParameter("email"),
						LocalDate.parse( request.getParameter("birthDate")),
						request.getParameter("indexNumber"),
						request.getParameter("username")
						));
		HttpRequest apiRequest = HttpRequest
				.newBuilder()
				.uri(URI.create("http://localhost:8085/api/students"))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(jsonBody))
				.build();
	    try {
	        
	        HttpResponse<String> apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());

			int statusCode = apiResponse.statusCode();
			String apiResult = apiResponse.body();
			
	        System.out.println("CREATE apiResult: " + apiResult);
	        Student student = JsonManager.responseStudentEdit(apiResult);
	        
			/*
			 * request.setAttribute("studentsList", listStudentsFromApi());
			 * request.getRequestDispatcher("/WEB-INF/pages/studentList.jsp")
			 * .forward(request, response);
			 */
	        listStudents(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	private void showAddForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/WEB-INF/pages/studentCreate.jsp")
			.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	private List<Student> listStudentsFromApi() {
		String url = "http://localhost:8085/api/students";
		List<Student> list = null;
		try {
			HttpClient client = HttpClientProvider.getClient();
			HttpRequest apiRequest = HttpRequest.
					newBuilder()
					.uri(URI.create(url))
					.GET().build();

			HttpResponse<String> apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());

			int statusCode = apiResponse.statusCode();
			String apiResult = apiResponse.body();
			
			list = JsonManager.responseStudentList(apiResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void listStudents(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		
		request.setAttribute("studentsList", listStudentsFromApi());	

		
		  RequestDispatcher dispatcher =
		  request.getRequestDispatcher("/WEB-INF/pages/studentList.jsp");
		  dispatcher.forward(request, response);
		 
		
	}
	
	
	private void showEditForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		HttpClient client = HttpClientProvider.getClient();
	    HttpRequest apiRequest = HttpRequest.newBuilder()
	            .uri(URI.create("http://localhost:8085/api/students/" + id))
	            .GET()
	            .build();
	    try {
	        HttpResponse<String> apiResponse =
	                client.send(apiRequest,
	                        HttpResponse.BodyHandlers.ofString());
	        String apiResult = apiResponse.body();
	        
	        Student student = JsonManager.responseStudentEdit(apiResult);
	        request.setAttribute("student", student);
	        request.getRequestDispatcher("/WEB-INF/pages/studentUpdate.jsp")
	               .forward(request, response);

	    } catch (InterruptedException e) {
	        throw new RuntimeException(e);
	    }		
	}
}
