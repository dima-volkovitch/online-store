package by.grsu.edu.dimav.onlinestore.entities;

import static by.grsu.edu.dimav.onlinestore.utils.StringWorker.uniteStrings;

public class Order extends Entity {
	private Customer customer;

	private Product product;

	private Admin admin;

	public Order() {

	}

	public Order(Order order) {
		super(order);
		this.customer = order.customer;
		this.product = order.product;
		this.admin = order.admin;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Order)) {
			return false;
		}
		Order order = (Order) o;
		return (this.customer == order.getCustomer() && this.product == order.getProduct());
	}

	@Override
	public int hashCode() {
		Integer customerHashCode = this.customer.hashCode();
		Integer productHashCode = this.product.hashCode();
		return uniteStrings(customerHashCode, productHashCode).hashCode();
	}

	@Override
	public String toString() {
		return uniteStrings(customer, DASH, product);
	}
}
