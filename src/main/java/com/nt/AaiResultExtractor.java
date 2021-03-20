package com.nt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nt.bean.HSStudent;
import com.nt.bean.Student;

import sun.font.FontFamily;

public class AaiResultExtractor {
	public static void main(String[] qargs) {
		List<HSStudent> all = getAllStudentData();
		List<HSStudent> newAll = new ArrayList<>();
		// deleting null record
		for (int i = 0; i < all.size(); i++) {
			HSStudent stud = all.get(i);
			if (stud.getName() != null)
				newAll.add(stud);
		}

		for (HSStudent student : newAll) {

			HSStudent checkedStudent = getMarks(student);
			student.setMarks(checkedStudent.getMarks());
			student.setCorrectAnswers(checkedStudent.getCorrectAnswers());
			student.setWrongAnswers(checkedStudent.getWrongAnswers());

		}
		for (HSStudent student : newAll) {
			System.out.print(student.getName() + " " + student.getMarks());
//			for (String str : student.getQa())
//				System.out.print(" " + str);
			System.out.println();
		}

		convertPDF(newAll);
	}

	public static HSStudent getMarks(HSStudent inputStudent) {
		int marks = 0, wrong = 0, correctedCount = 0, wrongCount = 0;
		HSStudent stud = getAnswersSheet();
		String[] correctAns = stud.getQa(), answers = inputStudent.getQa();
		for (int i = 0; i < 100; i++) {
			if (answers[i].equalsIgnoreCase(correctAns[i])) {
				marks = marks + 3;
				++correctedCount;
			} else {
				++wrong;
				++wrongCount;
			}
		}
		System.out.println("\n--------------");
		System.out.print("  " + inputStudent.getName());
		System.out.print("  |  Correct ansers: " + correctedCount + " ");
		System.out.print("Wrong ansers: " + wrongCount + " ");
		System.out.print("Marks total: " + marks + " ");
		System.out.print("Wrong : " + wrong + " ");
		marks = marks - wrong;
		System.out.print("Actual Mrks: " + marks + " ");
		for (String str : inputStudent.getQa()) {
			System.out.print(" " + str);
		}
		System.out.println("--------------");
		inputStudent.setMarks(marks);
		inputStudent.setCorrectAnswers(correctedCount);
		inputStudent.setWrongAnswers(wrongCount);
		return inputStudent;
	}

	public static HSStudent getAnswersSheet() {
		int rowCount = 1, cellCount = 0;
		FileInputStream file = null;
		HSStudent student = new HSStudent();
		try {
			file = new FileInputStream(new File("Result2.xlsx"));
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one //iterating over each column
			Iterator<Row> rowIterator = sheet.iterator();
			// create a student object
			String[] answers = new String[100];
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				cellCount = 0;
				int i = 0, display = 1;
				while (cellIterator.hasNext()) {// while inner
					Cell cell = cellIterator.next();
					// set values to student
					if ((cellCount > 1 && cellCount <= 102) && rowCount == 2) {
						if (i >= 0 && i <= 99) {
							answers[i++] = cell.getStringCellValue();
//							System.out.println(display++ + " " + cell.getStringCellValue());
						}
					}
					if (cellCount == 110 && rowCount == 2) {
						student.setEmail(cell.getStringCellValue());
					} else if (cellCount == 102 && rowCount == 2) {
						student.setName(cell.getStringCellValue());
					}
					cellCount = ++cellCount;
				} // while inner
				rowCount = ++rowCount;
			} // outer while
			student.setQa(answers);
			file.close();
//			System.out.println("=======");
//			for (String str : student.getQa()) {
//				System.out.print(str + " ");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	public static List<HSStudent> getAllStudentData() {
		FileInputStream file = null;
		List<HSStudent> allStudent = new ArrayList<>();
		try {
			file = new FileInputStream(new File("Ashu AFCAT Test.xlsx"));
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one //iterating over each column
			Iterator<Row> rowIterator = sheet.iterator();

			// create a student object
			int rowCount = 1, cellCount = 0;
			while (rowIterator.hasNext()) {// while outer
				String[] answers = new String[100];
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				cellCount = 0;
				int i = 0, display = 1;
				HSStudent student = new HSStudent();
				while (cellIterator.hasNext()) {//////////////////////////////////////////// while inner
					Cell cell = cellIterator.next();
					// set values to student
					if ((cellCount > 2 && cellCount <= 102) && (rowCount >= 1 && rowCount <= 11)) {
						if (i >= 0 && i <= 99) {
							answers[i++] = cell.getStringCellValue();
//							System.out.println(display++ + " " + cell.getStringCellValue());
						}
					}
					if (cellCount == 110 && (rowCount >= 1 && rowCount <= 11)) {
						student.setEmail(cell.getStringCellValue());
//						System.out.println(cell.getStringCellValue());
					} else if (cellCount == 102 && (rowCount >= 1 && rowCount <= 11)) {
//						System.out.println(cell.getStringCellValue());
						student.setName(cell.getStringCellValue());
					}
					/*
					 * if (cellCount == 110 && (rowCount >=1 && rowCount <=12)) {
					 * System.out.print(cell.getStringCellValue()+"  "); } else if (cellCount == 102
					 * && (rowCount >=1 && rowCount <=12)) {
					 * System.out.print(cell.getStringCellValue()+"   "); }
					 */
					cellCount = ++cellCount;
				} ////////////////////////////////////////////////////////////////////////// while
					////////////////////////////////////////////////////////////////////////// inner
				student.setQa(answers);
				allStudent.add(student);
				rowCount = ++rowCount;

//				//printing marks of each student
//				System.out.print("Name: "+student.getName()+"|");
//				for (String stud: student.getQa()) {
//					System.out.print(stud+" ");
//				}
//				System.out.println();

			} //////////////////////////////////////////// outer while
			file.close();
//			System.out.println("=======");
			int j = 1;
//			for (HSStudent student: allStudent) {
//				System.out.print("Name: "+student.getName()+"|");
//				for (String stud: student.getQa()) {
//					System.out.print(stud+" ");
//				}
//				System.out.println();
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allStudent;
	}

	static public void convertPDF(List<HSStudent> list) {

		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("C:\\Users\\Hi\\Desktop\\RiseAbove RESULT.pdf"));
			document.open();

			// add title
			Paragraph p = new Paragraph("Shree Maharudra's RiseAbove \n RESULT");
			document.add(p);
			PdfPTable table = new PdfPTable(4); // 3 columns.
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			// Set Column widths
			float[] columnWidths = { 1f, 1f, 1f, 1f };
			table.setWidths(columnWidths);

			//add titles
			PdfPCell title1 = new PdfPCell(new Paragraph("NAME"));
			title1.setBorderColor(BaseColor.BLUE);
			title1.setPaddingLeft(10);
			title1.setHorizontalAlignment(Element.ALIGN_CENTER);
			title1.setVerticalAlignment(Element.ALIGN_LEFT);
			PdfPCell title2 = new PdfPCell(new Paragraph("CORRECT ANS"));
			title2.setBorderColor(BaseColor.BLUE);
			title2.setPaddingLeft(10);
			title2.setHorizontalAlignment(Element.ALIGN_CENTER);
			title2.setVerticalAlignment(Element.ALIGN_LEFT);
			PdfPCell title3 = new PdfPCell(new Paragraph("WRONG ANS"));
			title3.setBorderColor(BaseColor.BLUE);
			title3.setPaddingLeft(10);
			title3.setHorizontalAlignment(Element.ALIGN_CENTER);
			title3.setVerticalAlignment(Element.ALIGN_LEFT);
			PdfPCell title4 = new PdfPCell(new Paragraph("MARKS"));
			title4.setBorderColor(BaseColor.BLUE);
			title4.setPaddingLeft(10);
			title4.setHorizontalAlignment(Element.ALIGN_CENTER);
			title4.setVerticalAlignment(Element.ALIGN_LEFT);
			table.addCell(title1);
			table.addCell(title2);
			table.addCell(title3);
			table.addCell(title4);
			
			for (HSStudent student : list) {

				PdfPCell cell1 = new PdfPCell(new Paragraph("" + student.getName()));
				cell1.setBorderColor(BaseColor.BLUE);
				cell1.setPaddingLeft(10);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setVerticalAlignment(Element.ALIGN_LEFT);

				PdfPCell cell2 = new PdfPCell(new Paragraph("" + student.getCorrectAnswers()));
				cell2.setBorderColor(BaseColor.RED);
				cell2.setPaddingLeft(10);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell cell3 = new PdfPCell(new Paragraph("" + student.getWrongAnswers()));
				cell3.setBorderColor(BaseColor.RED);
				cell3.setPaddingLeft(10);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);


				PdfPCell cell4 = new PdfPCell(new Paragraph("" + student.getMarks()));
				cell4.setBorderColor(BaseColor.RED);
				cell4.setPaddingLeft(10);
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);


				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				table.addCell(cell4);
			}
			document.add(table);

			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}