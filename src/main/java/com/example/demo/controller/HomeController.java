package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class HomeController {

	@GetMapping({ "/", "home" })
	public String home() {
		return "home";
	}

	// hello?name=name
	@GetMapping("hello")
	public String sayHello(@RequestParam(value = "name", required = false, defaultValue = "Guillaume") String name,
			Model model) {
		model.addAttribute("name", name);
		return "hello";
	}

	// hey?name=name
	@GetMapping("hey")
	public String saySalutation(@RequestParam String name, ModelMap model) {
		model.addAttribute("nom", name);
		return "hello";
	}

	// salut?myName=myName
	@GetMapping("salut")
	public ModelAndView saySalut(@RequestParam String myName) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("myName", myName);
		mv.setViewName("hello");
		return mv;
	}

	// hello/name
	@GetMapping("/hello/{name}")
	public void sayHelloTo(@PathVariable String name) {
		System.out.println("Hello " + name);
	}

	// Redirect to
	@GetMapping("/bonjour")
	public RedirectView accueil() {
		RedirectView rv = new RedirectView();
		rv.setUrl("home");
		return rv;
	}

	@GetMapping("/goodMorning")
	public RedirectView gretings() {
		RedirectView rv = new RedirectView("hello");
		rv.addStaticAttribute("name", "Benjamin");
		return rv;
	}

	@GetMapping("/hi")
	public String accueil(Model model) {
		System.out.println(model);
		return "redirect:hello?nom=dalton";
	}

}
