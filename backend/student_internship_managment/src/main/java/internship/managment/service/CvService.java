package internship.managment.service;

import org.springframework.stereotype.Service;

import internship.managment.dto.CvDTO;

@Service
public class CvService {

	public boolean create(CvDTO cv) {
		System.out.println("CvService: " + cv);
		return false;
	}

}
