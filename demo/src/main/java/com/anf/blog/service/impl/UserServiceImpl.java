package com.anf.blog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.anf.blog.domain.ChatGroup;
import com.anf.blog.domain.User;
import com.anf.blog.mapper.AccountMapper;
import com.anf.blog.service.UserService;
import com.anf.chat.netty.domain.Friend;
import com.anf.chat.netty.domain.FriendGroup;
import com.anf.chat.netty.domain.Group;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public List<FriendGroup> getFriendGroup(int userid) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map<String,Object>> groupNames = accountMapper.getGroupName(userid);
		List<FriendGroup> fgs = new ArrayList<FriendGroup>();
		for(Map<String,Object> m:groupNames) {
			FriendGroup fg = new FriendGroup();
			List<Friend> friends = new ArrayList<Friend>();
			fg.setGroupname((String) m.get("groupname"));
			fg.setId((int) m.get("groupid"));
			List<User> user = accountMapper.getFriends((int)m.get("groupid"));
			for(User u:user) {
				Friend f = new Friend();
				f.setId(u.getId());
				f.setSign(u.getSign());
				f.setUsername(u.getUsername());
				f.setAvatar(u.getPersonPic());
				f.setStatus("online");
				friends.add(f);
			}
			fg.setList(friends);
			fgs.add(fg);
		}
		resultMap.put("friend", fgs);
		String res = JSON.toJSONString(resultMap);
		System.out.println(res);
		return fgs;
	}

	@Override
	public List<Group> getGroups(int userid) {
		// TODO Auto-generated method stub
		List<ChatGroup> cgs = accountMapper.getGroups(userid);
		List<Group> gs = new ArrayList<Group>();
		for(ChatGroup cg:cgs) {
			Group g = new Group();
			g.setId(cg.getGroupid());
			g.setAvatar(cg.getGroupavatar());
			g.setGroupname(cg.getGroupname());
			gs.add(g);
		}
		return gs;
	}

	@Override
	public Map<String,Object> findUser(String key,int page) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		key = "%"+key+"%";
		page = 6*(page-1);	    					 
		List<User> userList = accountMapper.getUser(key,page);
		for(User user : userList) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", user.getUserId());
			map.put("src", user.getPersonPic());
			map.put("worktype", user.getWorkType()==null?"":user.getWorkType());
			map.put("name", user.getUsername());
			map.put("address", user.getCity()==null?"火星":user.getCity());
			map.put("sign", user.getSign()==null?"懒到什么都没写":user.getSign());
			list.add(map);
		}
		resultMap.put("list", list);
		return resultMap;
	}

}
