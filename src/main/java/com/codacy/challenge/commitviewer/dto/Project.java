package com.codacy.challenge.commitviewer.dto;

import java.util.Collection;

public final class Project {

    private final Integer projectId;
    private final String projectName;
    private final String url;
    private final Collection<GitCommitLog> gitCommitLogs;

    private Project(Integer projectId, String projectName, String url, Collection<GitCommitLog> gitCommitLogs) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.url = url;
        this.gitCommitLogs = gitCommitLogs;
    }

    public static Project getInstance(Integer projectId, String projectName, String url, Collection<GitCommitLog> gitCommitLogs) {
        return new Project(projectId, projectName, url, gitCommitLogs);
    }

    public Integer getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getUrl() {
        return url;
    }

    public Collection<GitCommitLog> getGitCommitLogs() {
        return gitCommitLogs;
    }
}
