package io.mountblue.naukriproject.mapper;

import io.mountblue.naukriclone.dto.ApplicationDTO;
import io.mountblue.naukriclone.entity.Application;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationMapper {

    public static ApplicationDTO toDTO(Application application) {
        if(application == null) {
            return null;
        }
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setId(application.getId());
        applicationDTO.setApplicationDate(application.getApplicationDate());
        applicationDTO.setStatus(application.getStatus());
        applicationDTO.setJobSeeker(JobSeekerMapper.toDTO(application.getJobSeeker()));
        applicationDTO.setJob(JobMapper.toDTO(application.getJob()));
        return applicationDTO;
    }

    public static Application toEntity(ApplicationDTO applicationDTO) {
        if(applicationDTO == null) {
            return null;
        }
        Application application = new Application();
        application.setId(applicationDTO.getId());
        application.setApplicationDate(applicationDTO.getApplicationDate());
        application.setStatus(applicationDTO.getStatus());
        return application;
    }

    public static List<ApplicationDTO> toDTOList(List<Application> applications) {
        if(applications == null) {
            return null;
        }
        return applications.stream().map(ApplicationMapper::toDTO).collect(Collectors.toList());
    }

    public static List<Application> toEntityList(List<ApplicationDTO> applicationDTOs) {
        if(applicationDTOs == null) {
            return null;
        }
        return applicationDTOs.stream().map(ApplicationMapper::toEntity).collect(Collectors.toList());
    }
}
