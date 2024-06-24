package com.agence.shop.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import com.agence.shop.dto.UserDto;
import com.agence.shop.dto.UserInsertDto;
import com.agence.shop.dto.UserUpdateDto;
import com.agence.shop.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	@Autowired
	private UserService service;
	
//	FIND ALL
	@GetMapping
	public ResponseEntity<List<UserDto>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
//	FIND BY ID
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDto> findId(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

//	INSERT
	@PostMapping
	public ResponseEntity<UserDto> insert(@Valid @RequestBody UserInsertDto userInsertDto) {
		UserDto userDto = service.insert(userInsertDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDto.getId()).toUri();
		return ResponseEntity.created(uri).body(userDto);
	}

//	UPDATE
	@PutMapping(value="/{id}")
	public ResponseEntity<UserDto> update(@RequestBody UserUpdateDto userUpdateDto, @PathVariable("id") Long id) {
		return ResponseEntity.ok().body(service.update(userUpdateDto, id));
	}
	
//	DELETE
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
