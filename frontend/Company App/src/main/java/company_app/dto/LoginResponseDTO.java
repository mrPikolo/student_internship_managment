package company_app.dto;

public class LoginResponseDTO {
	
	private CompanyDTO user;
    private String error;

	public LoginResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public CompanyDTO getUser() {
		return user;
	}

	public void setUser(CompanyDTO user) {
		this.user = user;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
