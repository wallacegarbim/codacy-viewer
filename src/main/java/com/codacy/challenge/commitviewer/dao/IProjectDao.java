package com.codacy.challenge.commitviewer.dao;

import com.codacy.challenge.commitviewer.model.ProjectEntity;

import java.util.Collection;
import java.util.Optional;

public interface IProjectDao {
    int save(ProjectEntity entity);
    Collection<ProjectEntity> listAll();
    Optional<ProjectEntity> getProjectByName(String projectName);
    Integer isProjectExist(String projectName);
}
