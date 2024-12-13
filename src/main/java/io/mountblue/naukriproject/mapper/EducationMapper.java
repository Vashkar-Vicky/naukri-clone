package io.mountblue.naukriproject.mapper;

import io.mountblue.naukriclone.dto.EducationDTO;
import io.mountblue.naukriclone.entity.Education;

import java.util.List;
import java.util.stream.Collectors;

public class EducationMapper {
    public static Education toEntity(EducationDTO educationDTO) {
        if (educationDTO == null) {
            return null;
        }

        Education education = new Education();
        education.setId(educationDTO.getId());
        education.setDegree(educationDTO.getDegree());
        education.setInstitution(educationDTO.getInstitution());
        education.setGraduationYear(educationDTO.getGraduationYear());
        return education;
    }
    public static EducationDTO toDTO(Education education) {
        if (education == null) {
            return null;
        }

        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setId(education.getId());
        educationDTO.setDegree(education.getDegree());
        educationDTO.setInstitution(education.getInstitution());
        educationDTO.setGraduationYear(education.getGraduationYear());
        return educationDTO;
    }

    public static List<Education> toEntityList(List<EducationDTO> educationDTOs) {
        if (educationDTOs == null) {
            return null;
        }
        return educationDTOs.stream().map(EducationMapper::toEntity).collect(Collectors.toList());
    }

    public static List<EducationDTO> toDTOList(List<Education> educations) {
        if (educations == null) {
            return null;
        }
        return educations.stream().map(EducationMapper::toDTO).collect(Collectors.toList());
    }
}
