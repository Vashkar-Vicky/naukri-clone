package io.mountblue.naukriproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
public class ApplicationDTO {
    private UUID id;
    private Date applicationDate;
    private String status;
    private JobSeekerResponseDTO jobSeeker;
    private JobDTO job;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JobSeekerResponseDTO getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeekerResponseDTO jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public JobDTO getJob() {
        return job;
    }

    public void setJob(JobDTO job) {
        this.job = job;
    }
}
