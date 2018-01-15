package com.codacy.challenge.commitviewer.util;

import com.codacy.challenge.commitviewer.CommitViewerApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CommitViewerApplication.class})
public class UtilFileTest {

    private static final String URL = "https://github.com/wallacegarbim/codacy-viewer.git";
    private static final String AUTHOR_AND_REPO = "wallacegarbim/codacy-viewer";

    @Autowired
    private UtilFile utilFile;

    @Test
    public void getAuthorAndRepoFromGitUrl() throws Exception {
        Assert.assertEquals(AUTHOR_AND_REPO, getUtilFile().getAuthorAndRepoFromGitUrl(URL));
    }

    public UtilFile getUtilFile() {
        return utilFile;
    }
}