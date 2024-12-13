package io.mountblue.naukriproject.mapper;

import io.mountblue.naukriclone.dto.ExperienceDTO;
import io.mountblue.naukriclone.entity.Experience;

import java.util.List;
import java.util.stream.Collectors;

public class ExperienceMapper {
    public static Experience toEntity(ExperienceDTO experienceDTO) {
        if (experienceDTO == null) {
            return null;
        }

        Experience experience = new Experience();
        experience.setId(experienceDTO.getId());
        experience.setCompanyName(experienceDTO.getCompany());
        experience.setJobTitle(experienceDTO.getTitle());
        experience.setYearsOfExperience(experienceDTO.getYearsOfExperience());
        experience.setDescription(experienceDTO.getDescription());
        return experience;
    }

    public static ExperienceDTO toDto(Experience experience) {
        if (experience == null) {
            return null;
        }

        ExperienceDTO experienceDTO = new ExperienceDTO();
        experienceDTO.setId(experience.getId());
        experienceDTO.setCompany(experience.getCompanyName());
        experienceDTO.setTitle(experience.getJobTitle());
        experienceDTO.setYearsOfExperience(experience.getYearsOfExperience());
        experienceDTO.setDescription(experience.getDescription());
        return experienceDTO;
    }

    public static List<Experience> toEntityList(List<ExperienceDTO> experienceDTOs) {
        if (experienceDTOs == null) {
            return null;
        }
        return experienceDTOs.stream().map(ExperienceMapper::toEntity).collect(Collectors.toList());
    }

    public static List<ExperienceDTO> toDTOList(List<Experience> experiences) {
        if (experiences == null) {
            return null;
        }
        return experiences.stream().map(ExperienceMapper::toDto).collect(Collectors.toList());
    }
}