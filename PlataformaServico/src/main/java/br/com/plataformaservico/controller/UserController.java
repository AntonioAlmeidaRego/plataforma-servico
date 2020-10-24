package br.com.plataformaservico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.plataformaservico.model.User;
import br.com.plataformaservico.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/registro")
	public ModelAndView registro(User user) {
		return new ModelAndView("user/registro").addObject("user", user);
	}
	
	@PostMapping("/saveRegistro")
	public ModelAndView save(User user) {
		userService.save(user);
		return registro(new User()).addObject("success", "Usuário registrado com sucesso!");
	}

}
