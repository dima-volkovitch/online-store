package by.grsu.edu.store.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.api.dao.ext.IUserDao;
import by.grsu.edu.store.api.service.IUserService;
import by.grsu.edu.store.dao.Connector;
import by.grsu.edu.store.dao.ext.UserDaoImpl;
import by.grsu.edu.store.entity.User;
import by.grsu.edu.store.entity.UserStatus;

public class UserService implements IUserService {
	private static Logger logger = LogManager.getLogger(UserService.class);
	
	private Connection connection;

	private IUserDao userDao;

	public UserService() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		connection = Connector.getConnection();
		userDao = new UserDaoImpl();
	}

	@Override
	public User get(Long id) throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			User user = userDao.get(connection, id);
			if (user == null) {
				throw new DaoException("User does not exist");
			}
			connection.commit();
			return user;
		} catch (Exception e) {
			connection.rollback();
			logger.log(Level.INFO, e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public User get(String login) throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			User user = userDao.get(connection, login);
			if (user == null) {
				throw new DaoException("User does not exist");
			}
			connection.commit();
			return user;
		} catch (Exception e) {
			connection.rollback();
			logger.log(Level.INFO, e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public List<User> getAll() throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			List<User> users = userDao.getAll(connection);
			connection.commit();
			return users;
		} catch (Exception e) {
			connection.rollback();
			logger.log(Level.INFO, e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public void add(User user) throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			userDao.persist(connection, user);
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
	public void update(User user, Long currentUserId) throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			User currentUser = userDao.get(connection, currentUserId);
			if (currentUser == null) {
				throw new DaoException("User does not exist");
			}
			if (currentUser.getUserStatus() != UserStatus.ADMIN && user.getId() != currentUser.getId()) {
				throw new DaoException("Close access");
			}

			userDao.update(connection, user);
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
	public void delete(Long userId, Long currentUserId) throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			User user = userDao.get(connection, userId);
			User currentUser = userDao.get(connection, currentUserId);
			if (currentUser == null || user == null) {
				throw new DaoException("User does not exist");
			}
			if (currentUser.getUserStatus() != UserStatus.ADMIN && user.getId() != currentUser.getId()) {
				throw new DaoException("Close access");
			}

			userDao.delete(connection, user);
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
