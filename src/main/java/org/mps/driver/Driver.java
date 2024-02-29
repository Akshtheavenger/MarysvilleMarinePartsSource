package org.mps.driver;

import org.mps.enums.ConfigProperties;
import org.mps.utils.PropertyUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

//Consists of all the code related to browser initialization and quitting
public final class Driver {

    private Driver() {
    }

    public static void initDriver(String browser) {
        if (Objects.isNull(DriverManager.getDriver())) {
            if(browser.equalsIgnoreCase("chrome")){
                DriverManager.setDriver(new ChromeDriver());
            }
            else if(browser.equalsIgnoreCase("firefox")){
                DriverManager.setDriver(new FirefoxDriver());
            } else if (browser.equalsIgnoreCase("edge")) {
                DriverManager.setDriver(new EdgeDriver());
            }

            DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.URL));
            DriverManager.getDriver().manage().window().maximize();
        }
    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
