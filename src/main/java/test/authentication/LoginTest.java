package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class LoginTest {
    private  SoftAssert softAssert;
    @BeforeClass
    public void beforeClass(){
        softAssert = new SoftAssert();
    }
    @AfterClass
    public void afterClass(){
        softAssert.assertAll();
    }

    @Test
    public  void a1loginWithCorrectCreds() {
        DriverFactory.startAppiumServer();
        try{
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            //Login page
            LoginPage loginPage = new LoginPage(androidDriver);
            //Bottom Nav Comp
            BottomNavComponent bottomNavComponent = loginPage.bottomNavComponent();
            bottomNavComponent.clickOnLoginLabel();

            //Fill Login Form
            loginPage
                    .inputUsername("autotest@mailinator.com")
                    .inputPassword("98765432")
                    .clickOnLoginBtn();
            //Verification
//            String loginMsg = loginPage.loginDialogComponent().msgTitle();
//            Assert.assertEquals(loginMsg,"Success","[ERR] Login msg title incorrect");
//            System.out.println(loginMsg);
//            String popupContent = loginPage.loginDialogComponent().msgContent();
//            boolean isContentPopupCorrect = popupContent.equals("You are logged in");
//            String customErrMsg = "[ERR] Login msg content incorrect";
//            Assert.assertTrue(isContentPopupCorrect,customErrMsg);
//            System.out.println(popupContent);
            //SoftAssert

            String loginMsg_softAssert = loginPage.loginDialogComponent().msgTitle();
            softAssert.assertEquals(loginMsg_softAssert,"success","[ERR] Login msg title incorrect");
            String popupContent = loginPage.loginDialogComponent().msgContent();
            boolean isContentPopupCorrect = popupContent.equals("You are logged in");
            String customErrMsg = "[ERR] Login msg content incorrect";
            softAssert.assertTrue(isContentPopupCorrect,customErrMsg);

        }catch(Exception e){
            e.printStackTrace();

        }finally {
            DriverFactory.stopAppiumServer();
        }

    }
    @Test (priority = 1, dependsOnMethods = {"a3Void"})
    void a2Void(){
        System.out.println("This should be executed first");
    }
    @Test (priority = 2)
    void a3Void(){
        Assert.assertTrue(true);
    }
}
