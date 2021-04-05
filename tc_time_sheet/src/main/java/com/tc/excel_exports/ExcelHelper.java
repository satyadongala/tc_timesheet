package com.tc.excel_exports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tc.controller.TimeSheetController;
import com.tc.model.TimeSheetModel;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "DATE", "DATE", "TASK", "TYPE", "EFFORTS", "COMMENTS" };
	static String nam = TimeSheetController.name;

	public static ByteArrayInputStream tutorialsToExcel(List<TimeSheetModel> list) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(nam);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}

			int rowIdx = 1;
			for (TimeSheetModel list1 : list) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(list1.getDate());
				row.createCell(1).setCellValue(list1.getDate());
				row.createCell(2).setCellValue(list1.getAssignmentId().getProjectName());
				row.createCell(3).setCellValue(list1.getAssignmentId().getTask());
				row.createCell(4).setCellValue(list1.getHours());
				row.createCell(5).setCellValue(list1.getComments());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}
}
