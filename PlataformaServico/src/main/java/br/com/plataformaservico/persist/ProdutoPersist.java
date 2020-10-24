package br.com.plataformaservico.persist;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.entityquerydb.persist.EntityPersist;
import br.com.entityquerydb.persist.ResponsePersist;
import br.com.entityquerydb.util.EntityQueryDB;
import br.com.entityquerydb.util.EntityQueryDBImpl;
import br.com.plataformaservico.model.Produto;
import br.com.plataformaservico.service.ProdutoService;

@Service
public class ProdutoPersist implements EntityPersist<Produto, ProdutoService>{
	private EntityQueryDB<Produto> queryDB = new EntityQueryDBImpl<Produto>();
	
	@Override
	public ResponsePersist persist(Produto entity, ProdutoService service, List<?> objects, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Produto entity, ProdutoService service, List<?> objects, Object role,
			MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Produto entity, ProdutoService service, List<?> objects, Object role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Produto entity, ProdutoService service, List<?> objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Produto entity, ProdutoService service, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Produto entity, ProdutoService service, Object role, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Produto entity, ProdutoService service, Object role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePersist persist(Produto entity, ProdutoService service) {
		queryDB.setObjectPersist(service);
		queryDB.setObject(service.getOne(entity.getId()));
		
		if(queryDB.isEntityCreate(entity)) {
			service.save(entity); 
			return new ResponsePersist()
					.setHttpStatus(HttpStatus.CREATED)
					.setMessage("Produto salvo com sucesso!");
		}else if(queryDB.isEntityUpdate(entity)) {
			Produto produto = queryDB.findByAttrsPossiblyCanUpdate(entity);
			service.update(produto); 
			return new ResponsePersist()
					.setHttpStatus(HttpStatus.CREATED)
					.setMessage("Produto atualizado com sucesso!");
		}
		return new ResponsePersist()
				.setHttpStatus(HttpStatus.CONFLICT)
				.setMessage("Produto já existe!");
	}

}
