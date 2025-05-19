package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "loginData")
	public Object[][] loginData() {

		String filepath = "./testdata/TestData.xlsx";
		String sheetname = "OpenCart-Login";
		ExcelUtils xlutils = new ExcelUtils(filepath);
		int totalRows = xlutils.getRowCount(sheetname);
		int totalCols = xlutils.getCellCount(sheetname, 1);

		String[][] loginData = new String[totalRows][totalCols];

		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				loginData[i - 1][j] = xlutils.getCellValue(sheetname, i, j);
			}
		}

		return loginData;
	}

}
