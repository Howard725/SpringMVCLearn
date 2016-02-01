package com.seu.howard.study.crud.test;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SpringMVCTestExceptionHandler {
	
	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView handleException(Exception ex){
		System.out.println("error: " + ex);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.setViewName("error");
		
		return modelAndView;
	}

}
