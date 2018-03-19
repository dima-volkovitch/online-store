package by.grsu.edu.dimav.onlinestore.service;

import java.util.List;

import by.grsu.edu.dimav.onlinestore.dao.ProductDao;
import by.grsu.edu.dimav.onlinestore.entities.Product;

public class ProductService implements IService<Product> {
	private ProductDao dao;

	public ProductService() {
		dao = new ProductDao("..//online-store-data//main");
	}

	public void saveNew(Product entity) {
		dao.saveNew(entity);
	}

	public void update(Product entity) {
		dao.update(entity);
	}

	public Product get(Integer id) {
		return dao.get(id);
	}

	public List<Product> getAll() {
		return dao.getAll();
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public void saveOrUpdate(Product entity) {
		if (entity.getId() == null) {
			dao.saveNew(entity);
		} else {
			dao.update(entity);
		}
	}
}
