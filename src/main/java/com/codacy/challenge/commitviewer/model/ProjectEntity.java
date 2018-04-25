package com.codacy.challenge.commitviewer.model;

import java.io.Serializable;
import java.util.Collection;

public class ProjectEntity implements Serializable, Comparable<ProjectEntity> {

    private static final long serialVersionUID = 8738362477968396937L;
    public static final String INSERT = "INSERT INTO " + ProjectEntity.PROJECT_TABLE + " (project_name, url) VALUES (?, ?)";
    public static final String SELECT_BY_NAME = "SELECT * FROM " + ProjectEntity.PROJECT_TABLE + " WHERE project_name = ?";
    public static final String COUNT_BY_NAME = "SELECT COUNT(*) FROM " + ProjectEntity.PROJECT_TABLE + " WHERE project_name = ?";
    public static final String SELECT_ALL = "SELECT * FROM " + ProjectEntity.PROJECT_TABLE + " ORDER BY project_name asc";
    private static final String PROJECT_TABLE = "project";

    private final Integer projectId;
    private final String projectName;
    private final String url;
    private Collection<GitCommitLogEntity> gitCommitLogEntities;

    private ProjectEntity(final Integer projectId, final String projectName, final String url, final Collection<GitCommitLogEntity> gitCommitLogEntities) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.url = url;
        this.gitCommitLogEntities = gitCommitLogEntities;
    }

    public static ProjectEntity getInstance(final Integer projectId, final String projectName, final String url, Collection<GitCommitLogEntity> gitCommitLogEntities){
        return new ProjectEntity( projectId, projectName, url, gitCommitLogEntities);
    }

    public String getProjectName() {
        return projectName;
    }

    public String getUrl() {
        return url;
    }

    public Collection<GitCommitLogEntity> getGitCommitLogEntities() {
        return gitCommitLogEntities;
    }

    public Integer getProjectId() {
        return projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectEntity)) return false;

        ProjectEntity that = (ProjectEntity) o;

        if (!projectId.equals(that.projectId)) return false;
        if (!projectName.equals(that.projectName)) return false;
        if (!url.equals(that.url)) return false;
        return gitCommitLogEntities != null ? gitCommitLogEntities.equals(that.gitCommitLogEntities) : that
                .gitCommitLogEntities == null;
    }

    @Override
    public int hashCode() {
        int result = projectId.hashCode();
        result = 31 * result + projectName.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + (gitCommitLogEntities != null ? gitCommitLogEntities.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(ProjectEntity o) {
        return this.projectName.compareTo(o.projectName);
    }
}
