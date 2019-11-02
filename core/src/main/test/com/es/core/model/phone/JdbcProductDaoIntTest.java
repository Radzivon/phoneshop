package com.es.core.model.phone;

import com.es.core.model.color.Color;
import com.es.core.model.color.ColorDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/context/applicationContext-core.xml")
@ComponentScan
public class JdbcProductDaoIntTest {
    @Resource
    private JdbcPhoneDao jdbcPhoneDao;
    @Resource
    private ColorDao colorDao;

    private int offset = 1;
    private int limit = 10;
    private Long testId = new Long(1000L);
    private Phone testPhone;

    @Before
    public void setup() {
        Set<Color> colors = colorDao.getColorsForPhoneById(testId).stream().collect(Collectors.toSet());
        testPhone = new Phone(testId, "ARCHOS", "ARCHOS 101 G9", null, new BigDecimal("10.1"), new Integer(482), new BigDecimal("276.0"), new BigDecimal("167.0"), new BigDecimal("12.6"), null, "Tablet", "Android (4.0)", colors, "1280 x  800", new Integer(149), null, null, new BigDecimal("1.3"), null, new BigDecimal("8.0"), 0, null, null, "2.1, EDR", "GPS", "manufacturer/ARCHOS/ARCHOS 101 G9.jpg", "The ARCHOS 101 G9 is a 10.1'' tablet, equipped with Google's open source OS. It offers a multi-core ARM CORTEX A9 processor at 1GHz, 8 or 16GB internal memory, microSD card slot, GPS, Wi-Fi, Bluetooth 2.1, and more.");
    }

    @Test
    public void testFindAll() {
        Assert.assertEquals(limit, jdbcPhoneDao.findAll(offset, limit).size());
    }
    @Ignore
    @Test
    public void testSave() {
        jdbcPhoneDao.save(testPhone);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveWithException() {
        Phone phone = null;
        jdbcPhoneDao.save(phone);
    }

    @Test
    public void testGet() {
        Assert.assertEquals(testPhone, jdbcPhoneDao.get(testId).get());
    }
}
