package com.danny.tools.initdbeneity.helper;

import com.danny.tools.initdbeneity.entity.Table;

import java.io.*;
import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: FileHelper
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-03-30 20:42:12
 */
public class FileHelper {
    public static void main(String[] args) {
        try {
            String excelPath = "/Users/dannyhoo/Downloads/征信数据表结构设计.xlsx";
            List<Table> tableList = ExcelHelper.getAllTables(excelPath);
            for (Table table : tableList) {
                String javaName = EntityHelper.getBeanName(table);
                String prefixName = "com.lxjr.bigdata.process.dao.data";
                String belongTo = table.getBelongTo();
                String javaFileName = getJavaFileName(javaName, prefixName, belongTo);
                String javaFileContent = EntityHelper.getJavaFileContent(table, prefixName);
                System.out.println(javaFileContent);
                boolean result = writeTxtFile(javaFileContent, javaFileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createSQLFile(String excelPath,String sqlFilePath){
        try {
            List<Table> tableList = ExcelHelper.getAllTables(excelPath);
            String sqlContent=DBHelper.getSQLString(tableList);
            boolean result = writeTxtFile(sqlContent, sqlFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createJavaFiles(String excelPath, String prefixName) {
        try {
            List<Table> tableList = ExcelHelper.getAllTables(excelPath);
            for (Table table : tableList) {
                String javaName = EntityHelper.getBeanName(table);
                String belongTo = table.getBelongTo();
                String javaFileName = getJavaFileName(javaName, prefixName, belongTo);
                String javaFileContent = EntityHelper.getJavaFileContent(table, prefixName);
                System.out.println(javaFileContent);
                boolean result = writeTxtFile(javaFileContent, javaFileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getJavaFileName(String javaName, String prefixName, String belongTo) {
        StringBuffer stringBuffer = new StringBuffer();
        File directory = new File(".");
        try {
            stringBuffer.append(directory.getCanonicalPath())  //获取当前路径
                    .append("/src/main/java/")
                    .append(prefixName.replace(".", "/"))
                    .append("/")
                    .append(belongTo).append("/")
                    .append(javaName)
                    .append(".java")
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public static boolean writeTxtFile(String content, String fileName) throws Exception {
        File file = new File(fileName);
        System.out.println(file.getParentFile().getPath());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}
