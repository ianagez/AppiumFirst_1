import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LauncherApp {
    public static void main(String[] args) throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
//        caps.setCapability("enableMultiWindows", true);
//        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "HUAWEI P20 Pro");
//        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

//        caps.setCapability(MobileCapabilityType.UDID, "WCR7N19220002727");
        caps.setCapability(MobileCapabilityType.APP, "C:/Users/ianag/IdeaProjects/AppiumFirst/src/app/API Demos_4.0_apkcombo.com.apk");
        caps.setCapability("autoGrantPermissions", true);
//        caps.setCapability("appWaitDuration", 30000);

//        caps.setCapability("appPackage", "com.google.android.permissioncontroller");
//        caps.setCapability("appActivity", "com.android.permissioncontroller.permission.ui.ReviewPermissionsActivity");

//        caps.setCapability("appPackage", "com.spotify.music");
//        caps.setCapability("appActivity", "com.spotify.*");
//        caps.setCapability("autoGrantPermissions", true);


//        caps.setCapability("appPackage", "com.spotify.music");
//        caps.setCapability("appActivity", "com.spotify.login.loginflowimpl.LoginActivity");
//        caps.setCapability("appActivity", "com.spotify.activities.fragments.otherActivities.LoginActivity");
//        caps.setCapability(MobileCapabilityType.APP, "C:/Users/ianag/IdeaProjects/AppiumFirst/src/app/spotify.apk");


        URL url = new URL("http://127.0.0.1:4723/wd/hub");

//        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(url, caps);
        AppiumDriver<AndroidElement> driver = new AppiumDriver<AndroidElement>(url, caps);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.getPageSource();
//
//        AndroidElement el = driver.findElement(By.xpath("//*[@text=\"Sign up free\"]"));
//
//        el.click();
        driver.close();
        driver.closeApp();

    }
}
