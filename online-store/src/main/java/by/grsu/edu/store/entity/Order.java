package by.grsu.edu.store.entity;

import java.util.Date;

public class Order extends Entity {
	private static final long serialVersionUID = -4156859834038874012L;

	private Date date;

	private User user;

	private Product product;

	public Order() {

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Order)) {
			return false;
		}
		Order order = (Order) obj;
		return (id.equals(order.id));
	}
}
