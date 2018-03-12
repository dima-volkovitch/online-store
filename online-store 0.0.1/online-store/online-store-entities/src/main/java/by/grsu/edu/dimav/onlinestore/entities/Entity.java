package by.grsu.edu.dimav.onlinestore.entities;

public abstract class Entity {
	protected static final String DASH = "-";
	
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
