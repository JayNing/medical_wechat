package com.zx.wx.http.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author njj
 * 类名称： UserInfoDTO  
 * 创建时间 ： 2016年6月7日 上午11:33:45   
 * 描述：通过基础access_token获取用户基本信息
 */
public class UserInfoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int subscribe;//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private String openid;
	private String nickname;
	private int sex = -1;
	private String language;
	private String city;
	private String province;
	private String country;
	private String headimgurl;
	private long subscribe_time;//用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	private String unionid;
	private String remark;//公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	private int groupid;//	用户所在的分组ID
	private List<String> tagid_list;
	/**  subscribe_scene  返回用户关注的渠道来源，
	 * ADD_SCENE_SEARCH 公众号搜索，
	 * ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，
	 * ADD_SCENE_PROFILE_CARD 名片分享，
	 * ADD_SCENE_QR_CODE 扫描二维码，
	 * ADD_SCENEPROFILE LINK 图文页内名称点击，
	 * ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，
	 * ADD_SCENE_PAID 支付后关注，
	 * ADD_SCENE_OTHERS 其他*/
	private String subscribe_scene;
	/**
	 * qr_scene 二维码扫码场景（开发者自定义）
	 * */
	private int qr_scene;
	/**
	 * qr_scene_str 二维码扫码场景描述（开发者自定义）
	 * */
	private String qr_scene_str;
	
	public String getSubscribe_scene() {
		return subscribe_scene;
	}
	public void setSubscribe_scene(String subscribe_scene) {
		this.subscribe_scene = subscribe_scene;
	}
	public int getQr_scene() {
		return qr_scene;
	}
	public void setQr_scene(int qr_scene) {
		this.qr_scene = qr_scene;
	}
	public String getQr_scene_str() {
		return qr_scene_str;
	}
	public void setQr_scene_str(String qr_scene_str) {
		this.qr_scene_str = qr_scene_str;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
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
	public long getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public List<String> getTagid_list() {
		return tagid_list;
	}
	public void setTagid_list(List<String> tagid_list) {
		this.tagid_list = tagid_list;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	@Override
	public String toString() {
		return "UserInfoDTO [subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname + ", sex=" + sex
				+ ", language=" + language + ", city=" + city + ", province=" + province + ", country=" + country
				+ ", headimgurl=" + headimgurl + ", subscribe_time=" + subscribe_time + ", unionid=" + unionid
				+ ", remark=" + remark + ", groupid=" + groupid + ", tagid_list=" + tagid_list + ", subscribe_scene="
				+ subscribe_scene + ", qr_scene=" + qr_scene + ", qr_scene_str=" + qr_scene_str + "]";
	}
}
