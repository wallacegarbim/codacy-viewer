package com.codacy.challenge.commitviewer.dao;

import com.codacy.challenge.commitviewer.model.GitCommitLogEntity;

import java.util.Collection;

public interface IGitCommitLogDao {
    void save(Collection<GitCommitLogEntity> gitCommitLogs);
    Collection<GitCommitLogEntity> getAll();
    Collection<GitCommitLogEntity> getCommitLogByProjectId(final Integer projectId);
}
