package com.codacy.challenge;

import com.codacy.challenge.commitviewer.CommitViewerApplication;
import com.codacy.challenge.commitviewer.configuration.DatabaseConfiguration;
import com.codacy.challenge.commitviewer.dto.Project;
import com.codacy.challenge.commitviewer.service.IProjectService;
import static org.junit.Assert.*;

import com.codacy.challenge.commitviewer.util.UtilFile;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CommitViewerApplication.class})
public class ProjectServiceTest {

    private static final String PROJECT_NAME = "codacy-viewer";
    private static final String URL = "https://github.com/wallacegarbim/codacy-viewer.git";

    @Autowired
    private IProjectService projectService;

    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    @Autowired
    private UtilFile utilFile;

    Project project;

    @Before
    public void databaseInitData(){
        getDatabaseConfiguration().createTables();
        project = Project.getInstance(null, PROJECT_NAME, URL, null);
        getProjectService().save(project);
    }

    @Test
    public void countListAll(){
        assertEquals(1, getProjectService().listAll().size());
    }

    @Test
    public void verifyProjectName(){
        assertEquals(PROJECT_NAME, getProjectService().getProjectByName(PROJECT_NAME).getProjectName());
    }

    @Test
    public void verifyProjectNameByUrl(){
        assertEquals(PROJECT_NAME, getProjectService().newProjectFromUrl(URL).getProjectName());
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

    public UtilFile getUtilFile() {
        return utilFile;
    }
}
