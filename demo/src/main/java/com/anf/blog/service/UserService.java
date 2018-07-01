package com.anf.blog.service;

import java.util.List;
import java.util.Map;

import com.anf.chat.netty.domain.FriendGroup;
import com.anf.chat.netty.domain.Group;

public interface UserService {

	List<FriendGroup> getFriendGroup(int userid);

	List<Group> getGroups(int userid);

	Map<String,Object> findUser(String key,int page);


}
