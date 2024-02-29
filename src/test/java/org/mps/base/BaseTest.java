package org.mps.base;

import org.mps.driver.Driver;
import org.mps.enums.ConfigProperties;
import org.mps.utils.PropertyUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected BaseTest() {
    }

    @BeforeMethod
    protected void setUp() {
        Driver.initDriver(PropertyUtils.get(ConfigProperties.BROWSER));
    }

    @AfterMethod
    protected void tearDown() {
        Driver.quitDriver();
    }

}
