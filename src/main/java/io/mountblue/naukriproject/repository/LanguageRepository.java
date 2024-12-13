package io.mountblue.naukriproject.repository;

import io.mountblue.naukriclone.entity.Language;
import io.mountblue.naukriclone.entity.enums.Proficiency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LanguageRepository extends JpaRepository<Language, UUID> {
    Language findByNameAndProficiency(String name, Proficiency proficiency);

    Language findLanguagesById(UUID id);
}
