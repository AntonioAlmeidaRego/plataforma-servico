package br.com.plataformaservico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.plataformaservico.model.User;
import br.com.plataformaservico.service.UserService;

@Controller
public class IndexController {
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@GetMapping("/login")
	public ModelAndView entrar() {
		return new ModelAndView("login");
	}
	
	@GetMapping("/portal-user")
	public ModelAndView portal() {
		return new ModelAndView("portal");
	}
	
	@PostMapping("/logar")
	public ModelAndView logar(@RequestParam("email") String email, @RequestParam("password") String password) {
		User user = userService.login(email, password);
		if(user != null) {
			return portal();
		}
		return entrar().addObject("error", "usuário inválido!");
	}
}
