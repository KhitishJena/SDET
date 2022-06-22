import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class extractJson {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException, ParseException {
		// TODO Auto-generated method stub

		ObjectMapper om = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("yyyy-mmm-dd");
		
		
		CustomerDetails cd = om.readValue(new File("D:\\SDET\\JsonJava\\customerinfo2.json"), CustomerDetails.class);
		
		
		System.out.println(cd.getCourseName());
		System.out.println(cd.getAmount());
		
		//first parse the string into date format, then covert into appropriate format
		System.out.println(cd.getPurchasedDate());
		Date date=df.parse(cd.getPurchasedDate());
		DateFormat df2=new SimpleDateFormat("dd-mm-yyyy");
		System.out.println("Formated Date: "+df2.format(date));
		
		System.out.println(cd.getLocation());
		
		
	}

}
