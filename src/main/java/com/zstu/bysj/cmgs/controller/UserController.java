package com.zstu.bysj.cmgs.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zstu.bysj.cmgs.model.entity.UserEntity;
import com.zstu.bysj.cmgs.service.UserService;
import com.zstu.bysj.cmgs.util.ResultWrapper;

/**
 * 用户
 * 
 * @author irving
 *
 */
@Controller
public class UserController {

	@Resource
	private UserService userService;

	/**
	 * 跳转登录界面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * 登录验证
	 */
	@RequestMapping(value = "/login/do", method = RequestMethod.POST)
	public @ResponseBody String loginUser(UserEntity loginUser, HttpServletRequest request) {
		UserEntity user = userService.checkUser(loginUser);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return ResultWrapper.success("", "登录成功");
		}
		return ResultWrapper.fail("用户名或密码错误");
	}

	/**
	 * 登出
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "login";
	}

	/**
	 * 注册界面
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	/**
	 * 用户注册
	 */
	@RequestMapping(value = "/register/do", method = RequestMethod.POST)
	public @ResponseBody String registerUser(UserEntity user) {
		if (userService.registerUser(user)) {
			return ResultWrapper.success("", "注册成功,请重新登录");
		}
		return ResultWrapper.fail("注册失败,此用户已存在");
	}

	/**
	 * 登录成功跳转首页
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		if (user == null) {
			mv.setViewName("login");
			return mv;
		}
		mv.addObject("user", user);
		mv.setViewName("jsp/index");
		return mv;
	}

	/**
	 * 个人信息
	 */
	@RequestMapping(value = "/user/message", method = RequestMethod.GET)
	public ModelAndView message(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		if (user == null) {
			mv.setViewName("login");
			return mv;
		}
		mv.addObject("id", user.getId());
		mv.setViewName("jsp/user/userMessage");
		return mv;
	}

	/**
	 * 根据id查询用户
	 */
	@RequestMapping(value = "/user/message/find", method = RequestMethod.GET)
	public @ResponseBody String findById(Integer id) {
		UserEntity user = userService.findById(id);
		return ResultWrapper.success(user);
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/user/message/save", method = RequestMethod.POST)
	public @ResponseBody String save(@RequestBody UserEntity user) {
		userService.updateUser(user);
		return ResultWrapper.success("个人信息更新成功");
	}

}
