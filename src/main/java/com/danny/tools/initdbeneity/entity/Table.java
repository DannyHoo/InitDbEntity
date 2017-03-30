package com.danny.tools.initdbeneity.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: Table
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-03-27 22:58:47
 */
public class Table {
    private String tableChName;
    private String tableEnName;
    /**
     * 所属包的类别名称，比如com.lxjr.credit.account.dao.data.bill.BillDO的类别名称为bill
     */
    private String belongTo;
    private List<Field> fieldList;

    public Table(){
    }

    public Table(String tableChName, String tableEnName,String belongTo, List<Field> fieldList) {
        this.tableChName = tableChName;
        this.tableEnName = tableEnName;
        this.belongTo = belongTo;
        this.fieldList=fieldList;
    }

    public String getTableChName() {
        return tableChName;
    }

    public Table setTableChName(String tableChName) {
        this.tableChName = tableChName;
        return this;
    }

    public String getTableEnName() {
        return tableEnName;
    }

    public Table setTableEnName(String tableEnName) {
        this.tableEnName = tableEnName;
        return this;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public Table setBelongTo(String belongTo) {
        this.belongTo = belongTo;
        return this;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public Table setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
        return this;
    }
}
