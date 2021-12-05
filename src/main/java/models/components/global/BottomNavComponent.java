package models.components.global;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By formsLabelSel = MobileBy.AccessibilityId("Forms");
    private static final By loginLabelSel = MobileBy.AccessibilityId("Login");
    private static final By swipeLabelSel = MobileBy.AccessibilityId("Swipe");


    public BottomNavComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    // Return Mobel Element
    public MobileElement loginLabelElem (){
        return appiumDriver.findElement(loginLabelSel);
    }
    public MobileElement formLabelElem (){
        return appiumDriver.findElement(formsLabelSel);
    }
    public MobileElement swipeLabelSel(){
        return appiumDriver.findElement(swipeLabelSel);
    }
    //Main interaction method
    public void clickOnLoginLabel(){
        appiumDriver.findElement(loginLabelSel).click();
    }
}
