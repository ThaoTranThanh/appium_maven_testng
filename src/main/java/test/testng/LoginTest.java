package test.testng;

import org.testng.annotations.*;

public class LoginTest {
    @BeforeTest
    public void beforeTest(){
        System.out.println("LoginTest | beforeTest");
    }
    @BeforeClass
    public  void beforeClass(){
        System.out.println("LoginTest | beforeClass");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("LoginTest | afterClass");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("LoginTest | afterTest");
    }
    @BeforeMethod
    public void beforeMeThod(){
        System.out.println("beforeMeThod");
    }

    @Test
    public void loginCorrectCreds(){
        System.out.println("loginCorrectCreds");
    }
    @Test
    public void loginInCorrectCreds(){
        System.out.println("loginInCorrectCreds");
    }

}
