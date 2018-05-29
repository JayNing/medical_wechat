package com.zx.wx.http.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * 微信菜单类
 */
public class Menu  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/* 定义名称与json中一致，不然解析名称对不上 */
     private List<MenuButton> button;

	public List<MenuButton> getButton() {
		return button;
	}

	public void setButton(List<MenuButton> button) {
		this.button = button;
	}

	@Override
	public String toString() {
		return "Menu [button=" + button + "]";
	}
	
}
