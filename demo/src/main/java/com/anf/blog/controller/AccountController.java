package com.anf.blog.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anf.blog.ConfigurationFilter;
import com.anf.blog.domain.Account;
import com.anf.blog.domain.User;
import com.anf.blog.mapper.AccountMapper;
import com.anf.blog.service.UserService;
import com.anf.blog.util.RandomUtil;
import com.anf.blog.util.RedisUtil;

@Controller
public class AccountController {
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private JavaMailSender mailSerder;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private RandomUtil randomUtil;
	
	@Autowired
	private UserService userService;
	
	@Value("${spring.mail.username}")
	private String Sender;
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> register(Account accountInfo,@RequestParam(value="emcode")String emcode) {
		String redisCode = (String) redisUtil.get("emailCode");
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		if(!emcode.equals(redisCode)) {
			m.put("status", 100);
			return m;
		}
		int userId = accountMapper.getId();
		accountInfo.setUserid(userId);
		accountMapper.userInsert(accountInfo);
		accountMapper.personalInsert(accountInfo);
		m.put("status", 200);
		return m;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String,Object> login(@RequestParam(value="email",required = true)String username,
			@RequestParam(value="password",required = true)String password,HttpServletRequest req) {
			List<Account> accountInfos = accountMapper.accontLogin(username, password);
			HashMap<String,Object> map = new HashMap<String,Object>();
			if(!(accountInfos.size()>0)) {
				map.put("status",100);
				map.put("message","密码错误！");
			}else {
				Account account = accountInfos.get(0);
				req = (HttpServletRequest) ConfigurationFilter.getRequestMap().get();
				req.getSession().setAttribute("isLogin",account);
				map.put("status",200);
				map.put("user", account);
				redisUtil.set("status:"+account.getUserid(), "online");
			}
			return map;
	}
	
	@RequestMapping(value="/emailSend",method = RequestMethod.POST)
	@ResponseBody
	public String emailSend(@RequestParam(value="email")String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		String emailCode = randomUtil.emailUtil();
		redisUtil.set("emailCode", emailCode);
		message.setFrom(Sender);
		message.setTo(email);
		message.setSubject("注册");
		message.setText(emailCode);
		mailSerder.send(message);
		return "success";
	}
	
	@RequestMapping(value="/beforeLogin",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> beforeLogin(HttpServletRequest req,Model model) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		req = (HttpServletRequest) ConfigurationFilter.getRequestMap().get();
		Account user = (Account) req.getSession().getAttribute("isLogin");
		if(user!=null){
			map.put("status", 200);
			map.put("user", user);
		}else{
			map.put("status", 100);
		}
		return map;
	}
	
	@RequestMapping(value="/home")
	public String homeCenter(HttpServletRequest req,Model model){
		req = (HttpServletRequest)ConfigurationFilter.getRequestMap().get();
		Account account = (Account) req.getSession().getAttribute("isLogin");
		List<User> persons = accountMapper.getPerson(account.getUserid());
		if(persons.size()>0) {
			model.addAttribute("account",account);
			model.addAttribute("person",persons.get(0));
		}
		return "user/home";
	}
	
	@RequestMapping(value="/finduser")
	@ResponseBody
	public Map<String, Object> findUser(@RequestParam(value="key",required = true)String key,@RequestParam(value="page",required = true)int page){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultJson = userService.findUser(key,page);
		long count = 0;
		if(page==1) {
			key = "%"+key+"%";
			count = accountMapper.findUserCount(key);
			map.put("count",count);
		}
		map.put("result", resultJson);
		return map;
	}
	 
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest req){
		req = (HttpServletRequest)ConfigurationFilter.getRequestMap().get();
		req.getSession().invalidate();
		return "index";
	}
	
	@RequestMapping(value="/v",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> test() {
		System.out.println(123);
//		String s = (String) map.get("user");
//		System.out.println(s);
		Map m = new HashMap();
		m.put("key","123");
		return m;
	}
} 
