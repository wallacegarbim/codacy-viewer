package com.codacy.challenge.commitviewer.dto;

import java.util.Collection;

public class Project {

    private Integer projectId;
    private String projectName;
    private String url;
    private Collection<GitCommitLog> gitCommitLogs;

    public Project(String projectName, String url) {
        this.projectName = projectName;
        this.url = url;
    }

    public Project(Integer projectId, String projectName, String url) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.url = url;
    }

    public Project() {
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getUrl() {
        return url;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Collection<GitCommitLog> getGitCommitLogs() {
        return gitCommitLogs;
    }

    public void setGitCommitLogs(Collection<GitCommitLog> gitCommitLogs) {
        this.gitCommitLogs = gitCommitLogs;
    }
}
