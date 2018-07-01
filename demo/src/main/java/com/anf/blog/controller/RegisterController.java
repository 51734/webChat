package com.anf.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anf.blog.ConfigurationFilter;
import com.anf.blog.domain.Account;
@Controller
public class RegisterController {
	@RequestMapping("/hello")
	public String index() {
		return "index";
	}
	@RequestMapping("/head")
	public String head(HttpServletRequest req) {
		return "base/header";
	}
	@RequestMapping("find")
	public String find() {
		return "user/find";
	}
	@RequestMapping("/prelogin")
	public String prelogin() {
		return "user/login";
	}
	
	@RequestMapping("/reg")
	public String reg() {
		return "user/reg";
	}
	@RequestMapping("/chat")
	public String chat() {
		return "chat";
	}
	@RequestMapping("/chat2")
	public String chat2() {
		return "chat2";
	}
	@RequestMapping("/normalset")
	public String normalset(HttpServletRequest req,Model model) {
		req = (HttpServletRequest)ConfigurationFilter.getRequestMap().get();
		if(req!=null) {
			Account account = (Account) req.getSession().getAttribute("isLogin");
			model.addAttribute("account",account);
			model.addAttribute("optype", "userset");
		}
		return "user/set";
	}
}
