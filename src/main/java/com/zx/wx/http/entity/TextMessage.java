package com.zx.wx.http.entity;

import java.io.Serializable;

public class TextMessage implements Serializable{

	private static final long serialVersionUID = 1L;

	private String fromUserName;
	private String toUserName;
	private long createTime;
	private String msgType;
	private String Content;
	private int funcFlag;
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getFuncFlag() {
		return funcFlag;
	}
	public void setFuncFlag(int funcFlag) {
		this.funcFlag = funcFlag;
	}
	@Override
	public String toString() {
		return "TextMessage [fromUserName=" + fromUserName + ", toUserName=" + toUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", Content=" + Content + ", funcFlag=" + funcFlag + "]";
	}
}
