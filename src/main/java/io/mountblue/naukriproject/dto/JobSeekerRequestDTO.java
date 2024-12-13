package io.mountblue.naukriproject.dto;

import io.mountblue.naukriclone.entity.enums.JobSeekerType;
import io.mountblue.naukriclone.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class JobSeekerRequestDTO {
    private String email;
    private String password;
    private String mobileNumber;
    private Role role;
    private String name;
    private String headline;
    private String profilePicture;
    private JobSeekerType jobSeekerType;
    private List<EducationDTO> educations;
    private List<ExperienceDTO> experiences;
    private List<SkillDTO> skills;
    private List<LanguageDTO> languages;
    private String resume;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public JobSeekerType getJobSeekerType() {
        return jobSeekerType;
    }

    public void setJobSeekerType(JobSeekerType jobSeekerType) {
        this.jobSeekerType = jobSeekerType;
    }

    public List<EducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationDTO> educations) {
        this.educations = educations;
    }

    public List<ExperienceDTO> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceDTO> experiences) {
        this.experiences = experiences;
    }

    public List<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }

    public List<LanguageDTO> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageDTO> languages) {
        this.languages = languages;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}