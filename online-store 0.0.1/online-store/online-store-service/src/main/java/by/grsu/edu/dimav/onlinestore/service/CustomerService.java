package by.grsu.edu.dimav.onlinestore.service;

import java.util.List;

import by.grsu.edu.dimav.onlinestore.dao.CustomerDao;
import by.grsu.edu.dimav.onlinestore.entities.Customer;

public class CustomerService implements IService<Customer> {
private CustomerDao dao;
	
	public CustomerService() {
		dao = new CustomerDao("..//online-store-data//main");
	}
	
	public void saveNew(Customer entity) {
		dao.saveNew(entity);
	}

	public void update(Customer entity) {
		dao.update(entity);
	}

	public Customer get(Integer id) {
		return dao.get(id);
	}

	public List<Customer> getAll() {
		return dao.getAll();
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public void saveOrUpdate(Customer entity) {
		if(entity.getId() == null) {
			dao.saveNew(entity);
		}else {
			dao.update(entity);
		}
	}
}
