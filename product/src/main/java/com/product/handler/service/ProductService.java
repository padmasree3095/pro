package com.product.handler.service;

import java.util.List;

import com.product.handler.model.ProductModel;
import com.product.handler.model.RequestProductModel;

public interface ProductService {

	List<ProductModel> getActiveProductList();

	void saveProductData(RequestProductModel product);
	
	void deleteProductData(Long id);
	
	ProductModel updateProductData(Long Id,RequestProductModel productModel);

}
