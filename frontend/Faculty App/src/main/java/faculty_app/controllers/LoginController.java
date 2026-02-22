package faculty_app.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import faculty_app.dto.AuthUser;
import faculty_app.dto.LoginRequest;
import faculty_app.net.HttpClientProvider;
import faculty_app.net.JsonManager;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String action = request.getParameter("action");

		request.setCharacterEncoding("UTF-8");
		String address = "";		
		
		HttpSession session = request.getSession();
		session.setAttribute("notification", "");
		
		if (action == null) {
			address = "login.jsp";
		}

		if ("login".equals(action)) {

			// request to api
			
			String loginUrl = "http://localhost:8085/api/auth/login";
			try {
				HttpClient client = HttpClientProvider.getClient();

				/*
				 * String jsonBody = String.format( "{\"username\":\"%s\",\"password\":\"%s\"}",
				 * username, password );
				 */

				String jsonBody = new Gson().toJson(new LoginRequest(username, password));

				HttpRequest apiRequest = HttpRequest
						.newBuilder()
						.uri(URI.create(loginUrl))
						.header("Content-Type", "application/json")
						.POST(HttpRequest.BodyPublishers.ofString(jsonBody))
						.build();

				HttpResponse<String> apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());

				int statusCode = apiResponse.statusCode();
				String apiResult = apiResponse.body();

				Map mapResponse = JsonManager.mapLoginResponseFromJson(statusCode, apiResult);
				// System.out.println("Response map:");
				// mapResponse.forEach( (k,v) -> System.out.println(k +"->" + v) );

				if (mapResponse.get("user") != null) {
					AuthUser authUser = (AuthUser) mapResponse.get("user");
					session.setAttribute("user", authUser);
					response.sendRedirect(request.getContextPath() + "/dashboard");
					return;
				} else if (mapResponse.get("error") != null) {
					session.setAttribute("notification", mapResponse.get("error"));
					address = "login.jsp";
				} else {
					address = "login.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
