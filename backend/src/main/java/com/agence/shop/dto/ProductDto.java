package com.agence.shop.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.agence.shop.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	private String description;
	private BigDecimal price;
	private Boolean available;
	
	private Long categoryId;
	private String categoryName;
	
	public ProductDto(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.categoryId = entity.getCategory().getId();
		this.categoryName = entity.getCategory().getName();
	}
}
