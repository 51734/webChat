package com.anf.blog.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.anf.blog.service.UserService;
import com.anf.chat.netty.domain.Friend;
import com.anf.chat.netty.domain.FriendGroup;
import com.anf.chat.netty.domain.Group;

@Controller
public class MessageController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/querygMemberList",method = RequestMethod.POST)
	@ResponseBody
	public String register(HashMap<String,Object> map,@RequestParam(value="userid")int userid,HttpServletRequest req) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<FriendGroup> friends = userService.getFriendGroup(userid);
		List<Group> groups = userService.getGroups(userid);
		returnMap.put("code",0);
		returnMap.put("msg","");
		resultMap.put("mine", "");
		A:for(FriendGroup fg:friends) {
			for(Friend f:fg.getList()) {
				if(f.getId()==userid) {
					resultMap.put("mine", f);
					fg.getList().remove(f);
					break A;
				}
			}
		}
		resultMap.put("friend", friends);
		resultMap.put("group", groups);
		returnMap.put("data",resultMap);
		String result = JSON.toJSONString(returnMap);
		System.out.println(result);
		return result;
	}
	
} 
