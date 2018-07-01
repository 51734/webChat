package com.anf.blog.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private String username;
	/*
	 * m:man  w:woman
	 */
	private String gender;
	/*
	 * 工作认证
	 */
	private String workType;
	private Date createTime;
	private Date modifyTime;
	private Date lastOnline;
	private String city;
	/**
	 * 说点什么把
	 */
	private String sign;
	private String friendIds;
	private List<User> myGroup;
	private String teamGroup;
	private String question;
	private String answer;
	private String personPic;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getLastOnline() {
		return lastOnline;
	}
	public void setLastOnline(Date lastOnline) {
		this.lastOnline = lastOnline;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFriendIds() {
		return friendIds;
	}
	public void setFriendIds(String friendIds) {
		this.friendIds = friendIds;
	}
	public List<User> getMyGroup() {
		return myGroup;
	}
	public void setMyGroup(List<User> myGroup) {
		this.myGroup = myGroup;
	}
	public String getTeamGroup() {
		return teamGroup;
	}
	public void setTeamGroup(String teamGroup) {
		this.teamGroup = teamGroup;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getPersonPic() {
		return personPic;
	}
	public void setPersonPic(String personPic) {
		this.personPic = personPic;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
	
	
}
