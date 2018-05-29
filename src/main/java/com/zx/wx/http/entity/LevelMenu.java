package com.zx.wx.http.entity;

import java.io.Serializable;
import java.util.List;

/**
  * 导航菜单
  * @author Damon
  */
public class LevelMenu extends MenuButton implements Serializable{
 
	private static final long serialVersionUID = 1L;
	/**
     * 包含多个子菜单 定义名称与json中一致，不然解析名称对不上
    */
    private List<SubMenuButton> sub_button;
	public List<SubMenuButton> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<SubMenuButton> sub_button) {
		this.sub_button = sub_button;
	}
	@Override
	public String toString() {
		return "LevelMenu [sub_button=" + sub_button + "]";
	}
 }