package com.webform.utils;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static Object[][] getExcelData(
            String filePath, String sheetName) {

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName);
            }

            int rowCount = sheet.getLastRowNum();
            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {
                throw new IllegalArgumentException(
                        "Excel header row is missing.");
            }

            int columnCount = headerRow.getLastCellNum();

            Object[][] data = new Object[rowCount][columnCount];

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= rowCount; i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    throw new IllegalArgumentException(
                            "Empty row found: " + (i + 1));
                }

                for (int j = 0; j < columnCount; j++) {

                    data[i - 1][j] =
                            formatter.formatCellValue(
                                    row.getCell(j));
                }
            }

            return data;

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to read Excel file: " + filePath, e);
        }
    }
}

