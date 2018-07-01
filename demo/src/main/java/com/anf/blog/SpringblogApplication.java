package com.anf.blog;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.anf.blog.netty.WebChatServer;



@SpringBootApplication
@MapperScan("com.anf.blog.mapper")
@Controller
public class SpringblogApplication {
	public static final Map<Object,Object> cacheMap=new HashMap<Object,Object>();

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringblogApplication.class, args);
		cacheMap.put("beans", ctx);
    	WebChatServer wcs = (WebChatServer) ctx.getBean("webChatServer");
		wcs.start();
	}
}
