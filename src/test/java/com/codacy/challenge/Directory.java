package com.codacy.challenge;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Directory {

    public static final String GIT = ".git";
    private String url = "https://github.com/wallacegarbim/codacy-viewer";
    private static final String GIT_URL = "https://github.com/";

    @Test
    public void getPath(){
        final String formmated = url.replace(GIT_URL, "").replace(GIT, "");
        final File file = new File(getBasePath() + formmated.substring(formmated.indexOf("/")));
        Assert.assertEquals(getBasePath() + "codacy-viewer", file.getPath());

    }

    public String getBasePath() {
        return "/Users/wallacegarbim/git-temp/";
    }
}
