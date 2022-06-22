package seleniumGrid.grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class test3 {
	@Test
	public void test1() throws MalformedURLException {
		
		URL u = new URL("http://192.168.0.103:4444");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("firefox");
		RemoteWebDriver driver = new RemoteWebDriver(u,cap);
		
		driver.get("https://www.facebook.com");
		System.out.println(driver.getTitle());
		driver.close();
		
	}

}
