package io.mountblue.naukriproject.controller;

import io.mountblue.naukriclone.dto.JobDTO;
import io.mountblue.naukriclone.entity.Job;
import io.mountblue.naukriclone.mapper.JobMapper;
import io.mountblue.naukriclone.repository.EmployerRepository;
import io.mountblue.naukriclone.repository.SkillRepository;
import io.mountblue.naukriclone.service.JobService;
import io.mountblue.naukriclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class JobController {

    private final JobService jobService;
    private final SkillRepository skillRepository;
    private final EmployerRepository employerRepository;
    private final UserService userService;

    @Autowired
    public JobController(JobService jobService, SkillRepository skillRepository,
                         EmployerRepository employerRepository, UserService userService) {
        this.jobService = jobService;
        this.skillRepository = skillRepository;
        this.employerRepository = employerRepository;
        this.userService = userService;
    }

    @GetMapping
    public String getAllJobsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Page<JobDTO> jobPage = jobService.getAllJobs(page, size).map(JobMapper::toDTO);
        model.addAttribute("jobs", jobPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", jobPage.getTotalPages());
        return "home";
    }

    @GetMapping("/jobs/{id}")
    public String getJobById(@PathVariable UUID id, Model model) {
        JobDTO job = JobMapper.toDTO(jobService.getJobById(id));
        model.addAttribute("job", job);
        return "JobDetails";
    }

    @GetMapping("/jobs/create")
    public String showCreateJobForm(Model model) {
        model.addAttribute("jobDTO", new JobDTO());
        return "CreateJob";
    }

    @PostMapping("/jobs/create")
    public String createJob(@ModelAttribute JobDTO jobDTO) {
        Job job = JobMapper.toEntity(jobDTO, userService,employerRepository, skillRepository);
        jobService.createJob(job);
        return "redirect:/";
    }

    @GetMapping("/jobs/edit/{id}")
    public String showEditJobForm(@PathVariable UUID id, Model model) {
        JobDTO jobDTO = JobMapper.toDTO(jobService.getJobById(id));
        model.addAttribute("jobDTO", jobDTO); // Use "jobDTO" to match the Thymeleaf form object
        return "CreateJob";
    }

    @PostMapping("/jobs/edit/{id}")
    public String updateJob(@PathVariable UUID id, @ModelAttribute JobDTO jobDTO) {
        jobDTO.setId(id);
        Job job = JobMapper.toEntity(jobDTO, userService,employerRepository, skillRepository);
        jobService.updateJob(job);
        return "redirect:/";
    }

    @PostMapping("/jobs/delete/{id}")
    public String deleteJob(@PathVariable UUID id) {
        jobService.deleteJobById(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer experienceRequired,
            @RequestParam(required = false) String companyName,
            Model model) {
        List<Job> jobs = jobService.searchJobs(title, location, experienceRequired, companyName);
        model.addAttribute("jobs", jobs);
        return "home";
    }

//    @GetMapping("/filter")
//    public String filterJobs(
//            @RequestParam(required = false) List<String> locations,
//            @RequestParam(required = false) List<String> industry,
//            @RequestParam(required = false) List<String> jobType,
//            @RequestParam(required = false) List<String> workMode, // Work modes added
//            Model model) {
//
//        // Fetch filtered jobs based on the provided criteria
//        List<Job> filteredJobs = jobService.filterJobsByLocationIndustryJobTypeAndWorkMode(
//                locations, industry, jobType, workMode);
//
//        // Debugging output
//        System.out.println("Locations: " + locations);
//        System.out.println("Industry: " + industry);
//        System.out.println("JobType: " + jobType);
//        System.out.println("WorkMode: " + workMode);
//
//        // Add filtered jobs to the model
//        model.addAttribute("jobs", filteredJobs);
//
//        // Add possible filter options to the model
//        model.addAttribute("locationsList", List.of("Bengaluru", "Delhi / NCR", "Mumbai", "Hyderabad"));
//        model.addAttribute("industryList", List.of("Engineering", "Software", "Marketing", "Sales"));
//        model.addAttribute("jobTypesList", List.of("FULL_TIME", "PART_TIME", "INTERNSHIP"));
//        model.addAttribute("workModeList", List.of("REMOTE", "ONSITE", "HYBRID")); // Added work modes
//
//        return "home";
//    }
}
