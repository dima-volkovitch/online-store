package by.grsu.edu.dimav.onlinestore.service;

import java.util.List;

import by.grsu.edu.dimav.onlinestore.dao.AdminDao;
import by.grsu.edu.dimav.onlinestore.entities.Admin;

public class AdminService implements IService<Admin>{
	private AdminDao dao;
	
	public AdminService() {
		dao = new AdminDao("..//online-store-data//main");
	}
	
	public void saveNew(Admin entity) {
		dao.saveNew(entity);
	}

	public void update(Admin entity) {
		dao.update(entity);
	}

	public Admin get(Integer id) {
		return dao.get(id);
	}

	public List<Admin> getAll() {
		return dao.getAll();
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public void saveOrUpdate(Admin entity) {
		if(entity.getId() == null) {
			dao.saveNew(entity);
		}else {
			dao.update(entity);
		}
	}

}
