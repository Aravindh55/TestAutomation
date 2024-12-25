package Utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataread {

	public static Object[][] readData(String filename) throws IOException {
		
		FileInputStream fis = new FileInputStream("./uploadfiles/"+filename+".xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		Object[][] obj = new Object[lastRowNum][lastCellNum];
		
		for (int i = 1; i <= lastRowNum; i++) {
			
			XSSFRow row = sheet.getRow(i);
			
			for (int j = 0; j < lastCellNum; j++) {
				String stringCellValue = row.getCell(j).getStringCellValue();
				
				obj[i-1][j] = stringCellValue;
			}
		}
		wb.close();
		fis.close();
		return obj;
		
		
	}
}
