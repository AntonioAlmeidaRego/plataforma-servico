package br.com.plataformaservico.util;

import java.util.List;

public interface EntityService<Entity extends Object> {
	public void save(Entity entity);
	public void update(Entity entity);
	public void deleteById(Long id);
	public List<Entity> findAll();
	public Entity getOne(Long id);
}
