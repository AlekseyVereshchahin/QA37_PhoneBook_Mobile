package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.rmi.server.ExportException;

public class BaseScreen {

    AppiumDriver<AndroidElement>driver;

    public BaseScreen(AppiumDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void type(AndroidElement element, String text){
        if(text!=null){
            element.click();
            element.clear();
            element.sendKeys(text);
        }
        driver.hideKeyboard();
    }

    public boolean isShouldHave(AndroidElement element,String text,int time){
        return new WebDriverWait(driver,time).until(ExpectedConditions.textToBePresentInElement(element,text));
    }

    public void should(AndroidElement element, int time){
        new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOf(element));
    }
    public void checkAlertText(String text){
        Alert alert = new WebDriverWait(driver,10)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        System.out.println(alert.getText());
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();

    }
}