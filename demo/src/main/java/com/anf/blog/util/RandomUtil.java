package com.anf.blog.util;

import org.springframework.stereotype.Service;

@Service
public class RandomUtil {

	public String emailUtil(){
		int randomNum = (int) (Math.random()*10000);;
		return String.valueOf(randomNum);
	}
}
