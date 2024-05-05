package com.product.handler.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.handler.entities.Product;
import com.product.handler.model.ProductModel;
import com.product.handler.model.RequestProductModel;
import com.product.handler.repositories.ProductReposiotry;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductReposiotry productReposiotry;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ProductModel> getActiveProductList() {
		List<ProductModel> products = productReposiotry.findAll().stream().filter(obj -> obj.getStatus().equals('A'))
				.sorted(Comparator.comparing(Product::getCreatedOn).reversed())
				.map(obj -> this.modelMapper.map(obj, ProductModel.class)).collect(Collectors.toList());
		return products;
	}

	@Override
	public void saveProductData(RequestProductModel productModel) {
		char statusval = priceValidator(productModel.getPrice());
		Product product = this.modelMapper.map(productModel, Product.class);
		product.setCreatedOn(Timestamp.from(Instant.now()));
		product.setStatus(statusval);
		productReposiotry.save(product);
	}

	private char priceValidator(Double price) {
		if(price > 5000 && price < 10000) {
			return 'P';
		}
		if(price > 10000) {
			throw new IllegalArgumentException("Price should not exceed $10000");
		}
		return 'A';
	}

	@Override
	public void deleteProductData(Long id) {
		Product product = productReposiotry.getById(id);
		productReposiotry.delete(product);
	}

	@Override
	public ProductModel updateProductData(Long Id, RequestProductModel productModel) {
		Optional<Product> productdata = productReposiotry.findById(Id);
		if(productdata.isPresent()) {
			Product product = productdata.get();
			product.setPrice(productModel.getPrice());
			product.setStatus(pricePercentageCaluclator(productModel.getPrice(),product.getPrice()));
			product.setName(productModel.getName());
			product.setCreatedOn(Timestamp.from(Instant.now()));
			productReposiotry.save(product);
		}
		
		return null;
	}
	
	private char pricePercentageCaluclator(Double currentPrice, Double previousPrice) {
		float percentage=0;
		if(currentPrice > previousPrice) {
			percentage = (float) ((previousPrice/currentPrice)*100);
		}
		if( percentage > 50) {
			return 'P';
		}
		return 'A';
	}
}
