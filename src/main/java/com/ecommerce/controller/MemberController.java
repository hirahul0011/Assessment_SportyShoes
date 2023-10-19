package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class MemberController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	public String editProfile(ModelMap map, HttpServletRequest request) 
	{
	  HttpSession session = request.getSession();
	  if (session.getAttribute("user_id") == null) {
		  return "login";
	  }
	  User user = userService.getUserById((Long) session.getAttribute("user_id"));
	
	  map.addAttribute("pageTitle", "SPORTY SHOES - MEMBER EDIT PROFILE");
	  map.addAttribute("user", user);
	
	    return "edit-profile"; 
	}

}
