package io.mountblue.naukriproject.mapper;

import io.mountblue.naukriclone.dto.EmployerRequestDTO;
import io.mountblue.naukriclone.dto.EmployerResponseDTO;
import io.mountblue.naukriclone.entity.Employer;

import java.util.List;
import java.util.stream.Collectors;

public class EmployerMapper {
    public static Employer toEntity(EmployerRequestDTO employerRequestDTO) {
        if (employerRequestDTO == null) {
            return null;
        }

        Employer employer = new Employer();
        employer.setCompanyName(employerRequestDTO.getCompanyName());
        employer.setIndustry(employerRequestDTO.getIndustry());
        employer.setProfilePicture(employerRequestDTO.getProfilePicture());
        return employer;
    }

    public static EmployerResponseDTO toDTO(Employer employer) {
        if (employer == null) {
            return null;
        }

        EmployerResponseDTO employerResponseDTO = new EmployerResponseDTO();
        employerResponseDTO.setId(employer.getId());
        employerResponseDTO.setCompanyName(employer.getCompanyName());
        employerResponseDTO.setIndustry(employer.getIndustry());
        employerResponseDTO.setProfilePicture(employer.getProfilePicture());
        return employerResponseDTO;
    }

    public static List<Employer> toEntityList(List<EmployerRequestDTO> employerRequestDTOs) {
        if (employerRequestDTOs == null) {
            return null;
        }
        return employerRequestDTOs.stream().map(EmployerMapper::toEntity).collect(Collectors.toList());
    }

    public static List<EmployerResponseDTO> toDTOList(List<Employer> employers) {
        if (employers == null) {
            return null;
        }
        return employers.stream().map(EmployerMapper::toDTO).collect(Collectors.toList());
    }
}