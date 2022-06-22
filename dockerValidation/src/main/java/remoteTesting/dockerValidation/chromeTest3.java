package remoteTesting.dockerValidation;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class chromeTest3 {

	@Test
	public void test3() throws MalformedURLException {
		// TODO Auto-generated method stub

		// when selenium TC are ran on remote, then RemoteWebDriver class is used to run
		// on remote

		URL url = new URL("http://localhost:4444/wd/hub");
		// DesiredCapabilities cap = DesiredCapabilities.chrome();
		//desiredCapabilities has been deprecated.
		ChromeOptions options = new ChromeOptions();
		RemoteWebDriver driver = new RemoteWebDriver(url, options);

		driver.get("http://facebook.com");
		System.out.println(driver.getTitle());
	}

}
