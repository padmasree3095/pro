package com.product.handler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.handler.model.ProductModel;
import com.product.handler.model.RequestProductModel;
import com.product.handler.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<ProductModel> getProducts(){
		List<ProductModel> list = productService.getActiveProductList();
		return list;
	}
	
	@PostMapping("/addproduct")
	public ResponseEntity<String> saveProduct(@RequestBody RequestProductModel productModel){
		productService.saveProductData(productModel);
		return new ResponseEntity<String>("created", HttpStatus.ACCEPTED);
	}
	
	

}
