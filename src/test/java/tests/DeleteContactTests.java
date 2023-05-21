package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class DeleteContactTests extends AppiumConfig {
    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("pop@gmail.com").password("Ppop12345$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");

    }
    @Test
    public void deleteFirstContact(){
        new ContactListScreen(driver)
                .deleteFirstContact()
                .isListSizeLessThenOne();
    }

    @Test
    public void deleteAllContacts(){
        new ContactListScreen(driver)
                .deleteAllContacts()
                .isListEmpty();

    }


}
