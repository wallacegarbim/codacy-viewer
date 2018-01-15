package com.codacy.challenge.commitviewer.dao;

import com.codacy.challenge.commitviewer.model.GitCommitLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GitCommitLogDao implements IGitCommitLogDao {

    private final JdbcTemplate jdbcTemplate;
    private Function<GitCommitLogEntity, Object[]> entityToObjectArray = gitLog -> new Object[]{ gitLog.getCommitId(), gitLog.getAuthor(), gitLog.getCommitDate(), gitLog.getComment(), gitLog.getProjectId()};

    @Autowired
    public GitCommitLogDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Collection<GitCommitLogEntity> gitCommitLogs) {

        List<Object[]> gitLogArray = gitCommitLogs.stream()
                .map(entityToObjectArray)
                .collect(Collectors.toList());

        getJdbcTemplate().batchUpdate(GitCommitLogEntity.INSERT_ALL_BATCH, gitLogArray);
    }

    @Override
    public Collection<GitCommitLogEntity> getAll() {
        return getJdbcTemplate().query(GitCommitLogEntity.SELECT_ALL, new BeanPropertyRowMapper<>(GitCommitLogEntity.class));
    }

    @Override
    public Collection<GitCommitLogEntity> getCommitLogByProjectId(Integer projectId) {
        return getJdbcTemplate().query(GitCommitLogEntity.SELECT_BY_PROJECT_ID, new Object[] {projectId}, new BeanPropertyRowMapper<>(GitCommitLogEntity.class));
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
