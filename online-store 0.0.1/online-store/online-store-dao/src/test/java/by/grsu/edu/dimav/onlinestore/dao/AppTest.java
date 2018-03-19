package by.grsu.edu.dimav.onlinestore.dao;

import by.grsu.edu.dimav.onlinestore.entities.Admin;

public class AppTest {

	private static final String XML_PATH = "..//online-store-data//test//xml";

	private static AdminDao adminDao = new AdminDao(XML_PATH);

	public static void main(String[] args) {
		adminDao.delete(14);;

	}

}
