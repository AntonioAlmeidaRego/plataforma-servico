package br.com.plataformaservico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.plataformaservico.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	@Query(value = "select * from produto p where p.id = ?", nativeQuery = true)
	public Produto getOne(Long id);
	@Query(value = "select * from produto p where p.id_crypt = ?", nativeQuery = true)
	public Produto findByIdCrypt(String idCrypt);
	@Query(value = "select * from produto p where p.is_active", nativeQuery = true)
	public List<Produto> findAll();
	@Query(value = "select * from produto p where !p.is_active", nativeQuery = true)
	public List<Produto> findAllByTrash();
}
