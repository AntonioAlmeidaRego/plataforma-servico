package br.com.plataformaservico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataformaservico.model.User;
import br.com.plataformaservico.repository.UserRepository;
import br.com.plataformaservico.util.EntityService;

@Service
public class UserService implements EntityService<User>{
	@Autowired
	private UserRepository repository;
	
	@Override
	public void save(User entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public void update(User entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User getOne(Long id) {
		return repository.getOne(id);
	}
	
	public User login(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

}
