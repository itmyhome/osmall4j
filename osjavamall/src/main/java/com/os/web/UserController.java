package com.os.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.os.entity.User;
import com.os.pageutil.PageInfoGrid;
import com.os.service.UserService;

public class UserController extends MultiActionController {
	@Resource
	private UserService userService;
	// 逻辑视图名 通过依赖注入方式注入，可配置
	private String createView;
	private String updateView;
	private String deleteView;
	private String listView;
	private String redirectToListView;

	public String getCreateView() {
		return createView;
	}

	public void setCreateView(String createView) {
		this.createView = createView;
	}

	public String getUpdateView() {
		return updateView;
	}

	public void setUpdateView(String updateView) {
		this.updateView = updateView;
	}

	public String getDeleteView() {
		return deleteView;
	}

	public void setDeleteView(String deleteView) {
		this.deleteView = deleteView;
	}

	public String getListView() {
		return listView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	public String getRedirectToListView() {
		return redirectToListView;
	}

	public void setRedirectToListView(String redirectToListView) {
		this.redirectToListView = redirectToListView;
	}

	public String create(HttpServletRequest request,
			HttpServletResponse response, User user) {
		if ("GET".equals(request.getMethod())) {
			// 如果是get请求 我们转向 新增页面
			return getCreateView();
		}
		userService.create(user);
		// 直接重定向到列表页面
		return getRedirectToListView();
	}

	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		PageInfoGrid pageInfoGrid = new PageInfoGrid();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userList", userService.getList(pageInfoGrid));
		mv.setViewName(getListView());
		return mv;
	}

	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response, User user) {
		if ("GET".equals(request.getMethod())) {
			// 如果是get请求 我们转向更新页面
			ModelAndView mv = new ModelAndView();
			// 查询要更新的数据
			mv.addObject("user",userService.findUserByCondition(user));
			mv.setViewName(getUpdateView());
			return mv;
		}
		userService.update(user);
		// 直接重定向到列表页面
		return new ModelAndView(getRedirectToListView());
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, User user) {
		if ("GET".equals(request.getMethod())) {
			// 如果是get请求 我们转向删除页面
			ModelAndView mv = new ModelAndView();
			// 查询要删除的数据
			mv.addObject("user", userService.findUserByCondition(user));
			mv.setViewName(getDeleteView());
			return mv;
		}
		userService.delete(user);
		// 直接重定向到列表页面
		return new ModelAndView(getRedirectToListView());
	}
}
