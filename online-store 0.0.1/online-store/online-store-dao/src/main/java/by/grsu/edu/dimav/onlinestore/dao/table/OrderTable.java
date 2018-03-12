package by.grsu.edu.dimav.onlinestore.dao.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.edu.dimav.onlinestore.entities.Order;

public class OrderTable extends AbstractTable<Order> {
	private static final long serialVersionUID = 1006159734821005656L;
	
	private List<Order> rows;

	@Override
	public List<Order> getRows() {
		if (rows == null) {
			rows = new ArrayList<Order>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Order> rows) {
		this.rows = rows;
	}
}
