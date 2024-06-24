package com.agence.shop.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agence.shop.dto.CategoryDto;
import com.agence.shop.services.CategoryService; 

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
//	FIND ALL
	@GetMapping
	public ResponseEntity<List<CategoryDto>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
//	FIND BY ID
	@GetMapping(value="/{id}")
	public ResponseEntity<CategoryDto> findId(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
}
