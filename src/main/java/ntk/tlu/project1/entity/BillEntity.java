package ntk.tlu.project1.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "Bill")
public class BillEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String buyDate;
	private String address;

	@ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
	
	@OneToMany(mappedBy = "billEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BillitemsEntity> billitemsEntities;
	public Long getId() {
		return id;
	}
	
	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	public List<BillitemsEntity> getBillitemsEntities() {
		return billitemsEntities;
	}
	public void setBillitemsEntities(List<BillitemsEntity> billitemsEntities) {
		this.billitemsEntities = billitemsEntities;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

	
	
}
