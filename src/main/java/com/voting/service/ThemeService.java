package com.voting.service;

import com.voting.model.Theme;

import java.util.List;

public interface ThemeService {

    Theme findById(Long id);

    void saveTheme(Theme theme);

    void updateTheme(Theme theme);

    void deleteThemeById(Long id);

    void closeDateTheme(Long id);

    List<Theme> findAllThemes();

}
