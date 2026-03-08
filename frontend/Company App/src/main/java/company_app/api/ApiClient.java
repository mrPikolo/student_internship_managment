package company_app.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8085/api";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static String get(String path) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .GET()
                .build();

        return client.send(request,
                HttpResponse.BodyHandlers.ofString()).body();
    }

    public static String post(String path, String json) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        return client.send(request,
                HttpResponse.BodyHandlers.ofString()).body();
    }

    public static void put(String path) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        client.send(request,
                HttpResponse.BodyHandlers.ofString());
    }

	public static String delete(String path) throws Exception{
		HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(BASE_URL + path))
	            .header("Content-Type", "application/json")
	            .DELETE()
	            .build();

	    HttpResponse<String> response = client.send(
	            request,
	            HttpResponse.BodyHandlers.ofString()
	    );
	    if (response.statusCode() >= 200 && response.statusCode() < 300) {
	        return response.body();
	    } else {
	        throw new RuntimeException(
	                "DELETE failed. Status: " + response.statusCode() +
	                ", Body: " + response.body()
	        );
	    }
		
	}
}
