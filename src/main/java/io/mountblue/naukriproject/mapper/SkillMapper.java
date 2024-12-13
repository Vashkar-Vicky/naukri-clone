package io.mountblue.naukriproject.mapper;

import io.mountblue.naukriclone.dto.SkillDTO;
import io.mountblue.naukriclone.entity.Skill;
import io.mountblue.naukriclone.service.SkillService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SkillMapper {
    public static SkillDTO toSkillDTO(Skill skill) {
        if (skill == null) {
            return null;
        }
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setId(skill.getId());
        skillDTO.setName(skill.getName());
        skillDTO.setProficiency(skill.getProficiency());
        return skillDTO;
    }

    public static Skill toSkill(SkillDTO skillDTO) {
        if (skillDTO == null) {
            return null;
        }
        Skill skill = new Skill();
        skill.setId(skillDTO.getId());
        skill.setName(skillDTO.getName());
        skill.setProficiency(skillDTO.getProficiency());
        return skill;
    }

    public static List<Skill> toEntityList(List<SkillDTO> skillDTOs, SkillService skillService) {
        if (skillDTOs == null) {
            return null;
        }

        return skillDTOs.stream()
                .map(skillDTO -> {
                    Optional<Skill> existingSkill = skillService.findByNameAndProficiency(
                            skillDTO.getName(),
                            skillDTO.getProficiency()
                    );
                    return existingSkill.orElseGet(() -> (Skill) skillService.createSkill(skillDTO.getName(), skillDTO.getProficiency()));
                })
                .collect(Collectors.toList());
    }

    public static List<SkillDTO> toDTOList(List<Skill> skills) {
        if (skills == null) {
            return null;
        }
        return skills.stream()
                .map(SkillMapper::toSkillDTO)
                .collect(Collectors.toList());
    }
}
