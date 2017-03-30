package com.danny.tools.initdbeneity.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.danny.tools.initdbeneity.entity.Field;
import com.danny.tools.initdbeneity.entity.Table;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author huyuyang@lxfintech.com
 * @Title: ExcelHelper
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-03-27 22:34:52
 */
public class ExcelHelper {

    public static void main(String[] args) throws IOException {
        String filePath = "/Users/dannyhoo/Downloads/POITest.xlsx";
        //String filePath = "/Users/dannyhoo/Downloads/征信数据表结构设计.xlsx";
        List<Table> tableList = getAllTables(filePath);

    }

    public static List<Table> getAllTables(String filePath) throws IOException {
        List<Table> tableList = new ArrayList<Table>();

        /** 获得Excel **/
        InputStream is = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(is);

        /** 遍历工作表 **/
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            Sheet sheet = workbook.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            String sheetName = sheet.getSheetName();

            int rows = sheet.getLastRowNum();
            Table table = null;
            /** 遍历行 **/
            try {
                for (int i = 1; i <= rows; i++) {//忽略表头
                    Row row = sheet.getRow(i);
                    //int a = row.getLastCellNum();
                    if (row == null || isBlankRow(row)) {//空行
                        tableList.add(table);
                        continue;
                    } else if (StringUtils.isNotBlank(getCellValue(row.getCell(0)))) {//表名所在行
                        String tableChName = getTableChName(getCellValue(row.getCell(0)));
                        String tableEnName = getTableEnName(getCellValue(row.getCell(0)));
                        table = new Table(tableChName, tableEnName, sheetName, new ArrayList<Field>());
                    } else {//字段所在行
                        String fieldChName = getCellValue(row.getCell(1));
                        String fieldEnName = getCellValue(row.getCell(2));
                        String fieldType = getCellValue(row.getCell(3));
                        String fieldLength = getCellValue(row.getCell(4));
                        String isNullable = getCellValue(row.getCell(5));
                        String isForeignKey = getCellValue(row.getCell(6));
                        String fieldDescribe = getCellValue(row.getCell(7));
                        Field field = new Field(fieldChName, fieldEnName, fieldType, fieldLength, isNullable, isForeignKey, fieldDescribe);
                        table.getFieldList().add(field);
                        if (isLastRow(sheet, row, i, rows)) {
                            tableList.add(table);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return tableList;
    }

    private static boolean isLastRow(Sheet sheet, Row row, int rowNumber, int rowsNumber) {
        if (rowNumber == rowsNumber) {
            return true;
        }
        if (isBlankRow(sheet.getRow(rowNumber + 1)) && isBlankRow(sheet.getRow(rowNumber + 2))) {
            return true;
        }
        return false;
    }

    private static boolean isBlankRow(Row row) {
        if (row == null) {
            return true;
        }
        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (StringUtils.isNotBlank(getCellValue(row.getCell(i)))) {
                return false;
            }
        }
        return true;
    }

    private static String getTableChName(String fullTableName) {
        String tableChName = fullTableName.substring(0, fullTableName.indexOf("("));
        return tableChName;
    }

    private static String getTableEnName(String fullTableName) {
        String tableEnName = fullTableName.substring(fullTableName.indexOf("(") + 1, fullTableName.indexOf(")"));
        return tableEnName;
    }

    @SuppressWarnings("static-access")
    private static String getCellValue(Cell cell) {
        String result = "";
        if (cell != null) {
            cell.setCellType(CellType.STRING);
            result = cell.getStringCellValue();
        }
        return result;
    }

}
