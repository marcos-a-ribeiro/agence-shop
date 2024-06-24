package com.agence.shop.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.agence.shop.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	private BigDecimal price;
	private Boolean available;
	
	@ManyToOne(optional = false,  fetch = FetchType.LAZY)
	private Category category;
	
	public Product(ProductDto productDto) {
		this.name = productDto.getName();
		this.description = productDto.getDescription();
		this.price = productDto.getPrice();
		this.available = productDto.getAvailable();
		this.category = new Category(productDto.getCategoryId());
	}

	public void fromDto(ProductDto productDto) {
		this.name = productDto.getName();
		this.description = productDto.getDescription();
		this.price = productDto.getPrice();
		this.available = productDto.getAvailable();
	}
	
}
