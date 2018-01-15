package com.codacy.challenge.commitviewer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class UtilFile {
    private static final String GIT_SUFIX = ".git";
    private final String gitUrl;
    private final String basePath;

    @Autowired
    public UtilFile(@Value("${git.directory.path}") String basePath, @Value("${git.url}") String gitUrl) {
        this.basePath = basePath;
        this.gitUrl = gitUrl;
    }

    public File getPath(String url){
        return new File(getBasePath() + getProjectName(url));
    }

    public String getProjectName(String url){
        final String formmated = url.replace(getGitUrl(), "").replace(UtilFile.GIT_SUFIX, "");
        return  formmated.substring(formmated.indexOf('/')).replace("/", "");

    }

    public String getAuthorAndRepoFromGitUrl(String url){
        return url.replace(getGitUrl(), "").replace(UtilFile.GIT_SUFIX, "");
    }

    public String getBasePath() {
        return basePath;
    }

    private String getGitUrl() {
        return gitUrl;
    }

}
