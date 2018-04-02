package edu.neu.blackboard.web;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.neu.blackboard.domain.*;
import edu.neu.blackboard.service.*;

@Controller

public class LoginController {
	@Autowired
	private LoginService loginService;
	
	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginpage(Model model){
		
		return"login";
	}
	@RequestMapping(value="/homey", method = RequestMethod.GET)
	public String homeypage(Model model){
		
		return"homey";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String processForm(@Valid Users user,BindingResult result, ModelMap model,HttpSession session) {
		if(user.getEmail().contains("<script>")||user.getEmail().contains("'")||user.getEmail().contains("=")||user.getEmail().contains("<")||user.getEmail().contains(">"))
	    {	
		model.put("cheat", "Dont try to hack the system you will get caught, valid inputs are allowed");
		return "login";
		}
		else if(user.getPassword().contains("<script>")||user.getPassword().contains("'")||user.getPassword().contains("=")||user.getPassword().contains("<")||user.getPassword().contains(">"))
	    {	
		model.put("cheat", "Dont try to hack the system you will get caught, valid inputs are allowed");
		return "login";
		}
		
		
		else{		
if(user.getEmail().equalsIgnoreCase("admin") && user.getPassword().equalsIgnoreCase("root")){
			
			session.setAttribute("name",user.getUserName());
			session.setAttribute("email",user.getEmail());
			session.setAttribute("address",user.getAddress());
			
			return "adminpage";
		}
else if(loginService.validateUser(user) != null){
			
			
	
			session.setAttribute("name",user.getUserName());
			session.setAttribute("email",user.getEmail());
			session.setAttribute("address",loginService.validateUser(user).getAddress());
			
			return "redirect:home";
			}
		
	else{
			model.put("errormessage","Invalid Credentials");
			return "login";
		}}
		}
	
		@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session)
	{

		session.invalidate();
		return "redirect:home";
		
	}

	@RequestMapping(value="/manage", method=RequestMethod.GET)
	public String manage(Model model, HttpSession session){
		if(session.getAttribute("email").toString().equalsIgnoreCase("admin")){
			
		return"manage";
		}
		else{
			return "login";
		}}
	@RequestMapping(value="/cart",method=RequestMethod.GET)
	public String cart(ModelMap model,HttpSession session)
	{
		 return "cart";
	}
	@RequestMapping(value="/aboutus",method=RequestMethod.GET)
	public String aboutus(ModelMap model,HttpSession session)
	{
		 return "aboutus";
	}
	@RequestMapping(value="/Terms",method=RequestMethod.GET)
	public String Terms(ModelMap model,HttpSession session)
	{
		 return "Terms";
	}
	@RequestMapping(value="/contact",method=RequestMethod.GET)
	public String contact(ModelMap model,HttpSession session)
	{
		 return "contact";
	}
	@RequestMapping(value="/userlist",method=RequestMethod.GET)
	public String userlist(ModelMap model,HttpSession session)
	{ if(session.getAttribute("email").toString().equalsIgnoreCase("admin")){
		model.addAttribute(new Users());
	  model.addAttribute("userlist",loginService.list());
		 return "userlist";}
	else{
		return"login";
		
	}
	}

}

