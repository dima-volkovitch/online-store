package by.grsu.edu.dimav.onlinestore.dao;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.edu.dimav.onlinestore.entities.Product;

public class ProductDaoTest {

	private static final String XML_PATH = "..//online-store-data//test//xml";

	private static ProductDao productDao;

	@BeforeClass
	public static void crateDao() {
		productDao = new ProductDao(XML_PATH);
	}

	@Test
	public void testSaveNew() {
		System.out.println("Start 'save' test for product");
		Product product = saveProduct();
		Assert.assertNotNull(productDao.get(product.getId()));
	}

	@Test
	public void testUpdate() {
		System.out.println("Start 'update' test for product");
		Product product = saveProduct();
		product.setDescription("00000000000");
		productDao.update(product);
		Assert.assertEquals(product, productDao.get(product.getId()));
	}

	@Test
	public void testGetAll() {
		System.out.println("Start 'getAll' test for product");
		final int initialRowsCount = productDao.getAll().size();
		saveProduct();
		Assert.assertEquals(productDao.getAll().size(), initialRowsCount + 1);
	}

	@Test
	public void testDelete() {
		System.out.println("Start 'delete' test for product");
		Product product = saveProduct();
		productDao.delete(product.getId());
		Assert.assertNull(productDao.get(product.getId()));
	}

	private static Product saveProduct() {
		Product product = new Product();
		product.setId(1);
		product.setDescription("some test description");
		productDao.saveNew(product);
		return product;
	}
}
