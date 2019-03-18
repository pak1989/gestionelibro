package it.prova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.prova.service.AutoreService;

@Controller
public class HomeController {
	
	@Autowired
	private AutoreService autoreService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcome() {
		System.out.println(autoreService.listAllAutori());
		return new ModelAndView("home");
	}

}
