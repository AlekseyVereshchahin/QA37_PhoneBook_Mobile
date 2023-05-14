package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.Auth;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AutenticationScreen extends BaseScreen{
    public AutenticationScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextViewAuth;

    @FindBy(xpath ="//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    AndroidElement emailEditText;


    @FindBy(id ="com.sheygam.contactapp:id/inputPassword")
    AndroidElement passwordEditText;


    @FindBy(xpath ="//*[@text='LOGIN']") //in apps text is attribute --> @
//    @FindBy(xpath ="//*[text()='LOGIN']") not work
    AndroidElement loginButton;

    public AutenticationScreen fillLoginRegistrationForm(Auth auth){
        should(emailEditText,10);
        type(emailEditText, auth.getEmail());
        type(passwordEditText, auth.getPassword());
        return this;
    }

    public AutenticationScreen fillEmail(String email){
        should(emailEditText,10);
        type(emailEditText,email);
        return this;
    }
    public AutenticationScreen fillPassword(String password){
        type(passwordEditText,password);
        return this;
    }

    public ContactListScreen submitLogin(){
        loginButton.click();
        return new ContactListScreen(driver);
    }

    public AutenticationScreen submitLoginNegative(){
        loginButton.click();
        return this;
    }

    public AutenticationScreen isErrorMessageContainsText(String text){
        Alert alert= new WebDriverWait(driver,10)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();
        return this;
    }

    public boolean isActivityTitleDisplayed(String text){

        //return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextViewAuth,text,10);
    }


}
