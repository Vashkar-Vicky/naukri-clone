package io.mountblue.naukriproject.controller;

import io.mountblue.naukriclone.entity.*;
import io.mountblue.naukriclone.service.ApplicationService;
import io.mountblue.naukriclone.service.JobSeekerService;
import io.mountblue.naukriclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/chatbot")
public class ChatbotController {

    @Autowired
    private ApplicationService applicationService; // Service to fetch job applications.

    @Autowired
    private UserService userService;

    @Autowired
    private JobSeekerService jobSeekerService;

    @GetMapping
    public String showChatbot(Model model) {
        User authenticatedUser = userService.getAuthenticatedUser();
        UUID userId = authenticatedUser.getId();
        JobSeeker jobSeeker = jobSeekerService.getJobSeekerById(userId);
        model.addAttribute("jobSeeker", jobSeeker);
        return "chatBot";
    }
    @GetMapping("/ask")
    public String askQuestion(
            @RequestParam("question") String question,
            Model model) {

        User authenticatedUser = userService.getAuthenticatedUser();
        UUID jobSeekerId = authenticatedUser.getId();
        String response;

        if (question.equalsIgnoreCase("give me applied jobs")) {
            List<Job> appliedJobs = applicationService.getAppliedJobs(jobSeekerId);
            if (appliedJobs.isEmpty()) {
                response = "You have not applied for any jobs yet.";
            } else {
                response = "You have applied for the following jobs:\n" +
                        appliedJobs.stream()
                                .map(Job::getTitle)
                                .collect(Collectors.joining(", "));
            }
        } else if (question.equalsIgnoreCase("Give me applied jobs with status")) {
            List<Application> applications = applicationService.getApplicationsByJobSeeker(jobSeekerId);
            if (applications.isEmpty()) {
                response = "You have not applied for any jobs yet.";
            } else {
                model.addAttribute("applications", applications);
                response = null; // No need for plain text response.
            }
        }else if (question.equalsIgnoreCase("List my skills")) {
            JobSeeker jobSeeker = jobSeekerService.getJobSeekerById(jobSeekerId);
            String skills = jobSeeker.getSkills().stream()
                    .map(Skill::getName) // Map each Skill object to its name
                    .collect(Collectors.joining(", "));
            response = "Your skills include: " + skills;
        } else if (question.equalsIgnoreCase("Give me my profile details")) {
            JobSeeker jobSeeker = jobSeekerService.getJobSeekerById(jobSeekerId);
            String languages = jobSeeker.getLanguages().stream()
                    .map(Language::getName) // Map each Language object to its name
                    .collect(Collectors.joining(", "));
            response = "Here are your profile details:\n" +
                    "Name: " + jobSeeker.getName() + "\n" +
                    "Headline: " + jobSeeker.getHeadline() + "\n" +
//                    "Experience: " + jobSeeker.getExperiences() + " years\n" +
                    "Languages: " + languages;
        } else if (question.equalsIgnoreCase("How many jobs have I applied for?")) {
            long jobCount = applicationService.getAppliedJobs(jobSeekerId).size();
            response = "You have applied for " + jobCount + " job(s).";
        } else {
            response = "I'm sorry, I didn't understand that question.";
        }
        model.addAttribute("response", response);
//        return "chatBot";// Add the response to the model
        model.addAttribute("jobSeekerId", jobSeekerId); // Make sure jobSeekerId is available in the view
        return "chatBot"; // Return to the same view to show the response
    }
}
