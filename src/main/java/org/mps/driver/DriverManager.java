package org.mps.driver;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class DriverManager {

    private DriverManager(){}

    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static void setDriver(WebDriver driverReference) {
        if(Objects.nonNull(driverReference)){
            threadLocalDriver.set(driverReference);
        }
    }
    public static void unload(){
        threadLocalDriver.remove();
    }
}
