package io.mountblue.naukriproject.service.impl;

import io.mountblue.naukriclone.entity.Language;
import io.mountblue.naukriclone.entity.enums.Proficiency;
import io.mountblue.naukriclone.repository.LanguageRepository;
import io.mountblue.naukriclone.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public  Optional<Language> findByNameAndProficiency(String name, Proficiency proficiency) {
        return Optional.ofNullable(languageRepository.findByNameAndProficiency(name, proficiency));
    }

    @Override
    public Language createLanguage(String name, Proficiency proficiency) {
        Language language = new Language();
        language.setName(name);
        language.setProficiency(proficiency);
        return languageRepository.save(language);
    }
}
