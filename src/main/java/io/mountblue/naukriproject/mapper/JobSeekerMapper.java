package io.mountblue.naukriproject.mapper;

import io.mountblue.naukriclone.dto.JobSeekerRequestDTO;
import io.mountblue.naukriclone.dto.JobSeekerResponseDTO;
import io.mountblue.naukriclone.entity.*;
import io.mountblue.naukriclone.service.LanguageService;
import io.mountblue.naukriclone.service.SkillService;

import java.util.List;
import java.util.stream.Collectors;

public class JobSeekerMapper {
    private static String blankProfilePicture = "https://naukri-clone.s3.eu-north-1.amazonaws.com/profile-pictures/blank-profile-picture.webp";
    public static JobSeeker toEntity(JobSeekerRequestDTO jobSeekerRequestDTO,
                                     SkillService skillService,
                                     LanguageService languageService) {
        if (jobSeekerRequestDTO == null) {
            return null;
        }

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setName(jobSeekerRequestDTO.getName());
        jobSeeker.setHeadline(jobSeekerRequestDTO.getHeadline());
        jobSeeker.setProfilePicture(jobSeekerRequestDTO.getProfilePicture());
        jobSeeker.setResume(jobSeekerRequestDTO.getResume());
        jobSeeker.setJobSeekerType(jobSeekerRequestDTO.getJobSeekerType());

        // Safely map skills
        if (jobSeekerRequestDTO.getSkills() != null) {
            jobSeeker.setSkills(jobSeekerRequestDTO.getSkills().stream()
                    .map(skillDTO -> skillService.findByNameAndProficiency(skillDTO.getName(), skillDTO.getProficiency())
                            .orElseGet(() -> (Skill) skillService.createSkill(skillDTO.getName(), skillDTO.getProficiency())))
                    .collect(Collectors.toList()));
        }

        // Safely map languages
        if (jobSeekerRequestDTO.getLanguages() != null) {
            jobSeeker.setLanguages(jobSeekerRequestDTO.getLanguages().stream()
                    .map(languageDTO -> languageService.findByNameAndProficiency(languageDTO.getName(), languageDTO.getProficiency())
                            .orElseGet(() -> (Language) languageService.createLanguage(languageDTO.getName(), languageDTO.getProficiency())))
                    .collect(Collectors.toList()));
        }

        if (jobSeekerRequestDTO.getEducations() != null) {
            List<Education> educations = EducationMapper.toEntityList(jobSeekerRequestDTO.getEducations());
            educations.forEach(education -> education.setJobSeeker(jobSeeker));
            jobSeeker.setEducations(educations);
        }

        // Safely map experiences and link JobSeeker
        if (jobSeekerRequestDTO.getExperiences() != null) {
            List<Experience> experiences = ExperienceMapper.toEntityList(jobSeekerRequestDTO.getExperiences());
            experiences.forEach(experience -> experience.setJobSeeker(jobSeeker));
            jobSeeker.setExperiences(experiences);
        }
        return jobSeeker;
    }


    public static JobSeekerResponseDTO toDTO(JobSeeker jobSeeker) {
        if (jobSeeker == null) {
            return null;
        }

        JobSeekerResponseDTO jobSeekerResponseDTO = new JobSeekerResponseDTO();
        jobSeekerResponseDTO.setId(jobSeeker.getId());
        jobSeekerResponseDTO.setName(jobSeeker.getName());
        jobSeekerResponseDTO.setHeadline(jobSeeker.getHeadline());
        jobSeekerResponseDTO.setProfilePicture(jobSeeker.getProfilePicture());
        jobSeekerResponseDTO.setResume(jobSeeker.getResume());
        jobSeekerResponseDTO.setJobSeekerType(jobSeeker.getJobSeekerType());

        jobSeekerResponseDTO.setEducations(EducationMapper.toDTOList(jobSeeker.getEducations()));
        jobSeekerResponseDTO.setSkills(SkillMapper.toDTOList(jobSeeker.getSkills()));
        jobSeekerResponseDTO.setLanguages(LanguageMapper.toDTOList(jobSeeker.getLanguages()));
        jobSeekerResponseDTO.setExperiences(ExperienceMapper.toDTOList(jobSeeker.getExperiences()));
        jobSeekerResponseDTO.setApplications(jobSeeker.getApplications().stream().map(Application::getId).collect(Collectors.toList()));

        return jobSeekerResponseDTO;
    }
}
