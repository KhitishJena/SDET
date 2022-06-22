package remoteTesting.dockerValidation;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class chromeTest2 {

	@Test
	public void test2() throws MalformedURLException {
		// TODO Auto-generated method stub

		// when selenium TC are ran on remote, then RemoteWebDriver class is used to run
		// on remote
		URL url = new URL("http://localhost:4444/wd/hub");
		//DesiredCapabilities cap = new DesiredCapabilities();
		//desiredCapabilities has been deprecated.
		ChromeOptions options = new ChromeOptions();
		//options.merge(cap); //this merges the desired capabilities options n chrome options
		//cap.setBrowserName("chrome");
		RemoteWebDriver driver = new RemoteWebDriver(url, options);
		
		driver.get("http://gmail.com");
		System.out.println(driver.getTitle());
	}

}
