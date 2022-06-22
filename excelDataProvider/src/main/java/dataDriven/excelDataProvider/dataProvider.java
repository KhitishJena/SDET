package dataDriven.excelDataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvider {

	@DataProvider(name = "driveTest")
	public Object[][] getData() throws IOException {

		// Object is a superset of every thing, it can take all integers and strings as
		// well both

		/*
		 * this hard-coding the data in the code //Object[][] data =
		 * {{"Satish","TCS",10},{"Sonali","Bank",9},{"Khitish","CTS",2}}; return data;
		 */
		DataFormatter formatter = new DataFormatter();
		FileInputStream fis = new FileInputStream("D:\\SDET\\excelDataProvider\\data.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getLastCellNum();
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^" + rowCount);
		Object data[][] = new Object[rowCount - 1][colCount];
		for (int i = 0; i < rowCount - 1; i++) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^" + i);
			row = sheet.getRow(i + 1);
			for (int j = 0; j < colCount; j++) {

				XSSFCell cell = row.getCell(j);
				System.out.println(cell);
				data[i][j] = formatter.formatCellValue(cell);
			}
		}
		return data;
	}

	@Test(dataProvider = "driveTest")
	public void testCaseData(String name, String dept, String age) {

		System.out.println(name + dept + age);
	}

}
