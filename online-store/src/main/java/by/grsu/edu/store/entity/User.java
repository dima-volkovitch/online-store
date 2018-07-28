package by.grsu.edu.store.entity;

import java.util.List;

public class User extends Entity {
	private static final long serialVersionUID = -2786908569288569122L;

	private String number;

	private String password;

	private UserStatus userStatus;

	private List<Order> orders;

	public User() {

	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}
		User user = (User) obj;
		return (id.equals(user.id) && number.equals(user.number) && password.equals(user.password));
	}
}
