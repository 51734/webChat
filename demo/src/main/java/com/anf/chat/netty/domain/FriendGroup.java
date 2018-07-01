package com.anf.chat.netty.domain;

import java.io.Serializable;
import java.util.List;

public class FriendGroup implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String groupname;
	private int id;
	private List<Friend> list;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Friend> getList() {
		return list;
	}
	public void setList(List<Friend> list) {
		this.list = list;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}
