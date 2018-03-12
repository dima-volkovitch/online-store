package by.grsu.edu.dimav.onlinestore.dao;

import java.util.List;

import by.grsu.edu.dimav.onlinestore.dao.table.ProductTable;
import by.grsu.edu.dimav.onlinestore.entities.Product;

public class ProductDao extends AbstractDao<ProductTable, Product> {

	public ProductDao(String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(Product entity) {
		entity.setId(getNextId());
		final ProductTable table = deserializeFromXml();
		table.getRows().add(entity);
		serializeToXml(table);
	}

	@Override
	public void update(Product entity) {
		final ProductTable table = deserializeFromXml();
		for (final Product product : table.getRows()) {
			if (product.getId().equals(entity.getId())) {
				product.setDescription(entity.getDescription());
				product.setProductName(entity.getProductName());
				product.setQuantityInStock(entity.getQuantityInStock());
			}
		}
		serializeToXml(table);
	}

	@Override
	public Product get(Integer id) {
		final ProductTable table = deserializeFromXml();
		for (final Product a : table.getRows()) {
			if (a.getId().equals(id)) {
				return a;
			}
		}
		return null;
	}

	@Override
	public List<Product> getAll() {
		return deserializeFromXml().getRows();
	}

	@Override
	public void delete(Integer id) {
		Product toBeDeleted = null;
		final ProductTable table = deserializeFromXml();
		for (Product a : table.getRows()) {
			if (a.getId().equals(id)) {
				toBeDeleted = a;
				break;
			}
		}
		table.getRows().remove(toBeDeleted);
		serializeToXml(table);
	}

	@Override
	protected Class<ProductTable> getTableClass() {
		return ProductTable.class;
	}
}
