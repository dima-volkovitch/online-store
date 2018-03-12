package by.grsu.edu.dimav.onlinestore.dao.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.edu.dimav.onlinestore.entities.Customer;

public class CustomerTable extends AbstractTable<Customer> {
	private static final long serialVersionUID = 3182573959560171159L;

	private List<Customer> rows;

	@Override
	public List<Customer> getRows() {
		if (rows == null) {
			rows = new ArrayList<Customer>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Customer> rows) {
		this.rows = rows;
	}
}
