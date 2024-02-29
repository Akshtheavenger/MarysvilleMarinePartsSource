package org.mps.factories;

import org.mps.constants.FrameworkConstants;
import org.mps.driver.DriverManager;
import org.mps.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaitFactory {

    public static WebElement performExpicitWait(WaitStrategy waitStrategy, By by){
        WebElement element = null;
        if (waitStrategy == WaitStrategy.CLICKABLE) {
         element =  new WebDriverWait(DriverManager.getDriver(),
                    Duration.ofSeconds(FrameworkConstants.getEXPLICITWAITDURATION())).until(ExpectedConditions.elementToBeClickable(by));

        } else if (waitStrategy == WaitStrategy.PRESENCE) {
            element = new WebDriverWait(DriverManager.getDriver(),
                    Duration.ofSeconds(FrameworkConstants.getEXPLICITWAITDURATION())).until(ExpectedConditions.presenceOfElementLocated(by));
        } else if(waitStrategy == WaitStrategy.VISIBILITY){
           element = new WebDriverWait(DriverManager.getDriver(),
                    Duration.ofSeconds(FrameworkConstants.getEXPLICITWAITDURATION())).until(ExpectedConditions.visibilityOfElementLocated(by));
        } else if(waitStrategy == WaitStrategy.NONE){
            element = DriverManager.getDriver().findElement(by);
        }
        return element;
    }


}
