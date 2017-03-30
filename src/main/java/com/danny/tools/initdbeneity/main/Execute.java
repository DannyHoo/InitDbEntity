package com.danny.tools.initdbeneity.main;

import com.danny.tools.initdbeneity.entity.Table;
import com.danny.tools.initdbeneity.helper.ExcelHelper;
import com.danny.tools.initdbeneity.helper.FileHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: Execute
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-03-30 22:00:08
 */
public class Execute {

    public static void main(String[] args) {
        try {
            String excelPath = "/Users/dannyhoo/Downloads/征信数据表结构设计.xlsx";
            List<Table> tableList = ExcelHelper.getAllTables(excelPath);
            /** JavaBean所在的包(实际存放的包会在prefixName后加上excel中sheet的名字) **/
            String prefixName = "com.lxjr.bigdata.process.dao.data";
            /** SQL文件存放位置 **/
            String sqlFilePath=getSqlFilePath(prefixName);
            /** 生成JavaBean **/
            FileHelper.createJavaFiles(excelPath,prefixName);
            /** SQL文件 **/
            FileHelper.createSQLFile(excelPath,sqlFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSqlFilePath(String prefixName) throws IOException {
        return new StringBuffer(new File(".").getCanonicalPath())
                .append("/src/main/java/")
                .append(prefixName.replace(".","/"))
                .append("InitDb.sql")
                .toString();
    }
}
