package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping(value = "/editprofileaction", method = RequestMethod.POST)
	public String editProfileAction(ModelMap map,
			HttpServletRequest request, 
			@RequestParam(value="user_id", required=true) String userId,
			 @RequestParam(value="pwd", required=true) String pwd,
			 @RequestParam(value="pwd2", required=true) String pwd2,
			 @RequestParam(value="fname", required=true) String fname,
			 @RequestParam(value="lname", required=true) String lname,
			 @RequestParam(value="age", required=true) String age,
			 @RequestParam(value="address", required=true) String address) 
	{
	
	  HttpSession session = request.getSession();
	  if (session.getAttribute("user_id") == null) {
		  return "login";
	  }
	
	  User user = userService.getUserById((Long) session.getAttribute("user_id"));
	  map.addAttribute("user", user);
	
	  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
		  map.addAttribute("error", "Error , Incomplete passwords submitted.");
		  return "edit-profile";
	  }
	
	  if (!pwd.equals(pwd2)) {
		  map.addAttribute("error", "Error , Passwords do not match.");
		  return "edit-profile";
	  }
	
	  if (fname == null || fname.equals("")) {
		  map.addAttribute("error", "First name is required.");
		  return "edit-profile";
	  }
	
	  if (lname == null || lname.equals("")) {
		  map.addAttribute("error", "Last name is required.");
		  return "edit-profile";
	  }
	  if (age == null || age.equals("")) {
		  age = "0";
	  }
	
	
	
	  user.setFname(fname);
	  user.setLname(lname);
	  user.setAge(Integer.parseInt(age));
	  user.setAddress(address);
	  user.setPwd(pwd);
	
	  userService.updateUser(user);
	
	    return "redirect:dashboard"; 
	}

}
