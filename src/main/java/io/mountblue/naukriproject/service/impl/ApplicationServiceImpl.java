package io.mountblue.naukriproject.service.impl;

import io.mountblue.naukriclone.entity.Application;
import io.mountblue.naukriclone.entity.Job;
import io.mountblue.naukriclone.entity.JobSeeker;
import io.mountblue.naukriclone.entity.User;
import io.mountblue.naukriclone.repository.ApplicationRepository;
import io.mountblue.naukriclone.repository.JobRepository;
import io.mountblue.naukriclone.repository.JobSeekerRepository;
import io.mountblue.naukriclone.service.ApplicationService;
import io.mountblue.naukriclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserService userService;

    @Override
    public void applyForJob(UUID jobId) {
        User user = userService.getAuthenticatedUser();
        JobSeeker jobSeeker = jobSeekerRepository.findById(user.getId()).orElse(null);
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));

        Application application = new Application();
        application.setApplicationDate(new java.sql.Date(new Date().getTime()));
        application.setStatus("Applied");
        application.setJobSeeker(jobSeeker);
        application.setJob(job);

        applicationRepository.save(application);
    }

    @Override
    public void updateApplicationStatus(UUID applicationId, String status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        applicationRepository.save(application);
    }

    @Override
    public List<Application> getApplicationsByJobSeekerIdAndStatus(UUID jobSeekerId) {
        return applicationRepository.findByJobSeekerIdAndStatus(jobSeekerId);
    }

    @Override
    public List<Job> getAppliedJobs(UUID jobSeekerId) {
        return applicationRepository.findByJobSeekerIdAndStatus(jobSeekerId)
                .stream()
                .map(Application::getJob)
                .collect(Collectors.toList());
    }

    @Override
    public List<Application> getApplicationsByJobSeeker(UUID jobSeekerId) {
        return applicationRepository.findByJobSeekerId(jobSeekerId);
    }
}
