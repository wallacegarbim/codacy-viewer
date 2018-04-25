package com.codacy.challenge.commitviewer.dto;

import java.util.Date;

public final class GitCommitLog {

    private final String commitId;
    private final String author;
    private final Date commitDate;
    private final String comment;
    private final Integer projectId;

    private GitCommitLog(String commitId, String author, Date commitDate, String comment, Integer projectId) {
        this.commitId = commitId;
        this.author = author;
        this.commitDate = commitDate;
        this.comment = comment;
        this.projectId = projectId;
    }

    public static GitCommitLog getInstance(String commitId, String author, Date commitDate, String comment, Integer projectId){
        return new GitCommitLog(commitId, author, commitDate, comment, projectId);
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

}
