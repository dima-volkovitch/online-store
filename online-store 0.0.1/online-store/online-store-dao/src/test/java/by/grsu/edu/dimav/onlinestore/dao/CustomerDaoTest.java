package by.grsu.edu.dimav.onlinestore.dao;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.edu.dimav.onlinestore.entities.Customer;

public class CustomerDaoTest {

	private static final String XML_PATH = "..//online-store-data//test//xml";

	private static CustomerDao customerDao;

	@BeforeClass
	public static void crateDao() {
		customerDao = new CustomerDao(XML_PATH);
	}

	@Test
	public void testSaveNew() {
		System.out.println("Start 'save' test for customer");
		Customer customer = saveCustomer();
		Assert.assertNotNull(customerDao.get(customer.getId()));
	}

	@Test
	public void testUpdate() {
		System.out.println("Start 'update' test for customer");
		Customer customer = saveCustomer();
		customer.setContactNumber("00000000000");
		customerDao.update(customer);
		Assert.assertEquals(customer, customerDao.get(customer.getId()));
	}

	@Test
	public void testGetAll() {
		System.out.println("Start 'getAll' test for customer");
		final int initialRowsCount = customerDao.getAll().size();
		saveCustomer();
		Assert.assertEquals(customerDao.getAll().size(), initialRowsCount + 1);
	}

	@Test
	public void testDelete() {
		System.out.println("Start 'delete' test for customer");
		Customer customer = saveCustomer();
		customerDao.delete(customer.getId());
		Assert.assertNull(customerDao.get(customer.getId()));
	}

	private static Customer saveCustomer() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setLogin("Vol40K");
		customer.setContactNumber("37529684615");
		customer.setEmail("emailtest");
		customerDao.saveNew(customer);
		return customer;
	}
}
