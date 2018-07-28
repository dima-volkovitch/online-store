package by.grsu.edu.store.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.api.dao.ext.IProductDao;
import by.grsu.edu.store.dao.ext.ProductDaoImpl;
import by.grsu.edu.store.entity.Product;

public class ProductDaoTest {
	private static Logger logger = LogManager.getLogger(ProductDaoTest.class);

	private static IProductDao dao;
	private static Connection connection;

	@BeforeClass
	public static void createDao() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		dao = new ProductDaoImpl();
		connection = Connector.getConnection();
	}

	@Test
	public void testPersist() throws SQLException {
		try {
			connection.setAutoCommit(false);
			System.out.println("Start 'persist' test for product");
			Product product = saveNew();
			System.out.println(product);
			Assert.assertNotNull(dao.get(connection, product.getId()));
			connection.commit();
		} catch (DaoException | SQLException e) {
			logger.log(Level.INFO, e.getMessage(), e);
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Test
	public void testUpdate() throws SQLException {
		try {
			connection.setAutoCommit(false);
			System.out.println("Start 'update' test for product");
			Product product = saveNew();
			System.out.println(product);
			product.setCount(0);
			dao.update(connection, product);
			Assert.assertEquals(product, dao.get(connection, product.getId()));
			connection.commit();
		} catch (DaoException | SQLException e) {
			logger.log(Level.INFO, e.getMessage(), e);
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Test
	public void testDelete() throws SQLException {
		try {
			connection.setAutoCommit(false);
			System.out.println("Start 'delete' test for product");
			Product product = saveNew();
			System.out.println(product);
			dao.delete(connection, product);
			Assert.assertNull(dao.get(connection, product.getId()));
			connection.commit();
		} catch (DaoException | SQLException e) {
			logger.log(Level.INFO, e.getMessage(), e);
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Test
	public void testGetAll() throws SQLException {
		try {
			connection.setAutoCommit(false);
			System.out.println("Start 'getAll' test for product");
			final int size = dao.getAll(connection).size();
			Product product = saveNew();
			System.out.println(product);
			Assert.assertEquals(dao.getAll(connection).size(), size + 1);
			connection.commit();
		} catch (DaoException | SQLException e) {
			logger.log(Level.INFO, e.getMessage(), e);
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Test
	public void testGet() throws SQLException {
		try {
			connection.setAutoCommit(false);
			System.out.println("Start 'get' test for product");
			Product product = saveNew();
			System.out.println(product);
			Assert.assertNotNull(dao.get(connection, product.getId()));
			connection.commit();
		} catch (DaoException | SQLException e) {
			logger.log(Level.INFO, e.getMessage(), e);
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	private static Product saveNew() {
		try {
			Product product = new Product();
			product.setName("TEST");
			product.setPrice("666");
			product.setCount(666);
			dao.persist(connection, product);
			System.out.println("product id - " + product.getId());
			return product;
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			return null;
		}
	}
}
