package io.mountblue.naukriproject.service.impl;

import io.mountblue.naukriclone.dto.EmployerRequestDTO;
import io.mountblue.naukriclone.dto.JobSeekerRequestDTO;
import io.mountblue.naukriclone.entity.Employer;
import io.mountblue.naukriclone.entity.JobSeeker;
import io.mountblue.naukriclone.entity.User;
import io.mountblue.naukriclone.entity.enums.Role;
import io.mountblue.naukriclone.mapper.EmployerMapper;
import io.mountblue.naukriclone.mapper.JobSeekerMapper;
import io.mountblue.naukriclone.repository.EmployerRepository;
import io.mountblue.naukriclone.repository.JobSeekerRepository;
import io.mountblue.naukriclone.repository.UserRepository;
import io.mountblue.naukriclone.service.LanguageService;
import io.mountblue.naukriclone.service.SignupService;
import io.mountblue.naukriclone.service.SkillService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService {

    private final UserRepository userRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final EmployerRepository employerRepository;
    private final PasswordEncoder passwordEncoder;
    private final LanguageService languageService;
    private final SkillService skillService;

    public SignupServiceImpl(UserRepository userRepository, JobSeekerRepository jobSeekerRepository,
                             EmployerRepository employerRepository, PasswordEncoder passwordEncoder, LanguageService languageService, SkillService skillService) {
        this.userRepository = userRepository;
        this.jobSeekerRepository = jobSeekerRepository;
        this.employerRepository = employerRepository;
        this.passwordEncoder = passwordEncoder;
        this.languageService = languageService;
        this.skillService = skillService;
    }

    @Override
    public void registerJobSeeker(JobSeekerRequestDTO jobSeekerRequestDTO, SkillService skillService, LanguageService languageService) {
        // Create and save the user
        User user = new User();
        user.setEmail(jobSeekerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(jobSeekerRequestDTO.getPassword()));
        user.setMobileNumber(jobSeekerRequestDTO.getMobileNumber());
        user.setRole(Role.JOB_SEEKER);
        User savedUser = userRepository.save(user);

        JobSeeker jobSeeker = JobSeekerMapper.toEntity(jobSeekerRequestDTO, skillService, languageService);
        jobSeeker.setUser(savedUser);
        jobSeekerRepository.save(jobSeeker);
    }


    @Override
    public void registerEmployer(EmployerRequestDTO employerRequestDTO) {
        User user = new User();
        user.setEmail(employerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(employerRequestDTO.getPassword()));
        user.setMobileNumber(employerRequestDTO.getMobileNumber());
        user.setRole(Role.EMPLOYER);
        User savedUser = userRepository.save(user);

        Employer employer = EmployerMapper.toEntity(employerRequestDTO);
        employer.setUser(savedUser);
        employerRepository.save(employer);
    }
}