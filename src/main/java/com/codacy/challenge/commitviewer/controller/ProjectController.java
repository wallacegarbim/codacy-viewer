package com.codacy.challenge.commitviewer.controller;

import com.codacy.challenge.commitviewer.dto.Project;
import com.codacy.challenge.commitviewer.dto.Url;
import com.codacy.challenge.commitviewer.facade.IProjectFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {

    private final IProjectFacade projectFacade;

    @Autowired
    public ProjectController(IProjectFacade projectFacade) {
        this.projectFacade = projectFacade;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView createNewProject(@ModelAttribute Url url, Map<String, Object> model){

        final Project project = getProjectFacade().createNewProject(url).orElse(new Project());

        if(project.getProjectName() != null && !"".equals(project.getProjectName())){
            return new ModelAndView("redirect:/project/" + project.getProjectName(), model);
        }else {
            return new ModelAndView("redirect:/", model);
        }
    }


    @RequestMapping(value = "/{url}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getProjecByUrl(@PathVariable String url, Map<String, Object> model){
        final Project project = getProjectFacade().getCommitLogListByProjectName(url);
        model.put("projectUrl", project.getUrl());
        model.put("commits", project.getGitCommitLogs());

        return new ModelAndView("project-commit", model);
    }

    public IProjectFacade getProjectFacade() {
        return projectFacade;
    }
}
