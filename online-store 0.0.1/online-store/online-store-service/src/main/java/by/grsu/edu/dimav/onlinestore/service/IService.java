package by.grsu.edu.dimav.onlinestore.service;

import java.util.List;

public interface IService<T> {
	
	void saveNew(T entity);
	
	void update(T entity);
	
	T get(Integer id);
	
	List<T> getAll();
	
	void delete(Integer id);
	
	void saveOrUpdate(T entity);
}
