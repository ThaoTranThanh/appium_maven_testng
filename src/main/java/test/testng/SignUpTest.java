package test.testng;

import org.testng.annotations.*;

public class SignUpTest {
    @BeforeTest
    public void beforeTest(){
        System.out.println("SignUpTest | beforeTest");
    }
    @BeforeClass
    public  void beforeClass(){
        System.out.println("SignUpTest | beforeClass");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("SignUpTest | afterClass");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("SignUpTest | afterTest");
    }
    @Test
    public void signupWithCorrectEmail(){
        System.out.println("signupWithCorrectEmail");
    }
    @Test
    public void signupWithInCorrectEmail(){
        System.out.println("signupWithInCorrectEmail");
    }

}
