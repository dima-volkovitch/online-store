package by.grsu.edu.store.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.api.dao.ext.IProductDao;
import by.grsu.edu.store.api.dao.ext.IUserDao;
import by.grsu.edu.store.api.service.IProductService;
import by.grsu.edu.store.dao.Connector;
import by.grsu.edu.store.dao.ext.ProductDaoImpl;
import by.grsu.edu.store.dao.ext.UserDaoImpl;
import by.grsu.edu.store.entity.Product;
import by.grsu.edu.store.entity.User;
import by.grsu.edu.store.entity.UserStatus;

public class ProductService implements IProductService {
	private static Logger logger = LogManager.getLogger(ProductService.class);
	
	private Connection connection;

	private IUserDao userDao;

	private IProductDao productDao;

	public ProductService() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		connection = Connector.getConnection();
		userDao = new UserDaoImpl();
		productDao = new ProductDaoImpl();
	}

	@Override
	public Product get(Long id) throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			Product product = productDao.get(connection, id);
			if (product == null) {
				throw new DaoException("Product does not exist");
			}
			connection.commit();
			return product;
		} catch (Exception e) {
			connection.rollback();
			logger.log(Level.INFO, e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public List<Product> getAll() throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			List<Product> products = productDao.getAll(connection);
			connection.commit();
			return products;
		} catch (Exception e) {
			connection.rollback();
			logger.log(Level.INFO, e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public void add(Product product, Long currentUserId) throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			User currentUser = userDao.get(connection, currentUserId);
			if (currentUser == null) {
				throw new DaoException("User does not exist");
			}
			if (currentUser.getUserStatus() != UserStatus.ADMIN) {
				throw new DaoException("Close access");
			}

			productDao.persist(connection, product);
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			logger.log(Level.INFO, e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public void update(Product product, Long currentUserId) throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			User currentUser = userDao.get(connection, currentUserId);
			if (currentUser == null) {
				throw new DaoException("User does not exist");
			}
			if (currentUser.getUserStatus() != UserStatus.ADMIN) {
				throw new DaoException("Close access");
			}

			productDao.update(connection, product);
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			logger.log(Level.INFO, e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public void delete(Long productId, Long currentUserId) throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			User currentUser = userDao.get(connection, currentUserId);
			if (currentUser == null) {
				throw new DaoException("User does not exist");
			}
			Product product = productDao.get(connection, productId);
			if (product == null) {
				throw new DaoException("Product does not exist");
			}

			if (currentUser.getUserStatus() != UserStatus.ADMIN) {
				throw new DaoException("Close access");
			}

			productDao.update(connection, product);
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			logger.log(Level.INFO, e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

}
