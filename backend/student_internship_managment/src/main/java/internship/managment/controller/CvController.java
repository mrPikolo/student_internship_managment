package internship.managment.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import internship.managment.component.PhotoManager;
import internship.managment.dto.CvDTO;
import internship.managment.service.CvService;

@RestController
@RequestMapping("/api/cv")
@CrossOrigin(origins = "http://localhost:4200")
public class CvController {
	
	@Autowired
	PhotoManager photoManager;
	
	@Autowired
	CvService cvService;
	
	@PostMapping("/photo")
	public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {

	    String uploadDir = photoManager.getPhotoDir();

	    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

	    Path path = Paths.get(uploadDir + fileName);

	    Files.createDirectories(path.getParent());

	    Files.write(path, file.getBytes());

	    return ResponseEntity.ok(fileName);
	}
	
	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<?> saveCv( @RequestPart("cv") CvDTO cv,
	        @RequestPart(value = "photo", required = false) MultipartFile photo) {


	    if(photo != null){
	        System.out.println(photo.getOriginalFilename());
	        
	        String originalName = photo.getOriginalFilename();

	        String extension = originalName.substring(originalName.lastIndexOf("."));

	        String fileName = UUID.randomUUID().toString() + extension;

	        Path path = Paths.get(photoManager.getPhotoDir() + fileName);

	        boolean error = false;
	        try {
				Files.write(path, photo.getBytes());
			} catch (IOException e) {
				error = true;
				e.printStackTrace();
			}
	        
	        if( !error) {
	        	cv.setImagePath(fileName);
	        	boolean cretedCv = cvService.create(cv);
	        }
	    }

	    return ResponseEntity.ok("CV saved");
	}

}
