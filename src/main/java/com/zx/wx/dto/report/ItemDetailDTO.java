package com.zx.wx.dto.report;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/8.
 */
public class ItemDetailDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String itemDetailId;
    private String itemDetailName;
    private String normalMinValue;
    private String checkupdate;
    private String normalMaxValue;
    private String itemDetailValue;
    private String itemDetailUnits; //计量单位
    private String itemDetailStatus;
    private String itemDetailPrompt;
    private String itemDetailDisporder;
    private String rangestd;
    private Integer range;

    public String getCheckupdate() {
        return checkupdate;
    }

    public void setCheckupdate(String checkupdate) {
        this.checkupdate = checkupdate;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public String getItemDetailStatus() {
        return itemDetailStatus;
    }

    public void setItemDetailStatus(String itemDetailStatus) {
        this.itemDetailStatus = itemDetailStatus;
    }

    public String getItemDetailPrompt() {
        return itemDetailPrompt;
    }

    public void setItemDetailPrompt(String itemDetailPrompt) {
        this.itemDetailPrompt = itemDetailPrompt;
    }

    public String getItemDetailDisporder() {
        return itemDetailDisporder;
    }

    public void setItemDetailDisporder(String itemDetailDisporder) {
        this.itemDetailDisporder = itemDetailDisporder;
    }

    public String getRangestd() {
        return rangestd;
    }

    public void setRangestd(String rangestd) {
        this.rangestd = rangestd;
    }

    public String getItemDetailId() {
        return itemDetailId;
    }

    public void setItemDetailId(String itemDetailId) {
        this.itemDetailId = itemDetailId;
    }

    public String getItemDetailName() {
        return itemDetailName;
    }

    public void setItemDetailName(String itemDetailName) {
        this.itemDetailName = itemDetailName;
    }

    public String getNormalMinValue() {
        return normalMinValue;
    }

    public void setNormalMinValue(String normalMinValue) {
        this.normalMinValue = normalMinValue;
    }

    public String getNormalMaxValue() {
        return normalMaxValue;
    }

    public void setNormalMaxValue(String normalMaxValue) {
        this.normalMaxValue = normalMaxValue;
    }

    public String getItemDetailValue() {
        return itemDetailValue;
    }

    public void setItemDetailValue(String itemDetailValue) {
        this.itemDetailValue = itemDetailValue;
    }

    public String getItemDetailUnits() {
        return itemDetailUnits;
    }

    public void setItemDetailUnits(String itemDetailUnits) {
        this.itemDetailUnits = itemDetailUnits;
    }

    @Override
    public String toString() {
        return "ItemDetailDTO{" +
                "itemDetailId='" + itemDetailId + '\'' +
                ", itemDetailName='" + itemDetailName + '\'' +
                ", normalMinValue='" + normalMinValue + '\'' +
                ", checkupdate='" + checkupdate + '\'' +
                ", normalMaxValue='" + normalMaxValue + '\'' +
                ", itemDetailValue='" + itemDetailValue + '\'' +
                ", itemDetailUnits='" + itemDetailUnits + '\'' +
                ", itemDetailStatus='" + itemDetailStatus + '\'' +
                ", itemDetailPrompt='" + itemDetailPrompt + '\'' +
                ", itemDetailDisporder='" + itemDetailDisporder + '\'' +
                ", rangestd='" + rangestd + '\'' +
                ", range=" + range +
                '}';
    }
}
