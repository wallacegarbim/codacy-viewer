package com.codacy.challenge;

import com.codacy.challenge.commitviewer.CommitViewerApplication;
import com.codacy.challenge.commitviewer.configuration.DatabaseConfiguration;
import com.codacy.challenge.commitviewer.dao.IGitCommitLogDao;
import com.codacy.challenge.commitviewer.dao.IProjectDao;
import com.codacy.challenge.commitviewer.model.GitCommitLogEntity;
import static org.junit.Assert.*;

import com.codacy.challenge.commitviewer.model.ProjectEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CommitViewerApplication.class})
public class DatabaseTests {

    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    @Autowired
    private IGitCommitLogDao gitCommitLogDao;

    @Autowired
    IProjectDao projectDao;


    @Test
    public void insertSomeData(){
        getDatabaseConfiguration().createTables();
        List<GitCommitLogEntity> lst = new ArrayList<>();
        final ProjectEntity project = ProjectEntity.getInstance(null, "commit-viewer", "url", null);

        getProjectDao().save(project);
        final ProjectEntity project2 = getProjectDao().getProjectByName(project.getProjectName()).get();

        lst.add(GitCommitLogEntity.getInstance("5454454554", "Wallace Garbim wallacegarbim@gmail.com", new Date(), "teste", project2.getProjectId()));
        lst.add(GitCommitLogEntity.getInstance("98798798", "Luciana Garbim lucianagarbim@gmail.com", new Date(), "teste 2", project2.getProjectId()));

        getGitCommitLogDao().save(lst);
        assertEquals("5454454554", getGitCommitLogDao().getAll().stream().findFirst().get().getCommitId());

    }

    @Test
    public void countData(){
        getDatabaseConfiguration().createTables();
        List<GitCommitLogEntity> lst = new ArrayList<>();
        ProjectEntity project = ProjectEntity.getInstance(null, "commit-viewer", "url", null);

        getProjectDao().save(project);
        final ProjectEntity project2 = getProjectDao().getProjectByName(project.getProjectName()).get();

        lst.add(GitCommitLogEntity.getInstance("5454454554", "Wallace Garbim wallacegarbim@gmail.com", new Date(), "teste", project2.getProjectId()));
        lst.add(GitCommitLogEntity.getInstance("98798798", "Luciana Garbim lucianagarbim@gmail.com", new Date(), "teste 2", project2.getProjectId()));

        getGitCommitLogDao().save(lst);
        assertEquals(2, getGitCommitLogDao().getAll().stream().count());

    }

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

    public IGitCommitLogDao getGitCommitLogDao() {
        return gitCommitLogDao;
    }

    public IProjectDao getProjectDao() {
        return projectDao;
    }
}
