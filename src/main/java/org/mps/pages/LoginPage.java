package org.mps.pages;

import org.mps.enums.WaitStrategy;
import org.mps.listeners.ListenerClass;
import org.mps.utils.DecodeUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;

@Listeners(value = ListenerClass.class)

public final class LoginPage extends FunctionLibraryPage {

    private final By inputFieldUsername = By.xpath("//div[@class='login-container']//input[@title='Email']");

    private final By inputFieldPassword = By.xpath("//div[@class='login-container']//input[@title='Password']");

    private final By buttonLogin = By.xpath("//div[@class='login-container']//button");


    public LoginPage enterUserName(String username) {
        sendKeys(inputFieldUsername, username, WaitStrategy.PRESENCE, "Username field");
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(inputFieldPassword, DecodeUtils.getDecodedString(password), WaitStrategy.PRESENCE, "Password field");
        return this;
    }

    public MyAccountPage clickOnSignInBtn() {
        click(buttonLogin, WaitStrategy.CLICKABLE, "Sign In Button");
        return new MyAccountPage();
    }
}