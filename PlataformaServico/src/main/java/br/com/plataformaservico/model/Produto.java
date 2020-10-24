package br.com.plataformaservico.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.entityquerydb.annotations.EntityQuery;
import br.com.entityquerydb.annotations.IdUpdate;
import br.com.entityquerydb.annotations.PossiblyCanUpdateAttribute;

@EntityQuery
@Entity
public class Produto implements Serializable, br.com.plataformaservico.util.Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@IdUpdate
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private Integer codigo;
	@PossiblyCanUpdateAttribute
	private String idCrypt;
	@PossiblyCanUpdateAttribute
	private boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getIdCrypt() {
		return idCrypt;
	}

	public void setIdCrypt(String idCrypt) {
		this.idCrypt = idCrypt;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public boolean isNull() {
		return this == null;
	}

	@Override
	public boolean isNotNull() {
		return !isNull();
	}

	@Override
	public boolean isEmpty() {
		return getNome().isEmpty();
	}

	@Override
	public boolean isNotEmpty() {
		return !isEmpty();
	}

}
