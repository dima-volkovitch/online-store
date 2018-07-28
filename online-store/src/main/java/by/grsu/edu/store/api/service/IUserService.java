package by.grsu.edu.store.api.service;

import java.sql.SQLException;
import java.util.List;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.entity.User;

public interface IUserService {

	User get(Long id) throws SQLException, DaoException;

	User get(String login) throws SQLException, DaoException;

	List<User> getAll() throws SQLException, DaoException;

	void add(User user) throws SQLException, DaoException;

	void update(User user, Long currentUserId) throws SQLException, DaoException;

	void delete(Long userId, Long currentUserId) throws SQLException, DaoException;

}
