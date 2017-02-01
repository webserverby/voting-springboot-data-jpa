package com.voting.service.impl;

import com.voting.model.Question;
import com.voting.repository.QuestionRepository;
import com.voting.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question findById(Long id) {
        return questionRepository.findOne(id);
    }

    public List<Question> findByThemeId(Long id) {
        return questionRepository.findByThemeId(id);
    }

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public List<Question> findAllQuestions(){
        return questionRepository.findAll();
    }

}
