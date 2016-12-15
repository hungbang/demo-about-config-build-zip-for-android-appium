package appiumDemo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumTest {

	private static AppiumDriver<WebElement> driver;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability(MobileCapabilityType.APP_PACKAGE, "com.yammer.v1");
		cap.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.yammer.droid.ui.LauncherActivity");
		
		driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(800, TimeUnit.SECONDS);
		Thread.sleep(10000);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Test
	public void testLogin() throws InterruptedException, IOException {
		driver.findElement(By.name("Log In")).click();
		List<WebElement> textFieldList  = driver.findElements(By.className("android.widget.EditText"));
		textFieldList.get(0).sendKeys("gopikannan@xyz.com");
		textFieldList.get(1).sendKeys("asdfghjkl");
		driver.findElement(By.id("com.yammer.v1:id/login_button")).click();
		Thread.sleep(10000);
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("/Users/KAI/iamgeTest"));
	}

}
