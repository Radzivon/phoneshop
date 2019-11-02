package com.es.core.model.color;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/context/applicationContext-core.xml")
public class ColorDaoTest {
    @Autowired
    private ColorDao colorDao;

    @Test
    public void testGetColorsForPhoneById() {
        Long id = new Long(1L);
        Assert.assertNotNull(colorDao.getColorsForPhoneById(id));
    }
}
