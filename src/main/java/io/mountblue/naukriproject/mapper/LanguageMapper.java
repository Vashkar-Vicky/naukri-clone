package io.mountblue.naukriproject.mapper;

import io.mountblue.naukriclone.dto.LanguageDTO;
import io.mountblue.naukriclone.entity.Language;
import io.mountblue.naukriclone.repository.LanguageRepository;

import java.util.List;
import java.util.stream.Collectors;

public class LanguageMapper {

    public static LanguageDTO toDTO(Language language) {
        if (language == null) {
            return null;
        }
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setId(language.getId());
        languageDTO.setName(language.getName());
        languageDTO.setProficiency(language.getProficiency());
        return languageDTO;
    }

    public static Language toEntity(LanguageDTO languageDTO) {
        if (languageDTO == null) {
            return null;
        }
        Language language = new Language();
        language.setId(languageDTO.getId());
        language.setName(languageDTO.getName());
        language.setProficiency(languageDTO.getProficiency());
        return language;
    }

    public static List<LanguageDTO> toDTOList(List<Language> languages) {
        if (languages == null) {
            return null;
        }
        return languages.stream()
                .map(LanguageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Language> toEntityList(List<LanguageDTO> languageDTOs, LanguageRepository languageRepository) {
        if (languageDTOs == null) {
            return null;
        }

        return languageDTOs.stream()
                .map(languageDTO -> {
                    Language existingLanguage = languageRepository != null
                            ? languageRepository.findByNameAndProficiency(
                            languageDTO.getName(),
                            languageDTO.getProficiency()
                    )
                            : null;

                    return existingLanguage != null ? existingLanguage : toEntity(languageDTO);
                })
                .collect(Collectors.toList());
    }
}