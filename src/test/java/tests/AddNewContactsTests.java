package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactsTests extends AppiumConfig {
    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("pop@gmail.com").password("Ppop12345$").build())
                .submitLogin();

    }

    @Test
    public void createNewContactSuccess(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Simona")
                .lastName( "Wowwww"+i)
                .email("wow"+i+"@gmail.com")
                .phone("6789456"+i)
                .address("NY")
                .description("The best friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastName());
    }

    @Test
    public void createContactWithEmptyName(){
        Contact contact = Contact.builder()
                .lastName( "Dow")
                .email("dow@gmail.com")
                .phone("678945633333")
                .address("NY")
                .description("Empty name")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{name=must not be blank}");


    }

    @Test
    public void createContactWithEmptyLastName(){
        Contact contact = Contact.builder()
                .name( "John")
                .email("dow@gmail.com")
                .phone("678945633333")
                .address("NY")
                .description("Empty lastname")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{lastName=must not be blank}");


    }

    @Test
    public void createContactWithEmptyEmail(){
        Contact contact = Contact.builder()
                .name( "John")
                .lastName("Dow")
                .phone("678945633333")
                .address("NY")
                .description("Empty email")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{email=must not be blank}");


    }

    @Test
    public void createContactWithEmptyPhone(){
        Contact contact = Contact.builder()
                .name( "John")
                .lastName("Dow")
                .email("dow@gmail.com")
                .address("NY")
                .description("Empty phone")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{phone=Phone number must contain only digits! And length min 10, max 15!}");


    }

    @Test
    public void createContactWithEmptyAddress(){
        Contact contact = Contact.builder()
                .name( "John")
                .lastName("Dow")
                .email("dow@gmail.com")
                .phone("678945633333")
                .description("Empty address")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{address=must not be blank}");


    }

    @Test
    public void createContactWithEmptyDescription(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name( "John"+i)
                .lastName("Dow"+i)
                .email("dow"+i+"@gmail.com")
                .phone("67894563"+i)
                .address("empty Description")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastName());

    }
    @Test
    public void createNewContactSuccessReq(){

    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver)
                .logout();
    }
}