package com.codacy.challenge.commitviewer.facade;

import com.codacy.challenge.commitviewer.dto.Project;
import com.codacy.challenge.commitviewer.dto.Url;

import java.util.Collection;
import java.util.Optional;

public interface IProjectFacade {
    Collection<Project> listAll();
    Optional<Project> createNewProject(Url url);
    void saveGitCommitLogsByProject(Project project);
    Project getCommitLogListByProjectName(String url);
}
