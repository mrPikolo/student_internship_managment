package company_app.api;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import company_app.dto.ApplicationDTO;

public class ApplicationApi {

	private static final Gson gson = new Gson();

    public static List<ApplicationDTO> getByCompany(Long companyId) throws Exception {

        String json = ApiClient.get("/applications/company/" + companyId);
        
        return gson.fromJson(json, new TypeToken<List<ApplicationDTO>>(){}.getType());
    }

    public static void accept(Long id) throws Exception {
        ApiClient.put("/applications/" + id + "/accept");
    }

    public static void reject(Long id) throws Exception {
        ApiClient.put("/applications/" + id + "/reject");
    }

}
