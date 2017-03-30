package com.danny.tools.initdbeneity.helper;

import com.danny.tools.initdbeneity.entity.Field;
import com.danny.tools.initdbeneity.entity.Table;
import com.danny.tools.initdbeneity.entity.TypeMapping;

import java.io.IOException;
import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: EntityHelper
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-03-28 22:38:44
 */
public class EntityHelper {

    public static void main(String[] args) {
        String prefixName = "com.lxjr.bigdata.process.dao.data";
        try {
            List<Table> tableList = ExcelHelper.getAllTables("/Users/dannyhoo/Downloads/征信数据表结构设计.xlsx");
            for (Table table : tableList) {
                String beanName = getBeanName(table);
                System.out.println(getImportPackagesString(table, prefixName));
                System.out.println(getFieldsString(table));
                System.out.println(getFieldsGetterSetterString(table));
                System.out.println(beanName);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得JavaBean的内容
     *
     * @param table
     * @param prefixName
     * @return
     */
    public static String getJavaFileContent(Table table, String prefixName) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getPackageString(table, prefixName))
                .append(getImportPackagesString(table, prefixName)).append("\n")
                .append("@Entity\n")
                .append("@Table(name = \"" + table.getTableEnName() + "\")\n")
                .append("public class " + getBeanName(table) + " extends BaseDO {\n")
                .append(getFieldsString(table))
                .append(getFieldsGetterSetterString(table))
                .append("}")
        ;
        return stringBuffer.toString();
    }

    /**
     * 根据表名获取Bean的名称
     *
     * @param table
     * @return
     */
    public static String getBeanName(Table table) {
        String tableName = table.getTableEnName();
        String[] temp = tableName.split("_");
        String beanName = "";
        for (int i = 1; i < temp.length; i++) {
            beanName += initcap(temp[i]);
        }
        return beanName;
    }

    /**
     * 获取该Bean所在包名
     *
     * @param prefixName 包名前缀，比如 com.lxjr.credit.account.dao.data
     * @param table
     * @return
     */
    public static String getPackageString(Table table, String prefixName) {
        return new StringBuffer("package ").append(prefixName).append(".").append(table.getBelongTo()).append(";\n\n").toString();
    }

    /**
     * 组装该Bean需要引入的包
     *
     * @param table
     * @param prefixName
     * @return
     */
    public static String getImportPackagesString(Table table, String prefixName) {

        /**取出该Bean中所有的字段**/
        List<Field> fieldList = table.getFieldList();
        StringBuffer stringBuffer = new StringBuffer()
                .append("import ").append(prefixName).append(".BaseDO;\n")
                .append("import javax.persistence.Entity;\n")
                .append("import javax.persistence.Table;\n");

        boolean hasAddDateType = false;
        boolean hasAddBigDecimalType = false;
        for (Field field : fieldList) {
            if (field.getFieldEnName().equals("id") || field.getFieldEnName().equals("createTime")
                    || field.getFieldEnName().equals("updateTime")) { // TODO: 17/3/30 忽略这些字段
                continue;
            }
            if (!hasAddDateType && "Date".equals(TypeMapping.dbTypeToJavaType(field))) {
                stringBuffer.append("import java.util.Date;\n");
                hasAddDateType = true;
                continue;
            }
            if (!hasAddBigDecimalType && "BigDecimal".equals(TypeMapping.dbTypeToJavaType(field))) {
                stringBuffer.append("import java.math.BigDecimal;\n");
                hasAddBigDecimalType = true;
                continue;
            }
        }
        String importPackageListNames = stringBuffer.toString();
        return importPackageListNames;
    }

    /**
     * 组装该Bean所有的字段和字段注释
     *
     * @param table
     * @return
     */
    public static String getFieldsString(Table table) {
        /**取出该Bean中所有的字段**/
        List<Field> fieldList = table.getFieldList();
        StringBuffer stringBuffer = new StringBuffer();
        for (Field field : fieldList) {
            if (field.getFieldEnName().equals("id") || field.getFieldEnName().equals("createTime")
                    || field.getFieldEnName().equals("updateTime")) { // TODO: 17/3/30 忽略这些字段
                continue;
            }
            stringBuffer
                    .append("\n    /** " + field.getFieldChName() + " **/\n")
                    .append("    private ")
                    .append(TypeMapping.dbTypeToJavaType(field))
                    .append(" ")
                    .append(field.getFieldEnName())
                    .append(";\n");
        }
        String importPackageListNames = stringBuffer.toString();
        return importPackageListNames;
    }

    /**
     * 获取getter、setter
     *
     * @param table
     * @return
     */
    public static String getFieldsGetterSetterString(Table table) {
        /**取出该Bean中所有的字段**/
        String beanName = getBeanName(table);
        List<Field> fieldList = table.getFieldList();
        StringBuffer stringBuffer = new StringBuffer();
        for (Field field : fieldList) {
            if (field.getFieldEnName().equals("id") || field.getFieldEnName().equals("createTime")
                    || field.getFieldEnName().equals("updateTime")) { // TODO: 17/3/30 忽略这些字段
                continue;
            }
            stringBuffer.append("\n    public ")
                    .append(TypeMapping.dbTypeToJavaType(field))
                    .append(" ")
                    .append("get")
                    .append(initcap(field.getFieldEnName()))
                    .append("() {return ")
                    .append(field.getFieldEnName())
                    .append(";}\n");
            stringBuffer.append("\n    public ")
                    .append(beanName)
                    .append(" set")
                    .append(initcap(field.getFieldEnName()))
                    .append("(")
                    .append(TypeMapping.dbTypeToJavaType(field))
                    .append(" ")
                    .append(field.getFieldEnName())
                    .append(") {\n")
                    .append("        this.").append(field.getFieldEnName()).append("=").append(field.getFieldEnName()).append(";\n")
                    .append("        return this;\n    }\n")
            ;
        }
        String importPackageListNames = stringBuffer.toString();
        return importPackageListNames;
    }

    /**
     * 把输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    public static String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
