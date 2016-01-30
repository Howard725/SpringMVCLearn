package com.seu.howard.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seu.howard.study.view.HelloView;

@Controller
public class HelloWorld {
	
	@RequestMapping("/testRedirect")
	public String testRedirect(){
		System.out.println("testRedirct");
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/testView")
	public String helloView(){
		System.out.println("testView");
		return "helloView";
	}

	@RequestMapping("/helloworld")
	public String helloWorld(){
		System.out.println("Hello World!");
		return "success";
	}
	
}
