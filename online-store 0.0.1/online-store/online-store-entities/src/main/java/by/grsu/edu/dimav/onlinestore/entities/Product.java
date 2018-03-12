package by.grsu.edu.dimav.onlinestore.entities;

public class Product extends Entity {
	private String productName;
	
	private String description;
	
	private Integer quantityInStock;
	
	public Product() {
		
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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
		return (this.productName == ((Product)o).getProductName());
	}
	
	@Override
	public int hashCode() {
		return this.productName.hashCode();
	}
	
	@Override
	public String toString() {
		return this.productName + quantityInStock;
	}
}
