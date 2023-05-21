package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    // xpath="//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView"
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;
    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement menuOptions;
    @FindBy(xpath = "//*[@text='Logout']")
    AndroidElement logoutButton;
    @FindBy(xpath = "//*[@content-desc='add']")
    AndroidElement plusButton;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<AndroidElement> contactNameList;


    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<AndroidElement> contactList;

    @FindBy(id = "android:id/button1")
    AndroidElement yesButton;

    int countBefore;
    int countAfter;

    public ContactListScreen isListSizeLessThenOne() {
        Assert.assertEquals(countBefore - countAfter, 1);
        return this;
    }

    public ContactListScreen isListEmpty() {
        Assert.assertEquals(contactList.size(), 0);
        return this;
    }


    public ContactListScreen deleteFirstContact() {
        isActivityTitleDisplayed("Contact list");
        countBefore = contactList.size();
        System.out.println(countBefore);
        if(contactList.size()<3) {
            create3contactsForDeletion();
            }
        AndroidElement first = contactList.get(0);
        Rectangle rectangle = first.getRect();

        int xFrom = rectangle.getX() + rectangle.getWidth() / 8;
        int yFrom = rectangle.getY() + rectangle.getHeight() / 2;
        // int xTo=rectangle.getX()+(rectangle.getWidth()/8)*7;
        int xto = rectangle.getWidth() - xFrom;
        int yTo = yFrom;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom, yFrom))
                .moveTo(PointOption.point(xto, yTo))
                .release().perform();

        should(yesButton, 8);
        yesButton.click();
        shouldLessOne(contactList, countBefore);
        countAfter = contactList.size();
        System.out.println(countAfter);
        return this;
    }

    public ContactListScreen deleteAllContacts() {
        countBefore = contactList.size();
        if (contactList.size() < 3) {
            create3contactsForDeletion();
        }
        while (contactList.size() > 0) {
                isActivityTitleDisplayed("Contact list");
                AndroidElement first = contactList.get(0);
                Rectangle rectangle = first.getRect();

                int xFrom = rectangle.getX() + rectangle.getWidth() / 8;
                int yFrom = rectangle.getY() + rectangle.getHeight() / 2;
                // int xTo=rectangle.getX()+(rectangle.getWidth()/8)*7;
                int xto = rectangle.getWidth() - xFrom;
                int yTo = yFrom;

                TouchAction<?> touchAction = new TouchAction<>(driver);
                touchAction.longPress(PointOption.point(xFrom, yFrom))
                        .moveTo(PointOption.point(xto, yTo))
                        .release().perform();

                should(yesButton, 8);
                yesButton.click();
                System.out.println(contactList.size());
//                shouldLessOne(contactList, countBefore);
            }
        return this;
    }

    public ContactListScreen create3contactsForDeletion(){
        countBefore = contactList.size();
        if (countBefore < 3) {
            while (countBefore <3) {
                int i = new Random().nextInt(1000) + 1000;
                Contact contact = Contact.builder()
                        .name("Simona")
                        .lastName("Wowwww" + i)
                        .email("wow" + i + "@gmail.com")
                        .phone("6789456" + i)
                        .address("NY")
                        .description("The best friend")
                        .build();
                new ContactListScreen(driver)
                        .openContactForm()
                        .fillContactForm(contact)
                        .submitContactForm()
                        .isContactAddedByName(contact.getName(), contact.getLastName());
                countBefore = contactList.size();
            }
        }
        return this;
    }


    public ContactListScreen isContactAddedByName(String name, String lastName) {
        // List<AndroidElement> list =  driver.findElements(By.xpath(""));
        isShouldHave(activityTextView, "Contact list", 10);
        System.out.println("size of list" + contactNameList.size());

        boolean isPresent = false;

        for (AndroidElement el : contactNameList) {
            if (el.getText().equals(name + " " + lastName)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }


    public AddNewContactScreen openContactForm() {
        //  isShouldHave(activityTextView,"Contact list",20);
        if (activityTextView.getText().equals("Contact list"))
            plusButton.click();
        return new AddNewContactScreen(driver);
    }


    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public boolean isActivityTitleDisplayed(String text) {
        //return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 20);
    }

    public AuthenticationScreen logout() {
        if (activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AuthenticationScreen logout2() {
        if (isElementDispl(menuOptions)) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }
}