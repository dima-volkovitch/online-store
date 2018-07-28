package by.grsu.edu.store.dao.ext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.api.dao.ext.IProductDao;
import by.grsu.edu.store.dao.AbstractDao;
import by.grsu.edu.store.entity.Product;

public class ProductDaoImpl extends AbstractDao<Product, Long> implements IProductDao {
	private static final String SELECT_QUERY = "SELECT * FROM online_store.product";
	private static final String PERSIST_QUERY = "INSERT INTO online_store.product(name, price, count) VALUES(?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE online_store.product SET name=?, price=?, count=? WHERE id=?;";
	private static final String DELETE_QUERY = "DELETE FROM online_store.product WHERE id=?;";

	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String PRICE = "price";
	private static final String COUNT = "count";

	public ProductDaoImpl() {

	}

	@Override
	protected String getSelectQuery() {
		return SELECT_QUERY;
	}

	@Override
	protected List<Product> parseResultSet(ResultSet rs) throws DaoException {
		try {
			List<Product> list = new ArrayList<>();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getLong(ID));
				product.setName(rs.getString(NAME));
				product.setPrice(rs.getString(PRICE));
				product.setCount(rs.getInt(COUNT));
				list.add(product);
			}
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	protected String getPersistQuery() {
		return PERSIST_QUERY;
	}

	@Override
	protected String getUpdateQuery() {
		return UPDATE_QUERY;
	}

	@Override
	protected String getDeleteQuery() {
		return DELETE_QUERY;
	}

	@Override
	protected void prepareStatementForPersist(PreparedStatement statement, Product entity) throws DaoException {
		try {
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getPrice());
			statement.setInt(3, entity.getCount());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Product entity) throws DaoException {
		try {
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getPrice());
			statement.setInt(3, entity.getCount());
			statement.setLong(4, entity.getId());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	protected void prepareStatementForDelete(PreparedStatement statement, Product entity) throws DaoException {
		try {
			statement.setLong(1, entity.getId());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
