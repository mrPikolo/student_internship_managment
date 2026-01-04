package internship.managment.service;

import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.managment.component.CompanyMapper;
import internship.managment.dto.CompanyDTO;
import internship.managment.dto.CompanyResponseDTO;
import internship.managment.model.Company;
import internship.managment.model.Role;
import internship.managment.model.User;
import internship.managment.repository.CompanyRepository;
import internship.managment.repository.UserRepository;

@Service
public class CompanyService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompanyMapper mapper;

	public CompanyDTO create(CompanyDTO companyDTO) {
		
		if(userRepository.existsByUsername(companyDTO.getUsername()))
			return null;
		else {		
			CompanyDTO dto = null;
			String defaultPassword = "company";
			
			User u = new User();
			u.setUsername(companyDTO.getUsername());
			u.setPassword(defaultPassword);
			u.setRole(Role.COMPANY);
			u.setActive(true);
			userRepository.save(u);
			
			Company c = new Company();
			c.setName(companyDTO.getName());
			c.setDescription(companyDTO.getDescription());
			c.setActive(true);
			c.setAccount(u);
			companyRepository.save(c);
			return companyDTO;
		}
	}

	public List<CompanyResponseDTO> getAll() {
		
		List<Company> all = companyRepository.findAll();
		
		List<CompanyResponseDTO> allDTO = new ArrayList<>();
		for(Company c : all) {
			CompanyResponseDTO cpDTO = mapper.toDTO(c);
			allDTO.add(cpDTO);			
		}
		return allDTO;
	}

	public @Nullable CompanyResponseDTO activateCompany(Long id) {
		
		Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
		
        company.setActive(true);
        company.getAccount().setActive(true);        
        Company activated = companyRepository.save(company);
        
        return mapper.toDTO(activated);
	}

	public @Nullable CompanyResponseDTO deactivateCompany(Long id) {
		
		Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
		
        company.setActive(false);
        company.getAccount().setActive(false);        
        Company deactivated = companyRepository.save(company);
        
        return mapper.toDTO(deactivated);
	}

}
