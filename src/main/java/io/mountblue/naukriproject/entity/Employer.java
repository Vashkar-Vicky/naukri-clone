package io.mountblue.naukriproject.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;



@Entity
public class Employer {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Job> getJobPostings() {
        return jobPostings;
    }

    public void setJobPostings(List<Job> jobPostings) {
        this.jobPostings = jobPostings;
    }

    @Id
    private UUID id;

    private String companyName;
    private String industry;
    private String profilePicture;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "employer")
    private List<Job> jobPostings;

}