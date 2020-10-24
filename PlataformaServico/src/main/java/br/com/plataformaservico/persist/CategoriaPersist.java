package br.com.plataformaservico.persist;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.entityquerydb.persist.EntityPersist;
import br.com.entityquerydb.persist.ResponsePersist;
import br.com.entityquerydb.util.EntityQueryDB;
import br.com.entityquerydb.util.EntityQueryDBImpl;
import br.com.plataformaservico.model.Categoria;
import br.com.plataformaservico.service.CategoriaService;

@Service
public class CategoriaPersist implements EntityPersist<Categoria, CategoriaService>{
	private EntityQueryDB<Categoria> queryDB = new EntityQueryDBImpl<Categoria>();
	
	private ResponsePersist update(Categoria entity, CategoriaService service) {
		entity = queryDB.findByAttrsPossiblyCanUpdate(entity);
		service.update(entity);
		return new ResponsePersist().setHttpStatus(HttpStatus.CREATED)
				.setMessage("Categoria Atualizada com Sucesso!")
				.setObject(entity)
				;
	}
	
	@Override
	public ResponsePersist persist(Categoria entity, CategoriaService service) {
		queryDB.setObjectPersist(service);
		queryDB.setObject(service.getOne(entity.getId()));
		
		if(queryDB.isEntityCreate(entity)) {
			if(!queryDB.isExist(entity.getNome())) {
				service.save(entity); 
				return new ResponsePersist()
						.setHttpStatus(HttpStatus.CREATED)
						.setMessage("Categoria salva com sucesso!");
			}
		}else if(queryDB.isEntityUpdate(entity)) {
			if(queryDB.isExist(entity.getNome()) && !queryDB.isUpdate(entity)) { 
				return update(entity, service);
			}else {
				if(!queryDB.isExist(entity.getNome())) {
					return update(entity, service);
				}
			} 
		}
		return new ResponsePersist()
			.setHttpStatus(HttpStatus.CONFLICT)
			.setMessage("Categoria já existe!");
	}

	@Override
	public ResponsePersist persist(Categoria entity, CategoriaService service, Object role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Categoria entity, CategoriaService service, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Categoria entity, CategoriaService service, Object role, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Categoria entity, CategoriaService service, List<?> objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Categoria entity, CategoriaService service, List<?> objects, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Categoria entity, CategoriaService service, List<?> objects, Object role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Categoria entity, CategoriaService service, List<?> objects, Object role,
			MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
