package org.mps.pages;

import org.mps.driver.DriverManager;
import org.mps.enums.WaitStrategy;
import org.mps.factories.ExplicitWaitFactory;
import org.mps.reports.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FunctionLibraryPage {

    protected void click(By by, WaitStrategy waitStrategy, String elementName) {
        WebElement element = ExplicitWaitFactory.performExpicitWait(waitStrategy, by);
        element.click();
        ExtentLogger.info(elementName + " is clicked successfully", true);
    }

    protected void sendKeys(By by, String value, WaitStrategy waitStrategy, String elementName) {
        WebElement element = ExplicitWaitFactory.performExpicitWait(waitStrategy, by);
        element.sendKeys(value);
        ExtentLogger.info(value + " is entered successfully in " + elementName, true);
    }

    protected String getPageTitle(String page) {
        ExtentLogger.info("Title of the " + page + " is : " + DriverManager.getDriver().getTitle(), true);
        return DriverManager.getDriver().getTitle();
    }

    protected String getText(By by, WaitStrategy waitStrategy, String elementName) {
        WebElement element = ExplicitWaitFactory.performExpicitWait(waitStrategy, by);
        ExtentLogger.info(elementName + " is : " + element.getText(), true);
        return element.getText();
    }

    public static String getDateAndTimeAndDay() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh-mm-ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        String dayOfTheWeek = new SimpleDateFormat("EEEE").format(date);
        return dayOfTheWeek + "_" + dateFormat.format(date) + "_" + timeFormat.format(calendar.getTime());
    }

    public static Date getTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh-mm-ss");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        return calendar.getTime();
    }

    //wait.until(d->d.findElement(link_logout).isEnabled())
}
