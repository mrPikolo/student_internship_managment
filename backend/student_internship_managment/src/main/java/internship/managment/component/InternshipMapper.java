package internship.managment.component;

import org.springframework.stereotype.Component;

import internship.managment.dto.InternshipDTO;
import internship.managment.model.Internship;

@Component
public class InternshipMapper {

	public InternshipDTO toDTO(Internship i) {
		InternshipDTO iDTO = new InternshipDTO();
		iDTO.setId(i.getId());
		iDTO.setCompanyName(i.getCompany().getName());
		iDTO.setTitle(i.getTitle());
		iDTO.setDescription(i.getDescription());
		iDTO.setTechnologies(i.getTechnologies());
		iDTO.setMinYear(i.getMinYear());
		iDTO.setMaxStudents(i.getMaxStudents());
		iDTO.setStartDate(i.getStartDate());
		iDTO.setEndDate(i.getEndDate());
		
		return iDTO;
	}
}
