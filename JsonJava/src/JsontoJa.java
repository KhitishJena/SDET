import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsontoJa {
	
	public static void main(String[] args)
			throws ClassNotFoundException, SQLException, StreamWriteException, DatabindException, IOException, InterruptedException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "root");

		// object of statement class helps us to create querries
		Statement st = conn.createStatement();
		ResultSet rs = st
				.executeQuery("select * from CustomerInfo where Location ='Asia';");

		
		ArrayList<CustomerDetails> al = new ArrayList<CustomerDetails>();

		// rs.next returns a boolean value
		while (rs.next()) {
			CustomerDetails cd = new CustomerDetails();
			/*
			 * Using GETTER AND SETTER methods instead System.out.println(rs.getString(1));
			 * System.out.println(rs.getString(2)); System.out.println(rs.getInt(3));
			 * System.out.println(rs.getString(4));
			 */

			cd.setCourseName(rs.getString(1));
			cd.setPurchasedDate(rs.getString(2));
			cd.setAmount(rs.getInt(3));
			cd.setLocation(rs.getString(4));
			al.add(cd);

			/*
			 * System.out.println(cd.getCourseName());
			 * System.out.println(cd.getPurchasedDate());
			 * System.out.println(cd.getAmount()); System.out.println(cd.getLocation());
			 */

		}
		//System.out.println("****************^^^^^^^^^ "+al.get(2));
		
		// Object mapper writes the output into the JSON file.

		for (int i = 0; i < al.size(); i++) {
			ObjectMapper om = new ObjectMapper();
			
			//This writes the values to the JSON files and create one at the location specified
			// om.writeValue(new File("D:\\SDET\\JsonJava\\customerinfo.json"), cd);
			om.writeValue(new File("D:\\SDET\\JsonJava\\customerinfo"+i+".json"), al.get(i));

		}
		
		
		
		  Thread.sleep(10000); 
		  for(int i=0; i < al.size();i++) { 
			  String fp = "D:\\SDET\\JsonJava\\customerinfo"+i+".json"; 
			  File f = new File(fp);
		  f.delete(); 
		  if (!f.delete()) {
		  System.out.println("customerinfo"+i+".json"+" file deleted successfully"); 
		  }
		  }
		 
		conn.close();

	}
}
