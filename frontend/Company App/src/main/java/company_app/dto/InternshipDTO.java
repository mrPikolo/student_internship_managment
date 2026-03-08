package company_app.dto;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import company_app.api.ApiClient;

public class InternshipDTO {
	
	private Long id;
	private String companyName;	
	private String title;
	private String description;	
	private String technologies;
	private int minYear;
	private int maxStudents;
	private String startDate;
	private String endDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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
	@Override
	public String toString() {
		return "InternshipDTO [id=" + id + ", companyName=" + companyName + ", title=" + title + ", description="
				+ description + ", technologies=" + technologies + ", minYear=" + minYear + ", maxStudents="
				+ maxStudents + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
