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
import by.grsu.edu.store.api.dao.ext.IUserDao;
import by.grsu.edu.store.dao.ext.UserDaoImpl;
import by.grsu.edu.store.entity.User;
import by.grsu.edu.store.entity.UserStatus;

public class UserDaoTest {
	private static Logger logger = LogManager.getLogger(UserDaoTest.class);
	
	private static IUserDao dao;
	private static Connection connection;

	@BeforeClass
	public static void createDao() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		dao = new UserDaoImpl();
		connection = Connector.getConnection();
	}

	@Test
	public void testPersist() throws SQLException {
		try {
			connection.setAutoCommit(false);
			System.out.println("Start 'persist' test for user");
			User user = saveNew();
			System.out.println(user);
			Assert.assertNotNull(dao.get(connection, user.getId()));
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
			System.out.println("Start 'update' test for user");
			User user = saveNew();
			System.out.println(user);
			user.setNumber("+66666666666");
			dao.update(connection, user);
			Assert.assertEquals(user, dao.get(connection, user.getId()));
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
			System.out.println("Start 'delete' test for user");
			User user = saveNew();
			System.out.println(user);
			dao.delete(connection, user);
			Assert.assertNull(dao.get(connection, user.getId()));
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
			System.out.println("Start 'getAll' test for user");
			final int size = dao.getAll(connection).size();
			User user = saveNew();
			System.out.println(user);
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
			System.out.println("Start 'get' test for user");
			User user = saveNew();
			System.out.println(user);
			Assert.assertNotNull(dao.get(connection, user.getId()));
			connection.commit();
		} catch (DaoException | SQLException e) {
			logger.log(Level.INFO, e.getMessage(), e);
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	public static User saveNew() {
		try {
			User user = new User();
			user.setNumber(new Long(System.nanoTime()).toString());
			user.setPassword("pchelka");
			user.setUserStatus(UserStatus.CUSTOMER);
			dao.persist(connection, user);
			System.out.println("user id - " + user.getId());
			return user;
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), e);
			return null;
		}
	}

}
