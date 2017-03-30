package com.danny.tools.initdbeneity.entity;

/**
 * 类型映射关系
 */
public class TypeMapping {
    public static String dbTypeToJavaType(Field field) {
        String type = field.getFieldType();

        if (type == null) {
            return null;
        }
        type = type.toLowerCase();//变成小写
        if (type.equals("varchar")) {
            return "String";
        } else if (type.equals("char")) {
            return "String";
        } else if (type.equals("nchar")) {
            return "String";
        } else if (type.equals("nvarchar")) {
            return "String";
        } else if (type.equals("text")) {
            return "String";
        } else if (type.equals("int") && ("Y".equals(field.getIsForeignKey()) || "id".equals(field.getFieldEnName()))) {
            return "Long";
        } else if (type.equals("int")) {
            return "Integer";
        } else if (type.equals("tinyint")) {
            return "Integer";
        } else if (type.equals("smallint")) {
            return "Integer";
        } else if (type.equals("bigint")) {
            return "Long";
        } else if (type.equals("float")) {
            return "Double";
        } else if (type.equals("decimal")) {
            return "BigDecimal";
        } else if (type.equals("numeric")) {
            return "BigDecimal";
        } else if (type.equals("timestamp")) {
            return "Date";
        } else if (type.equals("datetime")) {
            return "Date";
        }
        return null;
    }

}
