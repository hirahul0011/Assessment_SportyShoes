package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.entity.Admin;
import com.ecommerce.entity.Category;
import com.ecommerce.service.AdminService;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.PurchaseItemService;
import com.ecommerce.service.PurchaseService;
import com.ecommerce.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PurchaseItemService purchaseItemService;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public String login(ModelMap map, HttpServletRequest request) 
	{
	  map.addAttribute("pageTitle", "ADMIN LOGIN");
	    return "admin/login"; 
	}
	@RequestMapping(value = "/adminloginaction", method = RequestMethod.POST)
	public String loginAction(ModelMap map, HttpServletRequest request, 
			 @RequestParam(value="admin_id", required=true) String adminId,
			 @RequestParam(value="admin_pwd", required=true) String adminPwd) 
	{
	
	  Admin admin = adminService.authenticate(adminId, adminPwd);
	  if (admin == null) { 
		  map.addAttribute("error", "Admin login failed");
		  return "admin/login";
	  }
	  // store admin id in session
	  HttpSession session = request.getSession();
	  session.setAttribute("admin_id", admin.getID());
	
	    return "admin/dashboard"; 
	}
	@RequestMapping(value = "/admincategories", method = RequestMethod.GET)
	public String categories(ModelMap map, HttpServletRequest request) 
	{
	  // check if session is still alive
	  HttpSession session = request.getSession();
	  if (session.getAttribute("admin_id") == null) {
		  return "admin/login";
	  } 
	
	  List<Category> list = categoryService.getAllCategories();
	  map.addAttribute("list", list);
	  map.addAttribute("pageTitle", "ADMIN SETUP PRODUCT CATEGORIES");
	    return "admin/categories"; 
	}

}