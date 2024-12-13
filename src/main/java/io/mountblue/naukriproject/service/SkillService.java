package io.mountblue.naukriproject.service;

import io.mountblue.naukriclone.entity.Skill;
import io.mountblue.naukriclone.entity.enums.Proficiency;

import java.util.Optional;

public interface SkillService {
    Optional<Skill> findByNameAndProficiency(String name, Proficiency proficiency);

    Object createSkill(String name, Proficiency proficiency);
}
