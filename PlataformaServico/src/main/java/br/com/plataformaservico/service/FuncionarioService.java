package br.com.plataformaservico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entityquerydb.annotations.ExecuteQuery;
import br.com.plataformaservico.model.Funcionario;
import br.com.plataformaservico.repository.FuncionarioRepository;
import br.com.plataformaservico.util.EntityService;

@Service
public class FuncionarioService implements EntityService<Funcionario>{
	@Autowired
	private FuncionarioRepository repository;
	
	@Override
	public void save(Funcionario entity) {
		entity.setActive(true);
		repository.saveAndFlush(entity);
	}

	@Override
	public void update(Funcionario entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public void deleteById(Long id) {
		Funcionario funcionario = getOne(id);
		if(funcionario.isNotNull()) {
			funcionario.setActive(true);
			update(funcionario);
		}
	}

	@Override
	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Funcionario> findAllByTrash() {
		return repository.findAllByTrash();
	}

	@Override
	public Funcionario getOne(Long id) {
		return repository.getOne(id);
	}

	@Override
	public void restore(Long id) {
		Funcionario funcionario = getOne(id);
		
		if(funcionario.isNotNull()) {
			funcionario.setActive(true);
			update(funcionario);
		}
	}

	@Override
	public void restore(String idCrypt) {
		Funcionario funcionario = findByIdCrypt(idCrypt);
		
		if(funcionario.isNotNull()) {
			funcionario.setActive(true);
			update(funcionario);
		}	
	}

	@Override
	public void restoreAll(List<Funcionario> entities) {
		for(Funcionario funcionario : entities) {
			funcionario.setActive(false);
			update(funcionario);
		}
	}

	@Override
	public void restoreAll() {
		for(Funcionario funcionario : findAllByTrash()) {
			funcionario.setActive(false);
			update(funcionario);
		}
	}

	public Funcionario findByIdCrypt(String idCrypt) {
		return repository.findByIdCrypt(idCrypt);
	}
	
	@ExecuteQuery
	public Funcionario exist(String email, String cpf, String rg, String telefone) {
		return repository.exist(email, cpf, rg, telefone);
	}
}
