package by.grsu.edu.dimav.onlinestore.service;

import java.util.List;

import by.grsu.edu.dimav.onlinestore.dao.OrderDao;
import by.grsu.edu.dimav.onlinestore.entities.Order;

public class OrderService implements IService<Order> {
	private OrderDao dao;

	public OrderService() {
		dao = new OrderDao("..//online-store-data//main");
	}

	public void saveNew(Order entity) {
		dao.saveNew(entity);
	}

	public void update(Order entity) {
		dao.update(entity);
	}

	public Order get(Integer id) {
		return dao.get(id);
	}

	public List<Order> getAll() {
		return dao.getAll();
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public void saveOrUpdate(Order entity) {
		if (entity.getId() == null) {
			dao.saveNew(entity);
		} else {
			dao.update(entity);
		}
	}
}
