package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static FileInputStream fins;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static XSSFCellStyle style;
	String filepath;

	public ExcelUtils(String path) {
		this.filepath = path;
	}

	public int getRowCount(String sheetName) {
		int totalRows = 0;
		try {

			fins = new FileInputStream(filepath);
			workbook = new XSSFWorkbook(fins);
			sheet = workbook.getSheet(sheetName);
			totalRows = sheet.getLastRowNum();
			workbook.close();
			fins.close();

		} catch (Exception e) {

			System.out.println("getRowCount FAILED");
			e.printStackTrace();
		}
		return totalRows;
	}

	public int getCellCount(String sheetName, int rownum) {
		int totalCells = 0;
		try {

			fins = new FileInputStream(filepath);
			workbook = new XSSFWorkbook(fins);
			sheet = workbook.getSheet(sheetName);
			totalCells = sheet.getRow(rownum).getLastCellNum();
			workbook.close();
			fins.close();

		} catch (Exception e) {
			System.out.println("getCellCount FAILED");
			e.printStackTrace();
		}
		return totalCells;
	}

	public String getCellValue(String sheetName, int rownum, int cellnum) {
		String colData;
		try {

			fins = new FileInputStream(filepath);
			workbook = new XSSFWorkbook(fins);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rownum);
			cell = row.getCell(cellnum);
			DataFormatter formatter = new DataFormatter();
			colData = formatter.formatCellValue(cell);
			workbook.close();
			fins.close();

		} catch (Exception e) {
			colData = "";
			System.out.println("getCellValue FAILED");
			e.printStackTrace();
		}
		return colData;
	}

	public void setCellData(String sheetName, int rownum, int cellnum, String data) {
		try {
			File xlfile = new File(filepath);
			if (!xlfile.exists()) {
				workbook = new XSSFWorkbook();
				fos = new FileOutputStream(filepath);
				workbook.write(fos);
			}

			fins = new FileInputStream(filepath);
			workbook = new XSSFWorkbook(fins);
			if (workbook.getSheetIndex(sheetName) == -1) {
				workbook.createSheet(sheetName);
			}
			sheet = workbook.getSheet(sheetName);

			if (sheet.getRow(rownum) == null) {
				sheet.createRow(rownum);
			}
			row = sheet.getRow(rownum);

			cell = row.createCell(cellnum);
			cell.setCellValue(data);

			fos = new FileOutputStream(filepath);
			workbook.write(fos);
			workbook.close();
			fins.close();
			fos.close();

		} catch (Exception e) {
			System.out.println("setCellValue FAILED");
			e.printStackTrace();
		}
	}

	public void setCellDataWithFillColor(String sheetName, int rownum, int cellnum, String data, String color) {
		try {

			fins = new FileInputStream(filepath);
			workbook = new XSSFWorkbook(fins);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rownum);

			cell = row.createCell(cellnum);
			cell.setCellValue(data);

			style = workbook.createCellStyle();
			if (color.toLowerCase().equals("green")) {
				style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
			} else {
				style.setFillBackgroundColor(IndexedColors.RED.getIndex());
			}
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cell.setCellStyle(style);

			fos = new FileOutputStream(filepath);
			workbook.write(fos);
			workbook.close();
			fins.close();
			fos.close();

		} catch (Exception e) {
			System.out.println("setCellDataWithFillColor FAILED");
			e.printStackTrace();
		}
	}
}
