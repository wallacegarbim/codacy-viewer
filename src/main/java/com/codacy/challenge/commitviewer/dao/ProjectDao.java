package com.codacy.challenge.commitviewer.dao;

import com.codacy.challenge.commitviewer.model.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        return getJdbcTemplate().query(ProjectEntity.SELECT_ALL, new BeanPropertyRowMapper<>(ProjectEntity.class));
    }

    @Override
    public Optional<ProjectEntity> getProjectByName(String projectName) {
        return Optional.of(getJdbcTemplate().queryForObject(ProjectEntity.SELECT_BY_NAME, new Object[] {projectName}, new BeanPropertyRowMapper<>(ProjectEntity.class)));
    }

    @Override
    public Integer isProjectExist(String projectName) {
        return getJdbcTemplate().queryForObject(ProjectEntity.COUNT_BY_NAME, Integer.class, projectName);
    }


    private JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
