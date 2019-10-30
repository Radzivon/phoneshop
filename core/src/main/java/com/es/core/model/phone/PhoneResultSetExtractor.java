package com.es.core.model.phone;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class PhoneResultSetExtractor implements ResultSetExtractor<List<Phone>> {
    @Override
    public List<Phone> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Phone, Set<Color>> data = new LinkedHashMap<>();
        while (resultSet.next()) {
            Phone phone = new Phone();
            phone.setBrand(resultSet.getString("brand"));
            phone.setModel(resultSet.getString("model"));
            phone.setPrice(resultSet.getBigDecimal("price"));
            phone.setDisplaySizeInches(resultSet.getBigDecimal("displaySizeInches"));
            phone.setWeightGr(resultSet.getInt("weightGr"));
            phone.setLengthMm(resultSet.getBigDecimal("lengthMm"));
            phone.setWidthMm(resultSet.getBigDecimal("widthMm"));
            phone.setHeightMm(resultSet.getBigDecimal("heightMm"));
            phone.setAnnounced(resultSet.getDate("announced"));
            phone.setDeviceType(resultSet.getString("deviceType"));
            phone.setOs(resultSet.getString("os"));
            phone.setDisplayResolution(resultSet.getString("displayResolution"));
            phone.setPixelDensity(resultSet.getInt("pixelDensity"));
            phone.setDisplayTechnology(resultSet.getString("displayTechnology"));
            phone.setBackCameraMegapixels(resultSet.getBigDecimal("backCameraMegapixels"));
            phone.setFrontCameraMegapixels(resultSet.getBigDecimal("frontCameraMegapixels"));
            phone.setRamGb(resultSet.getBigDecimal("ramGb"));
            phone.setInternalStorageGb(resultSet.getBigDecimal("internalStorageGb"));
            phone.setBatteryCapacityMah(resultSet.getInt("batteryCapacityMah"));
            phone.setTalkTimeHours(resultSet.getBigDecimal("talkTimeHours"));
            phone.setStandByTimeHours(resultSet.getBigDecimal("standByTimeHours"));
            phone.setBluetooth(resultSet.getString("bluetooth"));
            phone.setPositioning(resultSet.getString("positioning"));
            phone.setImageUrl(resultSet.getString("imageUrl"));
            phone.setDescription(resultSet.getString("description"));

            data.putIfAbsent(phone, new HashSet<>());

            Color color = new Color();
            color.setId(resultSet.getLong("id"));
            color.setCode("code");

            data.get(phone).add(color);
        }
        data.entrySet().stream().forEach(phoneSetEntry -> phoneSetEntry.getKey().setColors(phoneSetEntry.getValue()));
        return data.keySet().stream().collect(Collectors.toList());
    }
}
