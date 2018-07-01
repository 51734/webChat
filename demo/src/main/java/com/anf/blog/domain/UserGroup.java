package com.anf.blog.domain;

import java.io.Serializable;
import java.util.Date;

public class UserGroup implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private int groupId;
	private int userId;
	private Date joinTime;
	private Date lastActive;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public Date getLastActive() {
		return lastActive;
	}
	public void setLastActive(Date lastActive) {
		this.lastActive = lastActive;
	}
	
	

}
