package by.grsu.edu.dimav.onlinestore.dao;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.edu.dimav.onlinestore.entities.Admin;

public class AdminDaoTest {

	private static final String XML_PATH = "..//online-store-data//test//xml";

	private static AdminDao adminDao;

	@BeforeClass
	public static void crateDao() {
		adminDao = new AdminDao(XML_PATH);
	}

////	@Test
//	public void testSaveNew() {
//		System.out.println("Start 'save' test for admin");
//		Admin admin = saveAdmin();
//		Assert.assertNotNull(adminDao.get(admin.getId()));
////	}
//
//	@Test
//	public void testUpdate() {
//		System.out.println("Start 'update' test for admin");
//		Admin admin = saveAdmin();
//		admin.setContactNumber("00000000000");
//		adminDao.update(admin);
//		Assert.assertEquals(admin, adminDao.get(admin.getId()));
//	}
//
//	@Test
//	public void testGetAll() {
//		System.out.println("Start 'getAll' test for admin");
//		final int initialRowsCount = adminDao.getAll().size();
//		saveAdmin();
//		Assert.assertEquals(adminDao.getAll().size(), initialRowsCount + 1);
//	}

	@Test
	public void testDelete() {
		System.out.println("Start 'delete' test for admin");
		Admin admin = saveAdmin();
		//System.out.println(admin.getId());
		adminDao.delete(14);
		
		Assert.assertNull(adminDao.get(14));
	}

	private static Admin saveAdmin() {
		Admin admin = new Admin();
	//	admin.setId(1);
		admin.setLogin("delete");
		admin.setContactNumber("37529684615");
		admin.setEmail("emailtest");
		adminDao.saveNew(admin);
		System.out.println(admin.getId());
		return admin;
	}
}
