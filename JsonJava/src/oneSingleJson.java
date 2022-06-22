import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class oneSingleJson {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, StreamWriteException,
			DatabindException, IOException, InterruptedException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "root");

		// object of statement class helps us to create queries
		Statement st = conn.createStatement();
		ResultSet rs = st
				.executeQuery("select * from CustomerInfo where Location ='Asia';");

		ArrayList<CustomerDetails> al = new ArrayList<CustomerDetails>();

		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();

		// rs.next returns a boolean value
		while (rs.next()) {
			CustomerDetails cd = new CustomerDetails();

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

		for (int i = 0; i < al.size(); i++) {
			Gson g = new Gson();
			// Gson is a Java library that can be used to convert Java Objects into their
			// JSON representation, as Java objects cannot be printed
			String jsonString = g.toJson(al.get(i));
			System.out.println("\n" + jsonString);
			ja.add(jsonString);
		}

		// ***********HERE IT CREATES DATA OF JSON IN RAW FORMAT*************
		jo.put("DATA", ja);
		System.out.println("\n" + jo.toJSONString());

		String unescapedString = StringEscapeUtils.unescapeJava(jo.toJSONString());
		System.out.println("The unescape string is: \n" + unescapedString);
		String string1 = unescapedString.replace("\"{", "{");
		System.out.println("the string1 is " + "\n" + string1);
		String finalString = string1.replace("}\"", "}");
		System.out.println("\n" + finalString);

		try (FileWriter file = new FileWriter("D:\\SDET\\JsonJava\\combinedJSON.json")) {
			
			file.write(finalString);
		}

		Thread.sleep(10000);
		// *****************DELETE THE FILE****************

		String filepath = "D:\\SDET\\JsonJava\\combinedJSON.json";
		File f = new File(filepath);
		f.delete();
		System.out.println("\n" + "combinedJSON.json file deleted successfully");

		conn.close();

	}
}