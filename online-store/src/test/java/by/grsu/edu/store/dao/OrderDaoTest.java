package by.grsu.edu.store.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.api.dao.ext.IOrderDao;
import by.grsu.edu.store.dao.ext.OrderDaoImpl;
import by.grsu.edu.store.entity.Order;
import by.grsu.edu.store.service.OrderService;

public class OrderDaoTest {
	private static Logger logger = LogManager.getLogger(OrderDaoTest.class);
	
	private static IOrderDao dao;
	private static Connection connection;

	@BeforeClass
	public static void createDao() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		dao = new OrderDaoImpl();
		connection = Connector.getConnection();
	}

	@Test
	public void testPersist() throws SQLException {
		try {
			connection.setAutoCommit(false);
			System.out.println("Start 'persist' test for order");
			Order order = saveNew();
			System.out.println(order);
			Assert.assertNotNull(dao.get(connection, order.getId()));
			connection.commit();

			logger.log(Level.INFO,"Logger - test");
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
			System.out.println("Start 'update' test for order");
			Order order = saveNew();
			System.out.println(order);
			order.setDate(new Date(0));
			dao.update(connection, order);
			Assert.assertEquals(order, dao.get(connection, order.getId()));
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
			System.out.println("Start 'delete' test for order");
			Order order = saveNew();
			System.out.println(order);
			dao.delete(connection, order);
			Assert.assertNull(dao.get(connection, order.getId()));
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
			System.out.println("Start 'getAll' test for order");
			final int size = dao.getAll(connection).size();
			Order order = saveNew();
			System.out.println(order);
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
			System.out.println("Start 'get' test for order");
			Order order = saveNew();
			System.out.println(order);
			Assert.assertNotNull(dao.get(connection, order.getId()));
			connection.commit();
		} catch (DaoException | SQLException e) {
			logger.log(Level.INFO, e.getMessage(), e);
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	public static Order saveNew() throws DaoException {
		try {
			Order order = new Order();
			order.setDate(new Date());
			dao.persist(connection, order);
			System.out.println("order id - " + order.getId());
			return order;
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			return null;
		}
	}
}
