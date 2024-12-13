package io.mountblue.naukriproject.service.impl;

import io.mountblue.naukriclone.entity.Job;
import io.mountblue.naukriclone.entity.enums.WorkMode;
import io.mountblue.naukriclone.repository.JobRepository;
import io.mountblue.naukriclone.repository.LanguageRepository;
import io.mountblue.naukriclone.repository.SkillRepository;
import io.mountblue.naukriclone.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final LanguageRepository languageRepository;
    private final SkillRepository skillRepository;


    @Autowired
    public JobServiceImpl(JobRepository jobRepository, LanguageRepository languageRepository, SkillRepository skillRepository) {
        this.jobRepository = jobRepository;
        this.languageRepository = languageRepository;
        this.skillRepository = skillRepository;
    }


    @Override
    public Page<Job> getAllJobs(int page, int size) {
        return jobRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(UUID id) {
        return jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    @Override
    public Page<Job> getJobsByEmployerId(UUID employerId, int page, int size) {
        return jobRepository.findByEmployerId(employerId, PageRequest.of(page, size));
    }

    @Override
    public void deleteJobById(UUID id) {
        jobRepository.deleteById(id);
    }

//    @Override
//    public Page<Job> findJobsByLocation(String location, int page, int size) {
//        return jobRepository.findByLocationContainingIgnoreCase(location, PageRequest.of(page, size));
//    }

    public void updateJob(Job job) {
        Job existingJob = jobRepository.findById(job.getId())
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        existingJob.setTitle(job.getTitle());
        existingJob.setDescription(job.getDescription());
        existingJob.setLocation(job.getLocation());
        existingJob.setSalaryRange(job.getSalaryRange());
        existingJob.setExperienceRequired(job.getExperienceRequired());
        existingJob.setPostedDate(job.getPostedDate());
        jobRepository.save(existingJob);
    }

    @Override
    public List<Job> searchJobs(String title, String location, Integer experienceRequired, String companyName) {
        return jobRepository.searchJobs(title, location, experienceRequired, companyName);
    }

    @Override
    public List<Job> filterJobsByLocation(List<String> locations) {
        return jobRepository.filterJobsByLocation(locations);



    }

    @Override
    public List<Job> filterJobsByIndustry(List<String> industries) {
        return jobRepository.filterJobsByIndustry(industries);
    }

    @Override
    public List<Job> filterJobs(List<String> locations, List<String> industries) {

        return jobRepository.filterJobsByLocationAndIndustry(locations, industries);
    }

    @Override
    public List<Job> filterJobsByLocationAndIndustryAndJobType(List<String> locations, List<String> industry, List<String> jobType) {
        return jobRepository.filterJobs(locations, industry, jobType);
    }

    @Override
    public List<Job> filterJobsByJobType(List<String> jobType) {
        return jobRepository.filterJobsByJobType(jobType);
    }

    @Override
    public List<Job> filterJobsByLocationIndustryAndJobType(List<String> locations, List<String> industries, List<String> jobType) {

        return jobRepository.filterJobsByLocationIndustryAndJobType(locations, industries, jobType);
    }
    @Override
    public List<Job> filterJobsByWorkMode(List<WorkMode> workModes) {
        return jobRepository.findByWorkModeIn(workModes);
    }



}