package by.grsu.edu.store.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {
	private static final long serialVersionUID = -369589133946095170L;

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + id;
	}
}
