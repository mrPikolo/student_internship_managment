package internship.managment.component;

import org.springframework.stereotype.Component;

import internship.managment.dto.CompanyResponseDTO;
import internship.managment.model.Company;

@Component
public class CompanyMapper {
	
	public CompanyResponseDTO toDTO(Company c) {
		CompanyResponseDTO cpDTO = new CompanyResponseDTO();
		cpDTO.setId(c.getId());
		cpDTO.setName(c.getName());
		cpDTO.setDescription(c.getDescription());
		cpDTO.setAccountStatus(c.getAccount().isActive());
		return cpDTO;
	}

}
