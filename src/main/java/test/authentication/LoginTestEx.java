package test.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
//import test_data.LoginCreds;

public class LoginTestEx extends BaseTest {

    @Test
    public  void loginWithCorrectCreds() {

            //Init driver
            AppiumDriver<MobileElement> androidDriver = getDriver();
            //Login page
            LoginPage loginPage = new LoginPage(androidDriver);
            //Bottom Nav Comp
            BottomNavComponent bottomNavComponent = loginPage.bottomNavComponent();
            bottomNavComponent.clickOnLoginLabel();

            //Fill Login Form
            loginPage
                    .inputUsername("auto@mail.com")
                    .inputPassword("12345678")
                    .clickOnLoginBtn();
            //VerificationLoginTest
            String loginMsg = loginPage.loginDialogComponent().msgTitle();
            Assert.assertEquals(loginMsg,"Success","[ERR] Login msg title incorrect");
            System.out.println(loginMsg);
            String popupContent = loginPage.loginDialogComponent().msgContent();
            boolean isContentPopupCorrect = popupContent.equals("You are logged in!");
            String customErrMsg = "[ERR] Login msg content incorrect";
            Assert.assertTrue(isContentPopupCorrect,customErrMsg);
            System.out.println(popupContent);
    }


}
