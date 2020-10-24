package br.com.plataformaservico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entityquerydb.annotations.ExecuteQuery;
import br.com.plataformaservico.model.Categoria;
import br.com.plataformaservico.repository.CategoriaRepository;
import br.com.plataformaservico.util.EntityService;

@Service
public class CategoriaService implements EntityService<Categoria> {
	@Autowired
	private CategoriaRepository repository;

	@Override
	public void save(Categoria entity) {
		entity.setActive(true); 
		repository.saveAndFlush(entity);
	}
	
	@Override
	public void update(Categoria entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public void deleteById(Long id) {
		Categoria categoria = getOne(id);
		if(categoria.isNotNull()) {
			categoria.setActive(false);
			update(categoria);
		}
	}

	@Override
	public List<Categoria> findAll() {
		return repository.findAll();
	}

	@Override
	public Categoria getOne(Long id) {
		return repository.getOne(id);
	}
	
	public Categoria findByIdCrypt(String idCrypt) {
		return repository.findByIdCrypt(idCrypt);
	}
	
	@ExecuteQuery
	public Categoria exist(String nome) {
		return repository.exist(nome);
	}

	@Override
	public List<Categoria> findAllByTrash() {
		return repository.findAllByTrash();
	}

	@Override
	public void restore(Long id) {
		Categoria categoria = getOne(id);
		if(categoria.isNotNull()) {
			categoria.setActive(true);
			update(categoria);
		}
	}

	@Override
	public void restore(String idCrypt) {
		Categoria categoria = findByIdCrypt(idCrypt);
		if(categoria.isNotNull()) {
			categoria.setActive(true);
			update(categoria);
		}
	}

	@Override
	public void restoreAll(List<Categoria> entities) {
		for(Categoria categoria : entities) {
			categoria.setActive(true);
			update(categoria);
		}
	}

	@Override
	public void restoreAll() {
		for(Categoria categoria : findAllByTrash()) {
			categoria.setActive(true);
			update(categoria);
		}
	}
	
}
