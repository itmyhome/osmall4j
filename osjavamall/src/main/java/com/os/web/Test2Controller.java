package com.os.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class Test2Controller extends AbstractController{

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		ModelAndView mv = new ModelAndView(); 
//		//添加模型数据  可以是任意的POJO对象
//		mv.addObject("message", "测试AbstractController"); 
//		//设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
//		mv.setViewName("hello"); 
//		return mv; 
		response.getWriter().write("<a href=''>this</a>"); 
		return null; 
	}

	

}
