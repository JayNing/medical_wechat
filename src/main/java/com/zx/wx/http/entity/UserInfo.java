package com.zx.wx.http.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author njj
 * 类名称： UserInfoDTO  
 * 描述：通过网页授权access_token获取用户基本信息
 */
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String openid;
	private String nickname;
	private int sex;
	private String province;
	private String city;
	private String country;
	private String headimgurl;
	private List<String> privilege;
	private String language;
	private String unionid;
	
	/**
	 {    "openid":" OPENID",    
	 " nickname": NICKNAME,   
	  "sex":"1",  
	    "province":"PROVINCE"  ,
	      "city":"CITY",   
	       "country":"COUNTRY",  
	          "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46", 
	           "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],  
	              "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
	  }
	  **/
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public List<String> getPrivilege() {
		return privilege;
	}
	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}
	public int getSex() {
		return sex;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	@Override
	public String toString() {
		return "UserInfo [openid=" + openid + ", nickname=" + nickname + ", sex=" + sex + ", province=" + province + ", city=" + city + ", country=" + country + ", headimgurl=" + headimgurl + ", privilege=" + privilege + ", language=" + language + ", unionid=" + unionid + "]";
	}
}
