package by.grsu.edu.store.api.dao.ext;

import java.sql.Connection;

import by.grsu.edu.store.api.dao.GenericDao;
import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.entity.User;

public interface IUserDao extends GenericDao<User, Long> {

	public User get(Connection connection, String login) throws DaoException;
}
