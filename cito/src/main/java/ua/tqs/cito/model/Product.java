package ua.tqs.cito.model;

import javax.persistence.*;

import com.sun.istack.NotNull;

import java.util.Objects;

@Entity
@Table(name = "product_table")

public class Product {
	
	public Product() {
		
	}
	
	public Product(String name, String category, String description, App app, Double price, String image) {
		super();
		this.name = name;
		this.category = category;
		this.description = description;
		this.setApp(app);
		this.price = price;
		this.image = image;
	}

	public Product(Long id,String name, String category, String description, App app, Double price, String image) {
		super();
		this.name = name;
		this.category = category;
		this.description = description;
		this.setApp(app);
		this.price = price;
		this.image = image;
		this.id=id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "appId") // An App has many products (foreign key)
	private App app;
	private String category;
	private String description;
	private Double price;
	
	@Lob
	private String image;

	public Long getId() {
		return id;
	}

	public void setId(Long carId) {
		this.id = carId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", app=" + app + ", category=" + category + ", description="
				+ description + ", price=" + price + ", image=" + image + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(app, product.app) && Objects.equals(category, product.category) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(image, product.image);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, app, category, description, price, image);
	}
}
