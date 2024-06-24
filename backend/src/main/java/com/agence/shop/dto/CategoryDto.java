package com.agence.shop.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.agence.shop.entities.Category;
import com.agence.shop.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	private String description;
	
	private List<ProductDto> products;

	public CategoryDto(Category entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.products = entity.getProducts().stream().map(product -> new ProductDto(product)).collect(Collectors.toList());
	}

}
