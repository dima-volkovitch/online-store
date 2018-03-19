package by.grsu.edu.dimav.onlinestore.dao.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.edu.dimav.onlinestore.entities.Admin;

public class AdminTable extends AbstractTable<Admin> {
	private static final long serialVersionUID = 7441451864974749220L;

	private List<Admin> rows;

	@Override
	public List<Admin> getRows() {
		if (rows == null) {
			rows = new ArrayList<Admin>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Admin> rows) {
		this.rows = rows;
	}
}
