package ntk.tlu.project1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ntk.tlu.project1.entity.ProductEntity;
import ntk.tlu.project1.model.ProductModel;
import ntk.tlu.project1.model.UserModel;
import ntk.tlu.project1.services.ProductCartServices;
import ntk.tlu.project1.services.ProductServices;
import ntk.tlu.project1.services.UserServices;

@Controller
@RequestMapping("/User")
public class UserController {

	@Autowired
	ProductServices productServices;
	@Autowired
	UserServices userServices;
	@Autowired
	ProductCartServices productCartServices;

	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		// search san pham ban chay nhat
		List<ProductModel> productModel = productServices.searchProductQuantityBuyBig();
		model.addAttribute("productEntity", productModel);
		// search toan bo san pham
		List<ProductModel> productModel1 = productServices.searchAllProduct();
		model.addAttribute("productEntity2", productModel1);
		// search product type (quan,ao) && fix cung
		List<ProductModel> productModel3s = productServices.searchProductType("Quần");
		List<ProductModel> productModel4s = productServices.searchProductType("Áo");
		for (ProductModel productModel4 : productModel4s) {
			productModel3s.add(productModel4);
		}
		model.addAttribute("productEntities3s", productModel3s);
		// search product type(trang suc)
		List<ProductModel> productModel5s = productServices.searchProductType("Trang Sức");
		model.addAttribute("productEntities5s", productModel5s);

		// search product type ( giày)
		List<ProductModel> productModel6s = productServices.searchProductType("Giày");
		model.addAttribute("productEntities6s", productModel6s);

		// in ra name
		String kien = (String) session.getAttribute("name");
		if(kien == null || kien.isEmpty()) {
			return "redirect:/api/login";
		}
		model.addAttribute("kien", kien);
		return "User/home";
	}

	@GetMapping("/search_product")
	public String searchProduct(@RequestParam(value = "search", defaultValue = "") String search, Model model,
			HttpSession session) {
		model.addAttribute("search", search);
		List<ProductModel> productModel = productServices.searchProduct(search);
		model.addAttribute("productEntity", productModel);
		String kien = (String) session.getAttribute("name");
		if(kien == null && kien.isEmpty()) {
			return "redirect:/api/login";
		}
		model.addAttribute("kien", kien);
		return "User/category";
	}

	@GetMapping("/detail")
	public String detail(@RequestParam("id") int id, Model model, HttpSession session) {
		model.addAttribute("productEntity", productServices.searchId(id));
		String kien = (String) session.getAttribute("name");
		if(kien == null && kien.isEmpty()) {
			return "redirect:/api/login";
		}
		model.addAttribute("kien", kien);
		session.setAttribute("idProduct", id);
		return "User/detail";
	}

	// danh sach yeu thich
	@GetMapping("/wishlist")
	public String wishList(Model model, HttpSession session) {
		String name = (String) session.getAttribute("name");
		Integer idUser = (Integer) session.getAttribute("idUser"); 
		if(name == null && name.isEmpty() || idUser == null) return "redirect:/api/login";
		model.addAttribute("name", name);
		model.addAttribute("wishList", userServices.showUserid(idUser).getProducts());
		return "/User/my-wishlist";
	}

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	// create product vao danh sach yeu thich
	@GetMapping("/createWishlist")
	public String createWishList(HttpSession session, @RequestParam("id") int idProduct, HttpServletRequest request) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		if(idUser == null) return "redirect:/api/login";
		userServices.createProductUser(idUser, idProduct);
		return "redirect:" + request.getHeader("Referer");
	}

	// remove product khoi danh sach yeu thich
	@GetMapping("/removeWishlist")
	public String removeWishlist(HttpSession session, @RequestParam("id") int id) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		if(idUser == null) return "redirect:/api/login";
		userServices.removeWishList(idUser, id);
		return "redirect:/User/wishlist";
	}
	
	//Dieu Khoan va Dieu Kien
	@GetMapping("/dieukhoanvadichvu")
	public String dieukhoanvadk(HttpSession session,Model model) {
		String name = (String) session.getAttribute("name");
		if(name == null && name.isEmpty()) return "redirect:/api/login";
		model.addAttribute("name",name);
		return "User/dieukhoanvadieukien";
	}
	
	//cau hoi thuong gap
	@GetMapping("/cauhoithuonggap")
	public String cauhoithuonggap(HttpSession session,Model model) {
		String name = (String) session.getAttribute("name");
		if(name == null ||  name.isEmpty()) return "redirect:/api/login";
		model.addAttribute("name",name);
		return "User/faq";
	}

	// Account User
	@GetMapping("/account")
	public String accountUser(Model model, HttpSession session) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		String name = (String) session.getAttribute("name");
		if(name == null || idUser == null) return "redirect:/api/login";
		model.addAttribute("userModel", userServices.showUserid(idUser));
		model.addAttribute("name", name);
		return "User/account-user";
	}

//	@GetMapping("/edit_account")
//	public String editAccount() {
//		return "/User/editinfomationuser";
//	}

	// show shopping cart
	@GetMapping("/cart")
	public String shoppingCart(Model model, HttpSession session) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		if(idUser == null) return "redirect:/api/login";
		model.addAttribute("name", userServices.showUserid(idUser).getName());
		model.addAttribute("listProducts", productCartServices.showlistPinPC(idUser));
		model.addAttribute("listQuantitis", productCartServices.showlistQinPC(idUser)); // 2 4 6
		return "User/shopping-cart";
	}

	// create product vao gio hang
	@GetMapping("/createCart")
	public String createProductCart(Model model, HttpServletRequest request, @RequestParam("id") int id,
			@RequestParam(value = "soluong", defaultValue = "1") String soluong, HttpSession session) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		if(idUser == null) return "redirect:/api/login";
		productCartServices.createProductCart(idUser, id, soluong);
		return "redirect:" + request.getHeader("Referer");
	}

	// remove product khoi Cart
	@GetMapping("/removeProductCart")
	public String removeProductCart(HttpServletRequest request, @RequestParam("idProduct") int idProduct,
			HttpSession session) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		if(idUser == null) return "redirect:/api/login";
		productCartServices.removeProductCart(idUser, idProduct);
		return "redirect:" + request.getHeader("Referer");
	}



	// checkout
	@GetMapping("/checkout")
	public String checkout(HttpSession session,Model model) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		if(idUser == null) return "redirect:/api/login";
		model.addAttribute("name", userServices.showUserid(idUser).getName());
		UserModel userModel = userServices.showUserid(idUser);
		model.addAttribute("userModel",userModel);
		model.addAttribute("listProducts", productCartServices.showlistPinPC(idUser));
		model.addAttribute("listQuantitis", productCartServices.showlistQinPC(idUser)); // 2 4 6
		String[] priceProduct =  (String[]) session.getAttribute("priceProduct");
		model.addAttribute("priceProduct",priceProduct);
		return "/User/checkout";
	}
	
	@GetMapping("/muangayrender")
	public String muangayrender(HttpSession session,Model model) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		if(idUser == null) return "redirect:/api/login";
		model.addAttribute("name", userServices.showUserid(idUser).getName());
		UserModel userModel = userServices.showUserid(idUser);
		model.addAttribute("userModel",userModel);
		int idProduct = (Integer) session.getAttribute("idProduct");
		ProductModel productModel = productServices.searchId(idProduct);
		model.addAttribute("productModel",productModel);
		String soluong = (String) session.getAttribute("soluong");
		if(soluong == null) return "redirect:/api/login";
		model.addAttribute("soluong",soluong);
		return "/User/muangay";
	}
	
}
