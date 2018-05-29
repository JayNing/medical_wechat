package com.zx.wx.dto.report;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/8.
 */
public class ReportDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private String reportId;
    private List<CheckItemDTO> checkItemList;
    private Date reportDate;
    private String reportDateShow;
    private String username;
    private String mobile;
    private String idCard;
    private String headImgUrl;

    public String getReportDateShow() {
        return reportDateShow;
    }

    public void setReportDateShow(String reportDateShow) {
        this.reportDateShow = reportDateShow;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public List<CheckItemDTO> getCheckItemList() {
        return checkItemList;
    }

    public void setCheckItemList(List<CheckItemDTO> checkItemList) {
        this.checkItemList = checkItemList;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "reportId='" + reportId + '\'' +
                ", checkItemList=" + checkItemList +
                ", reportDate=" + reportDate +
                ", reportDateShow='" + reportDateShow + '\'' +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", idCard='" + idCard + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                '}';
    }
}
