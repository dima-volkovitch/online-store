package by.grsu.edu.store.entity;

public class Product extends Entity {
	private static final long serialVersionUID = -8998167664000156996L;

	private String name;

	private String price;

	private Integer count;

	public Product() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Product)) {
			return false;
		}
		Product product = (Product) obj;
		return (id.equals(product.id));
	}
}
