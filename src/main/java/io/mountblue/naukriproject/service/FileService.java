package io.mountblue.naukriproject.service;

import io.mountblue.naukriclone.dto.EmployerResponseDTO;
import io.mountblue.naukriclone.dto.JobSeekerResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileService {

    public JobSeekerResponseDTO uploadProfilePicture(UUID jobSeekerId, MultipartFile file) throws Exception;
    public JobSeekerResponseDTO uploadResume(UUID jobSeekerId, MultipartFile file) throws Exception;
    EmployerResponseDTO uploadEmployerProfilePicture(UUID employerId, MultipartFile file) throws Exception;
    Resource loadResumeAsResource(UUID jobSeekerId);
}
