package br.com.plataformaservico.persist;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.entityquerydb.persist.EntityPersist;
import br.com.entityquerydb.persist.ResponsePersist;
import br.com.entityquerydb.util.EntityQueryDB;
import br.com.entityquerydb.util.EntityQueryDBImpl;
import br.com.plataformaservico.model.Funcionario;
import br.com.plataformaservico.service.FuncionarioService;

@Service
public class FuncionarioPersist implements EntityPersist<Funcionario, FuncionarioService>{
	private EntityQueryDB<Funcionario> queryDB = new EntityQueryDBImpl<Funcionario>();
	
	@Override
	public ResponsePersist persist(Funcionario entity, FuncionarioService service, List<?> objects,
			MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Funcionario entity, FuncionarioService service, List<?> objects, Object role,
			MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Funcionario entity, FuncionarioService service, List<?> objects, Object role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Funcionario entity, FuncionarioService service, List<?> objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Funcionario entity, FuncionarioService service, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Funcionario entity, FuncionarioService service, Object role, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Funcionario entity, FuncionarioService service, Object role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Funcionario entity, FuncionarioService service) {
		queryDB.setObjectPersist(service);
		queryDB.setObject(service.getOne(entity.getId()));
		
		if(queryDB.isEntityCreate(entity)) {
			if(!queryDB.isExist(entity.getEmail(), entity.getCpf(), entity.getRg(), entity.getTelefone())) {
				service.save(entity);
				return new ResponsePersist()
						.setMessage("Funcionario(a) salvo com sucesso!")
						.setHttpStatus(HttpStatus.CREATED);
			}
		}else if(queryDB.isEntityUpdate(entity)) {
			if(!queryDB.isExist(entity.getEmail(), entity.getCpf(), entity.getRg(),
					entity.getTelefone())
					&& !queryDB.isUpdate(entity)
					) {
				return update(entity, service);
			}else {
				if(!queryDB.isExist(entity.getNome())) {
					return update(entity, service);
				}
			}
		}
		return new ResponsePersist()
				.setHttpStatus(HttpStatus.CONFLICT)
				.setMessage("Funcionario(a) já existe!");
	}
	
	private ResponsePersist update(Funcionario entity, FuncionarioService service) {
		entity = queryDB.findByAttrsPossiblyCanUpdate(entity);
		service.update(entity);
		return new ResponsePersist().setHttpStatus(HttpStatus.CREATED)
				.setMessage("Funcionário(a) Atualizado(a) com Sucesso!")
				.setObject(entity)
				;
	}

}
