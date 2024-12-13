package io.mountblue.naukriproject.mapper;

import io.mountblue.naukriclone.dto.JobDTO;
import io.mountblue.naukriclone.entity.Employer;
import io.mountblue.naukriclone.entity.Job;
import io.mountblue.naukriclone.entity.Skill;
import io.mountblue.naukriclone.entity.User;
import io.mountblue.naukriclone.entity.enums.Proficiency;
import io.mountblue.naukriclone.repository.EmployerRepository;
import io.mountblue.naukriclone.repository.SkillRepository;
import io.mountblue.naukriclone.service.UserService;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JobMapper {
    public static JobDTO toDTO(Job job) {
        if (job == null) {
            return null;
        }

        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setSalaryRange(job.getSalaryRange());
        jobDTO.setType(job.getJobType());
        jobDTO.setWorkMode(job.getWorkMode());
        jobDTO.setExperienceRequired(job.getExperienceRequired());
        jobDTO.setPostedDate(job.getPostedDate());
        jobDTO.setRequiredSkills(job.getRequiredSkills());
        jobDTO.setEmployer(job.getEmployer());
        return jobDTO;
    }

    public static Job toEntity(JobDTO jobDTO, UserService userService,
                               EmployerRepository employerRepository, SkillRepository skillRepository) {
        if (jobDTO == null) {
            return null;
        }

        Job job = new Job();
        job.setId(jobDTO.getId());
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setSalaryRange(jobDTO.getSalaryRange());
        job.setJobType(jobDTO.getType());
        job.setWorkMode(jobDTO.getWorkMode());
        job.setExperienceRequired(jobDTO.getExperienceRequired());
        job.setPostedDate(new Date(System.currentTimeMillis()));

        // Set employer based on the authenticated user
        User authenticatedUser = userService.getAuthenticatedUser();
        Employer employer = employerRepository.getEmployerById(authenticatedUser.getId());
        job.setEmployer(employer);

        // Map required skills
        List<Skill> skills = jobDTO.getRequiredSkills().stream().map(skillDTO -> {
            Skill existingSkill = skillRepository.findByNameContainingAndProficiency(skillDTO.getName(), Proficiency.BEGINNER);

            if (existingSkill != null) {
                return existingSkill;
            } else {
                Skill newSkill = new Skill();
                newSkill.setName(skillDTO.getName());
                newSkill.setProficiency(Proficiency.BEGINNER);
                return newSkill;
            }
        }).collect(Collectors.toList());

        job.setRequiredSkills(skills);
        return job;
    }

    public static List<JobDTO> toDTOList(List<Job> jobs) {
        if (jobs == null) {
            return null;
        }
        return jobs.stream().map(JobMapper::toDTO).collect(Collectors.toList());
    }

    public static List<Job> toEntityList(List<JobDTO> jobDTOs, Employer employer) {
        if (jobDTOs == null) {
            return null;
        }
        return jobDTOs.stream().map(jobDTO -> toEntity(jobDTO, null, null, null)).collect(Collectors.toList());
    }
}
