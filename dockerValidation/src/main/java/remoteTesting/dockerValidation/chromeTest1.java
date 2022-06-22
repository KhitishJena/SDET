package remoteTesting.dockerValidation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class chromeTest1 {

	@BeforeTest
	public void startDockerScale() throws IOException, InterruptedException{
		
		File fi = new File("serverlog.txt");
		fi.delete();
		if (fi.delete()) {
			System.out.println("File deleted successfully");
		}
		
		startDocker sd = new startDocker();
		sd.startFile();
	}
	
	@AfterTest
	public void stopDockerDelete() throws IOException, InterruptedException {
		stopDocker sd = new stopDocker();
		sd.stopFile();
	}
	
	@Test
	public void test1() throws MalformedURLException {
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

		driver.get("http://google.com");
		System.out.println(driver.getTitle());
	}

}
