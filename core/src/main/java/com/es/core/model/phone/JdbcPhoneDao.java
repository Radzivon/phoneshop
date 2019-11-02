package com.es.core.model.phone;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcPhoneDao implements PhoneDao {

    private static final String SQL_SAVE_PHONE =
            "merge into phones (brand, model, price, displaySizeInches, weightGr, lengthMm, widthMm, heightMm, " +
                    "announced, deviceType, os, displayResolution, pixelDensity, displayTechnology, backCameraMegapixels, " +
                    "frontCameraMegapixels, ramGb, internalStorageGb, batteryCapacityMah, talkTimeHours, standByTimeHours, " +
                    "bluetooth, positioning, imageUrl, description)" +
                    " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_GET_PHONE_BY_ID = "select * from phones where id = ?";
    private static final String SQL_GET_ALL_PHONES = "select * from phones";
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private PhoneRowMapper phoneRowMapper;

    public Optional<Phone> get(final Long key) {
        List<Phone> phones = jdbcTemplate.query(SQL_GET_PHONE_BY_ID, new Object[]{key}, phoneRowMapper);

        return phones.stream().findFirst();
    }

    public void save(final Phone phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Incorrect data");
        }

        jdbcTemplate.update(SQL_SAVE_PHONE,
                phone.getBrand(),
                phone.getModel(),
                phone.getPrice(),
                phone.getDisplaySizeInches(),
                phone.getWeightGr(),
                phone.getLengthMm(),
                phone.getWidthMm(),
                phone.getHeightMm(),
                phone.getAnnounced(),
                phone.getDeviceType(),
                phone.getOs(),
                phone.getDisplayResolution(),
                phone.getPixelDensity(),
                phone.getDisplayTechnology(),
                phone.getBackCameraMegapixels(),
                phone.getFrontCameraMegapixels(),
                phone.getRamGb(),
                phone.getInternalStorageGb(),
                phone.getBatteryCapacityMah(),
                phone.getTalkTimeHours(),
                phone.getStandByTimeHours(),
                phone.getBluetooth(),
                phone.getPositioning(),
                phone.getImageUrl(),
                phone.getDescription());

    }

    public List<Phone> findAll(int offset, int limit) {
        return jdbcTemplate.query(SQL_GET_ALL_PHONES + " offset " + offset + " limit " + limit, phoneRowMapper);
    }
}
