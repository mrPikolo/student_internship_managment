package internship.managment.component;

import org.springframework.stereotype.Component;

@Component
public class PhotoManager {
	
	private String uploadDir = "storage/cv-images/";
	
	public String getPhotoDir() {
		return uploadDir;
	}

}
