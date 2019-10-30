package com.es.core.model.color;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ColorDaoImpl implements ColorDao {
    private static final String SQL_SELECT_COLORS = "select * from color join phone2color on phone2color.phoneId = colors.id where phone2color.phoneId = ?";
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    ColorRowMapper colorRowMapper;

    @Override
    public List<Color> getColorsForPhoneById(Long key) {
        return jdbcTemplate.query(SQL_SELECT_COLORS,new Object[]{key}, colorRowMapper);
    }


}
