package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginTest {

    @Test( dataProvider = "loginCredsData")
    public  void loginWithCorrectCreds(String username, String password) {
        DriverFactory.startAppiumServer();
        try{
            //Init driver
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            //Login page
            LoginPage loginPage = new LoginPage(androidDriver);
            //Bottom Nav Comp
            BottomNavComponent bottomNavComponent = loginPage.bottomNavComponent();
            bottomNavComponent.clickOnLoginLabel();

            //Fill Login Form
            loginPage
                    .inputUsername(username)
                    .inputPassword(password)
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
            //SoftAssert

//            String loginMsg_softAssert = loginPage.loginDialogComponent().msgTitle();
//            softAssert.assertEquals(loginMsg_softAssert,"success","[ERR] Login msg title incorrect");
//            String popupContent = loginPage.loginDialogComponent().msgContent();
//            boolean isContentPopupCorrect = popupContent.equals("You are logged in");
//            String customErrMsg = "[ERR] Login msg content incorrect";
//            softAssert.assertTrue(isContentPopupCorrect,customErrMsg);

        }catch(Exception e){
            e.printStackTrace();

        }finally {
            DriverFactory.stopAppiumServer();
        }

    }
    @DataProvider
    public Object[][] loginCredsData() {
        return new Object[][] {
                { "auto@mail.com", "12345678"},
                { "auto1@mail.com", "87654321"},
        };
    }

}
