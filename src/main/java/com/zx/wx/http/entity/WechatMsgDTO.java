package com.zx.wx.http.entity;

import java.io.Serializable;

public class WechatMsgDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String errcode;
    private String errmsg;
    private String msgid;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    @Override
    public String toString() {
        return "WechatMsgDTO{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", msgid='" + msgid + '\'' +
                '}';
    }
}