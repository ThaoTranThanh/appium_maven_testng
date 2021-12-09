package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.LoginCreds;
import test_data.authentication.DataObjectBuilder;


public class LoginTest_ReadFile {

    @Test( dataProvider = "loginCredsData")
    public  void loginWithCorrectCreds(LoginCreds loginCreds) {
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
                    .inputUsername(loginCreds.getUsername())
                    .inputPassword(loginCreds.getPassword())
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
    public LoginCreds[] loginCredsData() {
        String jsonLoc = "/src/main/resources/test-data/loginCreds.json";
        return DataObjectBuilder.buildDataObject(jsonLoc,LoginCreds[].class);

    }

}
