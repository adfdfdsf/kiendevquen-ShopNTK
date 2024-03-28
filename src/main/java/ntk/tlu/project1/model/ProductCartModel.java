package ntk.tlu.project1.model;

public class ProductCartModel {
	private Long id;
	private UserModel userEntity;
	private ProductModel productEntity;
	private String quanlitys;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserModel getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserModel userEntity) {
		this.userEntity = userEntity;
	}
	public ProductModel getProductEntity() {
		return productEntity;
	}
	public void setProductEntity(ProductModel productEntity) {
		this.productEntity = productEntity;
	}
	public String getQuanlitys() {
		return quanlitys;
	}
	public void setQuanlitys(String quanlitys) {
		this.quanlitys = quanlitys;
	}
	
}
