package by.grsu.edu.dimav.onlinestore.dao;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.edu.dimav.onlinestore.entities.Customer;
import by.grsu.edu.dimav.onlinestore.entities.Order;

public class OrderDaoTest {

	private static final String XML_PATH = "..//online-store-data//test//xml";

	private static OrderDao orderDao;

	@BeforeClass
	public static void crateDao() {
		orderDao = new OrderDao(XML_PATH);
	}

	@Test
	public void testSaveNew() {
		System.out.println("Start 'save' test for order");
		Order order = saveOrder();
		Assert.assertNotNull(orderDao.get(order.getId()));
	}

	@Test
	public void testUpdate() {
		System.out.println("Start 'update' test for order");
		Order order = saveOrder();
		Customer customer = new Customer();
		customer.setId(4);
		order.setCustomer(customer);
		orderDao.update(order);
		Assert.assertEquals(order, orderDao.get(order.getId()));
	}

	@Test
	public void testGetAll() {
		System.out.println("Start 'getAll' test for order");
		final int initialRowsCount = orderDao.getAll().size();
		saveOrder();
		Assert.assertEquals(orderDao.getAll().size(), initialRowsCount + 1);
	}

	@Test
	public void testDelete() {
		System.out.println("Start 'delete' test for order");
		Order order = saveOrder();
		orderDao.delete(order.getId());
		Assert.assertNull(orderDao.get(order.getId()));
	}

	private static Order saveOrder() {
		Order order = new Order();
		order.setId(1);
		Customer customer = new Customer();
		customer.setId(56);
		customer.setEmail("sdfsdfsd");
		order.setCustomer(customer);
		orderDao.saveNew(order);
		return order;
	}
}
