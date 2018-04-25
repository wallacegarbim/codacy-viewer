package com.codacy.challenge.commitviewer.dao;

import com.codacy.challenge.commitviewer.mapper.ProjectEntityMapper;
import com.codacy.challenge.commitviewer.model.ProjectEntity;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class ProjectDao implements IProjectDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(ProjectEntity entity) {
        return getJdbcTemplate().update(ProjectEntity.INSERT, entity.getProjectName(), entity.getUrl());
    }

    @Override
    public Collection<ProjectEntity> listAll() {
        return ImmutableList.copyOf(getJdbcTemplate().query(ProjectEntity.SELECT_ALL,
                ProjectEntityMapper.projectEntityRowMapper ));
    }

    @Override
    public Optional<ProjectEntity> getProjectByName(String projectName) {
        return Optional.of(getJdbcTemplate().queryForObject(ProjectEntity.SELECT_BY_NAME, new Object[] {projectName},
                ProjectEntityMapper.projectEntityRowMapper));
    }

    @Override
    public Integer isProjectExist(String projectName) {
        return getJdbcTemplate().queryForObject(ProjectEntity.COUNT_BY_NAME, Integer.class, projectName);
    }


    private JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
