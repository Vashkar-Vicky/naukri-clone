package io.mountblue.naukriproject.dto;

import io.mountblue.naukriclone.entity.enums.JobSeekerType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class JobSeekerResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String headline;
    private String profilePicture;
    private JobSeekerType jobSeekerType;
    private List<EducationDTO> educations;
    private List<ExperienceDTO> experiences;
    private List<SkillDTO> skills;
    private List<LanguageDTO> languages;
    private List<UUID> applications;
    private String resume;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public List<UUID> getApplications() {
        return applications;
    }

    public void setApplications(List<UUID> applications) {
        this.applications = applications;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}