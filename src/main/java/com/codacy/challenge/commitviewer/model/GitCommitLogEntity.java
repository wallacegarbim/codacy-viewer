package com.codacy.challenge.commitviewer.model;

import java.io.Serializable;
import java.util.Date;

public class GitCommitLogEntity implements Serializable, Comparable<GitCommitLogEntity> {

    private static final long serialVersionUID = -8716454278480162330L;
    private static final String TABLE_NAME = "git_commit_log";
    public static final String INSERT_ALL_BATCH = "INSERT INTO " + GitCommitLogEntity.TABLE_NAME + " (commit_id, author, commit_date, comment, project_id) VALUES (?,?,?,?,?)";
    public static final String SELECT_ALL = "SELECT * FROM " + GitCommitLogEntity.TABLE_NAME + " ORDER BY commit_date DESC";
    public static final String SELECT_BY_PROJECT_ID = "SELECT * FROM " + GitCommitLogEntity.TABLE_NAME + " WHERE project_id = ? ORDER BY commit_date DESC";

    private final String commitId;
    private final String author;
    private final Date commitDate;
    private final String comment;
    private final Integer projectId;

    private GitCommitLogEntity(String commitID, String author, Date commitDate, String comment, Integer projectId) {
        this.commitId = commitID;
        this.author = author;
        this.commitDate = commitDate;
        this.comment = comment;
        this.projectId = projectId;
    }

    public static GitCommitLogEntity getInstance(String commitID, String author, Date commitDate, String comment, Integer projectId){
        return new GitCommitLogEntity(commitID, author, commitDate, comment, projectId);
    }

    public String getCommitId() {
        return commitId;
    }


    public String getAuthor() {
        return author;
    }


    public Date getCommitDate() {
        return commitDate;
    }


    public String getComment() {
        return comment;
    }


    public Integer getProjectId() {
        return projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GitCommitLogEntity)) return false;

        GitCommitLogEntity that = (GitCommitLogEntity) o;

        if (!commitId.equals(that.commitId)) return false;
        if (!author.equals(that.author)) return false;
        if (!commitDate.equals(that.commitDate)) return false;
        if (!comment.equals(that.comment)) return false;
        return projectId.equals(that.projectId);
    }

    @Override
    public int hashCode() {
        int result = commitId.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + commitDate.hashCode();
        result = 31 * result + comment.hashCode();
        result = 31 * result + projectId.hashCode();
        return result;
    }

    public String toString() {
        return commitId + "," + author + "," + commitDate + ","+ comment + "," +projectId;
    }

    @Override
    public int compareTo(GitCommitLogEntity o) {
        return this.commitId.compareTo(o.commitId);
    }
}
