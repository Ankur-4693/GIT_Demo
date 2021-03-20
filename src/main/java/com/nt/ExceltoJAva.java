package com.nt;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nt.bean.Student;

public class ExceltoJAva {
	public static void main(String[] args) {
		System.out.println(getAllDataFromExcel());
	}

	public static List<Student> getAllDataFromExcel() {
		int rowCount = 0, cellCount = 0;
		List<Student> list = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream(new File("Demo Exam.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Student student = new Student();
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				cellCount = 0;
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					
					// Check the cell type and format accordingly
					if (cellCount == 3) {
						student.setRollNo(cell.getStringCellValue());
					} else if (cellCount == 5) {
						student.setM1(String.valueOf(cell.getNumericCellValue()));
					} else if (cellCount == 6) {
						student.setM2(String.valueOf(cell.getNumericCellValue()));
					} else if (cellCount == 7) {
						student.setM3(cell.getStringCellValue());
					} else if (cellCount == 2) {
						student.setName(cell.getStringCellValue());
					} else if (cellCount == 4) {
						student.setEmail(cell.getStringCellValue());
					}

					cellCount = ++cellCount;
				}
				System.out.println("");
				list.add(student);
				rowCount = ++rowCount;
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
