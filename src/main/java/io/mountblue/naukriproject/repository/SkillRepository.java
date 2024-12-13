package io.mountblue.naukriproject.repository;

import io.mountblue.naukriclone.entity.Skill;
import io.mountblue.naukriclone.entity.enums.Proficiency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SkillRepository extends JpaRepository<Skill, UUID> {
    Skill findByNameAndProficiency(String name, Proficiency proficiency);

    Skill findByNameContainingAndProficiency(String name, Proficiency proficiency);

}