package com.zx.wx.dto.report.detail;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */
public class MenuDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String xh;
    private String tjbh;
    private String orgid;
    private String checkupdate;
    private String name;
    private String idcard;
    private String sex;
    private String companyname;
    private String examcategory;
    private String baomingname;
    private String summary;
    private String advice;
    private String conclusion;
    private String summarydoctor;
    private String summarydate;
    private String verifydoctor;
    private String verifydate;
    private String explanation;
    private String status;
    private String address;
    private String phone;
    private String married;
    private String email;
    private String nation;
    private String country;
    private List<SubReportDTO> subreports;
    private List<ReportItemDTO> reportitems;
    private List<DiseaseDTO> Diseases;

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getTjbh() {
        return tjbh;
    }

    public void setTjbh(String tjbh) {
        this.tjbh = tjbh;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getCheckupdate() {
        return checkupdate;
    }

    public void setCheckupdate(String checkupdate) {
        this.checkupdate = checkupdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getBaomingname() {
        return baomingname;
    }

    public void setBaomingname(String baomingname) {
        this.baomingname = baomingname;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
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

    public String getVerifydoctor() {
        return verifydoctor;
    }

    public void setVerifydoctor(String verifydoctor) {
        this.verifydoctor = verifydoctor;
    }

    public String getVerifydate() {
        return verifydate;
    }

    public void setVerifydate(String verifydate) {
        this.verifydate = verifydate;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<SubReportDTO> getSubreports() {
        return subreports;
    }

    public void setSubreports(List<SubReportDTO> subreports) {
        this.subreports = subreports;
    }

    public List<ReportItemDTO> getReportitems() {
        return reportitems;
    }

    public void setReportitems(List<ReportItemDTO> reportitems) {
        this.reportitems = reportitems;
    }

    public List<DiseaseDTO> getDiseases() {
        return Diseases;
    }

    public void setDiseases(List<DiseaseDTO> diseases) {
        Diseases = diseases;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "xh='" + xh + '\'' +
                ", tjbh='" + tjbh + '\'' +
                ", orgid='" + orgid + '\'' +
                ", checkupdate='" + checkupdate + '\'' +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", sex='" + sex + '\'' +
                ", companyname='" + companyname + '\'' +
                ", examcategory='" + examcategory + '\'' +
                ", baomingname='" + baomingname + '\'' +
                ", summary='" + summary + '\'' +
                ", advice='" + advice + '\'' +
                ", conclusion='" + conclusion + '\'' +
                ", summarydoctor='" + summarydoctor + '\'' +
                ", summarydate='" + summarydate + '\'' +
                ", verifydoctor='" + verifydoctor + '\'' +
                ", verifydate='" + verifydate + '\'' +
                ", explanation='" + explanation + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", married='" + married + '\'' +
                ", email='" + email + '\'' +
                ", nation='" + nation + '\'' +
                ", country='" + country + '\'' +
                ", subreports=" + subreports +
                ", reportitems=" + reportitems +
                ", Diseases=" + Diseases +
                '}';
    }
}
