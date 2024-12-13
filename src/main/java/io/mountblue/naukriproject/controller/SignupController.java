package io.mountblue.naukriproject.controller;

import io.mountblue.naukriclone.dto.EmployerRequestDTO;
import io.mountblue.naukriclone.dto.JobSeekerRequestDTO;
import io.mountblue.naukriclone.dto.UserRequestDTO;
import io.mountblue.naukriclone.service.LanguageService;
import io.mountblue.naukriclone.service.SignupService;
import io.mountblue.naukriclone.service.SkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private final SignupService signupService;
    private final SkillService skillService;
    private final LanguageService languageService;

    public SignupController(SignupService signupService, SkillService skillService, LanguageService languageService) {
        this.signupService = signupService;
        this.skillService = skillService;
        this.languageService = languageService;
    }

    @RequestMapping
    public String showInitialSignupForm(Model model) {
        model.addAttribute("userRequestDTO", new UserRequestDTO());
        return "signup-initial"; // Render the initial form
    }

    @PostMapping("/next")
    public String handleInitialSignup(
            @ModelAttribute UserRequestDTO userRequestDTO,
            Model model) {

        if ("JOB_SEEKER".equalsIgnoreCase(userRequestDTO.getRole().name())) {
            JobSeekerRequestDTO jobSeekerRequestDTO = new JobSeekerRequestDTO();
            jobSeekerRequestDTO.setEmail(userRequestDTO.getEmail());
            jobSeekerRequestDTO.setPassword(userRequestDTO.getPassword());
            jobSeekerRequestDTO.setMobileNumber(userRequestDTO.getMobileNumber());
            jobSeekerRequestDTO.setRole(userRequestDTO.getRole());

            model.addAttribute("jobSeekerRequestDTO", jobSeekerRequestDTO);
            return "jobseeker-form";
        } else if ("EMPLOYER".equalsIgnoreCase(userRequestDTO.getRole().name())) {
            EmployerRequestDTO employerRequestDTO = new EmployerRequestDTO();
            employerRequestDTO.setEmail(userRequestDTO.getEmail());
            employerRequestDTO.setPassword(userRequestDTO.getPassword());
            employerRequestDTO.setMobileNumber(userRequestDTO.getMobileNumber());
            employerRequestDTO.setRole(userRequestDTO.getRole());

            model.addAttribute("employerRequestDTO", employerRequestDTO);
            return "employer-form";
        }
        return "redirect:/signup";
    }

    @PostMapping("/jobseeker")
    public String registerJobSeeker(@ModelAttribute JobSeekerRequestDTO jobSeekerRequestDTO) {
        signupService.registerJobSeeker(jobSeekerRequestDTO, skillService, languageService);
        return "redirect:/signin";
    }

    @PostMapping("/employer")
    public String registerEmployer(@ModelAttribute EmployerRequestDTO employerRequestDTO) {
        signupService.registerEmployer(employerRequestDTO);
        return "redirect:/signin";
    }
}