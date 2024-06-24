package com.agence.shop.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agence.shop.dto.ProductDto;
import com.agence.shop.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	@Autowired
	private ProductService service;
	
//	FIND ALL
	@GetMapping
	public ResponseEntity<List<ProductDto>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
//	FIND BY ID
	@GetMapping(value="/{id}")
	public ResponseEntity<ProductDto> findId(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

//	INSERT
	@PostMapping
	public ResponseEntity<ProductDto> insert(@RequestBody ProductDto productDto) {
		productDto = service.insert(productDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productDto.getId()).toUri();
		return ResponseEntity.created(uri).body(productDto);
	}

//	UPDATE
	@PutMapping(value="/{id}")
	public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto, @PathVariable("id") Long id) {
		return ResponseEntity.ok().body(service.update(productDto, id));
	}
	
//	DELETE
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
