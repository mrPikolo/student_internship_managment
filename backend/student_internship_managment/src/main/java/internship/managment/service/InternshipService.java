package internship.managment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.managment.component.InternshipMapper;
import internship.managment.dto.InternshipDTO;
import internship.managment.dto.UpdateInternshipDTO;
import internship.managment.model.Company;
import internship.managment.model.Internship;
import internship.managment.repository.CompanyRepository;
import internship.managment.repository.InternshipRepository;

@Service
public class InternshipService {
	
	@Autowired
	private InternshipRepository internshipRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private InternshipMapper internshipMapper;

	public List<InternshipDTO> getAll() {
		List<Internship> listI =  internshipRepository.findAllActive();
		List<InternshipDTO> listDTO = new ArrayList<>();
		
		for(Internship i : listI) {
			InternshipDTO dto = internshipMapper.toDTO(i);
			listDTO.add(dto);
		}
		
		return listDTO;
	}

	public InternshipDTO create(InternshipDTO internshipDTO) {
		
		Optional<Company> optC = companyRepository.findByName(internshipDTO.getCompanyName());
		
		if(optC.isPresent()) {
			Internship i = new Internship();
			i.setCompany(optC.get());
			i.setTitle(internshipDTO.getTitle());
			i.setDescription(internshipDTO.getDescription());
			i.setTechnologies(internshipDTO.getTechnologies());
			i.setMinYear(internshipDTO.getMinYear());
			i.setMaxStudents(internshipDTO.getMaxStudents());
			i.setStartDate(internshipDTO.getStartDate());
			i.setEndDate(internshipDTO.getEndDate());
			i.setActive(true);
			internshipRepository.save(i);
			
			return internshipMapper.toDTO(i);
		}
		else {
			return null;
		}		
	}

	public InternshipDTO update(Long id, UpdateInternshipDTO uiDTO) {
		
		InternshipDTO iDTO = null;
		Optional <Internship> optI = internshipRepository.findById(id);
		
		if(optI.isPresent()) {
			Internship i = optI.get();
			i.setTechnologies(uiDTO.getTechnologies());
			i.setMinYear(uiDTO.getMinYear());
			i.setMaxStudents(uiDTO.getMaxStudents());
			i.setStartDate(uiDTO.getStartDate());
			i.setEndDate(uiDTO.getEndDate());
			internshipRepository.save(i);
			
			iDTO = internshipMapper.toDTO(i);
		}
		return iDTO;
	}

	public void softDeleteStudent(Long id) {
		Optional<Internship> optI = internshipRepository.findById(id);
		if(optI.isPresent()) {
			Internship i = optI.get();
			i.setActive(false);
			internshipRepository.save(i);
		}
		
	}

}
