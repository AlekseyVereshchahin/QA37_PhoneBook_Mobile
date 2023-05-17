package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    @Test
    public void loginSuccess() {
        boolean result = new AuthenticationScreen(driver)
                .fillEmail("pop@gmail.com")
                .fillPassword("Ppop12345$")
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessModel() {
        //boolean result = new Splashcreen(driver)
        boolean result = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("pop@gmail.com").password("Ppop12345$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }




    @Test
    public void loginWrongEmail() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("popgmail.com").password("Ppop12345$").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }

    @Test
    public void loginWrongPassword() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("pop@gmail.com").password("Ppop12345").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }

    @AfterMethod
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }

}
