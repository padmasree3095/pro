package com.product.handler.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(content = Include.NON_NULL)
@Data
public class RequestProductModel {
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value="price")
	private Double price;
}
