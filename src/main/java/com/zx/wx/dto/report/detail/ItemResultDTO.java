package com.zx.wx.dto.report.detail;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/9.
 */
public class ItemResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String rowid;
    private String nodeid;
    private String nodename;
    private String valuestr;
    private String rangestd;
    private String status;
    private String unit;
    private String prompt;
    private String disporder;

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public String getValuestr() {
        return valuestr;
    }

    public void setValuestr(String valuestr) {
        this.valuestr = valuestr;
    }

    public String getRangestd() {
        return rangestd;
    }

    public void setRangestd(String rangestd) {
        this.rangestd = rangestd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getDisporder() {
        return disporder;
    }

    public void setDisporder(String disporder) {
        this.disporder = disporder;
    }

    @Override
    public String toString() {
        return "ItemResultDTO{" +
                "rowid='" + rowid + '\'' +
                ", nodeid='" + nodeid + '\'' +
                ", nodename='" + nodename + '\'' +
                ", valuestr='" + valuestr + '\'' +
                ", rangestd='" + rangestd + '\'' +
                ", status='" + status + '\'' +
                ", unit='" + unit + '\'' +
                ", prompt='" + prompt + '\'' +
                ", disporder='" + disporder + '\'' +
                '}';
    }
}
