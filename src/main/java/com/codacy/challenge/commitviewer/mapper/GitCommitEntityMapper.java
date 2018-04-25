package com.codacy.challenge.commitviewer.mapper;

import com.codacy.challenge.commitviewer.model.GitCommitLogEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class GitCommitEntityMapper {
    public static Function<GitCommitLogEntity, Object[]> entityToObjectArray = gitLog -> new Object[]{ gitLog.getCommitId(), gitLog.getAuthor(), gitLog.getCommitDate(), gitLog.getComment(), gitLog.getProjectId()};
    public static final RowMapper<GitCommitLogEntity> gitCommitLogEntityRowMapper = (ResultSet rs, int rowNum) -> entityFromResultSet(rs);

    private static GitCommitLogEntity entityFromResultSet(ResultSet rs) throws SQLException {
        return GitCommitLogEntity.getInstance(rs.getString("commit_id"),
                rs.getString("author"), rs.getDate("commit_date"), rs.getString("comment"), rs.getInt("project_id"));
    }
}
