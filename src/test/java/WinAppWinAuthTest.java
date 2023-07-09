import com.guleri24.WinDriver;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URI;

public class WinAppWinAuthTest {
    public static String password = "secret##";
    public String appPath = "C:\\Program Files (x86)\\WinAuth-package\\WinAuth.exe";
    WindowsDriver<WindowsElement> driver = null;

    @BeforeTest
    public void testSetup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("ms:experimental-webdriver", true);
        capabilities.setCapability("app", appPath);
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "Windows10Machine");

        WinDriver.start();

        driver = new WindowsDriver<>(new URI("http://127.0.0.1:4723/").toURL(), capabilities);
    }

    @Test(description = "Login and Get Authentication Code from WinAuth")
    public void getAuthCode() throws InterruptedException {
        Actions action = new Actions(driver);

        WebElement passwordField = driver.findElementByAccessibilityId("passwordField");
        action.click(passwordField);
        action.sendKeys(password).perform();

        WebElement okButton = driver.findElementByAccessibilityId("passwordButton");
        action.click(okButton).perform();

        Thread.sleep(2000);
        WebElement openDropDown = driver.findElementByAccessibilityId("WinAuthForm");
        action.contextClick(openDropDown).perform();

        WebElement getCode = driver.findElementByName("Copy Code");
        action.click(getCode).perform();

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            /* The instance of WinAppDriver will be freed once
            last test is complete
            */
            WinDriver.stop();
            driver.quit();
        }
    }

}
