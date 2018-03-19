package by.grsu.edu.dimav.onlinestore.dao;

import java.util.List;

import by.grsu.edu.dimav.onlinestore.dao.table.AdminTable;
import by.grsu.edu.dimav.onlinestore.entities.Admin;

public class AdminDao extends AbstractDao<AdminTable, Admin> {

	public AdminDao(final String rootFolderPath) {
		super(rootFolderPath);
	}
	
	public void saveNew(Admin entity) {
		entity.setId(getNextId());
		final AdminTable table = deserializeFromXml();
		table.getRows().add(entity);
		serializeToXml(table);
	}


	public void update(Admin entity) {
		final AdminTable table = deserializeFromXml();
		for (final Admin admin : table.getRows()) {
			if (admin.getId().equals(entity.getId())) {
				admin.setContactNumber(entity.getContactNumber());
				admin.setEmail(entity.getEmail());
				admin.setLastName(entity.getLastName());
				admin.setLogin(entity.getLogin());
				admin.setName(entity.getName());
				admin.setPassword(entity.getPassword());
				admin.setSecondName(entity.getSecondName());
			}
		}
		serializeToXml(table);
	}


	public Admin get(Integer id) {
		final AdminTable table = deserializeFromXml();
		for (final Admin admin : table.getRows()) {
			if (admin.getId().equals(id)) {
				System.out.println(admin.getId());
				return admin;
			}
		}
		return null;
	}


	public List<Admin> getAll() {
		return deserializeFromXml().getRows();
	}


	public void delete(Integer id) {
		Admin toBeDeleted = null;
		final AdminTable table = deserializeFromXml();
		for (Admin admin : table.getRows()) {
			if (admin.getId().equals(id)) {
				toBeDeleted = admin;
				System.out.println("FOUND");
				break;
			}
		}
		table.getRows().remove(toBeDeleted);
		System.out.println(table.getRows().contains(toBeDeleted));
		serializeToXml(table);
	}

	protected Class<AdminTable> getTableClass() {
		return AdminTable.class;
	}
}
