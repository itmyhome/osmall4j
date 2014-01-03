package com.os.web;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.os.entity.User;
import com.os.service.UserService;

@Controller
public class LoginController{
	
	@Resource
	private UserService userService;
	
	private Log log = LogFactory.getLog(this.getClass());
    
	@RequestMapping(value = "/index")
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value = "/loginCheck")
	public @ResponseBody Object loginCheck(HttpServletRequest request,User user){
		
		User userModel = userService.hasMatchUser(user.getUserName(),user.getPassword());
		
		if (userModel==null) {
			return JSON.toJSON("{success:false,info:'2222'}");
		} else {
			user = userService.findUserByCondition(userModel);
			user.setLastIp(request.getLocalAddr());
	//		user.setLastVisit(new Date());
			userService.loginSuccess(user);
			request.getSession().setAttribute("user", user);
			return JSON.toJSON("{success:true,info:'1111'}");
		}
	}
	@RequestMapping(value = "/logout")
	public String LogOut(){
		return "login";
	}
	
	@RequestMapping(value = "/main")
	public String jumpMain(){
		return "main";
	}
}
