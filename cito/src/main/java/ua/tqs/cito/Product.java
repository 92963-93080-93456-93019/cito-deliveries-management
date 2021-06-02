package ua.tqs.cito;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "product_table")
public class Product {
	
	public Product() {
		
	}
	
	public Product(String name, String category, String description, Long appId, Double price, String image) {
		super();
		this.name = name;
		this.category = category;
		this.description = description;
		this.setAppId(appId);
		this.price = price;
		this.image = image;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	private String name;
	private Long appId;
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

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", appId=" + appId + ", category=" + category + ", description="
				+ description + ", price=" + price + ", image=" + image + "]";
	}
}
