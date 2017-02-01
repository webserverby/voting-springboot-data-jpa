package com.voting.controller;

import com.voting.model.Question;
import com.voting.model.Theme;
import com.voting.service.QuestionService;
import com.voting.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Controllers
 *
 * @author Artem Faenko
 */
@RestController
public class AppController {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = { "/" })
    public ModelAndView home(ModelMap model) {
        model.addAttribute("title","Приложение-голосовалка");
        model.addAttribute("themes", themeService.findAllThemes());
        return new ModelAndView("index");
    }

    @RequestMapping(value = { "/create" }, method = RequestMethod.POST)
    public Theme createTheme(Theme theme)  {
        theme.setCreateDate(new Date());
        themeService.saveTheme(theme);
        Long id = theme.getId();
        theme.setLink("/theme/" + id);
        themeService.updateTheme(theme);
        return theme;
    }

    @RequestMapping(value = { "/question" }, method = RequestMethod.POST)
    public Question createQuestion(Question question)  {
        question.setAmount((long) 0);
        questionService.saveQuestion(question);
        questionService.findById(question.getId());
        return question;
    }

    @RequestMapping(value = { "/theme/{id}" }, method = RequestMethod.GET )
    public ModelAndView themeOpen(@PathVariable Long id, ModelMap model) {
        model.addAttribute("title","Голосование");
        model.addAttribute("theme", themeService.findById(id));
        model.addAttribute("questions", questionService.findByThemeId(id));
        return new ModelAndView("theme");
    }

    @RequestMapping(value= {"/theme/close/{id}" }, method = RequestMethod.GET)
    public ModelAndView close(@PathVariable Long id) {
        themeService.closeDateTheme(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value= {"/theme/delete/{id}" }, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id) {
        themeService.deleteThemeById(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = { "/question/increment/{idTheme}/{idQuestion}" }, method = RequestMethod.GET)
    public ModelAndView incrementQuestion(@PathVariable Long idTheme,
                                          @PathVariable Long idQuestion)  {
        Question question = questionService.findById(idQuestion);
        question.setAmount(question.getAmount() + 1);
        questionService.saveQuestion(question);
        return new ModelAndView("redirect:/theme/" + idTheme);
    }


}
