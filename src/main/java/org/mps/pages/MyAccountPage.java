package org.mps.pages;

import org.mps.enums.WaitStrategy;
import org.mps.listeners.ListenerClass;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;

@Listeners(value = ListenerClass.class)
public class MyAccountPage extends FunctionLibraryPage {

    private final By signoutButton = By.xpath("//div[@class='customer-menu']//a[text()='Sign Out']");

    public HomePage clickOnSignOutBtn() {
        click(signoutButton, WaitStrategy.CLICKABLE, "Sign Out Button");
        return new HomePage();
    }
}
