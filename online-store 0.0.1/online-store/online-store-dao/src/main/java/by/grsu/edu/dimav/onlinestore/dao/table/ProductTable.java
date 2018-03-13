package by.grsu.edu.dimav.onlinestore.dao.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.edu.dimav.onlinestore.entities.Product;

public class ProductTable extends AbstractTable<Product>{
	private static final long serialVersionUID = -5502631879549516904L;
	
	private List<Product> rows;

	@Override
	public List<Product> getRows() {
		if (rows == null) {
			rows = new ArrayList<Product>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Product> rows) {
		this.rows = rows;
	}
}
