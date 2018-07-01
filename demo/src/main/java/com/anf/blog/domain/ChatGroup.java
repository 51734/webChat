package com.anf.blog.domain;

import java.io.Serializable;
import java.util.Date;

public class ChatGroup implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int groupid;
	private String groupname;
	private Date createtime;
	private String groupsign;
	private String groupavatar;
	private String grouptype;
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getGroupsign() {
		return groupsign;
	}
	public void setGroupsign(String groupsign) {
		this.groupsign = groupsign;
	}
	public String getGroupavatar() {
		return groupavatar;
	}
	public void setGroupavatar(String groupavatar) {
		this.groupavatar = groupavatar;
	}
	public String getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
	
	
	
}
