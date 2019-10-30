package com.es.core.model.phone;

import com.es.core.model.color.Color;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class JdbcPhoneDao implements PhoneDao {
    private static final String SQL_SAVE_PHONE =
            "insert into phones (brand, model, price, displaySizeInches, weightGr, lengthMm, widthMm, heightMm, announced, deviceType, os, displayResolution, pixelDensity, displayTechnology, backCameraMegapixels, frontCameraMegapixels, ramGb, internalStorageGb, batteryCapacityMah, talkTimeHours, standByTimeHours, bluetooth, positioning, imageUrl, description)" +
                    "values " +
                    "(:brand, :model, :price, :displaySizeInches, :weightGr, :lengthMm, :widthMm, :heightMm, :announced, :deviceType, :os, :displayResolution, :pixelDensity, :displayTechnology, :backCameraMegapixels, :frontCameraMegapixels, :ramGb, :internalStorageGb, :batteryCapacityMah, :talkTimeHours, :standByTimeHours, :bluetooth, :positioning, :imageUrl, :description)";
    private static final String SQL_SAVE_COLORS = "insert into phone2color (code, id) values (?, ?)";
    private static final String SQL_GET_PHONE_BY_ID = "select * from phones where id = ?";
    private static final String SQL_GET_ALL_PHONES = "select * from phones";
    @Resource
    private JdbcTemplate jdbcTemplate;

    public Optional<Phone> get(final Long key) {
        List<Phone> phones = jdbcTemplate.query(SQL_GET_PHONE_BY_ID, new Object[]{key}, new PhoneRowMapper());

        return phones.stream().findFirst();
    }

    public void save(final Phone phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Incorrect data");
        }
        Set<Color> colors = phone.getColors();

        colors.forEach(color -> jdbcTemplate.update(SQL_SAVE_COLORS, color.getId(), color.getCode()));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("brand", phone.getBrand());
        paramMap.put("model", phone.getModel());
        paramMap.put("price", phone.getPrice());
        paramMap.put("displaySizeInches", phone.getDisplaySizeInches());
        paramMap.put("weightGr", phone.getWeightGr());
        paramMap.put("lengthMm", phone.getLengthMm());
        paramMap.put("widthMm", phone.getWidthMm());
        paramMap.put("heightMm", phone.getHeightMm());
        paramMap.put("announced", phone.getAnnounced());
        paramMap.put("deviceType", phone.getDeviceType());
        paramMap.put("os", phone.getOs());
        paramMap.put("displayResolution", phone.getDisplayResolution());
        paramMap.put("pixelDensity", phone.getPixelDensity());
        paramMap.put("displayTechnology", phone.getDisplayTechnology());
        paramMap.put("backCameraMegapixels", phone.getBackCameraMegapixels());
        paramMap.put("frontCameraMegapixels", phone.getFrontCameraMegapixels());
        paramMap.put("ramGb", phone.getRamGb());
        paramMap.put("internalStorageGb", phone.getInternalStorageGb());
        paramMap.put("batteryCapacityMah", phone.getBatteryCapacityMah());
        paramMap.put("talkTimeHours", phone.getTalkTimeHours());
        paramMap.put("standByTimeHours", phone.getStandByTimeHours());
        paramMap.put("bluetooth", phone.getBluetooth());
        paramMap.put("positioning", phone.getPositioning());
        paramMap.put("imageUrl", phone.getImageUrl());
        paramMap.put("description", phone.getDescription());

        jdbcTemplate.update(SQL_SAVE_PHONE, paramMap);
    }

    public List<Phone> findAll(int offset, int limit) {
        return jdbcTemplate.query(SQL_GET_ALL_PHONES + " offset " + offset + " limit " + limit, new PhoneRowMapper());
    }
}
