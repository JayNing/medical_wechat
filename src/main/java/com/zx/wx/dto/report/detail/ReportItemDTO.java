package com.zx.wx.dto.report.detail;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */
public class ReportItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String itemid;
    private String itemname;
    private String doctor;
    private String checkupdate;
    private String comment;
    private String itemtype;
    private String disporder;
    private List<ItemResultDTO> itemresults;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getCheckupdate() {
        return checkupdate;
    }

    public void setCheckupdate(String checkupdate) {
        this.checkupdate = checkupdate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getDisporder() {
        return disporder;
    }

    public void setDisporder(String disporder) {
        this.disporder = disporder;
    }

    public List<ItemResultDTO> getItemresults() {
        return itemresults;
    }

    public void setItemresults(List<ItemResultDTO> itemresults) {
        this.itemresults = itemresults;
    }

    @Override
    public String toString() {
        return "ReportItemDTO{" +
                "itemid='" + itemid + '\'' +
                ", itemname='" + itemname + '\'' +
                ", doctor='" + doctor + '\'' +
                ", checkupdate='" + checkupdate + '\'' +
                ", comment='" + comment + '\'' +
                ", itemtype='" + itemtype + '\'' +
                ", disporder='" + disporder + '\'' +
                ", itemresults=" + itemresults +
                '}';
    }
}
