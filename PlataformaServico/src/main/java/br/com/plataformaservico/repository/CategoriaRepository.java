package br.com.plataformaservico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.plataformaservico.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	@Query
	public Categoria findByIdCrypt(String idCrypt);
	@Query(value = "select * from categoria c where c.id = ?", nativeQuery = true)
	public Categoria getOne(Long id);
	@Query(value = "select * from categoria c where c.nome = ?", nativeQuery = true)
	public Categoria exist(String name);
	@Query(value = "select * from categoria c where c.is_active", nativeQuery = true)
	public List<Categoria> findAll();
	@Query(value = "select * from categoria c where !c.is_active", nativeQuery = true)
	public List<Categoria> findAllByTrash();
}
