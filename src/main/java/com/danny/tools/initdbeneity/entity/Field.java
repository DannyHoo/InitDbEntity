package com.danny.tools.initdbeneity.entity;

/**
 * @author huyuyang@lxfintech.com
 * @Title: Field
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-03-27 22:55:21
 */
public class Field {
    private String fieldChName;
    private String fieldEnName;
    private String fieldType;
    private String fieldLength;
    private String isNullable;//是否可以为空 Y可以 N不可以
    private String isForeignKey;//是否外键 Y是 N不是
    private String fieldDescribe;


    public Field(){}

    public Field(String fieldChName, String fieldEnName, String fieldType, String fieldLength, String isNullable, String isForeignKey, String fieldDescribe) {
        this.fieldChName = fieldChName;
        this.fieldEnName = fieldEnName;
        this.fieldType = fieldType;
        this.fieldLength = fieldLength;
        this.isNullable = isNullable;
        this.isForeignKey = isForeignKey;
        this.fieldDescribe = fieldDescribe;
    }

    public String getFieldChName() {
        return fieldChName;
    }

    public Field setFieldChName(String fieldChName) {
        this.fieldChName = fieldChName;
        return this;
    }

    public String getFieldEnName() {
        return fieldEnName;
    }

    public Field setFieldEnName(String fieldEnName) {
        this.fieldEnName = fieldEnName;
        return this;
    }

    public String getFieldType() {
        return fieldType;
    }

    public Field setFieldType(String fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public String getFieldLength() {
        return fieldLength;
    }

    public Field setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
        return this;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public Field setIsNullable(String isNullable) {
        this.isNullable = isNullable;
        return this;
    }

    public String getIsForeignKey() {
        return isForeignKey;
    }

    public Field setIsForeignKey(String isForeignKey) {
        this.isForeignKey = isForeignKey;
        return this;
    }

    public String getFieldDescribe() {
        return fieldDescribe;
    }

    public Field setFieldDescribe(String fieldDescribe) {
        this.fieldDescribe = fieldDescribe;
        return this;
    }
}
