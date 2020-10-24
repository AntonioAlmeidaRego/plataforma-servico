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
import br.com.plataformaservico.model.Produto;
import br.com.plataformaservico.persist.ProdutoPersist;
import br.com.plataformaservico.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController extends EntityController {
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ProdutoPersist produtoPersist;
	private String uriFindAll = "produto/findAll";

	@GetMapping("/cadastro")
	public ModelAndView cadastro(Produto produto) {
		return viewSession(new ModelAndView("produto/cadastro").addObject("produto", produto));
	}

	@PostMapping("/saveProduto")
	public ModelAndView save(Produto produto) {
		ResponsePersist persist = produtoPersist.persist(produto, produtoService);

		if (persist.getHttpStatus().is2xxSuccessful()) {
			setMessage(persist.getMessage());
			setRedirectStatus(persist.getHttpStatus());
			return redirect(uriFindAll);
		}

		return cadastro(produto);
	}

	@GetMapping("/findAll")
	public ModelAndView findAll() {
		return viewSession(view(new ModelAndView("produto/lista").addObject("lista", produtoService.findAll())));
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") String idCrypt) {
		Produto produto = produtoService.findByIdCrypt(idCrypt);
		if (produto.isNotNull()) {
			produtoService.deleteById(produto.getId());
			setMessage("Produto removido com sucesso!");
			setRedirectStatus(HttpStatus.OK);
			return redirect(uriFindAll);
		}
		setMessage("Não encontro, tente outro!");
		setRedirectStatus(HttpStatus.NOT_FOUND);
		return redirect(uriFindAll);
	}

	@GetMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") String idCrypt) {
		Produto produto = produtoService.findByIdCrypt(idCrypt);
		if (produto.isNotNull()) {
			return cadastro(produto);
		}
		setMessage("Não encontro, tente outro!");
		setRedirectStatus(HttpStatus.NOT_FOUND);
		return redirect(uriFindAll);
	}
}
