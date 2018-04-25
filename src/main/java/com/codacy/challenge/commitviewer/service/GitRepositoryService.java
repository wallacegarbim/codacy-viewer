package com.codacy.challenge.commitviewer.service;

import com.codacy.challenge.commitviewer.dao.IGitCommitLogDao;
import com.codacy.challenge.commitviewer.dto.GitCommitLog;
import com.codacy.challenge.commitviewer.dto.Project;
import com.codacy.challenge.commitviewer.model.GitCommitLogEntity;
import com.codacy.challenge.commitviewer.util.UtilFile;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GitRepositoryService implements IGitRepositoryService {

    private static final Logger log = LoggerFactory.getLogger(GitRepositoryService.class);
    private UtilFile utilFile;
    private final IGitCommitLogDao gitCommitLogDao;
    private Function<GitCommitLogEntity, GitCommitLog> entityToDtoWithProjectId = entity -> GitCommitLog.getInstance(entity.getCommitId(), entity.getAuthor(), entity.getCommitDate(), entity.getComment(), entity.getProjectId());
    private Function<GitCommitLog, GitCommitLogEntity> dtoToEntityWithProjectId = dto -> GitCommitLogEntity.getInstance(dto.getCommitId(), dto.getAuthor(), dto.getCommitDate(), dto.getComment(), dto.getProjectId());

    @Autowired
    public GitRepositoryService(UtilFile utilFile, IGitCommitLogDao gitCommitLogDao) {
        this.utilFile = utilFile;
        this.gitCommitLogDao = gitCommitLogDao;
    }


    @Override
    public Git cloneRepository(String url) {

        try {
            return Git.cloneRepository()
                    .setDirectory(getUtilFile().getPath(url))
                    .setURI(url).call();
        } catch (GitAPIException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Collection<GitCommitLog> getCommitListByProjectId(Integer projectId) {
        return getGitCommitLogDao().getCommitLogByProjectId(projectId)
                .stream()
                .map(entityToDtoWithProjectId)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<GitCommitLog> getGitCommitLogFromLocalRepository(Project project) {
        final Git git = cloneRepository(project.getUrl());
        Collection<GitCommitLog> result = new HashSet<>();
        try {
            git.log().call().forEach( revCommit ->
                result.add(GitCommitLog.getInstance(revCommit.getName(), revCommit.getAuthorIdent().getName() + " - " +
                        revCommit.getAuthorIdent().getEmailAddress(), revCommit.getCommitterIdent().getWhen(), revCommit.getShortMessage(), project.getProjectId())));
            return result;
        } catch (GitAPIException e) {
            log.error(e.getMessage());
            return new HashSet<>();
        }
    }

    @Override
    public void saveCommitLogByProject(final Collection<GitCommitLog> gitCommitLogs, final Project project) {
        Collection<GitCommitLogEntity> result = gitCommitLogs.stream()
                .map(dtoToEntityWithProjectId)
                .collect(Collectors.toList());

        getGitCommitLogDao().save(result);
    }

    private UtilFile getUtilFile() {
        return utilFile;
    }

    private IGitCommitLogDao getGitCommitLogDao() {
        return gitCommitLogDao;
    }
}
