package br.com.plataformaservico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataformaservico.model.Produto;
import br.com.plataformaservico.repository.ProdutoRepository;
import br.com.plataformaservico.util.EntityService;

@Service
public class ProdutoService implements EntityService<Produto>{
	@Autowired
	private ProdutoRepository repository;
	
	@Override
	public void save(Produto entity) {
		entity.setActive(true);
		repository.saveAndFlush(entity);
	}

	@Override
	public void update(Produto entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public void deleteById(Long id) {
		Produto produto = getOne(id);
		if(produto.isNotNull()) {
			produto.setActive(false);
			update(produto);
		}
	}

	@Override
	public List<Produto> findAll() {
		return repository.findAll();
	}

	@Override
	public Produto getOne(Long id) {
		return repository.getOne(id);
	}
	
	public Produto findByIdCrypt(String idCrypt) {
		return repository.findByIdCrypt(idCrypt);
	}

	@Override
	public List<Produto> findAllByTrash() {
		return repository.findAllByTrash();
	}

	@Override
	public void restore(Long id) {
		Produto produto = getOne(id);
		if(produto.isNotNull()) {
			produto.setActive(true);
			update(produto);
		}
	}

	@Override
	public void restoreAll(List<Produto> entities) {
		for(Produto produto : entities) {
			produto.setActive(true);
			update(produto);
		}
	}

	@Override
	public void restore(String idCrypt) {
		Produto produto = findByIdCrypt(idCrypt);
		if(produto.isNotNull()) {
			produto.setActive(true);
			update(produto);
		}
	}

	@Override
	public void restoreAll() {
		for(Produto produto : findAllByTrash()) {
			produto.setActive(true);
			update(produto);
		}
	}
	
}
