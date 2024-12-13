package io.mountblue.naukriproject.service;

import io.mountblue.naukriclone.entity.Language;
import io.mountblue.naukriclone.entity.enums.Proficiency;

import java.util.Optional;

public interface LanguageService {
    Optional<Language> findByNameAndProficiency(String name, Proficiency proficiency);

    Object createLanguage(String name, Proficiency proficiency);
}
