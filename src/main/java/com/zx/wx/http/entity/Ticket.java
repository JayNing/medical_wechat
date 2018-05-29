package com.zx.wx.http.entity;

import java.io.Serializable;

public class Ticket implements Serializable{

	private static final long serialVersionUID = 1L;

	//{"errcode":0,"errmsg":"ok","ticket":"sM4AOVdWfPE4DxkXGEs8VMB_4vWYFNDGPA6rwT-Ks6FbTQPEJ3qxspvpd6M9QKESQUL9XgyAvxu42IMvcfwBOw","expires_in":7200}
	private int errcode;
	private String errmsg;
	private String ticket;
	private int expires_in;
	public Ticket() {
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
}
