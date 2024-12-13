package io.mountblue.naukriproject.controller;

import io.mountblue.naukriclone.service.EmployerService;
import io.mountblue.naukriclone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    private final EmployerService employerService;
    private final UserService userService;

//    private final UserService userService;

    public EmployerController(EmployerService employerService, UserService userService) {
        this.employerService = employerService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String showEmployerHomePage(Model model) {
        UUID employerId = userService.getAuthenticatedUser().getId();
        model.addAttribute("employer", employerService.getEmployerById(employerId));
        model.addAttribute("jobPostingsCount", employerService.getJobPostingsCount(employerId));
        model.addAttribute("applicationsCount", employerService.getApplicationsCount(employerId));
        model.addAttribute("recentActivities", employerService.getRecentActivities(employerId));
        return "employer/employer-home";
    }

    @GetMapping("/jobs")
    public String showJobPostings(Model model) {
        UUID employerId = userService.getAuthenticatedUser().getId();
        model.addAttribute("jobs", employerService.getJobsByEmployerId(employerId, null));
        return "employer/job-postings";
    }

    @GetMapping("/jobs/{id}")
    public String showJobApplications(@PathVariable UUID id, Model model) {
        model.addAttribute("applications", employerService.getApplicationsForJob(id));
        return "employer/job-applications";
    }

    @GetMapping("/profile/{id}")
    public String showEmployerProfile(@PathVariable UUID id, Model model) {
        model.addAttribute("employer", employerService.getEmployerById(id));
        model.addAttribute("jobPostings", employerService.getJobsByEmployerId(id, null));
        return "employer/employer-profile";
    }
}
