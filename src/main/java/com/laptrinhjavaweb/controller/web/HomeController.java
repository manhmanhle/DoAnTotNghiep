package com.laptrinhjavaweb.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.DonViDTO;
import com.laptrinhjavaweb.service.IDonViService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
	@Autowired 
	private IDonViService Idonvi;
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("web/home");
		return mav;
	}
	@RequestMapping(value = "/trang-chu/report", method = RequestMethod.GET)
	public ModelAndView reportPage() {
		ModelAndView mav = new ModelAndView("web/report");
		DonViDTO donvi=new DonViDTO();
		List<DonViDTO> list = Idonvi.findAll();
		donvi.setListResult(list);
		mav.addObject("list", donvi);
		return mav;
	}
	@RequestMapping(value = "/trang-chu/dashboard", method = RequestMethod.GET)
	public ModelAndView homeDashBoard() {
		ModelAndView mav = new ModelAndView("web/Dashboard");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
}
