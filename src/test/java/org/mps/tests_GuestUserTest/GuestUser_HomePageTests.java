package org.mps.tests_GuestUserTest;

import org.mps.annotations.FrameworkAnnotation;
import org.mps.base.BaseTest;
import org.mps.driver.DriverManager;
import org.mps.enums.CategoryType;
import org.mps.enums.JsonProperties;
import org.mps.enums.Priority;
import org.mps.enums.Severity;
import org.mps.listeners.ListenerClass;
import org.mps.pages.HomePage;
import org.mps.utils.JsonUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Listeners(value = ListenerClass.class)
public final class GuestUser_HomePageTests extends BaseTest {
    private GuestUser_HomePageTests() {
    }

    @FrameworkAnnotation(author = {"Akshay"}, category = {CategoryType.REGRESSION}, severity = {Severity.MAJOR}, priority = {Priority.MEDIUM})
    @Test(description = "Validating Home Page Redirectionality")
    public void homePageRedirectionTest() {
        String actualHomePageTitle = new HomePage().getHomePageTitle();
        assertThat(actualHomePageTitle)
                .isEqualTo(JsonUtils.getData(JsonProperties.HOMEPAGETITLE));
        System.out.println(DriverManager.getDriver().getTitle());
    }
}
