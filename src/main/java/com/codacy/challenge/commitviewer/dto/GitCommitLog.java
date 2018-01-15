package com.codacy.challenge.commitviewer.dto;

import java.util.Date;

public class GitCommitLog {

    private String commitId;
    private String author;
    private Date commitDate;
    private String comment;
    private Integer projectId;

    public GitCommitLog() {
    }

    public GitCommitLog(String commitID, String author, Date commitDate, String comment) {
        this.commitId = commitID;
        this.author = author;
        this.commitDate = commitDate;
        this.comment = comment;
    }

    public GitCommitLog(String commitId, String author, Date commitDate, String comment, Integer projectId) {
        this.commitId = commitId;
        this.author = author;
        this.commitDate = commitDate;
        this.comment = comment;
        this.projectId = projectId;
    }

    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
