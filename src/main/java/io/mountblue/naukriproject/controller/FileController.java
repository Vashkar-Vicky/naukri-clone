package io.mountblue.naukriproject.controller;

import io.mountblue.naukriclone.dto.EmployerResponseDTO;
import io.mountblue.naukriclone.dto.JobSeekerResponseDTO;
import io.mountblue.naukriclone.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/{id}/uploadProfilePicture")
    public JobSeekerResponseDTO uploadProfilePicture(@PathVariable UUID id, @RequestParam("file") MultipartFile file) throws Exception {
        return fileService.uploadProfilePicture(id, file);
    }

    @PostMapping("/{id}/uploadResume")
    public JobSeekerResponseDTO uploadResume(@PathVariable UUID id, @RequestParam("file") MultipartFile file) throws Exception {
        return fileService.uploadResume(id, file);
    }

    @PostMapping("/{employerId}/uploadEmployerProfilePicture")
    public ResponseEntity<?> uploadEmployerProfilePicture(@PathVariable UUID employerId, @RequestParam("file") MultipartFile file) {
        try {
            EmployerResponseDTO employerResponseDTO = fileService.uploadEmployerProfilePicture(employerId, file);
            return ResponseEntity.ok(employerResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/downloadResume/{jobSeekerId}")
    public ResponseEntity<Resource> downloadResume(@PathVariable UUID jobSeekerId) {
        Resource file = fileService.loadResumeAsResource(jobSeekerId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
