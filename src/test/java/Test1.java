

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Test1 {
    public static void main(String[] args) throws Exception {

        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        cap.setCapability("appPackage", "io.appium.android.apis");
        cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(url, cap);


        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


//        AndroidTouchAction touch = new AndroidTouchAction(driver);
//        touch.tap(TapOptions.tapOptions().withElement(ElementOption.element(el))).perform();
//
//        LongPressOptions longPressOptions = new LongPressOptions();
//
//        longPressOptions.withDuration(Duration.ofSeconds(5)).withElement(ElementOption.element(el));
//
//        touch.longPress(longPressOptions).release().perform();


        driver.findElement(By.xpath("//android.widget.TextView[@text = 'Views']")).click();



        // scroll to element
             driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
                    ".scrollIntoView(new UiSelector().text(\"WebView3\"));")).click();

        swipeScreen(driver, Direction.UP);


//
//
//        AndroidTouchAction action = new AndroidTouchAction(driver);
////      action.tap(TapOptions.tapOptions().withElement(ElementOption.element(expandableList)));
//        AndroidElement customAdapter = driver.findElementByAndroidUIAutomator("text(\"1. Custom Adapter\")");
//        action = new AndroidTouchAction(driver);
//        action.tap(TapOptions.tapOptions().withElement(ElementOption.element(customAdapter))).perform();
//
//
        AndroidTouchAction touch = new AndroidTouchAction(driver);
        AndroidElement peopleNames = driver.findElement(By.xpath("//android.widget.TextView[@text = 'People Names']"));
        LongPressOptions longPressOptions= new LongPressOptions();
        longPressOptions.withDuration(Duration.ofSeconds(5)).withElement(ElementOption.element(peopleNames));
        touch.longPress(longPressOptions).release().perform();
    }


    public static void swipeScreen(AndroidDriver<AndroidElement> driver, Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = driver.manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }


}