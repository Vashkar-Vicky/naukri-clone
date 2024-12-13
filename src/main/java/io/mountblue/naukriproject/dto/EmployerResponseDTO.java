package io.mountblue.naukriproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class EmployerResponseDTO {
    private UUID id;
    private String companyName;
    private String industry;
    private String profilePicture;
    private List<JobDTO> jobPostings;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<JobDTO> getJobPostings() {
        return jobPostings;
    }

    public void setJobPostings(List<JobDTO> jobPostings) {
        this.jobPostings = jobPostings;
    }
}
