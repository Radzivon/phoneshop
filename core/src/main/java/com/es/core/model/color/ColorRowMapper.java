package com.es.core.model.color;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class ColorRowMapper implements RowMapper {
    @Override
    public Color mapRow(ResultSet resultSet, int i) throws SQLException {
        Color color = new Color();
        color.setId(resultSet.getLong("id"));
        color.setCode(resultSet.getString("code"));
        return color;
    }
}
