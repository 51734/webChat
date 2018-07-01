package com.anf.chat.netty.domain;

import java.io.Serializable;

public class Group implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String groupname;
	private int id;
	private String avatar;
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupName) {
		this.groupname = groupName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
