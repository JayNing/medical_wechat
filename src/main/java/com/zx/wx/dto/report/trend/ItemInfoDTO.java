package com.zx.wx.dto.report.trend;

import java.io.Serializable;
import java.util.List;

public class ItemInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String unit;
    private String min;
    private String max;
    private String itemName;
    private List<ItemValueDTO> itemValueDTOList;
    private List<Integer> valueList;
    private List<String> dateStrList;

    public List<Integer> getValueList() {
        return valueList;
    }

    public void setValueList(List<Integer> valueList) {
        this.valueList = valueList;
    }

    public List<String> getDateStrList() {
        return dateStrList;
    }

    public void setDateStrList(List<String> dateStrList) {
        this.dateStrList = dateStrList;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<ItemValueDTO> getItemValueDTOList() {
        return itemValueDTOList;
    }

    public void setItemValueDTOList(List<ItemValueDTO> itemValueDTOList) {
        this.itemValueDTOList = itemValueDTOList;
    }

    @Override
    public String toString() {
        return "ItemInfoDTO{" +
                "unit='" + unit + '\'' +
                ", min='" + min + '\'' +
                ", max='" + max + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemValueDTOList=" + itemValueDTOList +
                ", valueList=" + valueList +
                ", dateStrList=" + dateStrList +
                '}';
    }
}