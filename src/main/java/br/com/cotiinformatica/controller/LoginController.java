package br.com.cotiinformatica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller


public class LoginController {

	
	@RequestMapping(value="/")
	public ModelAndView login() {
	//define
	ModelAndView modelAndView = new ModelAndView("login");
	return modelAndView;
	}
}
