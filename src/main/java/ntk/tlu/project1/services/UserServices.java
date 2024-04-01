package ntk.tlu.project1.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntk.tlu.project1.entity.ProductEntity;
import ntk.tlu.project1.entity.UserEntity;
import ntk.tlu.project1.model.ProductModel;
import ntk.tlu.project1.model.UserModel;
import ntk.tlu.project1.repository.ProductCartRepo;
import ntk.tlu.project1.repository.ProductRepo;
import ntk.tlu.project1.repository.UserRepo;

@Service
public class UserServices {
	@Autowired
	UserRepo userRepo;
	@Autowired
	ProductRepo productRepo;
	@Autowired
	ProductCartRepo productCartRepo;
	@Autowired
	private ModelMapper modelMapper;
	// Create User
	public void createUser(UserModel userModel) {
		UserEntity userEntity = modelMapper.map(userModel, UserEntity.class);
		userRepo.save(userEntity);
	}

	// CHECK LOGIN
	public boolean checkLogin(String email, String password) {
		if (userRepo.checkLogin(email, password).getEmail().equals(email)
				&& userRepo.checkLogin(email, password).getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	// SHOW ALL USER
	public List<UserModel> showAllUser() {
		List<UserEntity> userEntities = userRepo.findAll();
		List<UserModel> userModels = userEntities.stream().map(entity -> modelMapper.map(entity, UserModel.class))
	            .collect(Collectors.toList());
		return userModels;
	}

	// Search User theo Email
	public UserModel showUser(String email) {
		UserEntity userEntity = userRepo.showUser(email);
		UserModel userModel = modelMapper.map(userEntity, UserModel.class);
		return userModel;
	}

	// search theo id
	public UserModel showUserid(int id) {
		UserEntity userEntity = userRepo.showUserid(id);
		UserModel userModel = modelMapper.map(userEntity, UserModel.class);
		return userModel;
	}

	// create product vào list<products> User them vao wishlist
	public void createProductUser(int idUser, int idProduct) {
		if (!userRepo.showUserid(idUser).getProducts().contains(productRepo.searchById(idProduct))) {
			userRepo.showUserid(idUser).getProducts().add(productRepo.searchById(idProduct));
			userRepo.save(userRepo.showUserid(idUser));
		}
	}

	// remove product tu field wishlist list<product> in User
	public void removeWishList(int idUser, int idProduct) {
		// search product theo id
		ProductEntity product = productRepo.searchById(idProduct);
		// search user theo id
		UserEntity user = userRepo.showUserid(idUser);
		// remove product tu field list<product> in User
		for (int i = 0; i < user.getProducts().size(); i++) {
			if (user.getProducts().get(i).getId() == idProduct) {
				user.getProducts().remove(i);
				userRepo.save(user);
			}
		}
	}
	
	

	
	
	

}
