package company_app.dto;

import java.time.LocalDate;

public class UpdateInternshipDTO {

	private String technologies;
	private int minYear;
	private int maxStudents;
	private String startDate;
	private String endDate;
	public String getTechnologies() {
		return technologies;
	}
	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}
	public int getMinYear() {
		return minYear;
	}
	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}
	public int getMaxStudents() {
		return maxStudents;
	}
	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	

}
