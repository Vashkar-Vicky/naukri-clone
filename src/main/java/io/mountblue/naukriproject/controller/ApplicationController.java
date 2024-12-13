package io.mountblue.naukriproject.controller;

import io.mountblue.naukriclone.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply/{jobId}")
    public String applyJob(@PathVariable UUID jobId, Model model) {
        applicationService.applyForJob(jobId);
        model.addAttribute("message", "Application submitted successfully.");
        return "application/success";
    }

    @PostMapping("/update-status/{applicationId}")
    public String updateApplicationStatus(@PathVariable UUID applicationId, @RequestParam String status, Model model) {
        applicationService.updateApplicationStatus(applicationId, status);
        model.addAttribute("message", "Application status updated successfully.");
        return "application/status-update";
    }

    @GetMapping("/{jobSeekerId}/applications")
    public String viewAppliedJobs(@PathVariable UUID jobSeekerId, Model model) {
        model.addAttribute("applications", applicationService.getApplicationsByJobSeekerIdAndStatus(jobSeekerId));
        return "application/applied";
    }
}