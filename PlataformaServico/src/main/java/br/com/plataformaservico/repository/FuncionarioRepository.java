package br.com.plataformaservico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.plataformaservico.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	@Query(value = "select * from funcionario f where f.id_crypt=?", nativeQuery = true)
	public Funcionario findByIdCrypt(String crypt);

	@Query(value = "select * from funcionario f where f.is_active", nativeQuery = true)
	public List<Funcionario> findAll();

	@Query(value = "select * from funcionario f where !f.is_active", nativeQuery = true)
	public List<Funcionario> findAllByTrash();

	@Query(value = "select * from funcionario f where (f.email = ?) or (f.cpf = ?) or (f.rg = ?) or (f.telefone=?)", nativeQuery = true)
	public Funcionario exist(String email, String cpf, String rg, String telefone);
}
