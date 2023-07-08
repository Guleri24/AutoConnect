import com.guleri24.WinDriver;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URI;

public class WinAppDriverCloseTest {
    public String appPath = "C:\\Program Files\\Windows Application Driver\\WinAppDriver.exe";
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

    @Test(description = "Close WinAppDriver Window")
    public void closeWinApp() throws InterruptedException {
        Actions action = new Actions(driver);

        WebElement textArea = driver.findElementByName("Text Area");
        action.click(textArea);
        action.sendKeys(Keys.ENTER);

        action.perform();
    }

    @AfterTest
    public void tearDown() {
        WinDriver.stop();
        driver.quit();
    }
}
