package ntk.tlu.project1.model;

import java.util.List;

public class BillitemsModel {
	private Long id;
	private BillModel billEntity;
	private ProductModel productEntity;
	private String quantity;
	private String buyPrice;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BillModel getBillEntity() {
		return billEntity;
	}
	public void setBillEntity(BillModel billEntity) {
		this.billEntity = billEntity;
	}
	public ProductModel getProductEntity() {
		return productEntity;
	}
	public void setProductEntity(ProductModel productEntity) {
		this.productEntity = productEntity;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	
}
