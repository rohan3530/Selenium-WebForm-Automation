
package com.webform.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {
                throw new IllegalArgumentException(
                        "Excel header row is missing.");
            }

            int columnCount = headerRow.getLastCellNum();

            if (columnCount <= 0) {
                throw new IllegalArgumentException(
                        "Excel header row has no columns.");
            }

            DataFormatter formatter = new DataFormatter();

            List<Object[]> validRows = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                String[] rowData = new String[columnCount];
                boolean isEmptyRow = true;

                for (int j = 0; j < columnCount; j++) {

                    String cellValue =
                            formatter.formatCellValue(
                                    row.getCell(j)).trim();

                    rowData[j] = cellValue;

                    if (!cellValue.isEmpty()) {
                        isEmptyRow = false;
                    }
                }

                if (!isEmptyRow) {
                    validRows.add(rowData);
                }
            }

            return validRows.toArray(new Object[0][]);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to read Excel file: " + filePath, e);
        }
    }
}
