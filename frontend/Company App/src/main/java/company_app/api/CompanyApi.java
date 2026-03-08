package company_app.api;

import com.google.gson.Gson;

import company_app.dto.CompanyDTO;
import company_app.dto.InternshipDTO;
import company_app.dto.LoginResponseDTO;

public class CompanyApi {

	private static final Gson gson = new Gson();

    public static LoginResponseDTO login(String username, String password) throws Exception {

        String json = """
            {
              "username": "%s",
              "password": "%s"
            }
        """.formatted(username, password);

        String response = ApiClient.post("/companies/login", json);
        System.out.println("CompanyApi api response:" + response);
        
        return gson.fromJson(response, LoginResponseDTO.class);
    }
    
    public static CompanyDTO getByUser(Long userId) throws Exception {

        String json = ApiClient.get("/companies/" + userId);
        return gson.fromJson(json, CompanyDTO.class);
    }
    
    public static String getCompanyName(Long userId) throws Exception {

        String response = ApiClient.get("/companies/name/" + userId);
        return response;
    }

}
