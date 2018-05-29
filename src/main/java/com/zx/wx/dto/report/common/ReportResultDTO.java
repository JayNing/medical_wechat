package com.zx.wx.dto.report.common;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/9.
 */
public class ReportResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String resultcode;
    private String reason;
    private Object result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ReportResultDTO{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
