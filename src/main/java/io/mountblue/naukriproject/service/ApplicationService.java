package io.mountblue.naukriproject.service;

import io.mountblue.naukriclone.entity.Application;
import io.mountblue.naukriclone.entity.Job;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {

    void applyForJob(UUID jobId);

    void updateApplicationStatus(UUID applicationId, String status);
    List<Application> getApplicationsByJobSeekerIdAndStatus(UUID jobSeekerId);

    List<Job> getAppliedJobs(UUID jobSeekerId);

    List<Application> getApplicationsByJobSeeker(UUID jobSeekerId);
}
