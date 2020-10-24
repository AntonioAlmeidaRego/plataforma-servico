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
		repository.saveAndFlush(entity);
	}
	
	@Override
	public void update(Categoria entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
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
}
