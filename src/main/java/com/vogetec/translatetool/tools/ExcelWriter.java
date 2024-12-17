package com.vogetec.translatetool.tools;

import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ExcelWriter {
    public static void writeExcel(List<List<String>> data, String fileName) throws IOException {
        Workbook workbook = WorkbookFactory.create(true); // 创建一个新的 Excel 工作簿
        Sheet sheet = workbook.createSheet(); // 创建一个新的工作表

        // 遍历数据列表，逐行写入 Excel
        int rowNum = 0;
        for (List<String> rowData : data) {
            Row row = sheet.createRow(rowNum++); // 创建一行
            int colNum = 0;
            for (String cellData : rowData) {
                Cell cell = row.createCell(colNum++); // 创建一个单元格
                cell.setCellValue(cellData); // 设置单元格的值
            }
        }

        // 将 Excel 数据写入文件
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }

        // 关闭工作簿
        workbook.close();
    }
    public static void writeExcel(Object[][] data, String fileName) throws IOException {
        Workbook workbook = WorkbookFactory.create(true); // 创建一个新的 Excel 工作簿
        Sheet sheet = workbook.createSheet(); // 创建一个新的工作表
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.createCell(j); // 创建一个单元格
                if (data[i][j] instanceof String){
                    cell.setCellValue((String) data[i][j]); // 设置单元格的值
                }
                if (data[i][j] instanceof Integer){
                    cell.setCellValue((int) data[i][j]); // 设置单元格的值
                }
                if (data[i][j] instanceof Boolean){
                    cell.setCellValue((Boolean) data[i][j]); // 设置单元格的值
                }
            }
        }

        // 将 Excel 数据写入文件
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }

        // 关闭工作簿
        workbook.close();
    }

    public static void main(String[] args) {
        // 示例数据
        List<List<String>> data = Arrays.asList(
                Arrays.asList("Name", "Age", "City"),
                Arrays.asList("John", "30", "New York"),
                Arrays.asList("Alice", "25", "Los Angeles"),
                Arrays.asList("Bob", "35", "Chicago")
        );

        // 将数据写入 Excel 文档
        try {
            writeExcel(data, "F:\\java\\Workspaces\\VogetetFranslate\\values\\output.xlsx");
            //System.out.println("Excel file written successfully!");
        } catch (IOException e) {
            System.err.println("Error writing Excel file: " + e.getMessage());
        }
    }
}
