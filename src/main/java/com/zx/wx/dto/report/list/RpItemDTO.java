package com.zx.wx.dto.report.list;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */
public class RpItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tjbh;
    private String companyname;
    private String examcategory;
    private String checkupdate;
    private String tjzt;
    private String status;
    private String summarydoctor;
    private String summarydate;
    private String baoming;
    private String baomingid;
    private List<ReportItemDetailDTO> items;

    public String getTjbh() {
        return tjbh;
    }

    public void setTjbh(String tjbh) {
        this.tjbh = tjbh;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getExamcategory() {
        return examcategory;
    }

    public void setExamcategory(String examcategory) {
        this.examcategory = examcategory;
    }

    public String getCheckupdate() {
        return checkupdate;
    }

    public void setCheckupdate(String checkupdate) {
        this.checkupdate = checkupdate;
    }

    public String getTjzt() {
        return tjzt;
    }

    public void setTjzt(String tjzt) {
        this.tjzt = tjzt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSummarydoctor() {
        return summarydoctor;
    }

    public void setSummarydoctor(String summarydoctor) {
        this.summarydoctor = summarydoctor;
    }

    public String getSummarydate() {
        return summarydate;
    }

    public void setSummarydate(String summarydate) {
        this.summarydate = summarydate;
    }

    public String getBaoming() {
        return baoming;
    }

    public void setBaoming(String baoming) {
        this.baoming = baoming;
    }

    public String getBaomingid() {
        return baomingid;
    }

    public void setBaomingid(String baomingid) {
        this.baomingid = baomingid;
    }

    public List<ReportItemDetailDTO> getItems() {
        return items;
    }

    public void setItems(List<ReportItemDetailDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ReportItemDTO{" +
                "tjbh='" + tjbh + '\'' +
                ", companyname='" + companyname + '\'' +
                ", examcategory='" + examcategory + '\'' +
                ", checkupdate='" + checkupdate + '\'' +
                ", tjzt='" + tjzt + '\'' +
                ", status='" + status + '\'' +
                ", summarydoctor='" + summarydoctor + '\'' +
                ", summarydate='" + summarydate + '\'' +
                ", baoming='" + baoming + '\'' +
                ", baomingid='" + baomingid + '\'' +
                ", items=" + items +
                '}';
    }
}
