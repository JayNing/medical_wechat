package com.zx.wx.dto.report.trend;

import java.io.Serializable;

public class ItemValueDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String value;
    private String dateStr;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public String toString() {
        return "ItemValueDTO{" +
                "value='" + value + '\'' +
                ", dateStr='" + dateStr + '\'' +
                '}';
    }
}