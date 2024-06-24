package com.agence.shop.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agence.shop.dto.UserDto;
import com.agence.shop.dto.UserInsertDto;
import com.agence.shop.dto.UserUpdateDto;
import com.agence.shop.entities.User;
import com.agence.shop.repositories.UserRepository;
import com.agence.shop.services.exceptions.EntityNotFoundException;
import com.agence.shop.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository repo;

//											FIND ALL
	/**
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<UserDto> findAll(){
		return repo.findAll().stream().map(entity -> new UserDto(entity)).collect(Collectors.toList());
	}

//											FIND BY ID
	/**
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public UserDto findById(Long id) {
		return new UserDto(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not exists!")));
	}
	
//											FIND BY EMAIL
	/**
	 * @param email
	 * @return
	 */
	@Transactional(readOnly = true)
	public UserDto findByEmail(String email) {
		return new UserDto(repo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User does not exists with this e-mail address!")));
	}

//											INSERT
	/**
	 * @param userInsertDtoo
	 * @return
	 */
	@Transactional
	public UserDto insert(UserInsertDto userInsertDtoo) {
		User user = new User(userInsertDtoo);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = repo.save(user);
		return new UserDto(user);
	}
	
//											UPDATE
	/**
	 * @param userDto 
	 * @param userId
	 * @return UserDto
	 */
	@Transactional
	public UserDto update(UserUpdateDto userDto, Long userId) {
		try {
			User user = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
			user.setEmail(userDto.getEmail());
			user.setName(userDto.getName());
			user = repo.save(user);
			return new UserDto(user);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("User not found");
		}
	}
	
//											DELETE
	/**
	 * Delete user giving its id
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		try {
			repo.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("User not found");
		}
	}

	
	/**
	 * Implementing user details service
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = repo.findByEmail(username);
		if (!optionalUser.isPresent()) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("User not found");
		}
		logger.info("User found: " + username);
		return optionalUser.get();
	}
	
}
