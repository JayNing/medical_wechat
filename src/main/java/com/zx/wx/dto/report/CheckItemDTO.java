package com.zx.wx.dto.report;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/8.
 */
public class CheckItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String itemId;
    private String itemName;
    private int columnNum; //需要展示几列
    private List<ItemDetailDTO> itemDetailDTOList;
    private String checkItemDateStr;
    private String checkItemDate;
    private String disporder;
    private String itemtype;

    public String getDisporder() {
        return disporder;
    }

    public void setDisporder(String disporder) {
        this.disporder = disporder;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public List<ItemDetailDTO> getItemDetailDTOList() {
        return itemDetailDTOList;
    }

    public void setItemDetailDTOList(List<ItemDetailDTO> itemDetailDTOList) {
        this.itemDetailDTOList = itemDetailDTOList;
    }

    public String getCheckItemDateStr() {
        return checkItemDateStr;
    }

    public void setCheckItemDateStr(String checkItemDateStr) {
        this.checkItemDateStr = checkItemDateStr;
    }

    public String getCheckItemDate() {
        return checkItemDate;
    }

    public void setCheckItemDate(String checkItemDate) {
        this.checkItemDate = checkItemDate;
    }

    @Override
    public String toString() {
        return "CheckItemDTO{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", columnNum=" + columnNum +
                ", itemDetailDTOList=" + itemDetailDTOList +
                ", checkItemDateStr='" + checkItemDateStr + '\'' +
                ", checkItemDate=" + checkItemDate +
                ", disporder='" + disporder + '\'' +
                ", itemtype='" + itemtype + '\'' +
                '}';
    }
}
