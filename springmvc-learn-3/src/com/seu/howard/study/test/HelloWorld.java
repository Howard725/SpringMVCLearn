package com.seu.howard.study.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {
	
	@Autowired
	private UserService userService;
	
	public HelloWorld() {
		System.out.println("HelloWorld constructor...");
	}
	
	@RequestMapping("/helloWorld")
	public String helloWorld(){
		System.out.println(userService);
		return "success";
	}

}
