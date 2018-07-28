package by.grsu.edu.store.api.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import by.grsu.edu.store.api.dao.exceptions.DaoException;

public interface GenericDao<T, ID extends Serializable> {

	public void persist(Connection connection, T entity) throws DaoException;

	public void update(Connection connection, T entity) throws DaoException;

	public void delete(Connection connection, T entity) throws DaoException;

	public T get(Connection connection, ID id) throws DaoException;

	public List<T> getAll(Connection connection) throws DaoException;
}
