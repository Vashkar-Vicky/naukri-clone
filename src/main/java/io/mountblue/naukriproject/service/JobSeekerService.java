package io.mountblue.naukriproject.service;

import io.mountblue.naukriclone.entity.*;
import io.mountblue.naukriclone.entity.enums.Proficiency;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobSeekerService {
    JobSeeker getJobSeekerById(UUID id);
    void updateJobSeeker(JobSeeker jobSeeker);
    void deleteExperience(UUID experienceId);
    void deleteSkill(UUID skillId, UUID jobSeekerId);
    void deleteLanguage(UUID languageId, UUID jobSeekerId);
    void updateExperience(UUID jobSeekerID, Experience experience);
    JobSeeker getJobSeekerByExperienceId(UUID jobSeekerId);
    void deleteEducation(UUID jobSeekerId);


    Language getJobSeekerByEducationId(UUID id);


    Optional<JobSeeker> findById(UUID id);

    void save(JobSeeker jobSeeker);

    List<Skill> getSkills(UUID id);

    List<Language> getLanguages(UUID id);

    void addSkill(UUID id, String name, Proficiency proficiency);

    void addLanguage(UUID jobSeekerId, String name, Proficiency proficiency);

    void addEducation(UUID jobSeekerId, Education education);

    void addExperience(UUID jobSeekerId, Experience experience);

    void updateProfile(UUID jobSeekerId, String name, String headline, MultipartFile profilePicture);


}
