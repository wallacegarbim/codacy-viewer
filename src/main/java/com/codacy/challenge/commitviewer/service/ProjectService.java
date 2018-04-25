package com.codacy.challenge.commitviewer.service;


import com.codacy.challenge.commitviewer.dao.IProjectDao;
import com.codacy.challenge.commitviewer.dto.Project;
import com.codacy.challenge.commitviewer.model.ProjectEntity;
import com.codacy.challenge.commitviewer.util.UtilFile;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProjectService implements IProjectService {


    private final IProjectDao projectDao;
    private final UtilFile utilFile;
    private Function<ProjectEntity, Project> entityToDto = entity -> Project.getInstance(entity.getProjectId(), entity.getProjectName(), entity.getUrl(), null);

    @Autowired
    public ProjectService(IProjectDao projectDao, UtilFile utilFile) {
        this.projectDao = projectDao;
        this.utilFile = utilFile;
    }

    @Override
    public int save(Project project) {
        return getProjectDao().save(ProjectEntity.getInstance(null, project.getProjectName(), project.getUrl(), null));
    }

    @Override
    public Collection<Project> listAll() {
        return ImmutableList.copyOf(getProjectDao().listAll()
                .stream()
                .map(entityToDto)
                .collect(Collectors.toList()));

    }

    @Override
    public Project getProjectByName(String projectName) {
        return entityToDto.apply(getProjectDao().getProjectByName(projectName).get());
    }

    @Override
    public Project newProjectFromUrl(final String url) {
        return Project.getInstance(null, getUtilFile().getProjectName(url), url, null);
    }

    @Override
    public boolean createNewProjectByUrl(final String url) {
        return save(newProjectFromUrl(url)) == 1;
    }

    @Override
    public boolean isProjectExist(String url) {
        return getProjectDao().isProjectExist(getUtilFile().getProjectName(url)) > 0;
    }

    private IProjectDao getProjectDao() {
        return projectDao;
    }

    private UtilFile getUtilFile() {
        return utilFile;
    }
}
