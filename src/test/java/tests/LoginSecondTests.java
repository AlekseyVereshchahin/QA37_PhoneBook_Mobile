package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

public class LoginSecondTests extends AppiumConfig {

    @Test
    public void loginSuccess(){
        new AuthenticationScreen(driver)
                .fillEmail("pop@gmail.com")
                .fillPassword("Ppop12345$")
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginSuccessModel(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("pop@gmail.com").password("Ppop12345$").build())
                .submitLogin()
                .isAccountOpened()
                .logout();
    }
    @Test
    public void loginWrongEmail(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("popgmail.com").password("Ppop12345$").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }

}