package com.product.handler.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
public class ProductModel {
	
	private Long id;
	private String name;
	private Double price;
	private Character status;
	private Timestamp createdOn;
}
