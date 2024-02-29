package org.mps.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.mps.driver.DriverManager;
import org.mps.enums.ConfigProperties;
import org.mps.utils.PropertyUtils;
import org.mps.utils.ScreenshotUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Base64;

public final class ExtentLogger {

    private ExtentLogger() {
    }

    public static void pass(String message) {
        ExtentManager.getExtentTest().pass(message);
    }

    public static void fail(String message) {
        ExtentManager.getExtentTest().fail(message);
    }

    public static void info(String message) {
        ExtentManager.getExtentTest().info(message);
    }

    public static void skip(String message) {
        ExtentManager.getExtentTest().skip(message);
    }

    public static void pass(String message, Boolean isScreenShotNeeded) {
        if (PropertyUtils.get(ConfigProperties.PASSEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
                && isScreenShotNeeded) {
            ExtentManager.getExtentTest().pass(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Screenshot()).build());
        } else {
            pass(message);
        }
    }

    public static void fail(String message, Boolean isScreenShotNeeded) {
        if (PropertyUtils.get(ConfigProperties.FAILEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
                && isScreenShotNeeded) {
            ExtentManager.getExtentTest().fail(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Screenshot()).build());
        } else {
            fail(message);
        }
    }

    public static void skip(String message, Boolean isScreenShotNeeded) {
        if (PropertyUtils.get(ConfigProperties.SKIPPEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
                && isScreenShotNeeded) {
            ExtentManager.getExtentTest().skip(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Screenshot()).build());
        } else {
            skip(message);
        }
    }

    public static void info(String message, Boolean isScreenShotNeeded) {
        if (PropertyUtils.get(ConfigProperties.INFOSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
                && isScreenShotNeeded) {
            ExtentManager.getExtentTest().info(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Screenshot()).build());
        } else {
            info(message);
        }
    }
}
