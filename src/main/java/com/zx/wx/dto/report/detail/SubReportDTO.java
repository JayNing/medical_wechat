package com.zx.wx.dto.report.detail;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/9.
 */
public class SubReportDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String rowid;
    private String reportname;
    private String reportno;
    private String position;
    private String findings;
    private String conclusion;
    private String doctor;
    private String checkdate;

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getReportname() {
        return reportname;
    }

    public void setReportname(String reportname) {
        this.reportname = reportname;
    }

    public String getReportno() {
        return reportno;
    }

    public void setReportno(String reportno) {
        this.reportno = reportno;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(String checkdate) {
        this.checkdate = checkdate;
    }

    @Override
    public String toString() {
        return "SubReportDTO{" +
                "rowid='" + rowid + '\'' +
                ", reportname='" + reportname + '\'' +
                ", reportno='" + reportno + '\'' +
                ", position='" + position + '\'' +
                ", findings='" + findings + '\'' +
                ", conclusion='" + conclusion + '\'' +
                ", doctor='" + doctor + '\'' +
                ", checkdate='" + checkdate + '\'' +
                '}';
    }
}
