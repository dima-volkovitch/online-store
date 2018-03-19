package by.grsu.edu.dimav.onlinestore.entities;

public class Product extends Entity {
	private String productName;
	
	private String description;
	
	private Integer quantityInStock;
	
	private Integer productPrice;
	
	public Product() {
		
	}
	
	public Product(Product product) {
		super(product);
		this.productName = product.productName;
		this.description = product.description;
		this.quantityInStock = product.quantityInStock;
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

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
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
