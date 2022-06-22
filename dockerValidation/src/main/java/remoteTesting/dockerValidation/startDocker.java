package remoteTesting.dockerValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class startDocker {
	
	@Test
	public void startFile() throws IOException, InterruptedException {
		
		boolean flag = false;
		//Runtime is a static class helps us to invoke any file present on the Windows system
		
		Runtime runtime = Runtime.getRuntime();
		
		//runtime.exec("cmd /c start C:\\Users\\Khitish\\Desktop\\dockerUp.bat");
		
		runtime.exec("cmd /c start dockerUp.bat"); //the docker compose and bat file are in project location
		
		// >>serverlog.txt written in bat file to capture the logs in the serverlog file for further exam
		
		String serverlogFile = "serverlog.txt";

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 45);
		long stopnow = cal.getTimeInMillis();
		Thread.sleep(3000);
		// Bufferedreader class reads a file contents inside a file that is present in the external system
		BufferedReader reader = new BufferedReader(new FileReader(serverlogFile));
		
		while(System.currentTimeMillis()<stopnow) {
			
			if(flag) {
				break;
			}
			String currentLine = reader.readLine();
			
			while(currentLine != null && !flag) {
				
				if(currentLine.contains("registered to the hub and ready to use")) {
					
					System.out.println("found my text");
					flag = true;
					break;
				}
				currentLine = reader.readLine(); 				
			}
		}
		reader.close();	
		
		Assert.assertTrue(flag);
		
		runtime.exec("cmd /c start scale.bat");
		System.out.println("found my text*********");
		
		//http://localhost:4444/grid/console --check instance of chrome on docker
		
		Thread.sleep(15000);	
	}
	
}
