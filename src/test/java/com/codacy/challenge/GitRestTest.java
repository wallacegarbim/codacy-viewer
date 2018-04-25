package com.codacy.challenge;

import com.codacy.challenge.commitviewer.CommitViewerApplication;
import com.codacy.challenge.commitviewer.dto.Project;
import com.codacy.challenge.commitviewer.ws.git.IGitClientService;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CommitViewerApplication.class})
public class GitRestTest {

    @Autowired
    private IGitClientService  gitClientService;
    private static final String URL = "https://github.com/wallacegarbim/codacy-viewer.git";

    @Test
    public void testConnection(){
        final Project project = Project.getInstance(1, null, null, null);

        final int commitId = getGitClientService().getGitCommitLogFromRestApi(URL, project)
                .stream()
                .findFirst()
                .get()
                .getProjectId();
        assertEquals(1, commitId);

    }

//    @Test
//    public void verifyAuthor(){
//        final Project project = Project.getInstance(1, null, null, null);
//
//        final String author = getGitClientService().getGitCommitLogFromRestApi(URL, project)
//                .stream()
//                .findFirst()
//                .get()
//                .getAuthor();
//
//        assertEquals("Wallace Garbim - wallacegarbim@gmail.com", author);
//    }

    public IGitClientService getGitClientService() {
        return gitClientService;
    }
}