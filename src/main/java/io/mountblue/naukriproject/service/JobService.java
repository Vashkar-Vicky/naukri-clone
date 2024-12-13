package io.mountblue.naukriproject.service;

import io.mountblue.naukriclone.entity.Job;
import io.mountblue.naukriclone.entity.enums.WorkMode;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface JobService {
    Page<Job> getAllJobs(int page, int size);
    void createJob(Job job);
    Job getJobById(UUID id);
    Page<Job> getJobsByEmployerId(UUID ID, int page, int size);
    void deleteJobById(UUID id);
    void updateJob(Job job);
    List<Job> searchJobs(String title, String location, Integer experienceRequired, String companyName);

    public List<Job> filterJobsByLocation(List<String> locations) ;
    public List<Job> filterJobsByIndustry(List<String> industries);

    public List<Job> filterJobs(List<String> locations, List<String> industries);

    public List<Job> filterJobsByLocationAndIndustryAndJobType(List<String> locations,
                                                               List<String> industry, List<String> jobType);

    List<Job> filterJobsByJobType(List<String> jobType);

    public List<Job> filterJobsByLocationIndustryAndJobType(List<String> locations, List<String> industries, List<String> jobType);

    public List<Job> filterJobsByWorkMode(List<WorkMode> workModes);

}
