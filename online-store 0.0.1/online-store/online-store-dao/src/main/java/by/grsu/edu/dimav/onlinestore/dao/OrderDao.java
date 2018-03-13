package by.grsu.edu.dimav.onlinestore.dao;

import java.util.List;

import by.grsu.edu.dimav.onlinestore.dao.table.OrderTable;
import by.grsu.edu.dimav.onlinestore.entities.Order;

public class OrderDao extends AbstractDao<OrderTable, Order> {

	public OrderDao(String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(Order entity) {
		entity.setId(getNextId());
		final OrderTable table = deserializeFromXml();
		table.getRows().add(entity);
		serializeToXml(table);
	}

	@Override
	public void update(Order entity) {
		final OrderTable table = deserializeFromXml();
		for (final Order order : table.getRows()) {
			if (order.getId().equals(entity.getId())) {
				order.setAdmin(entity.getAdmin());
				order.setCustomer(entity.getCustomer());
				order.setProduct(entity.getProduct());
			}
		}
		serializeToXml(table);
	}

	@Override
	public Order get(Integer id) {
		final OrderTable table = deserializeFromXml();
		for (final Order a : table.getRows()) {
			if (a.getId().equals(id)) {
				return a;
			}
		}
		return null;
	}

	@Override
	public List<Order> getAll() {
		return deserializeFromXml().getRows();
	}

	@Override
	public void delete(Integer id) {
		Order toBeDeleted = null;
		final OrderTable table = deserializeFromXml();
		for (Order a : table.getRows()) {
			if (a.getId().equals(id)) {
				toBeDeleted = a;
				break;
			}
		}
		table.getRows().remove(toBeDeleted);
		serializeToXml(table);
	}

	@Override
	protected Class<OrderTable> getTableClass() {
		return OrderTable.class;
	}
}
