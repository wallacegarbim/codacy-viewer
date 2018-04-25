package com.codacy.challenge.commitviewer.facade;

import com.codacy.challenge.commitviewer.dto.Project;
import com.codacy.challenge.commitviewer.dto.Url;
import com.codacy.challenge.commitviewer.service.IGitRepositoryService;
import com.codacy.challenge.commitviewer.service.IProjectService;
import com.codacy.challenge.commitviewer.util.UtilFile;
import com.codacy.challenge.commitviewer.ws.git.IGitClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProjectFacade implements IProjectFacade {

    private static final Logger log = LoggerFactory.getLogger(ProjectFacade.class);
    private final IProjectService projectService;
    private final IGitRepositoryService gitRepository;
    private final UtilFile utilFile;
    private final IGitClientService gitClientService;

    @Autowired
    public ProjectFacade(IProjectService projectService, IGitRepositoryService gitRepository, UtilFile utilFile, IGitClientService gitClientService) {
        this.projectService = projectService;
        this.gitRepository = gitRepository;
        this.utilFile = utilFile;
        this.gitClientService = gitClientService;
    }

    @Override
    public Collection<Project> listAll() {
        return getProjectService().listAll();
    }

    @Override
    public Optional<Project> createNewProject(Url url) {

        if (url.getUrl() != null && !"".equals(url.getUrl()) && !getProjectService().isProjectExist(url.getUrl())
                && getProjectService().createNewProjectByUrl(url.getUrl().trim())) {

                final Project project = getProjectService()
                        .getProjectByName(getUtilFile().getProjectName(url.getUrl().trim()));

                saveGitCommitLogsByProject(project);

            return Optional.of(project);

        }else {
            return  Optional.empty();
        }
    }

    @Override
    public void saveGitCommitLogsByProject(Project project) {

            try {
                getGitRepository().saveCommitLogByProject(getGitClientService().getGitCommitLogFromRestApi(project.getUrl(), project), project);
            }catch (RestClientException e){
                log.error(e.getMessage());
                getGitRepository().saveCommitLogByProject(getGitRepository().getGitCommitLogFromLocalRepository(project), project);
            }


    }


    @Override
    public Project getCommitLogListByProjectName(String url) {
        final Project project = getProjectService().getProjectByName(url);
        return Project.getInstance(project.getProjectId(), project.getProjectName(), project.getUrl(), getGitRepository().getCommitListByProjectId(project.getProjectId()));
    }

    private IProjectService getProjectService() {
        return projectService;
    }

    private IGitRepositoryService getGitRepository() {
        return gitRepository;
    }

    private UtilFile getUtilFile() {
        return utilFile;
    }

    private IGitClientService getGitClientService() {
        return gitClientService;
    }
}
