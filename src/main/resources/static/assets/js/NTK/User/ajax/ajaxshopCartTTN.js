function thanhtoangay(idProduct) {
	var soluong = document.getElementsByClassName("sum_checkout").length;
	var listPriceProduct = [];
	for(var i =0;i<soluong;i++) {
		listPriceProduct.push(document.getElementsByClassName("sum_checkout")[i].textContent);
	}
	var jsonPriceProduct = JSON.stringify(listPriceProduct);
	console.log(jsonPriceProduct);
	$.ajax({
		url: "/User/restcheckout", // Thay đổi "/your-api-endpoint" thành endpoint API thích hợp trên máy chủ của bạn
		type: "GET",
		data: {
			jsonPriceProduct: jsonPriceProduct,
		},
		success: function() {
			// Xử lý phản hồi từ API ở đây
			console.log("thanhcong");
		},
		error: function(xhr, status, error) {
			// Xử lý khi có lỗi xảy ra trong quá trình gọi API
			console.error(error);
		}
	});
}