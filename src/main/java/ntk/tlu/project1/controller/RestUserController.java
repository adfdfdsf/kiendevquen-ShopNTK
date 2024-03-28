package ntk.tlu.project1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;
import ntk.tlu.project1.entity.ProductEntity;
import ntk.tlu.project1.model.ProductModel;
import ntk.tlu.project1.model.UserModel;
import ntk.tlu.project1.services.EmailServices;
import ntk.tlu.project1.services.ProductCartServices;
import ntk.tlu.project1.services.ProductServices;
import ntk.tlu.project1.services.UserServices;

@RestController
@RequestMapping("/User")
public class RestUserController {
	@Autowired
	ProductServices productServices;
	@Autowired
	ProductCartServices productCartServices;
	@Autowired
	UserServices userServices;
	@Autowired
	EmailServices emailServices;

	// search product localbuy
	@PostMapping("/search_localBuyhanoiandthanhhoaandhaiphong")
	public List<ProductModel> searchProductsBuyhanoiandthanhhoaandhaiphong(@RequestParam("search") String search,
			@RequestParam("hanoi") String hanoi, @RequestParam("thanhhoa") String thanhhoa,
			@RequestParam("haiphong") String haiphong) {
		List<ProductModel> listProductModel = productServices.searchLocalBuy(search, hanoi, thanhhoa, haiphong);
		return listProductModel;
	}

	@PostMapping("/searchAll")
	public List<ProductModel> searchAll(@RequestParam("search") String search) {
		List<ProductModel> listProductModel = productServices.searchProduct(search);
		return listProductModel;
	}

	// search product shipping

	@PostMapping("/searchbyShipping")
	public List<ProductModel> searchbyShipping(@RequestParam("search") String search,
			@RequestParam("hoatoc") String hoatoc, @RequestParam("nhanh") String nhanh,
			@RequestParam("tietkiem") String tietkiem) {
		List<ProductModel> listProductModel = productServices.searchShipping(search, hoatoc, nhanh, tietkiem);
		return listProductModel;
	}

	@PostMapping("/searchAllShipping")
	public List<ProductModel> searchAllShipping(@RequestParam("search") String search) {
		List<ProductModel> listProductModel = productServices.searchProduct(search);
		return listProductModel;
	}

	// search Brand

	@PostMapping("/searchbyBrand")
	public List<ProductModel> searchbyBrand(@RequestParam("search") String search,
			@RequestParam("unisex") String unisex, @RequestParam("tlu") String tlu, @RequestParam("yody") String yody) {
		List<ProductModel> listProductModel = productServices.searchBrand(search, unisex, tlu, yody);
		return listProductModel;
	}

	@PostMapping("/searchAllBrand")
	public List<ProductModel> searchAllBrand(@RequestParam("search") String search) {
		List<ProductModel> listProductModel = productServices.searchProduct(search);
		return listProductModel;
	}

	// search Buy localbuy and shipping

	@PostMapping("/searchbyLocalBuyandShipping")
	public List<ProductModel> searchbyLocalBuyandShipping(@RequestParam("search") String search,
			@RequestParam("hanoi") String hanoi, @RequestParam("thanhhoa") String thanhhoa,
			@RequestParam("haiphong") String haiphong, @RequestParam("hoatoc") String hoatoc,
			@RequestParam("nhanh") String nhanh, @RequestParam("tietkiem") String tietkiem) {
		List<ProductModel> listProductModel = productServices.searchLocalBuyandShipping(search, hanoi, thanhhoa,
				haiphong, hoatoc, nhanh, tietkiem);
		return listProductModel;
	}

	// search local buy and brand
	@PostMapping("/searchbyLocalBuyandBrand")
	public List<ProductModel> searchbyLocalBuyandBrand(@RequestParam("search") String search,
			@RequestParam("hanoi") String hanoi, @RequestParam("thanhhoa") String thanhhoa,
			@RequestParam("haiphong") String haiphong, @RequestParam("unisex") String unisex,
			@RequestParam("tlu") String tlu, @RequestParam("yody") String yody) {
		List<ProductModel> listProductModel = productServices.searchLocalBuyandBrand(search, hanoi, thanhhoa, haiphong,
				unisex, tlu, yody);
		return listProductModel;
	}

	// shipping and brand

	@PostMapping("/searchbyShippingandBrand")
	public List<ProductModel> searchbyShippingandBrand(@RequestParam("search") String search,
			@RequestParam("hoatoc") String hoatoc, @RequestParam("nhanh") String nhanh,
			@RequestParam("tietkiem") String tietkiem, @RequestParam("unisex") String unisex,
			@RequestParam("tlu") String tlu, @RequestParam("yody") String yody) {
		List<ProductModel> listProductModel = productServices.searchShippingandBrand(search, hoatoc, nhanh, tietkiem,
				unisex, tlu, yody);
		return listProductModel;
	}

	// LocalBuy and Shipping and Brand

	@PostMapping("/searchbyLocalBuyandShippingandBrand")
	public List<ProductModel> searchbyLocalBuyandShippingandBrand(@RequestParam("search") String search,
			@RequestParam("hanoi") String hanoi, @RequestParam("thanhhoa") String thanhhoa,
			@RequestParam("haiphong") String haiphong, @RequestParam("hoatoc") String hoatoc,
			@RequestParam("nhanh") String nhanh, @RequestParam("tietkiem") String tietkiem,
			@RequestParam("unisex") String unisex, @RequestParam("tlu") String tlu, @RequestParam("yody") String yody) {
		List<ProductModel> listProductModel = productServices.searchLocalBuyandShippingandBrand(search, hanoi, thanhhoa,
				haiphong, hoatoc, nhanh, tietkiem, unisex, tlu, yody);
		return listProductModel;
	}

	// remove checkbox (button : xoatatca)
	@PostMapping("/removeCheckbox")
	public List<ProductModel> removeCheckbox(@RequestParam("search") String search) {
		List<ProductModel> listProductModel = productServices.searchProduct(search);
		return listProductModel;
	}

//	cap nhat gio hang trong Cart
	@GetMapping("/updateProductCart")
	public void updateProductCart(@RequestParam("listSoLuong") String listSoLuong, HttpSession session) {
		int idUser = (Integer) session.getAttribute("idUser");
		List<String> listQuantitis = productCartServices.showlistQinPC(idUser);
		Gson gson = new Gson();
		String[] soLuongs = gson.fromJson(listSoLuong, String[].class);
		for (Integer i = 0; i < listQuantitis.size(); i++) {
			listQuantitis.set(i, soLuongs[i]);
		}
		productCartServices.updateProduct(listQuantitis, idUser);
	}
	// lay du lieu tu ajax muangayDetail : soluong ,idProduct
	@GetMapping("/muangay")
	public void muangay(HttpSession session, @RequestParam("soluong") String soluong,
			@RequestParam("idProduct") int idProduct) {
		session.setAttribute("soluong", soluong);
		session.setAttribute("idProduct", idProduct);
	}

	

	
}
