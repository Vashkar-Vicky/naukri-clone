package io.mountblue.naukriproject.controller;

import io.mountblue.naukriclone.entity.*;
import io.mountblue.naukriclone.entity.enums.Proficiency;
import io.mountblue.naukriclone.service.JobSeekerService;
import io.mountblue.naukriclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
@RequestMapping("/jobSeeker")
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public String viewProfile(Model model) {
        User authenticatedUser = userService.getAuthenticatedUser();
        UUID userId = authenticatedUser.getId();
        JobSeeker jobSeeker = jobSeekerService.getJobSeekerById(userId);
        model.addAttribute("jobSeeker", jobSeeker);
        return "profile/view";
    }

    @GetMapping("/edit")
    public String editProfile( Model model) {
        User authenticatedUser = userService.getAuthenticatedUser();
        UUID userId = authenticatedUser.getId();
        model.addAttribute("skills", jobSeekerService.getSkills(userId));
        model.addAttribute("languages", jobSeekerService.getLanguages(userId));
        model.addAttribute("proficiencies", Proficiency.values());
        model.addAttribute("newEducation", new Education());
        model.addAttribute("newExperience", new Experience());

        JobSeeker jobSeeker = jobSeekerService.getJobSeekerById(userId);
        model.addAttribute("jobSeeker", jobSeeker);
        return "profile/edit";
    }

    @PostMapping("/education/add")
    public String addEducation(@ModelAttribute Education education, @RequestParam UUID jobSeekerId) {
        jobSeekerService.addEducation(jobSeekerId, education);
        return "redirect:/jobSeeker/edit";
    }

    @PostMapping("/education/delete")
    public String deleteEducation(@RequestParam UUID educationId) {
        jobSeekerService.deleteEducation(educationId);
        return "redirect:/jobSeeker/edit";
    }

    @PostMapping("/experience/add")
    public String updateExperience(@ModelAttribute Experience experience, @RequestParam UUID jobSeekerId) {
        jobSeekerService.addExperience(jobSeekerId ,experience);
        return "redirect:/jobSeeker/edit" ;
    }

    @PostMapping("/experience/delete")
    public String deleteExperience(@RequestParam UUID experienceId) {
        jobSeekerService.deleteExperience(experienceId);
        return "redirect:/jobSeeker/edit" ;
    }


//    @PostMapping("/update/{id}")
//    public String updateJobSeeker(@PathVariable UUID id,
//                                  @RequestParam(value = "skills", required = false) List<UUID> skillIds,
//                                  @RequestParam(value = "languages", required = false) List<UUID> languageIds,
//                                  Model model) {
//        Optional<JobSeeker> jobSeekerOpt = jobSeekerService.findById(id);
//
//        if (jobSeekerOpt.isPresent()) {
//            JobSeeker jobSeeker = jobSeekerOpt.get();
//
//            List<Skill> selectedSkills = skillRepository.findAllById(skillIds);
//            List<Language> selectedLanguages = languageRepository.findAllById(languageIds);
//
//            jobSeeker.setSkills(selectedSkills);
//            jobSeeker.setLanguages(selectedLanguages);
//
//            jobSeekerService.save(jobSeeker);
//
//            model.addAttribute("jobSeeker", jobSeeker);
//            return "redirect:/jobSeeker";
//        }
//
//        return "error";
//    }

    @PostMapping("/update")
    public String updateProfile(
            @RequestParam("name") String name,
            @RequestParam("headline") String headline,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture) {

        User authenticatedUser = userService.getAuthenticatedUser();
        UUID jobSeekerId = authenticatedUser.getId();
        jobSeekerService.updateProfile(jobSeekerId, name, headline, profilePicture);
        return "redirect:/jobSeeker/edit?success";
    }


    @PostMapping("/skills/add")
    public String addSkill(@RequestParam String name, @RequestParam Proficiency proficiency) {
        User authenticatedUser = userService.getAuthenticatedUser();
        UUID jobSeekerId = authenticatedUser.getId();
        jobSeekerService.addSkill(jobSeekerId, name, proficiency);
        return "redirect:/jobSeeker/edit";
    }

    @PostMapping("/skills/delete")
    public String deleteSkill(@RequestParam UUID skillId) {
        User authenticatedUser = userService.getAuthenticatedUser();
        UUID jobSeekerId = authenticatedUser.getId();
        jobSeekerService.deleteSkill(skillId,jobSeekerId);
        return "redirect:/jobSeeker/edit";
    }

    @PostMapping("/language/add")
    public String addLanguage(@RequestParam String name, @RequestParam Proficiency proficiency){
        User authenticatedUser = userService.getAuthenticatedUser();
        UUID jobSeekerId = authenticatedUser.getId();
        jobSeekerService.addLanguage(jobSeekerId,name,proficiency);
        return "redirect:/jobSeeker/edit";
    }

    @PostMapping("/language/delete")
    public String deleteLanguage(@RequestParam UUID languageId) {
        User authenticatedUser = userService.getAuthenticatedUser();
        UUID jobSeekerId = authenticatedUser.getId();
        jobSeekerService.deleteLanguage(languageId,jobSeekerId);
        return "redirect:/jobSeeker/edit";
    }

}