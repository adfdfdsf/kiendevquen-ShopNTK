package ntk.tlu.project1.model;

import java.util.Date;
import java.util.List;

import ntk.tlu.project1.entity.BillitemsEntity;
import ntk.tlu.project1.entity.UserEntity;

public class BillModel {
	private Long id;
	private String buyDate;
	private UserModel userEntity;
	private List<BillitemsModel>  billitemsEntities;
	private String address;
	private String tongHoaDon;
	
	public String getTongHoaDon() {
		return tongHoaDon;
	}
	public void setTongHoaDon(String tongHoaDon) {
		this.tongHoaDon = tongHoaDon;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public UserModel getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserModel userEntity) {
		this.userEntity = userEntity;
	}
	public List<BillitemsModel> getBillitemsEntities() {
		return billitemsEntities;
	}
	public void setBillitemsEntities(List<BillitemsModel> billitemsEntities) {
		this.billitemsEntities = billitemsEntities;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
