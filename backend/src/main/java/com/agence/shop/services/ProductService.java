package com.agence.shop.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agence.shop.dto.ProductDto;
import com.agence.shop.entities.Product;
import com.agence.shop.repositories.ProductRepository;
import com.agence.shop.services.exceptions.DatabaseException;
import com.agence.shop.services.exceptions.EntityNotFoundException;
import com.agence.shop.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repo;

//	FIND ALL
	@Transactional(readOnly = true)
	public List<ProductDto> findAll(){
		return repo.findAll().stream().map(entity -> new ProductDto(entity)).collect(Collectors.toList());
	}

//	FIND BY ID
	@Transactional(readOnly = true)
	public ProductDto findById(Long id) {
		return new ProductDto(repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product does not exists!")));
	}
	
//	SAVE
	@Transactional
	public ProductDto insert(ProductDto productDto) {
		return new ProductDto(repo.save(new Product(productDto)));
	}
	
//	UPDATE
	@Transactional
	public ProductDto update(ProductDto dto, Long id) {
		if (dto.getId() != id)
			throw new ResourceNotFoundException("Product does not match! Id: " + dto.getId());
		
		try {
			Product entity = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product does not exists!"));
			entity.fromDto(dto);
			entity = repo.save(entity);
			return new ProductDto(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Failed to update product for id=" + id);
		}
	}

//	DELETE
	public void delete(Long id) {
		try {
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Failed to delete product for id=" + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");

		}
	}

}
