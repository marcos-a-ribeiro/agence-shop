package com.agence.shop.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agence.shop.dto.CategoryDto;
import com.agence.shop.repositories.CategoryRepository;
import com.agence.shop.services.exceptions.EntityNotFoundException;


@Service
public class CategoryService {

	@Autowired
	CategoryRepository repo;
//	FIND ALL
	@Transactional(readOnly = true)
	public List<CategoryDto> findAll(){
		return repo.findAll().stream().map(entity -> new CategoryDto(entity)).collect(Collectors.toList());
	}

//	FIND BY ID
	@Transactional(readOnly = true)
	public CategoryDto findById(Long id) {
		return new CategoryDto(repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Category does not exists!")));
	}
	
}
