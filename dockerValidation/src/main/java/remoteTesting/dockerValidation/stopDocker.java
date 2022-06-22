package remoteTesting.dockerValidation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class stopDocker {

	@Test
	public void stopFile() throws IOException, InterruptedException {

		boolean flag = false;
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start dockerDown.bat");

		String f = "serverlog.txt";

		Calendar cal = Calendar.getInstance();// 2:44 15th second
		cal.add(Calendar.SECOND, 45);// 2:44 45seconds
		long stopnow = cal.getTimeInMillis();
		Thread.sleep(3000);
		BufferedReader reader = new BufferedReader(new FileReader(f));
		//Buffer reader has to be declared outside the while loop because the close output stream was not
		//getting executed because of the break statement in the while loop.
		while (System.currentTimeMillis() < stopnow) {
			if (flag) {
				break;
			}

			
			String currentLine = reader.readLine();
			while (currentLine != null && !flag)
			{
				if (currentLine.contains("selenium-hub exited")) {
					System.out.println("found my text");
					flag = true;// 14th seconds
					break;
				}

				currentLine = reader.readLine();
			}
		}
		reader.close();
		Assert.assertTrue(flag);

		
		Thread.sleep(5000);
		File fi = new File(f);
		if(fi.exists()) {
			System.out.println("****");
			fi.delete();
			if (fi.delete()) {
			 System.out.println("File deleted successfully"); 
			 }
		}
	}
}
