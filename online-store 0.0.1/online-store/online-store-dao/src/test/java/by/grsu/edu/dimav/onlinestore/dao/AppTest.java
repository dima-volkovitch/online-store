package by.grsu.edu.dimav.onlinestore.dao;


public class AppTest {

	private static final String XML_PATH = "..//online-store-data//test//xml";

	private static AdminDao adminDao = new AdminDao(XML_PATH);

	public static void main(String[] args) {
		adminDao.delete(14);;

	}

}
