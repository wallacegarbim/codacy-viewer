package com.codacy.challenge.commitviewer.dao;

import com.codacy.challenge.commitviewer.mapper.GitCommitEntityMapper;
import com.codacy.challenge.commitviewer.model.GitCommitLogEntity;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GitCommitLogDao implements IGitCommitLogDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GitCommitLogDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final Collection<GitCommitLogEntity> gitCommitLogs) {

        List<Object[]> gitLogArray = gitCommitLogs.stream()
                .map(GitCommitEntityMapper.entityToObjectArray)
                .collect(Collectors.toList());

        getJdbcTemplate().batchUpdate(GitCommitLogEntity.INSERT_ALL_BATCH, gitLogArray);
    }

    @Override
    public Collection<GitCommitLogEntity> getAll() {
        return ImmutableList.copyOf(getJdbcTemplate().query(GitCommitLogEntity.SELECT_ALL,
                GitCommitEntityMapper.gitCommitLogEntityRowMapper));
    }

    @Override
    public Collection<GitCommitLogEntity> getCommitLogByProjectId(final Integer projectId) {
        return ImmutableList.copyOf(getJdbcTemplate().query(GitCommitLogEntity.SELECT_BY_PROJECT_ID, new Object[] {projectId},
                GitCommitEntityMapper.gitCommitLogEntityRowMapper));
    }

    private JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
