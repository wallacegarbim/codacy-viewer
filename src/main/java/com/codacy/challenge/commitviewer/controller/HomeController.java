package com.codacy.challenge.commitviewer.controller;

import com.codacy.challenge.commitviewer.dto.Url;
import com.codacy.challenge.commitviewer.facade.IProjectFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class HomeController {

    private final IProjectFacade projectFacade;

    @Autowired
    public HomeController(final IProjectFacade projectFacade) {
        this.projectFacade = projectFacade;
    }

    @GetMapping
    public ModelAndView home(final Map<String, Object> model) {
       model.put("url", new Url());
       model.put("projectList", getProjectFacade().listAll());
       return new ModelAndView("index", model);
    }

    private IProjectFacade getProjectFacade() {
        return projectFacade;
    }
}
