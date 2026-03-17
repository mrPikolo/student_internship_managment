package internship.managment.model.cv;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class CvLanguage {

	private String name;

    @Enumerated(EnumType.STRING)
    private LanguageLevel level;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LanguageLevel getLevel() {
		return level;
	}

	public void setLevel(LanguageLevel level) {
		this.level = level;
	}
    
    
}
