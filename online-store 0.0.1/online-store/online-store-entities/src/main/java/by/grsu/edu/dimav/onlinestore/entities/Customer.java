package by.grsu.edu.dimav.onlinestore.entities;

import java.util.LinkedList;
import java.util.List;

public class Customer extends User {
	private List<Order> orders;
	
	private Customer() {
		this.orders = new LinkedList<Order>();
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order o) {
		orders.add(o);
	}
	
	public boolean removeOrder(Order o) {
		return orders.remove(o);
	}
}
