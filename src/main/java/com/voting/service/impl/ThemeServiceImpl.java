package com.voting.service.impl;

import com.voting.model.Theme;
import com.voting.repository.ThemeRepository;
import com.voting.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    public Theme findById(Long id) {
        return themeRepository.findOne(id);
    }

    public void saveTheme(Theme theme) {
        themeRepository.save(theme);
    }

    public void updateTheme(Theme theme){
        saveTheme(theme);
    }

    public void deleteThemeById(Long id){
        themeRepository.delete(id);
    }

    public void closeDateTheme(Long id){
        themeRepository.closeDateTheme(id);
    }

    public List<Theme> findAllThemes(){
        return themeRepository.findAll();
    }



}
