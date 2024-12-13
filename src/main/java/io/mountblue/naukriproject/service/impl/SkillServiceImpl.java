package io.mountblue.naukriproject.service.impl;

import io.mountblue.naukriclone.entity.Skill;
import io.mountblue.naukriclone.entity.enums.Proficiency;
import io.mountblue.naukriclone.repository.SkillRepository;
import io.mountblue.naukriclone.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public  Optional<Skill> findByNameAndProficiency(String name, Proficiency proficiency) {
        return Optional.ofNullable(skillRepository.findByNameAndProficiency(name, proficiency));
    }

    @Override
    public Skill createSkill(String name, Proficiency proficiency) {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setProficiency(proficiency);
        return skillRepository.save(skill);
    }
}
