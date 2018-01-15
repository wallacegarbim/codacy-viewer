package com.codacy.challenge.commitviewer.service;

import com.codacy.challenge.commitviewer.dto.Project;

import java.util.Collection;

public interface IProjectService {

    int save(Project project);
    Collection<Project> listAll();
    Project getProjectByName(String projectName);
    Project newProjectFromUrl(String url);
    boolean createNewProjectByUrl(String url);
    boolean isProjectExist(String url);
}
