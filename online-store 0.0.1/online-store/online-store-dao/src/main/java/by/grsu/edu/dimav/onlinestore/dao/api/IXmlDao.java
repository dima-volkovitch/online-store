package by.grsu.edu.dimav.onlinestore.dao.api;

import java.util.List;

public interface IXmlDao<E> {

	void saveNew(E entity);

	void update(E entity);

	E get(Integer id);

	List<E> getAll();

	void delete(Integer id);
}
