package driver;

import caps.MobileCapabilityTypeEx;
import flags.AndroidServerFlagEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class DriverFactoryEx {
    private static AppiumDriverLocalService appiumServer;
    private AppiumDriver<MobileElement> appiumDriver;

    public  AppiumDriver<MobileElement> getAppiumDriver() {
        if (appiumDriver == null)
            appiumDriver = initAppiumDriver();
            return appiumDriver;

    }
    private AppiumDriver<MobileElement> initAppiumDriver(){
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withArgument(AndroidServerFlagEx.ALLOW_INSECURE," chromedriver_autodownload");
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
        appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumServer.start();

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME,"Android");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME,"uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID,"RF8RA15CHBE");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY,"com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE,"com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT,120);
        appiumDriver = new AndroidDriver<>(appiumServer.getUrl(),desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
        return appiumDriver;
    }
    public void quitAppiumSession(){
        if(appiumDriver != null){
            appiumDriver.quit();
            appiumDriver = null;
            //Note: When we have final infratructure for parallel testing, this one is not necessary
            stopAppiumServer();
        }
    }
//    public static void startAppiumServer(){
//        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
//        appiumServiceBuilder.withArgument(AndroidServerFlagEx.CHROMEDRIVER_EXECUTABLE,
//                System.getProperty("user.dir").concat("/src/driver/chromedriver"));
//        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
//        appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
//        appiumServer.start();
//    }
    public static void stopAppiumServer(){
        String killNodeWindowsCmd = "taskkill /F/IN node.exe";
        String killNodeLinuxCmd = "Killall node";
        String killNode = null;
        String currentOS = System.getProperty("os.name").toLowerCase();
        String killNodeCmd = currentOS.startsWith("windows") ? killNodeWindowsCmd :killNodeLinuxCmd;
        Runtime runtime =Runtime.getRuntime();
        try{
            runtime.exec(killNodeCmd);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
//    public static AndroidDriver<MobileElement> getAndroidDriver() throws MalformedURLException {
//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME,"Android");
//        desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME,"uiautomator2");
//        desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID,"RF8RA15CHBE");
//        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY,"com.wdiodemoapp.MainActivity");
//        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE,"com.wdiodemoapp");
//        desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT,120);
//        androidDriver = new AndroidDriver<>(appiumServer.getUrl(),desiredCapabilities);
//        androidDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
//        return androidDriver;
//    }
}
