package faculty_app.net;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import faculty_app.dto.AuthUser;
import faculty_app.dto.Company;
import faculty_app.dto.CreateCompanyRequest;
import faculty_app.dto.Internship;
import faculty_app.dto.Student;
import faculty_app.dto.UpdateStudentRequest;
import faculty_app.util.LocalDateAdapter;

public class JsonManager {
	
	private static Gson gson = new GsonBuilder()
	        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
	        .create();
	
	public static AuthUser authUserFromJson(int statusCode, String responseBody) {
		
		//return gson.fromJson(responseBody, new TypeToken<AuthUser>() {}.getType());
		
		Map responseMap = gson.fromJson(responseBody, Map.class);

		if (statusCode == 200) {
		    Map user = (Map) responseMap.get("user");
		    AuthUser aUser = new AuthUser();
		    
		    Double idDouble = (Double) user.get("id");
		    int id = idDouble.intValue();
		    aUser.setId(id);
		    aUser.setUsername(user.get("username").toString());
		    aUser.setRole(user.get("role").toString());
		    return aUser;
		}
		else
			return null;
	}
	
	public static Map mapLoginResponseFromJson(int statusCode, String responseBody) {
		
		//System.out.println("Response from API-: " + responseBody);
		
		Map responseMap = gson.fromJson(responseBody, Map.class);
		Map<String , Object> response = new HashMap<>();
		
		if (statusCode == 200) {
			
			Map user = (Map) responseMap.get("user");
			AuthUser aUser = new AuthUser();
			    
			Double idDouble = (Double) user.get("id");
			int id = idDouble.intValue();
			aUser.setId(id);
			aUser.setUsername(user.get("username").toString());
			aUser.setRole(user.get("role").toString());
			response.put("user", aUser);
		}
		else {
			String errorMesage = (String) responseMap.get("error");
			response.put("error", errorMesage);
		}
		return response;
	}
	
	public static List<Student> responseStudentList (String responseBody) {
		List<Student> response = gson.fromJson(responseBody, new TypeToken<List<Student>>() {}.getType());
		return response;
	}
	
	public static Student responseStudentEdit(String responseBody) {
		return gson.fromJson(responseBody, new TypeToken<Student>() {}.getType());
	}
	
	public static String updateStudentToJson(UpdateStudentRequest usr) {
		return gson.toJson(usr);
	}
	
	public static List<Company> responseCompanyList (String responseBody) {
		List<Company> response = gson.fromJson(responseBody, new TypeToken<List<Company>>() {}.getType());
		return response;
	}
	
	public static String createCompanyToJson(CreateCompanyRequest ccr) {
		return gson.toJson(ccr);
	}

	public static List<Internship> responseInternshipList(String responseBody) {
		List<Internship> response = gson.fromJson(responseBody, new TypeToken<List<Internship>>() {}.getType());
		return response;
	}

	public static Internship responseInternship(String responseBody) {
		Internship response = gson.fromJson(responseBody, new TypeToken<Internship>() {}.getType());
		return response;
	}
}
