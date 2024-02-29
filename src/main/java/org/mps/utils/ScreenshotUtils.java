package org.mps.utils;

import org.mps.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static String getBase64Screenshot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
