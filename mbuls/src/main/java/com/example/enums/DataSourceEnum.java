package com.example.enums;

/**
 * @author Tu
 * @Package com.example.enums
 * @date 2021/4/10-9:56
 */
public enum DataSourceEnum {
    /**
     * @param null
     * @author Tu
     * @date 2021/4/10 11:44
     * @message
     * @return
     */
    DB1("db1"), DB2("db2");

    private String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

