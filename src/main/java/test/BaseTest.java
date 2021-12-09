package test;

import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseTest {
    //Thread-safe
    // SynchronizedList
    //LocalThread | isolate appium threads
    private final static List<DriverFactoryEx> webThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static  ThreadLocal<DriverFactoryEx> driverThread;
    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite(){
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactoryEx webDriverThread = new DriverFactoryEx();
            webThreadPool.add(webDriverThread);
            return webDriverThread;
        });

    }
    @AfterSuite(alwaysRun = true)
    public   void afterSuite(){
        for (DriverFactoryEx webDriverThread : webThreadPool){
            webDriverThread.quitAppiumSession();
        }
    }
    public static AppiumDriver<MobileElement> getDriver(){
        return driverThread.get().getAppiumDriver();
    }
    //TODO: This can be enum type

//    public  static  AppiumDriver<MobileElement> getDriver(String mobileDriverName){
//        return driverThread.get().getAppiumDriver(browserName);
//    }
    //TODO: capture screen and attach into report
//    @AfterMethod(alwaysRun = true)
//    public void afterMethod(ITestResult result){
//
//    }
}
