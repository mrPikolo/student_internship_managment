package internship.managment.model.cv;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CvWorkExperience {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "cv_id")
    private CV cv;

	private String company;
	private String position;
    private LocalDate startDate;
    private LocalDate endDate;
    private String city;
    private String country;
}
