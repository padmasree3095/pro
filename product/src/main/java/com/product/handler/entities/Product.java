package com.product.handler.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "product")
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	@Column(name="name", nullable = false, length = 60)
	private String name;
	
	@Column(name="price", nullable = false, length = 3)
	private Double price;
	
	@Column(name="status",nullable=false, length = 1)
	private Character status;
	
	@Column(name="createdOn", nullable = false)
	private Timestamp createdOn;
}
