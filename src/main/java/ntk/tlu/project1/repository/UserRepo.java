package ntk.tlu.project1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ntk.tlu.project1.entity.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	// Select email , password
	@Query("SELECT u From UserEntity u WHERE u.email LIKE :email AND u.password LIKE :password")
	UserEntity checkLogin(@Param("email") String email,@Param("password") String password);
	
	// Search User theo email
	@Query("SELECT u From UserEntity u WHERE u.email LIKE:email")
	UserEntity showUser(@Param("email") String email);
	
	//search theo id
	@Query("SELECT u From UserEntity u WHERE u.id = :id")
	UserEntity showUserid(@Param("id") Integer id);
	
}
