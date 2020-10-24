package br.com.plataformaservico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.entityquerydb.controller.EntityController;
import br.com.entityquerydb.persist.ResponsePersist;
import br.com.plataformaservico.model.Funcionario;
import br.com.plataformaservico.persist.FuncionarioPersist;
import br.com.plataformaservico.service.FuncionarioService;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController extends EntityController {
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private FuncionarioPersist funcionarioPersist;
	private String uriFindAll = "funcionario/findAll";

	@GetMapping("/cadastro")
	public ModelAndView cadastro(Funcionario funcionario) {
		return viewSession(new ModelAndView("funcionario/cadastro"));
	}

	@PostMapping("/save")
	public ModelAndView save(Funcionario funcionario) {

		ResponsePersist persist = funcionarioPersist.persist(funcionario, funcionarioService);

		if (persist.getHttpStatus().is2xxSuccessful()) {
			setMessage(persist.getMessage());
			setRedirectStatus(persist.getHttpStatus());
			return redirect(uriFindAll);
		}

		return cadastro(funcionario);
	}

	@GetMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") String idCrypt) {
		Funcionario funcionario = funcionarioService.findByIdCrypt(idCrypt);

		if (funcionario.isNotNull()) {
			return cadastro(funcionario);
		}
		setMessage("Não encontro, tente outro!");
		setRedirectStatus(HttpStatus.NOT_FOUND);
		return redirect(uriFindAll);
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") String idCrypt) {
		Funcionario funcionario = funcionarioService.findByIdCrypt(idCrypt);

		if (funcionario.isNotNull()) {
			funcionarioService.deleteById(funcionario.getId());
			setMessage("Funcionario(a) removido(a) com sucesso!");
			setRedirectStatus(HttpStatus.OK);
			return redirect(uriFindAll);
		}
		setMessage("Não encontro, tente outro!");
		setRedirectStatus(HttpStatus.NOT_FOUND);
		return redirect(uriFindAll);
	}
	
	@GetMapping("/findAll")
	public ModelAndView findAll() {
		return viewSession(view(new ModelAndView("funcionario/lista").addObject("funcionarios", funcionarioService.findAll())));
	}
}
