package com.codacy.challenge.commitviewer.mapper;

import com.codacy.challenge.commitviewer.model.ProjectEntity;
import org.springframework.jdbc.core.RowMapper;

public class ProjectEntityMapper {
    public static final RowMapper<ProjectEntity> projectEntityRowMapper =
            (rs, rowNum) -> ProjectEntity.getInstance(rs.getInt("project_id"), rs.getString("project_name"), rs.getString("url"), null);
}
