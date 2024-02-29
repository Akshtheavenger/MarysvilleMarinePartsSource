package org.mps.tests_ValidCustomerTest;

import org.mps.annotations.FrameworkAnnotation;
import org.mps.base.BaseTest;
import org.mps.enums.*;
import org.mps.listeners.ListenerClass;
import org.mps.pages.HomePage;
import org.mps.reports.ExtentLogger;
import org.mps.utils.PropertyUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Listeners(value = ListenerClass.class)
public final class RegisteredUser_LoginPageTests extends BaseTest {

    private RegisteredUser_LoginPageTests() {
    }

    @FrameworkAnnotation(author = {"Akshay"}, category = {CategoryType.REGRESSION}, severity = {Severity.MAJOR}, priority = {Priority.HIGHEST})
    @Test(description = "Validating login Functionality for a valid user")
    public void userLoginLogoutTest() {
        String loggedOutsuccessMessage = new HomePage().clickOnHeaderSignIn()
                .enterUserName(PropertyUtils.get(ConfigProperties.USERNAME)).enterPassword(PropertyUtils.get(ConfigProperties.PASSWORD))
                .clickOnSignInBtn()
                .clickOnSignOutBtn()
                .getLoggedOutSuccessMessage();
        assertThat(loggedOutsuccessMessage).isEqualTo("You are signed out");
        ExtentLogger.pass("Validated the actual success message after user sign out as " + loggedOutsuccessMessage, true);
    }
}
