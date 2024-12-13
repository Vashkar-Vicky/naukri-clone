package io.mountblue.naukriproject.service;

import io.mountblue.naukriclone.dto.ApplicationDTO;
import io.mountblue.naukriclone.dto.EmployerResponseDTO;
import io.mountblue.naukriclone.dto.JobDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface EmployerService {
    JobDTO getJobById(UUID jobId);
    List<ApplicationDTO> getApplicationsForJob(UUID jobId);
    Page<JobDTO> getJobsByEmployerId(UUID employerId, Pageable pageable);
    EmployerResponseDTO getEmployerById(UUID employerId);
    int getJobPostingsCount(UUID employerId);
//    int getActiveJobPostingsCount(UUID employerId);
    int getApplicationsCount(UUID employerId);
    List<String> getRecentActivities(UUID employerId);
}
