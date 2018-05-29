package com.zx.common.entity;

import java.io.Serializable;
import java.util.Date;

/******
 * ��ͨ�û�ʵ����
 * @author ���ĿƼ�
 */
public class NormalUser implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * ��ͨ�û�id
	 */
	private int userId;
	/*
	 * �û�����
	 */
	private String name;
	/*
	 * ΢�Ź���id
	 */
	private String openId;
	/*
	 * ΢���ǳ�
	 */
	private String nickName;
	/*
	 * ΢��ͷ��·��
	 */
	private String headImgUrl;
	/*
	 * �û��Ա�
	 */
	private int sex;
	/*
	 * �û��ֻ���
	 */
	private String mobile;
	/*
	 * �û����֤����
	 */
	private String idCard;
	/*
	 * �û���½����
	 */
	private String password;
	/*
	 * ��������
	 */
	private Date birthday;
	/*
	 * �û�����
	 */
	private int type;
	/*
	 * �û�ע��ʱ��
	 */
	private Date registerTime;
	/*
	 * �û�����ʱ��
	 */
	private Date createTime;

	private String secret;

	public NormalUser() {
	}

	public NormalUser(String name, String openId, String nickName, String headImgUrl, int sex, String mobile, String idCard, String password, Date birthday, int type, Date registerTime, Date createTime, String secret) {
		this.name = name;
		this.openId = openId;
		this.nickName = nickName;
		this.headImgUrl = headImgUrl;
		this.sex = sex;
		this.mobile = mobile;
		this.idCard = idCard;
		this.password = password;
		this.birthday = birthday;
		this.type = type;
		this.registerTime = registerTime;
		this.createTime = createTime;
		this.secret = secret;
	}

	public NormalUser(int userId, String name, String openId, String nickName, String headImgUrl, int sex,
					  String mobile, String idCard, String password, Date birthday, int type, Date registerTime, Date createTime) {
		super();
		this.userId = userId;
		this.name = name;
		this.openId = openId;
		this.nickName = nickName;
		this.headImgUrl = headImgUrl;
		this.sex = sex;
		this.mobile = mobile;
		this.idCard = idCard;
		this.password = password;
		this.birthday = birthday;
		this.type = type;
		this.registerTime = registerTime;
		this.createTime = createTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((headImgUrl == null) ? 0 : headImgUrl.hashCode());
		result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		result = prime * result + ((openId == null) ? 0 : openId.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((registerTime == null) ? 0 : registerTime.hashCode());
		result = prime * result + sex;
		result = prime * result + type;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NormalUser other = (NormalUser) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (headImgUrl == null) {
			if (other.headImgUrl != null)
				return false;
		} else if (!headImgUrl.equals(other.headImgUrl))
			return false;
		if (idCard == null) {
			if (other.idCard != null)
				return false;
		} else if (!idCard.equals(other.idCard))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		if (openId == null) {
			if (other.openId != null)
				return false;
		} else if (!openId.equals(other.openId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (registerTime == null) {
			if (other.registerTime != null)
				return false;
		} else if (!registerTime.equals(other.registerTime))
			return false;
		if (sex != other.sex)
			return false;
		if (type != other.type)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NormalUser{" +
				"userId=" + userId +
				", name='" + name + '\'' +
				", openId='" + openId + '\'' +
				", nickName='" + nickName + '\'' +
				", headImgUrl='" + headImgUrl + '\'' +
				", sex=" + sex +
				", mobile='" + mobile + '\'' +
				", idCard='" + idCard + '\'' +
				", password='" + password + '\'' +
				", birthday=" + birthday +
				", type=" + type +
				", registerTime=" + registerTime +
				", createTime=" + createTime +
				", secret='" + secret + '\'' +
				'}';
	}
}
