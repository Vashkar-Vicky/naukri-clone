package io.mountblue.naukriproject.service.impl;

import io.mountblue.naukriclone.entity.*;
import io.mountblue.naukriclone.entity.enums.Proficiency;
import io.mountblue.naukriclone.repository.*;
import io.mountblue.naukriclone.service.JobSeekerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

    private final JobSeekerRepository jobSeekerRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final SkillRepository skillRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public JobSeekerServiceImpl(JobSeekerRepository jobSeekerRepository,
                                EducationRepository educationRepository,
                                ExperienceRepository experienceRepository,
                                SkillRepository skillRepository,
                                LanguageRepository languageRepository) {
        this.jobSeekerRepository = jobSeekerRepository;
        this.educationRepository = educationRepository;
        this.experienceRepository = experienceRepository;
        this.skillRepository = skillRepository;
        this.languageRepository = languageRepository;
    }

    @Override
    public JobSeeker getJobSeekerById(UUID id) {
        return jobSeekerRepository.findById(id).orElseThrow(() -> new RuntimeException("JobSeeker not found"));
    }

    @Override
    public void updateJobSeeker(JobSeeker jobSeeker) {
        JobSeeker existingJobSeeker = jobSeekerRepository.findById(jobSeeker.getId())
                .orElseThrow(() -> new IllegalArgumentException("JobSeeker not found"));
        existingJobSeeker.setName(jobSeeker.getName());
        existingJobSeeker.setHeadline(jobSeeker.getHeadline());
        jobSeekerRepository.save(jobSeeker);
    }

    @Override
    public void deleteSkill(UUID skillId, UUID jobSeekerId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found"));
        if (jobSeeker.getSkills().contains(skill)) {
            jobSeeker.getSkills().remove(skill);
            jobSeekerRepository.save(jobSeeker);
        } else {
            throw new RuntimeException("Unauthorized");
        }
    }

    @Override
    public void deleteLanguage(UUID languageId, UUID jobSeekerId) {
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new RuntimeException("Language not found"));
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found"));
        if (jobSeeker.getLanguages().contains(language)) {
            jobSeeker.getLanguages().remove(language);
            jobSeekerRepository.save(jobSeeker);
        } else {
            throw new RuntimeException("Unauthorized");
        }
    }

    @Override
    public void updateExperience(UUID jobSeekerID, Experience experience) {

    }

    @Override
    public void deleteEducation(UUID educationId) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new EntityNotFoundException("Education not found"));
        JobSeeker jobSeeker = education.getJobSeeker();
        jobSeeker.getEducations().remove(education);
        jobSeekerRepository.save(jobSeeker);
        educationRepository.delete(education);
    }

    @Override
    public void deleteExperience(UUID experienceId) {
        Experience experience = experienceRepository.findById(experienceId)
                .orElseThrow(()-> new EntityNotFoundException("Experience not found "));
        JobSeeker jobSeeker = experience.getJobSeeker();
        jobSeeker.getExperiences().remove(experience);
        jobSeekerRepository.save(jobSeeker);
        experienceRepository.delete(experience);
    }

    @Override
    public JobSeeker getJobSeekerByExperienceId(UUID id) {
        return jobSeekerRepository.findByExperiencesId(id);
    }



    @Override
    public Language getJobSeekerByEducationId(UUID id) {
        return languageRepository.findLanguagesById(id);
    }


    @Override
    public Optional<JobSeeker> findById(UUID id) {
        return jobSeekerRepository.findById(id);
    }

    @Override
    public void save(JobSeeker jobSeeker) {
        jobSeekerRepository.save(jobSeeker);

    }

    @Override
    public List<Skill> getSkills(UUID jobSeekerId) {
        return jobSeekerRepository.findById(jobSeekerId).orElseThrow().getSkills();
    }

    @Override
    public List<Language> getLanguages(UUID jobSeekerId) {
        return jobSeekerRepository.findById(jobSeekerId).orElseThrow().getLanguages();
    }

    @Override
    public void addSkill(UUID id, String name, Proficiency proficiency) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id).orElseThrow();
        Skill skill = new Skill();
        skill.setName(name);
        skill.setProficiency(proficiency);
        if (!skill.getJobSeekers().contains(jobSeeker)) { // Avoid duplicates
            skill.getJobSeekers().add(jobSeeker);
        }

        if (!jobSeeker.getSkills().contains(skill)) { // Avoid duplicates
            jobSeeker.getSkills().add(skill);
        }
        skillRepository.save(skill);
    }

    @Override
    public void addLanguage(UUID jobSeekerId, String name, Proficiency proficiency) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId).orElseThrow();
        Language language = new Language();
        language.setName(name);
        language.setProficiency(proficiency);
        if(!language.getJobSeekers().contains(jobSeeker)) {
            language.getJobSeekers().add(jobSeeker);
        }
        if(!jobSeeker.getLanguages().contains(language)) {
            jobSeeker.getLanguages().add(language);
        }
        languageRepository.save(language);

    }

    @Override
    public void addEducation(UUID jobSeekerId, Education education) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new IllegalArgumentException("Education not found"));

        education.setJobSeeker(jobSeeker);
        jobSeeker.getEducations().add(education);
        jobSeekerRepository.save(jobSeeker);
    }

    @Override
    public void addExperience(UUID jobSeekerId, Experience experience) {
        JobSeeker jobSeeker = findById(jobSeekerId).orElseThrow();
        experience.setJobSeeker(jobSeeker);
        jobSeeker.getExperiences().add(experience);
        jobSeekerRepository.save(jobSeeker);
    }

    @Override
    public void updateProfile(UUID jobSeekerId, String name, String headline, MultipartFile profilePicture) {
        JobSeeker jobSeeker = findById(jobSeekerId).orElseThrow();

        jobSeeker.setName(name);
        jobSeeker.setHeadline(headline);

        if (profilePicture != null && !profilePicture.isEmpty()) {
            String profilePicUrl = uploadProfilePicture(profilePicture);
            jobSeeker.setProfilePicture(profilePicUrl);
        }

        jobSeekerRepository.save(jobSeeker);
    }

    private String uploadProfilePicture(MultipartFile file) {
        // Add your logic to store the file and return its URL or path
        return "uploaded/file/path/" + file.getOriginalFilename();
    }

}
