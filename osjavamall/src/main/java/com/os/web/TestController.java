package com.os.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class TestController implements Controller{

	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ModelAndView handleRequest(HttpServletRequest req,
//			HttpServletResponse res) throws Exception {
//
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("message", "hello world");
//		mv.setViewName("hello");
//		return mv;
//	}

}
