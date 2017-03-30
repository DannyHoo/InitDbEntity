package com.danny.tools.initdbeneity.helper;

import com.danny.tools.initdbeneity.entity.Field;
import com.danny.tools.initdbeneity.entity.Table;

import java.io.IOException;
import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: DBHelper
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-03-28 22:35:30
 */
public class DBHelper {

    public static void main(String[] args) {
        String excelPath="/Users/dannyhoo/Downloads/POITest.xlsx";
        try {
            List<Table> tableList = ExcelHelper.getAllTables(excelPath);
            for (Table table:tableList){
                String tableSqlString=getTableSQLString(table);
                System.out.println(tableSqlString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getSQLString(List<Table> tableList){
        StringBuffer stringBuffer=new StringBuffer()
                .append("SET NAMES utf8mb4;\n")
                .append("SET FOREIGN_KEY_CHECKS = 0;\n\n");
        for (Table table:tableList){
            stringBuffer.append(getTableSQLString(table));
        }
        stringBuffer.append("SET FOREIGN_KEY_CHECKS = 1;");
        return stringBuffer.toString();
    }

    public static String getTableSQLString(Table table) {
        StringBuffer stringBuffer = new StringBuffer()
                .append("DROP TABLE IF EXISTS `" + table.getTableEnName() + "`;\n")
                .append("CREATE TABLE `"+table.getTableEnName()+"` (\n");

        List<Field> fieldList = table.getFieldList();
        for (Field field : fieldList) {
            //字段名
            stringBuffer.append("  `"+field.getFieldEnName().trim()+"` ").append(field.getFieldType());
            //类型(长度)
            if (field.getFieldType().equals("int")) {
                stringBuffer.append("(").append("11").append(")");
            } else if (field.getFieldType().equals("tinyint")) {
                stringBuffer.append("(").append("4").append(")");
            } else if (!field.getFieldType().equals("timestamp") && !field.getFieldType().equals("datetime")
                    &&!field.getFieldType().equals("text")) {
                stringBuffer.append("").append(addBrackets(field.getFieldLength()));
            }
            //是否为空
            if ("N".equals(field.getIsNullable())) {
                stringBuffer.append(" NOT NULL");
            }
            //自增长(id)
            if ("id".equals(field.getFieldEnName())) {
                stringBuffer.append(" AUTO_INCREMENT");
            }
            //默认值(创建时间、最近修改时间)
            if ("createTime".equals(field.getFieldEnName())) {
                stringBuffer.append(" DEFAULT CURRENT_TIMESTAMP");
            }
            if ("updateTime".equals(field.getFieldEnName())) {
                stringBuffer.append(" DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP");
            }
            //备注
            stringBuffer.append(" COMMENT ").append("'").append(field.getFieldDescribe()).append("'").append(",\n");
        }

        stringBuffer.append("PRIMARY KEY (`id`)\n")
                .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n\n");
        return stringBuffer.toString();
    }

    public static String addBrackets(String str){
        if (str.contains("(")||str.contains(")")){
            return str;
        }else if(str.contains("（")||str.contains("）")){
            str=str.replace("（","(").replace("）",")");
        }
        else{
            str=new StringBuffer("(").append(str).append(")").toString();
        }
        return str;
    }
}
