package ntk.tlu.project1.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ntk.tlu.project1.model.UserModel;
import ntk.tlu.project1.services.UserServices;

@Controller
@RequestMapping("/Admin")
public class AdminController {
	Logger logger 
    = Logger.getLogger( 
    		AdminController.class.getName()); 
	@Autowired
	UserServices userServices;
	@GetMapping("/dashboard")
	public String dashboard() {
		return "Admin/dashboard";
	}
	
	@GetMapping("/client")
	public String qlClient(Model model) {
		List<UserModel> userModels = userServices.showAllUser();
		model.addAttribute("listUserModel",userModels);
		return "Admin/quanlikhachhang";
	}
	
	@GetMapping("/product")
	public String qlProduct() {
		return "Admin/quanlisanpham";
	}
}
