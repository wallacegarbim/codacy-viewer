package com.codacy.challenge.commitviewer.service;

import com.codacy.challenge.commitviewer.dto.GitCommitLog;
import com.codacy.challenge.commitviewer.dto.Project;
import org.eclipse.jgit.api.Git;

import java.util.Collection;

public interface IGitRepositoryService {
    Git cloneRepository(String url);
    Collection<GitCommitLog> getCommitListByProjectId(Integer projectId);
    Collection<GitCommitLog> getGitCommitLogFromLocalRepository(Project project);
    void saveCommitLogByProject(Collection<GitCommitLog> entities, Project project);
}
