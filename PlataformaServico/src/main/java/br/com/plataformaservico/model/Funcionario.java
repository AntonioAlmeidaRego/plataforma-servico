package br.com.plataformaservico.model;

import java.io.Serializable;

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
public class Funcionario implements Serializable, br.com.plataformaservico.util.Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@IdUpdate
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@PossiblyCanUpdateAttribute
	private String nome;
	@AttrName(value = "E-mail")
	@AttrUnique
	@AttrUpdate
	@PossiblyCanUpdateAttribute
	private String email;
	@AttrName(value = "Telefone")
	@AttrUnique
	@AttrUpdate
	@PossiblyCanUpdateAttribute
	private String telefone;
	@AttrName(value = "CPF")
	@AttrUnique
	@AttrUpdate
	@PossiblyCanUpdateAttribute
	private String cpf;
	@AttrUnique
	@AttrUpdate
	@PossiblyCanUpdateAttribute
	private String rg;
	private boolean isActive;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getIdCrypt() {
		return idCrypt;
	}

	public void setIdCrypt(String idCrypt) {
		this.idCrypt = idCrypt;
	}

	@Override
	public boolean isNull() {
		return this == null;
	}

	@Override
	public boolean isNotNull() {
		return !isNotNull();
	}

	@Override
	public boolean isEmpty() {
		return ((this.getNome().isEmpty()) && (this.getEmail().isEmpty()) && (this.getCpf().isEmpty())
				&& (this.getRg().isEmpty())
				&& (this.getTelefone().isEmpty())
				);
	}

	@Override
	public boolean isNotEmpty() {
		return !isEmpty();
	}

}
