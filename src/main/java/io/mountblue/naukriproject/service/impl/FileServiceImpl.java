package io.mountblue.naukriproject.service.impl;

import io.mountblue.naukriclone.dto.EmployerResponseDTO;
import io.mountblue.naukriclone.dto.JobSeekerResponseDTO;
import io.mountblue.naukriclone.entity.Employer;
import io.mountblue.naukriclone.entity.JobSeeker;
import io.mountblue.naukriclone.mapper.EmployerMapper;
import io.mountblue.naukriclone.mapper.JobSeekerMapper;
import io.mountblue.naukriclone.repository.EmployerRepository;
import io.mountblue.naukriclone.repository.JobSeekerRepository;
import io.mountblue.naukriclone.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private final S3Service s3Service;

    private final JobSeekerRepository jobSeekerRepository;

    private final EmployerRepository employerRepository;

    public FileServiceImpl(S3Service s3Service, JobSeekerRepository jobSeekerRepository, EmployerRepository employerRepository) {
        this.s3Service = s3Service;
        this.jobSeekerRepository = jobSeekerRepository;
        this.employerRepository = employerRepository;
    }

    @Override
    public JobSeekerResponseDTO uploadProfilePicture(UUID jobSeekerId, MultipartFile file) throws Exception {
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.equals("image/jpeg") && !contentType.equals("image/png"))) {
            throw new IllegalArgumentException("Invalid file type. Only JPEG and PNG are allowed.");
        }
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Job Seeker ID"));
        String tempDir = System.getProperty("java.io.tmpdir");
        Path tempPath = Paths.get(tempDir, file.getOriginalFilename());
        file.transferTo(tempPath.toFile());

        String key = "profile-pictures/" + jobSeekerId + "/" + file.getOriginalFilename();
        String url = s3Service.uploadFile(key, tempPath.toString());

        jobSeeker.setProfilePicture(url);
        jobSeekerRepository.save(jobSeeker);
        return JobSeekerMapper.toDTO(jobSeeker);
    }

    @Override
    public JobSeekerResponseDTO uploadResume(UUID jobSeekerId, MultipartFile file) throws Exception {
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.equals("application/pdf") &&
                !contentType.equals("application/msword") &&
                !contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
            throw new IllegalArgumentException("Invalid file type. Only PDF and Word documents are allowed.");
        }
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Job Seeker ID"));
        String tempDir = System.getProperty("java.io.tmpdir");
        Path tempPath = Paths.get(tempDir, file.getOriginalFilename());
        file.transferTo(tempPath.toFile());

        String key = "resumes/" + jobSeekerId + "/" + file.getOriginalFilename();
        String url = s3Service.uploadFile(key, tempPath.toString());

        jobSeeker.setResume(url);
        jobSeekerRepository.save(jobSeeker);
        return JobSeekerMapper.toDTO(jobSeeker);
    }

    @Override
    public EmployerResponseDTO uploadEmployerProfilePicture(UUID employerId, MultipartFile file) throws Exception {
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.equals("image/jpeg") && !contentType.equals("image/png"))) {
            throw new IllegalArgumentException("Invalid file type. Only JPEG and PNG are allowed.");
        }

        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Employer ID"));
        String tempDir = System.getProperty("java.io.tmpdir");
        Path tempPath = Paths.get(tempDir, file.getOriginalFilename());
        file.transferTo(tempPath.toFile());

        String key = "employer-profile-pictures/" + employerId + "/" + file.getOriginalFilename();
        String url = s3Service.uploadFile(key, tempPath.toString());

        employer.setProfilePicture(url);
        employerRepository.save(employer);
        return EmployerMapper.toDTO(employer);
    }

    @Override
    public Resource loadResumeAsResource(UUID jobSeekerId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Job Seeker ID"));

        String resumeUrl = jobSeeker.getResume();
        if (resumeUrl == null || resumeUrl.isEmpty()) {
            throw new RuntimeException("Resume not found for Job Seeker ID " + jobSeekerId);
        }

        try {
            Resource resource = new UrlResource(resumeUrl);
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + jobSeekerId);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + jobSeekerId, ex);
        }
    }
}
