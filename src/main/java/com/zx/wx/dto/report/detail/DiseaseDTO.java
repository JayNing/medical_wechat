package com.zx.wx.dto.report.detail;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/9.
 */
public class DiseaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String jbid;
    private String jbmc;
    private String jbms;
    private String jbjy;
    private String jyksdm;
    private String jyksmc;

    public String getJbid() {
        return jbid;
    }

    public void setJbid(String jbid) {
        this.jbid = jbid;
    }

    public String getJbmc() {
        return jbmc;
    }

    public void setJbmc(String jbmc) {
        this.jbmc = jbmc;
    }

    public String getJbms() {
        return jbms;
    }

    public void setJbms(String jbms) {
        this.jbms = jbms;
    }

    public String getJbjy() {
        return jbjy;
    }

    public void setJbjy(String jbjy) {
        this.jbjy = jbjy;
    }

    public String getJyksdm() {
        return jyksdm;
    }

    public void setJyksdm(String jyksdm) {
        this.jyksdm = jyksdm;
    }

    public String getJyksmc() {
        return jyksmc;
    }

    public void setJyksmc(String jyksmc) {
        this.jyksmc = jyksmc;
    }

    @Override
    public String toString() {
        return "DiseaseDTO{" +
                "jbid='" + jbid + '\'' +
                ", jbmc='" + jbmc + '\'' +
                ", jbms='" + jbms + '\'' +
                ", jbjy='" + jbjy + '\'' +
                ", jyksdm='" + jyksdm + '\'' +
                ", jyksmc='" + jyksmc + '\'' +
                '}';
    }
}
