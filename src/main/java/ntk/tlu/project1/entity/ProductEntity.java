package ntk.tlu.project1.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Product")
@Data
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String localBuy;
	private String shipping;
	private String brand;
	private String imageUrl;
	private String tinhtrang; // con hang or het hang
	private String price;
	private String priceBegin;
	private int quantitySold;
	private String productType;
	// user and productWishlist
	@JsonBackReference
	@ManyToMany(mappedBy = "products")
	private List<UserEntity> users;
	
	@OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BillitemsEntity> billitemsEntities;
	// user and productCart
	
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getQuantitySold() {
		return quantitySold;
	}

	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setPriceBegin(String priceBegin) {
		this.priceBegin = priceBegin;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalBuy() {
		return localBuy;
	}

	public void setLocalBuy(String localBuy) {
		this.localBuy = localBuy;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTinhtrang() {
		return tinhtrang;
	}

	public void setTinhtrang(String tinhtrang) {
		this.tinhtrang = tinhtrang;
	}

	public String getPriceBegin() {
		return priceBegin;
	}

}
