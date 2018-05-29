package com.zx.common.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/9.
 */
public class SubmitReq implements Serializable {
    private static final long serialVersionUID = 1L;

    private String addSerial;
    private String apId;
    private String content;
    private String ecName;
    private String mac;
    private String mobiles;
    private String secretKey;
    private String sign;

    public SubmitReq(String apId, String ecName, String secretKey, String mobiles, String content, String sign, String addSerial) {
        this.apId = apId;
        this.ecName = ecName;
        this.secretKey = secretKey;
        this.mobiles = mobiles;
        this.content = content;
        this.sign = sign;
        this.addSerial = addSerial;
    }

    public String getApId() {
        return apId;
    }

    public void setApId(String apId) {
        this.apId = apId;
    }

    public String getEcName() {
        return ecName;
    }

    public void setEcName(String ecName) {
        this.ecName = ecName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAddSerial() {
        return addSerial;
    }

    public void setAddSerial(String addSerial) {
        this.addSerial = addSerial;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "SubmitReq{" +
                "apId='" + apId + '\'' +
                ", ecName='" + ecName + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", mobiles='" + mobiles + '\'' +
                ", content='" + content + '\'' +
                ", sign='" + sign + '\'' +
                ", addSerial='" + addSerial + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
