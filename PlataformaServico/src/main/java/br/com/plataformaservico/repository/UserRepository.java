package br.com.plataformaservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.plataformaservico.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query
	public User findByEmailAndPassword(String email, String password);
}
