package by.grsu.edu.dimav.onlinestore.dao;

import java.util.List;

import by.grsu.edu.dimav.onlinestore.dao.table.CustomerTable;
import by.grsu.edu.dimav.onlinestore.entities.Customer;

public class CustomerDao extends AbstractDao<CustomerTable, Customer> {

	public CustomerDao(String rootFolderPath) {
		super(rootFolderPath);
	}

	public void saveNew(Customer entity) {
		entity.setId(getNextId());
		final CustomerTable table = deserializeFromXml();
		table.getRows().add(entity);
		serializeToXml(table);
	}

	public void update(Customer entity) {
		final CustomerTable table = deserializeFromXml();
		for (final Customer customer : table.getRows()) {
			if (customer.getId().equals(entity.getId())) {
				customer.setContactNumber(entity.getContactNumber());
				customer.setEmail(entity.getEmail());
				customer.setLastName(entity.getLastName());
				customer.setLogin(entity.getLogin());
				customer.setName(entity.getName());
				customer.setPassword(entity.getPassword());
				customer.setSecondName(entity.getSecondName());
				customer.setBasket(entity.getBasket());
				customer.setOrders(entity.getOrders());
			}
		}
		serializeToXml(table);
	}

	public Customer get(Integer id) {
		final CustomerTable table = deserializeFromXml();
		for (final Customer a : table.getRows()) {
			if (a.getId().equals(id)) {
				return a;
			}
		}
		return null;
	}

	public List<Customer> getAll() {
		return deserializeFromXml().getRows();
	}

	public void delete(Integer id) {
		Customer toBeDeleted = null;
		final CustomerTable table = deserializeFromXml();
		for (Customer a : table.getRows()) {
			if (a.getId().equals(id)) {
				toBeDeleted = a;
				break;
			}
		}
		table.getRows().remove(toBeDeleted);
		serializeToXml(table);
	}

	protected Class<CustomerTable> getTableClass() {
		return CustomerTable.class;
	}
}
