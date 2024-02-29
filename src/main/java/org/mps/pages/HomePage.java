package org.mps.pages;

import org.mps.enums.WaitStrategy;
import org.openqa.selenium.By;


public final class HomePage extends FunctionLibraryPage {

    private final By signInButton = By.xpath("//a[text()='Sign In']");

    private final By successMessage = By.xpath("//span[@data-ui-id='page-title-wrapper']");

    public LoginPage clickOnHeaderSignIn() {
        click(signInButton, WaitStrategy.CLICKABLE, "Header Sign In Button");
        return new LoginPage();
    }

    public String getLoggedOutSuccessMessage() {
        return getText(successMessage, WaitStrategy.PRESENCE,"Logged Out Success Message");
    }
    public String getHomePageTitle() {
        return getPageTitle("Home Page");
    }
}
