package by.grsu.edu.store.api.service;

import java.sql.SQLException;
import java.util.List;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.entity.Product;

public interface IProductService {

	Product get(Long id) throws SQLException, DaoException;

	List<Product> getAll() throws SQLException, DaoException;

	void add(Product product, Long currentUserId) throws SQLException, DaoException;

	void update(Product product, Long currentUserId) throws SQLException, DaoException;

	void delete(Long productId, Long currentUserId) throws SQLException, DaoException;

}
