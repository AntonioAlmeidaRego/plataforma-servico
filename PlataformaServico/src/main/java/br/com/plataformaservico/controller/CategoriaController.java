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
import br.com.plataformaservico.model.Categoria;
import br.com.plataformaservico.persist.CategoriaPersist;
import br.com.plataformaservico.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController extends EntityController {
	@Autowired
	private CategoriaService categoriaService;
	private String uriFindAll = "categoria/findAll";
	@Autowired
	private CategoriaPersist categoriaPersist;

	@GetMapping("/cadastro")
	public ModelAndView cadastro(Categoria categoria) {
		return viewSession(new ModelAndView("categoria/cadastro").addObject("categoria", categoria));
	}

	@PostMapping("/save")
	public ModelAndView save(Categoria categoria) {

		ResponsePersist persist = categoriaPersist.persist(categoria, categoriaService);

		if (persist.getHttpStatus().is2xxSuccessful()) {
			setMessage(persist.getMessage());
			setRedirectStatus(HttpStatus.OK);
			return redirect(uriFindAll);
		}

		return cadastro(categoria).addObject("error", persist.getMessage());
	}

	@GetMapping("/findAll")
	public ModelAndView findAll() {
		return viewSession(
				view(new ModelAndView("categoria/lista").addObject("categorias", categoriaService.findAll())));
	}

	@GetMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") String id) {
		if (categoriaService.findByIdCrypt(id).isNotNull()) {
			return cadastro(categoriaService.findByIdCrypt(id));
		}
		setMessage("Não Encontrado, por favor tente outro!");
		setRedirectStatus(HttpStatus.NOT_FOUND);
		return redirect(uriFindAll);
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") String id) {
		Categoria categoria = categoriaService.findByIdCrypt(id);
		if (categoria.isNotNull()) {
			categoriaService.deleteById(categoria.getId());
			setMessage("Categoria removida com sucesso!");
			setRedirectStatus(HttpStatus.OK);
			return redirect(uriFindAll);
		}
		setMessage("Não Encontrado, por favor tente outro!");
		setRedirectStatus(HttpStatus.NOT_FOUND);
		return redirect(uriFindAll);
	}
}
