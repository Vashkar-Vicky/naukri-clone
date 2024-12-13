package io.mountblue.naukriproject.service.impl;

import io.mountblue.naukriclone.dto.ApplicationDTO;
import io.mountblue.naukriclone.dto.EmployerResponseDTO;
import io.mountblue.naukriclone.dto.JobDTO;
import io.mountblue.naukriclone.entity.Job;
import io.mountblue.naukriclone.mapper.ApplicationMapper;
import io.mountblue.naukriclone.mapper.EmployerMapper;
import io.mountblue.naukriclone.mapper.JobMapper;
import io.mountblue.naukriclone.repository.ApplicationRepository;
import io.mountblue.naukriclone.repository.EmployerRepository;
import io.mountblue.naukriclone.repository.JobRepository;
import io.mountblue.naukriclone.repository.JobSeekerRepository;
import io.mountblue.naukriclone.service.EmployerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployerServiceImpl implements EmployerService {

    private final JobSeekerRepository jobSeekerRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final EmployerRepository employerRepository;
    private final EmployerMapper employerMapper;

    public EmployerServiceImpl(JobSeekerRepository jobSeekerRepository, JobRepository jobRepository, ApplicationRepository applicationRepository, EmployerRepository employerRepository) {
        this.jobSeekerRepository = jobSeekerRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.employerRepository = employerRepository;
        this.employerMapper = new EmployerMapper();
    }


    @Override
    public JobDTO getJobById(UUID jobId) {
        Job job = jobRepository.findById(jobId).orElse(null);
        return JobMapper.toDTO(job);
    }

    @Override
    public List<ApplicationDTO> getApplicationsForJob(UUID jobId) {
        return ApplicationMapper.toDTOList(applicationRepository.findByJobId(jobId));
    }


    @Override
    public Page<JobDTO> getJobsByEmployerId(UUID employerId, Pageable pageable) {
        return jobRepository.findByEmployerId(employerId, pageable).map(JobMapper::toDTO);
    }

    @Override
    public EmployerResponseDTO getEmployerById(UUID employerId) {
        return employerMapper.toDTO(employerRepository.findById(employerId).orElse(null));
    }

    @Override
    public int getJobPostingsCount(UUID employerId) {
        return jobRepository.findByEmployerId(employerId, Pageable.unpaged()).getNumberOfElements();
    }

//    @Override
//    public int getActiveJobPostingsCount(UUID employerId) {
//        return jobRepository.findByEmployerIdAndStatus(employerId,).getNumberOfElements();
//    }

    @Override
    public int getApplicationsCount(UUID employerId) {
        List<Job> jobs = jobRepository.findByEmployerId(employerId, Pageable.unpaged()).getContent();
        return jobs.stream()
                .mapToInt(job -> applicationRepository.countByJobId(job.getId()))
                .sum();
    }

    @Override
    public List<String> getRecentActivities(UUID employerId) {
        List<Job> jobs = jobRepository.findByEmployerId(employerId, Pageable.unpaged()).getContent();
        List<String> jobActivities = jobs.stream()
                .map(job -> "Job posted: " + job.getTitle() + " on " + job.getPostedDate())
                .collect(Collectors.toList());

        List<String> applicationActivities = jobs.stream()
                .flatMap(job -> applicationRepository.findByJobId(job.getId()).stream())
                .map(application -> "Application received for job: " + application.getJob().getTitle() + " from " + application.getJobSeeker().getName())
                .collect(Collectors.toList());

        jobActivities.addAll(applicationActivities);
        return jobActivities;
    }
}
