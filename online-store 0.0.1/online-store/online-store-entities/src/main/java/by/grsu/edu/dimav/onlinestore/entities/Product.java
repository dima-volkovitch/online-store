package by.grsu.edu.dimav.onlinestore.entities;

public class Product extends Entity {
	private String orderName;
	
	private String description;
	
	private Integer quantityInStock;
	
	public Product() {
		
	}
	
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Order)) {
			return false;
		}
		return (this.orderName == ((Order)o).getOrderName());
	}
	
	@Override
	public int hashCode() {
		return this.orderName.hashCode();
	}
	
	@Override
	public String toString() {
		return this.orderName + quantityInStock;
	}
}
