package com.zx.wx.http.entity;

import java.io.Serializable;

/**
 * @author Administrator
 * 微信菜单基类
 *
 */
public class MenuButton implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
      * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
	*/
	private String type = "";
	 
    /**
      * 菜单标题，不超过16个字节，子菜单不超过60个字节
    */
     private String name = "";

     
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "MenuButton [type=" + type + ", name=" + name + "]";
	}
     
     
}
