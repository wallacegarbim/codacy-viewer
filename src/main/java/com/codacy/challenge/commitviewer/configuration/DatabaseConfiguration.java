package com.codacy.challenge.commitviewer.configuration;

import com.codacy.challenge.commitviewer.util.UtilFile;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class DatabaseConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);
    private final JdbcTemplate jdbcTemplate;
    private final UtilFile utilFile;
    private static final String DROP_GIT_COMMIT_TABLE = "DROP TABLE git_commit_log IF EXISTS";
    private static final String DROP_PROJECT_TABLE = "DROP TABLE project IF EXISTS";
    private static final String CREATE_LOG_TABLE = "CREATE TABLE git_commit_log(" +
            "id INTEGER IDENTITY PRIMARY KEY, commit_id VARCHAR(255), author VARCHAR(255), commit_date DATETIME," +
            " comment VARCHAR(255), project_id INTEGER, FOREIGN KEY (project_id) REFERENCES project(project_id))";

    private static final String CREATE_PROJECT_TABLE = "CREATE TABLE project(" +
            "project_id INTEGER IDENTITY PRIMARY KEY, project_name VARCHAR(255), url VARCHAR(255))";

    @Autowired
    public DatabaseConfiguration(JdbcTemplate jdbcTemplate, UtilFile utilFile) {
        this.jdbcTemplate = jdbcTemplate;
        this.utilFile = utilFile;
        createTables();
        initFileSystem();
    }

    private void initFileSystem() {
        try {
            FileUtils.deleteDirectory(new File(getUtilFile().getBasePath()));
            Files.createDirectory(Paths.get(getUtilFile().getBasePath()));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void createTables(){
        getJdbcTemplate().execute(DROP_GIT_COMMIT_TABLE);
        getJdbcTemplate().execute(DROP_PROJECT_TABLE);
        getJdbcTemplate().execute(CREATE_PROJECT_TABLE);
        getJdbcTemplate().execute(CREATE_LOG_TABLE);
    }


    private JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    private UtilFile getUtilFile() {
        return utilFile;
    }
}
