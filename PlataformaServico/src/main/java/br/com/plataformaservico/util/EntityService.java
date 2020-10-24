package br.com.plataformaservico.util;

import java.util.List;

@SuppressWarnings("hiding")
public interface EntityService<Entity extends Object> {
	public void save(Entity entity);
	public void update(Entity entity);
	public void deleteById(Long id);
	public List<Entity> findAll();
	public List<Entity> findAllByTrash();
	public Entity getOne(Long id);
	public void restore(Long id);
	public void restore(String idCrypt);
	public void restoreAll(List<Entity> entities);
	public void restoreAll();
}
