package by.grsu.edu.dimav.onlinestore.entities;

import static by.grsu.edu.dimav.onlinestore.utils.StringWorker.uniteStrings;

public abstract class User extends Entity{
	private Integer contactNumber;
	
	private String email;
	
	private String login;
	
	private String password;
	
	private String name;
	
	private String secondName;
	
	private String lastName;
	
	public User() {
		
	}

	public Integer getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Integer contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof User)) {
			return false;
		}
		return (this.contactNumber == ((User)o).getContactNumber());
	}
	
	@Override
	public int hashCode() {
		return this.contactNumber.hashCode();
	}
	
	@Override
	public String toString() {
		return uniteStrings(login, DASH, contactNumber.toString());
	}
}
