package io.mountblue.naukriproject.service;

import io.mountblue.naukriclone.dto.EmployerRequestDTO;
import io.mountblue.naukriclone.dto.JobSeekerRequestDTO;

public interface SignupService {
    void registerJobSeeker(JobSeekerRequestDTO jobSeekerRequestDTO, SkillService skillService, LanguageService languageService);
    void registerEmployer(EmployerRequestDTO employerRequestDTO);
}
