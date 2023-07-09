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

public class WinAppAnyConnectTest {
    public static String password = "secret";
    public String appPath = "C:\\Program Files (x86)\\Cisco\\Cisco AnyConnect Secure Mobility Client\\vpnui.exe";
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

    @Test(description = "Login and enable AnyConnect VPN using WinAuth Authentication Code")
    public void enableVPN() throws InterruptedException {
        Actions action = new Actions(driver);

        WebElement connectButton = driver.findElementByName("Connect");
        action.click(connectButton);
        action.perform();
        Thread.sleep(3000);

        WebElement passcodeField = driver.findElementByName("Passcode:");
        action.click(passcodeField);
        action.sendKeys(password).perform();

        WebElement okButton = driver.findElementByName("OK");
        action.click(okButton).perform();
    }

    @AfterTest
    public void tearDown() {
            WinDriver.stop();
            driver.quit();
    }
}
