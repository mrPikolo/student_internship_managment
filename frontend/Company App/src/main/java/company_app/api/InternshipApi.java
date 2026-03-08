package company_app.api;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import company_app.dto.InternshipDTO;
import company_app.dto.UpdateInternshipDTO;

public class InternshipApi {

	private static final Gson gson = new Gson();

    public static List<InternshipDTO> getByCompany(Long userId) throws Exception {

        String json = ApiClient.get("/internships/company/" + userId);
        System.out.println("InternshipApi getByCompany(): " + json);
        return gson.fromJson(json, new TypeToken<List<InternshipDTO>>(){}.getType());
    }
    
    public static List<InternshipDTO> getAll() throws Exception {
    	
    	String json = ApiClient.get("/internships" );
    	System.out.println("InternshipApi getAll(): " + json);
        return gson.fromJson(json, new TypeToken<List<InternshipDTO>>(){}.getType()); 
    }
    
    public static InternshipDTO getById(Long id) throws Exception {

        String json = ApiClient.get("/internships/" + id);

        return gson.fromJson(json, InternshipDTO.class);
    }

    public static void create(InternshipDTO dto) throws Exception {
        ApiClient.post("/internships", gson.toJson(dto));
    }
    
    public static void update(Long companyId, UpdateInternshipDTO uDTO) throws Exception {
        ApiClient.post("/internships/"+ companyId, gson.toJson(uDTO));
    }
    
    public static void delete(Long id) throws Exception {
    	System.out.println("InternshipApi delete: id=" + id);
        ApiClient.delete("/internships/" + id);
    }

}
