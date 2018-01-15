package com.codacy.challenge.commitviewer.ws.git;

import com.codacy.challenge.commitviewer.dto.GitCommitLog;
import com.codacy.challenge.commitviewer.dto.Project;

import java.util.Collection;

public interface IGitClientService {
    Collection<GitCommitLog> getGitCommitLogFromRestApi(String url, Project project);
}
