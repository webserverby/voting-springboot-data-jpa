package com.voting.service;

import com.voting.model.Question;
import com.voting.model.Theme;

import java.util.List;

public interface QuestionService {

    Question findById(Long id);

    List<Question> findByThemeId(Long id);

    void saveQuestion(Question question);

    List<Question> findAllQuestions();

}
