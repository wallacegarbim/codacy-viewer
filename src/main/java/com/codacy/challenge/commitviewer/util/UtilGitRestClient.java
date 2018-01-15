package com.codacy.challenge.commitviewer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UtilGitRestClient {

    private final String resourceUrl;
    private final String repos;
    private final String operation;
    private Function<String, String> buildGitCommitLogUrl = url -> getResourceUrl() + getRepos() + url + getOperation();


    @Autowired
    public UtilGitRestClient(@Value("${git.resourceURL}") String resourceUrl,
                             @Value("${git.repos}") String repos,
                             @Value("${git.operation}") String operation) {
        this.resourceUrl = resourceUrl;
        this.repos = repos;
        this.operation = operation;
    }

    private String getResourceUrl() {
        return resourceUrl;
    }

    private String getRepos() {
        return repos;
    }

    private String getOperation() {
        return operation;
    }

    public Function<String, String> getBuildGitCommitLogUrl() {
        return buildGitCommitLogUrl;
    }
}
