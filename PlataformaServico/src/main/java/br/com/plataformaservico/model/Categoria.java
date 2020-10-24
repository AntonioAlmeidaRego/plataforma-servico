package br.com.plataformaservico.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.entityquerydb.annotations.AttrName;
import br.com.entityquerydb.annotations.AttrUnique;
import br.com.entityquerydb.annotations.AttrUpdate;
import br.com.entityquerydb.annotations.EntityQuery;
import br.com.entityquerydb.annotations.IdUpdate;
import br.com.entityquerydb.annotations.PossiblyCanUpdateAttribute;

@Entity
@EntityQuery
public class Categoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@IdUpdate
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@PossiblyCanUpdateAttribute
	@AttrUpdate
	@AttrName(value = "Nome")
	@AttrUnique
	@Column(length = 100, name = "nome_col")
	private String nome;
	@PossiblyCanUpdateAttribute
	private String idCrypt;

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

	public String getIdCrypt() {
		return idCrypt;
	}

	public void setIdCrypt(String idCrypt) {
		this.idCrypt = idCrypt;
	}

}
