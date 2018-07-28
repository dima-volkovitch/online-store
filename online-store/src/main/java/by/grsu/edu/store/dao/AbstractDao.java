package by.grsu.edu.store.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import by.grsu.edu.store.api.dao.GenericDao;
import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.entity.Entity;

public abstract class AbstractDao<T extends Entity, ID extends Serializable> implements GenericDao<T, ID> {
	private static final String SEMICOLON = ";";
	private static final String WHERE_ID = " WHERE id=?;";
	private static final String LAST_INSERT_ID_QUERY = "SELECT LAST_INSERT_ID();";
	private static final String MODIFY_MORE_THEN_1 = "Modify more then 1 record : ";
	private static final String ID_IS_NULL = "Id is null";

	public AbstractDao() {

	}

	protected abstract String getSelectQuery();

	protected abstract String getPersistQuery();

	protected abstract String getUpdateQuery();

	protected abstract String getDeleteQuery();

	protected abstract List<T> parseResultSet(ResultSet rs) throws DaoException;

	protected abstract void prepareStatementForPersist(PreparedStatement statement, T entity) throws DaoException;

	protected abstract void prepareStatementForUpdate(PreparedStatement statement, T entity) throws DaoException;

	protected abstract void prepareStatementForDelete(PreparedStatement statement, T entity) throws DaoException;

	private Long getLastInsertId(Connection connection) throws DaoException {
		try (PreparedStatement statement = connection.prepareStatement(LAST_INSERT_ID_QUERY)) {
			ResultSet rs = statement.executeQuery();
			rs.next();
			return rs.getLong(1);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public void persist(Connection connection, T entity) throws DaoException {
		String sql = getPersistQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareStatementForPersist(statement, entity);

			int size = statement.executeUpdate();
			if (size != 1) {
				throw new DaoException(MODIFY_MORE_THEN_1 + size);
			}
			entity.setId(getLastInsertId(connection));

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public void update(Connection connection, T entity) throws DaoException {
		if (entity.getId() == null) {
			throw new DaoException(ID_IS_NULL);
		}

		String sql = getUpdateQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareStatementForUpdate(statement, entity);

			int size = statement.executeUpdate();
			if (size != 1) {
				throw new DaoException(MODIFY_MORE_THEN_1);
			}

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public void delete(Connection connection, T entity) throws DaoException {
		if (entity.getId() == null) {
			throw new DaoException(ID_IS_NULL);
		}

		String sql = getDeleteQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareStatementForDelete(statement, entity);

			int size = statement.executeUpdate();
			if (size != 1) {
				throw new DaoException(MODIFY_MORE_THEN_1);
			}

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public T get(Connection connection, ID id) throws DaoException {
		List<T> list;
		String sql = getSelectQuery() + WHERE_ID + SEMICOLON;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setObject(1, id);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);

			if (list.size() == 0 || list == null) {
				return null;
			}
			return list.iterator().next();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public List<T> getAll(Connection connection) throws DaoException {
		List<T> list;
		String sql = getSelectQuery() + SEMICOLON;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
